package org.example.superapiv1.services;

import jakarta.persistence.EntityNotFoundException;
import org.example.superapiv1.DTO.DirectorDTO;
import org.example.superapiv1.entities.Director;
import org.example.superapiv1.repositories.DirectorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DirectorService {

    @Autowired
    private DirectorRepository directorRepository;



    @Transactional(readOnly = true)
    public List<DirectorDTO> findAll() {
        List<Director> directors = directorRepository.findAll();
        return directors.stream().map(DirectorDTO::new).toList();
    }

    @Transactional(readOnly = true)
    public Director findById(Long id) {
        return directorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Diretor"));
    }

    @Transactional
    public DirectorDTO create(DirectorDTO directorDTO) {
        Director director = new Director();
        director.setName(directorDTO.getName());
        director.setFavoriteSport(directorDTO.getFavoriteSport());
        Director savedDirector = directorRepository.save(director);
        return new DirectorDTO(savedDirector);
    }

    @Transactional
    public DirectorDTO update(Long id, DirectorDTO directorDTO) {
        Director existingDirector = directorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Diretor"));
        existingDirector.setName(directorDTO.getName());
        existingDirector.setFavoriteSport(directorDTO.getFavoriteSport());
        Director updatedDirector = directorRepository.save(existingDirector);
        return new DirectorDTO(updatedDirector);
    }


    @Transactional
    public void delete(Long id) {
        Director director = directorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Diretor"));
        directorRepository.delete(director);
    }
}
