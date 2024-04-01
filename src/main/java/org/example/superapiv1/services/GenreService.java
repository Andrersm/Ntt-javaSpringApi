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
        return listGenre.stream().map(GenreDTO::new).collect(Collectors.toList());
    }

    public Genre findGenreById(Long id) {
        Genre newgenre = genreRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Genero"));
        return new Genre(newgenre);
    }

    @Transactional
    public Genre create(GenreDTO genreDTO) {
        Genre genre = new Genre();
        genre.setName(genreDTO.getName());
        return genreRepository.save(genre);
    }

    @Transactional
    public GenreDTO update(GenreDTO genreDTO) {
        if (genreDTO.getId() == null) {
            throw new EntityNotFoundException("O ID não pode ser nulo para atualização.");
        }
        Genre existingGenre = genreRepository.findById(genreDTO.getId())
                .orElseThrow(() -> new EntityNotFoundException("Gênero não encontrado com ID: " + genreDTO.getId()));
        existingGenre.setName(genreDTO.getName());
        Genre updatedGenre = genreRepository.save(existingGenre);
        return new GenreDTO(updatedGenre);
    }


    public void delete(Long id) {
        if (!genreRepository.existsById(id)) {
            throw new EntityDeletionException("Genero", id);
        }
        genreRepository.deleteById(id);
    }
}
