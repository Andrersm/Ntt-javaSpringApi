package org.example.superapiv1.services;

import jakarta.persistence.EntityNotFoundException;
import org.example.superapiv1.DTO.GenreDTO;
import org.example.superapiv1.entities.Genre;
import org.example.superapiv1.exception.EntityDeletionException;
import org.example.superapiv1.exception.UnexpectedIdException;
import org.example.superapiv1.repositories.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GenreService {

    @Autowired
    private GenreRepository genreRepository;

    public List<GenreDTO> findAll() {
        List<Genre> listGenre = genreRepository.findAll();
        return listGenre.stream().map(GenreDTO::new).toList();
    }

    public GenreDTO findById(Long id) {
        Genre genre = genreRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Genero"));
        return new GenreDTO(genre);
    }

    public GenreDTO save(GenreDTO genreDTO) {
        Genre genre;
        if (genreDTO.getId() != null) {
            genre = genreRepository.findById(genreDTO.getId())
                    .orElseThrow(() -> new UnexpectedIdException("Genero"));
        } else {
            genre = new Genre();
        }
        genre.setName(genreDTO.getName());
        genre = genreRepository.save(genre);
        return new GenreDTO(genre);
    }

    public void delete(Long id) {
        if (!genreRepository.existsById(id)) {
            throw new EntityDeletionException("Genero", id);
        }
        genreRepository.deleteById(id);
    }
}
