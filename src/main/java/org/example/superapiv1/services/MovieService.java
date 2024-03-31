package org.example.superapiv1.services;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.transaction.annotation.Transactional;
import org.example.superapiv1.DTO.MovieDTO;
import org.example.superapiv1.entities.Movie;
import org.example.superapiv1.exception.EntityDeletionException;
import org.example.superapiv1.exception.UnexpectedIdException;
import org.example.superapiv1.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    @Transactional
    public List<MovieDTO> findAll(){
        List<Movie> listMovie = movieRepository.findAll();
        return listMovie.stream().map(MovieDTO::new).toList();

    }
    @Transactional
    public MovieDTO findById(Long id) {
        Movie movie = movieRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Filme"));
        return new MovieDTO(movie);
    }
    @Transactional
    public MovieDTO save(MovieDTO movieDTO){
        Movie movie = new Movie();
        if (movieDTO.getId() != null){
            movie = movieRepository.findById(movieDTO.getId()).orElseThrow(()-> new UnexpectedIdException("Filme"));
        }
        movie.setTitle(movieDTO.getTitle());
        movie.setDuration(movieDTO.getDuration());
        movie.setShortDescription(movieDTO.getShortDescription());
        movie = movieRepository.save(movie);
        return new MovieDTO(movie);

    }

    public void delete(Long id) {

        if (!movieRepository.existsById(id)) {
            throw new EntityDeletionException("Filme", id);
        }
        movieRepository.deleteById(id);
    }

}
