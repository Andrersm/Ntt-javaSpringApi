package org.example.superapiv1.services;

import jakarta.persistence.EntityNotFoundException;
import org.example.superapiv1.DTO.GenreDTO;
import org.example.superapiv1.DTO.MovieDTO;
import org.example.superapiv1.entities.Genre;
import org.example.superapiv1.entities.Movie;
import org.example.superapiv1.exception.EntityDeletionException;
import org.example.superapiv1.exception.UnexpectedIdException;
import org.example.superapiv1.repositories.GenreRepository;
import org.example.superapiv1.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GenreService {

    @Autowired
    private GenreRepository genreRepository;

    @Autowired
    private MovieRepository movieRepository;

    public List<GenreDTO> findAll() {
        List<Genre> listGenre = genreRepository.findAll();
        return listGenre.stream().map(GenreDTO::new).toList();
    }

    public GenreDTO findById(Long id) {
        Genre genre = genreRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Genero"));
        return new GenreDTO(genre);
    }

    @Transactional
    public GenreDTO save(GenreDTO genreDTO) {
        Genre genre;
        if (genreDTO.getId() != null) {
            genre = genreRepository.findById(genreDTO.getId())
                    .orElseThrow(() -> new UnexpectedIdException("Gênero não encontrado com o ID: " + genreDTO.getId()));
        } else {
            genre = new Genre();
        }
        genre.setName(genreDTO.getName());

        if (genre.getMovies() != null) {
            genre.getMovies().forEach(movie -> movie.setGenre(null));
        }


        if (genreDTO.getMoviesIds() != null && !genreDTO.getMoviesIds().isEmpty()) {
            List<Movie> movies = movieRepository.findAllById(genreDTO.getMoviesIds());
            Genre finalGenre = genre;
            movies.forEach(movie -> movie.setGenre(finalGenre));
            genre.setMovies(movies);
        } else {
            genre.setMovies(new ArrayList<>());
        }

        genre = genreRepository.save(genre);


        GenreDTO savedGenreDTO = new GenreDTO(genre);
        savedGenreDTO.setMoviesIds(genre.getMovies().stream().map(Movie::getId).collect(Collectors.toList()));
        savedGenreDTO.setMovies(genre.getMovies().stream().map(MovieDTO::new).collect(Collectors.toList()));
        return savedGenreDTO;
    }



    public void delete(Long id) {
        if (!genreRepository.existsById(id)) {
            throw new EntityDeletionException("Genero", id);
        }
        genreRepository.deleteById(id);
    }
}
