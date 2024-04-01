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

    @Transactional
    public List<ActorDTO> findAll() {
        List<Actor> list = actorRepository.findAll();
        return list.stream().map(ActorDTO::new).toList();
    }

    @Transactional
    public ActorDTO findById(Long id) {
        Actor actor = actorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Ator não encontrado"));
        return new ActorDTO(actor);
    }

    @Transactional
    public ActorDTO create(ActorDTO actorDTO) {
        Actor actor = new Actor();
        actor.setName(actorDTO.getName());
        actor.setFavoriteFood(actorDTO.getFavoriteFood());
        actor.setAge(actorDTO.getAge());
        actor = actorRepository.save(actor);
        return new ActorDTO(actor);
    }

    @Transactional
    public ActorDTO update(Long id, ActorDTO actorDTO) {
        Actor actor = actorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Ator não encontrado"));
        actor.setName(actorDTO.getName());
        actor.setFavoriteFood(actorDTO.getFavoriteFood());
        actor.setAge(actorDTO.getAge());
        actor = actorRepository.save(actor);
        return new ActorDTO(actor);
    }

    @Transactional
    public void delete(Long id) {
        Actor actor = actorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Ator não encontrado"));
        actorRepository.delete(actor);
    }

}
