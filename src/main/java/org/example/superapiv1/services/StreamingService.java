package org.example.superapiv1.services;

import jakarta.persistence.EntityNotFoundException;
import org.example.superapiv1.DTO.MovieDTO;
import org.example.superapiv1.DTO.StreamingDTO;
import org.example.superapiv1.entities.Movie;
import org.example.superapiv1.entities.Streaming;
import org.example.superapiv1.exception.EntityDeletionException;
import org.example.superapiv1.exception.UnexpectedIdException;
import org.example.superapiv1.repositories.MovieRepository;
import org.example.superapiv1.repositories.StreamingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StreamingService {

    @Autowired
    private StreamingRepository streamingRepository;

    @Autowired
    private MovieRepository movieRepository;

    public List<StreamingDTO> findAll() {
        List<Streaming> listStreaming = streamingRepository.findAll();
        return listStreaming.stream().map(StreamingDTO::new).toList();
    }

    public StreamingDTO findById(Long id) {
        Streaming streaming = streamingRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Streaming"));
        return new StreamingDTO(streaming);
    }

    @Transactional
    public StreamingDTO save(StreamingDTO streamingDTO) {
        Streaming streaming;
        if (streamingDTO.getId() != null) {
            streaming = streamingRepository.findById(streamingDTO.getId())
                    .orElseThrow(() -> new UnexpectedIdException("Streaming não encontrado com o ID: " + streamingDTO.getId()));
        } else {
            streaming = new Streaming();
        }
        streaming.setName(streamingDTO.getName());

        if (streaming.getCatalog() != null) {
            streaming.getCatalog().forEach(movie -> movie.setStreaming(null));
        }

        if (streamingDTO.getMoviesIds() != null && !streamingDTO.getMoviesIds().isEmpty()) {
            List<Movie> movies = movieRepository.findAllById(streamingDTO.getMoviesIds());
            Streaming finalStreaming = streaming;
            movies.forEach(movie -> movie.setStreaming(finalStreaming));
            streaming.setCatalog(movies);
        } else {
            streaming.setCatalog(new ArrayList<>());
        }

        streaming = streamingRepository.save(streaming);


        StreamingDTO savedStreamingDTO = new StreamingDTO(streaming);
        savedStreamingDTO.setMoviesIds(streaming.getCatalog().stream().map(Movie::getId).collect(Collectors.toList()));
        savedStreamingDTO.setCatolog(streaming.getCatalog().stream().map(MovieDTO::new).collect(Collectors.toList()));
        return savedStreamingDTO;
    }

    public void delete(Long id) {
        if (!streamingRepository.existsById(id)) {
            throw new EntityDeletionException("Ator", id);
        }
        streamingRepository.deleteById(id);
    }
}
