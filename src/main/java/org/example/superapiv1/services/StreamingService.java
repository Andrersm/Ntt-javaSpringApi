package org.example.superapiv1.services;

import jakarta.persistence.EntityNotFoundException;
import org.example.superapiv1.DTO.StreamingDTO;
import org.example.superapiv1.entities.Streaming;
import org.example.superapiv1.exception.EntityDeletionException;
import org.example.superapiv1.exception.UnexpectedIdException;
import org.example.superapiv1.repositories.StreamingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StreamingService {

    @Autowired
    private StreamingRepository streamingRepository;

    public List<StreamingDTO> findAll() {
        List<Streaming> listStreaming = streamingRepository.findAll();
        return listStreaming.stream().map(StreamingDTO::new).toList();
    }

    public StreamingDTO findById(Long id) {
        Streaming streaming = streamingRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Streaming"));
        return new StreamingDTO(streaming);
    }

    public StreamingDTO save(StreamingDTO streamingDTO) {
        Streaming streaming;
        if (streamingDTO.getId() != null) {
            streaming = streamingRepository.findById(streamingDTO.getId())
                    .orElseThrow(() -> new UnexpectedIdException("Streaming"));
        } else {
            streaming = new Streaming();
        }
        streaming.setName(streamingDTO.getName());
        streaming = streamingRepository.save(streaming);
        return new StreamingDTO(streaming);
    }

    public void delete(Long id) {
        if (!streamingRepository.existsById(id)) {
            throw new EntityDeletionException("Ator", id);
        }
        streamingRepository.deleteById(id);
    }
}
