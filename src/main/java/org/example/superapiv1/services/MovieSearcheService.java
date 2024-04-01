package org.example.superapiv1.services;

import org.example.superapiv1.DTO.MovieDTO;
import org.example.superapiv1.entities.Movie;
import org.example.superapiv1.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class MovieSearcheService {

    @Autowired
    private MovieRepository movieRepository;

    public List<MovieDTO> searchMoviesByTitle(String title) {
        List<Movie> movies = movieRepository.findByTitleContainingIgnoreCase(title);
        return movies.stream()
                .map(this::convertToMovieDTO)
                .toList();
    }

    private MovieDTO convertToMovieDTO(Movie movie) {
        MovieDTO movieDTO = new MovieDTO();
        movieDTO.setId(movie.getId());
        movieDTO.setTitle(movie.getTitle());
        return movieDTO;
    }
}
