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

    @Transactional
    public List<WriterDTO> findAll() {
        List<Writer> list = writerRepository.findAll();
        return list.stream().map(WriterDTO::new).toList();
    }

    @Transactional
    public WriterDTO findById(Long id) {
        Writer writer = writerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Escritor não encontrado"));
        return new WriterDTO(writer);
    }

    @Transactional
    public WriterDTO create(WriterDTO writerDTO) {
        Writer writer = new Writer();
        writer.setName(writerDTO.getName());
        writer = writerRepository.save(writer);
        return new WriterDTO(writer);
    }
    @Transactional
    public WriterDTO update(Long id, WriterDTO writerDTO) {
        Writer writer = writerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Escritor não encontrado"));
        writer.setName(writerDTO.getName());
        writer = writerRepository.save(writer);
        return new WriterDTO(writer);
    }

    @Transactional
    public void delete(Long id) {
        Writer writer = writerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Escritor não encontrado"));
        writerRepository.delete(writer);
    }
}
