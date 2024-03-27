package org.example.superapiv1.services;
import jakarta.persistence.EntityNotFoundException;
import org.example.superapiv1.DTO.MovieDTO;
import org.example.superapiv1.DTO.StudioDTO;
import org.example.superapiv1.entities.Movie;
import org.example.superapiv1.entities.Studio;
import org.example.superapiv1.exception.EntityDeletionException;
import org.example.superapiv1.exception.UnexpectedIdException;
import org.example.superapiv1.repositories.StudioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class StudioService {

    @Autowired
    private StudioRepository studioRepository;

    public List<StudioDTO> findAll(){
        List<Studio> listStudio = studioRepository.findAll();
        return listStudio.stream().map(StudioDTO::new).toList();
    }

    public StudioDTO findById(Long id){
        Studio studio = studioRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("Estudio"));
        return new StudioDTO(studio);

    }


    public StudioDTO save(StudioDTO studioDTO){
        Studio studio = new Studio();
        if (studioDTO.getId() != null){
            studio = studioRepository.findById(studioDTO.getId()).orElseThrow(()->
                    new UnexpectedIdException("Estudio"));
        }
        studio.setName(studioDTO.getName());
        studio = studioRepository.save(studio);
        return new StudioDTO(studio);
    }
    public void delete(Long id) {

        if (!studioRepository.existsById(id)) {
            throw new EntityDeletionException("Estudio", id);
        }
        studioRepository.deleteById(id);
    }

}

