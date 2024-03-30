package org.example.superapiv1.services;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.example.superapiv1.DTO.MovieDTO;
import org.springframework.transaction.annotation.Transactional;
import org.example.superapiv1.DTO.ActorDTO;
import org.example.superapiv1.entities.Actor;
import org.example.superapiv1.entities.Movie;
import org.example.superapiv1.exception.EntityDeletionException;
import org.example.superapiv1.exception.UnexpectedIdException;
import org.example.superapiv1.repositories.ActorRepository;
import org.example.superapiv1.repositories.MovieRepository;
import org.springframework.stereotype.Service;
import java.util.*;

@AllArgsConstructor
@Service
public class ActorService {
    private ActorRepository actorRepository ;
    private MovieRepository movieRepository;

    @Transactional(readOnly = true)
    public List<ActorDTO> findAll() {
        List<Actor> listActor = actorRepository.findAll();
        return listActor.stream().map(actor -> {
            ActorDTO dto = new ActorDTO();
            dto.setId(actor.getId());
            dto.setName(actor.getName());
            dto.setFavoriteFood(actor.getFavoriteFood());
            dto.setAge(actor.getAge());
            dto.setMovieIds(actor.getActedMovies().stream().map(Movie::getId).toList());
            dto.setMovies(actor.getActedMovies().stream().map(MovieDTO::new).toList());
            return dto;
        }).toList();
    }

    @Transactional
    public ActorDTO findById(Long id) {
        Actor actor = actorRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Ator"));
        return new ActorDTO(actor);
    }

    @Transactional
    public ActorDTO save(ActorDTO actorDTO){
        Actor actor = new Actor();
        if (actorDTO.getId() != null){
            actor = actorRepository.findById(actorDTO.getId())
                    .orElseThrow(() -> new UnexpectedIdException("Ator"));
        }
        actor.setName(actorDTO.getName());
        actor.setFavoriteFood(actorDTO.getFavoriteFood());
        actor.setAge(actorDTO.getAge());

        if (actorDTO.getMovieIds() != null && !actorDTO.getMovieIds().isEmpty()) {
            List<Movie> movies = movieRepository.findAllById(actorDTO.getMovieIds());
            actor.getActedMovies().clear();
            for (Movie movie : movies) {
                actor.getActedMovies().add(movie);
                movie.getActors().add(actor);
            }
        } else {
            actor.getActedMovies().clear();
        }

        actor = actorRepository.save(actor);

        return new ActorDTO(actor);
    }

    @Transactional
    public ActorDTO update(ActorDTO actorDTO) {
        if (actorDTO.getName().isBlank()) {
            throw new IllegalArgumentException("O nome do ator é obrigatório.");
        }
        Actor actor = actorDTO.getId() != null ? actorRepository.findById(actorDTO.getId())
                .orElseThrow(() -> new UnexpectedIdException("Ator não encontrado para o ID fornecido.")) : new Actor();

        updateActorProperties(actor, actorDTO);
        updateActedMovies(actor, actorDTO.getMovieIds());
        actor = actorRepository.save(actor);

        return new ActorDTO(actor);
    }

    public void delete(Long id) {

        if (!actorRepository.existsById(id)) {
            throw new EntityDeletionException("Ator", id);
        }
        actorRepository.deleteById(id);
    }

    private void updateActorProperties(Actor actor, ActorDTO actorDTO) {
        actor.setName(actorDTO.getName());
        actor.setFavoriteFood(actorDTO.getFavoriteFood());
        actor.setAge(actorDTO.getAge());
    }

    private void updateActedMovies(Actor actor, List<Long> movieIds) {
        if (movieIds != null && !movieIds.isEmpty()) {
            List<Movie> movies = movieRepository.findAllById(movieIds);

            actor.getActedMovies().forEach(movie -> movie.getActors().remove(actor));
            actor.getActedMovies().clear();

            movies.forEach(movie -> {
                actor.getActedMovies().add(movie);
                movie.getActors().add(actor);
            });
        } else {
            actor.getActedMovies().forEach(movie -> movie.getActors().remove(actor));
            actor.getActedMovies().clear();
        }
    }



}
