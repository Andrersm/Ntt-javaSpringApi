package org.example.superapiv1.services;

import jakarta.persistence.EntityNotFoundException;
import org.example.superapiv1.DTO.GenreDTO;
import org.example.superapiv1.entities.Genre;
import org.example.superapiv1.repositories.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class GenreService {

    @Autowired
    private GenreRepository genreRepository;


    public List<GenreDTO> findAll() {
        List<Genre> listGenre = genreRepository.findAll();
        return listGenre.stream().map(GenreDTO::new).toList();
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
            throw new EntityNotFoundException("Genero");
        }
        Genre existingGenre = genreRepository.findById(genreDTO.getId())
                .orElseThrow(() -> new EntityNotFoundException("Gênero"));
        existingGenre.setName(genreDTO.getName());
        Genre updatedGenre = genreRepository.save(existingGenre);
        return new GenreDTO(updatedGenre);
    }


    public void delete(Long id) {
        if (!genreRepository.existsById(id)) {
            throw new EntityNotFoundException("Genero");
        }
        genreRepository.deleteById(id);
    }
}
