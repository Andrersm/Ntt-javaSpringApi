package org.example.superapiv1.services;

import jakarta.persistence.EntityNotFoundException;
import org.example.superapiv1.DTO.MovieDTO;
import org.example.superapiv1.DTO.WriterDTO;
import org.example.superapiv1.entities.Movie;
import org.example.superapiv1.entities.Writer;
import org.example.superapiv1.exception.EntityDeletionException;
import org.example.superapiv1.exception.UnexpectedIdException;
import org.example.superapiv1.repositories.MovieRepository;
import org.example.superapiv1.repositories.WriterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class WriterService {

    @Autowired
    private WriterRepository writerRepository;

    @Autowired
    private MovieRepository movieRepository;

    public List<WriterDTO> findAll() {
        List<Writer> listWriter = writerRepository.findAll();
        return listWriter.stream()
                .map(WriterDTO::new)
                .collect(Collectors.toList());
    }

    public WriterDTO findById(Long id) {
        Writer writer = writerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Escritor"));
        return new WriterDTO(writer);
    }

    @Transactional
    public WriterDTO save(WriterDTO writerDTO) {
        Writer writer;
        if (writerDTO.getId() != null) {
            writer = writerRepository.findById(writerDTO.getId())
                    .orElseThrow(() -> new UnexpectedIdException("Escritor não encontrado com o ID: " + writerDTO.getId()));
        } else {
            writer = new Writer();
        }
        writer.setName(writerDTO.getName());

        if (writer.getWritedMovies() != null) {
            writer.getWritedMovies().forEach(movie -> movie.setWriters(null));
        }

        if (writerDTO.getMoviesId() != null && !writerDTO.getMoviesId().isEmpty()) {
            List<Movie> movies = movieRepository.findAllById(writerDTO.getMoviesId());
            Writer finalWriter = writer;
            movies.forEach(movie -> {
                if (movie.getWriters() == null) {
                    movie.setWriters(new ArrayList<>());
                }
                movie.getWriters().add(finalWriter);
            });
            writer.setWritedMovies(movies);
        } else {
            writer.setWritedMovies(new ArrayList<>());
        }

        writer = writerRepository.save(writer);

        WriterDTO savedWriterDTO = new WriterDTO(writer);
        savedWriterDTO.setMoviesId(writer.getWritedMovies().stream().map(Movie::getId).collect(Collectors.toList()));
        savedWriterDTO.setWritedMovies(writer.getWritedMovies().stream().map(MovieDTO::new).collect(Collectors.toList()));
        return savedWriterDTO;
    }

    public void delete(Long id) {
        if (!writerRepository.existsById(id)) {
            throw new EntityDeletionException("Escritor", id);
        }
        writerRepository.deleteById(id);
    }
}
