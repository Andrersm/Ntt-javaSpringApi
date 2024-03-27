package org.example.superapiv1.services;

import org.example.superapiv1.DTO.DirectorDTO;
import org.example.superapiv1.entities.Director;
import org.example.superapiv1.exception.EntityDeletionException;
import org.example.superapiv1.exception.UnexpectedIdException;
import org.example.superapiv1.repositories.DirectorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.persistence.EntityNotFoundException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DirectorService {

    @Autowired
    private DirectorRepository directorRepository;

    public List<DirectorDTO> findAll(){
        List<Director> listDirectors = directorRepository.findAll();
        return listDirectors.stream().map(DirectorDTO::new).collect(Collectors.toList());
    }

    public DirectorDTO findById(Long id) {
        Director director = directorRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Diretor"));
        return new DirectorDTO(director);
    }

    public DirectorDTO save(DirectorDTO directorDTO){
        Director director;
        if (directorDTO.getId() != null){
            director = directorRepository.findById(directorDTO.getId()).orElseThrow(() -> new UnexpectedIdException("Diretor"));
        } else {
            director = new Director();
        }
        director.setName(directorDTO.getName());
        director.setFavoriteSport(directorDTO.getFavoriteSport());
        director = directorRepository.save(director);
        return new DirectorDTO(director);
    }

    public void delete(Long id) {
        if (!directorRepository.existsById(id)) {
            throw new EntityDeletionException("Diretor", id);
        }
        directorRepository.deleteById(id);
    }
}
