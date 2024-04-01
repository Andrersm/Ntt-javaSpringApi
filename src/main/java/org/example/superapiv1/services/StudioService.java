package org.example.superapiv1.services;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.example.superapiv1.DTO.StudioDTO;
import org.example.superapiv1.entities.Studio;
import org.example.superapiv1.repositories.StudioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class StudioService {

    @Autowired
    private StudioRepository studioRepository;


    @Transactional
    public List<StudioDTO> findAll() {
        List<Studio> list = studioRepository.findAll();
        return list.stream().map(StudioDTO::new).toList();
    }

    @Transactional
    public StudioDTO findById(Long id) {
        Studio studio = studioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Estúdio"));
        return new StudioDTO(studio);
    }

    @Transactional
    public StudioDTO create(StudioDTO studioDTO) {
        Studio studio = new Studio();
        studio.setName(studioDTO.getName());
        studio = studioRepository.save(studio);
        return new StudioDTO(studio);
    }

    @Transactional
    public StudioDTO update(Long id, StudioDTO studioDTO) {
        Studio studio = studioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Estúdio"));
        studio.setName(studioDTO.getName());
        studio = studioRepository.save(studio);
        return new StudioDTO(studio);
    }

    @Transactional
    public void delete(Long id) {
        Studio studio = studioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Estúdio"));
        studioRepository.delete(studio);
    }

}

