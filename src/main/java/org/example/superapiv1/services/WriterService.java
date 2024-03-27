package org.example.superapiv1.services;

import jakarta.persistence.EntityNotFoundException;
import org.example.superapiv1.DTO.WriterDTO;
import org.example.superapiv1.entities.Writer;
import org.example.superapiv1.exception.EntityDeletionException;
import org.example.superapiv1.exception.UnexpectedIdException;
import org.example.superapiv1.repositories.WriterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class WriterService {

    @Autowired
    private WriterRepository writerRepository;

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

    public WriterDTO save(WriterDTO writerDTO) {
        Writer writer;
        if (writerDTO.getId() != null) {
            writer = writerRepository.findById(writerDTO.getId())
                    .orElseThrow(() -> new UnexpectedIdException("Escritor"));
        } else {
            writer = new Writer();
        }
        writer.setName(writerDTO.getName());
        writer = writerRepository.save(writer);
        return new WriterDTO(writer);
    }

    public void delete(Long id) {
        if (!writerRepository.existsById(id)) {
            throw new EntityDeletionException("Escritor", id);
        }
        writerRepository.deleteById(id);
    }
}
