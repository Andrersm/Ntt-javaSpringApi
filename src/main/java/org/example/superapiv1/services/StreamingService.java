package org.example.superapiv1.services;

import jakarta.persistence.EntityNotFoundException;
import org.example.superapiv1.DTO.StreamingDTO;
import org.example.superapiv1.entities.Streaming;
import org.example.superapiv1.repositories.StreamingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class StreamingService {

    @Autowired
    private StreamingRepository streamingRepository;


    @Transactional(readOnly = true)
    public List<StreamingDTO> findAll() {
        List<Streaming> list = streamingRepository.findAll();
        return list.stream().map(StreamingDTO::new).toList();
    }

    @Transactional(readOnly = true)
    public StreamingDTO findById(Long id) {
        Streaming streaming = streamingRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Streaming"));
        return new StreamingDTO(streaming);
    }

    @Transactional
    public StreamingDTO create(StreamingDTO streamingDTO) {
        Streaming streaming = new Streaming();
        streaming.setName(streamingDTO.getName());
        streaming = streamingRepository.save(streaming);
        return new StreamingDTO(streaming);
    }

    @Transactional
    public StreamingDTO update(Long id, StreamingDTO streamingDTO) {
        Streaming streaming = streamingRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Streaming"));
        streaming.setName(streamingDTO.getName());
        streaming = streamingRepository.save(streaming);
        return new StreamingDTO(streaming);
    }

    @Transactional
    public void delete(Long id) {
        Streaming streaming = streamingRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Streaming"));
        streamingRepository.delete(streaming);
    }
}
