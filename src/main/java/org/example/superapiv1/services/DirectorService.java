package org.example.superapiv1.services;

import java.util.ArrayList;
import org.example.superapiv1.DTO.DirectorDTO;
import org.example.superapiv1.DTO.MovieDTO;
import org.example.superapiv1.entities.Director;
import org.example.superapiv1.entities.Movie;
import org.example.superapiv1.exception.EntityDeletionException;
import org.example.superapiv1.exception.UnexpectedIdException;
import org.example.superapiv1.repositories.DirectorRepository;
import org.example.superapiv1.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DirectorService {

    @Autowired
    private DirectorRepository directorRepository;

    @Autowired
    private MovieRepository movieRepository;


    public List<DirectorDTO> findAll() {
        List<Director> listDirector = directorRepository.findAll();
        return listDirector.stream().map(DirectorDTO::new).toList();
    }

    public DirectorDTO findById(Long id) {
        Director director = directorRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Diretor"));
        return new DirectorDTO(director);
    }

    @Transactional
    public DirectorDTO save(DirectorDTO directorDTO) {
        Director director;
        if (directorDTO.getId() != null) {
            director = directorRepository.findById(directorDTO.getId())
                    .orElseThrow(() -> new UnexpectedIdException("Diretor não encontrado com o ID: " + directorDTO.getId()));
        } else {
            director = new Director();
        }
        director.setName(directorDTO.getName());
        director.setFavoriteSport(directorDTO.getFavoriteSport());

        if (director.getDirectedMovies() != null) {
            director.getDirectedMovies().forEach(movie -> movie.setDirector(null));
        }

        if (directorDTO.getMovieIds() != null && !directorDTO.getMovieIds().isEmpty()) {
            List<Movie> movies = movieRepository.findAllById(directorDTO.getMovieIds());
            Director finalDirector = director;
            movies.forEach(movie -> movie.setDirector(finalDirector));
            director.setDirectedMovies(movies);
        } else {
            director.setDirectedMovies(new ArrayList<>());
        }

        director = directorRepository.save(director);

        DirectorDTO savedDirectorDTO = new DirectorDTO(director);
        savedDirectorDTO.setMovieIds(director.getDirectedMovies().stream().map(Movie::getId).collect(Collectors.toList()));
        savedDirectorDTO.setDirectedMovies(director.getDirectedMovies().stream().map(MovieDTO::new).collect(Collectors.toList()));
        return savedDirectorDTO;
    }


    public void delete(Long id) {
        if (!directorRepository.existsById(id)) {
            throw new EntityDeletionException("Diretor", id);
        }
        directorRepository.deleteById(id);
    }
}
