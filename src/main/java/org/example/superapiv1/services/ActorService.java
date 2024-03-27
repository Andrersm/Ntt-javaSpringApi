package org.example.superapiv1.services;

import jakarta.persistence.EntityNotFoundException;
import org.example.superapiv1.DTO.ActorDTO;
import org.example.superapiv1.entities.Actor;
import org.example.superapiv1.exception.EntityDeletionException;
import org.example.superapiv1.exception.UnexpectedIdException;
import org.example.superapiv1.repositories.ActorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActorService {

    @Autowired
    private ActorRepository actorRepository ;

    public List<ActorDTO> findAll(){
        List<Actor> listActor = actorRepository.findAll();
        return listActor.stream().map(ActorDTO::new).toList();

    }

    public ActorDTO findById(Long id) {
        Actor actor = actorRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Ator"));
        return new ActorDTO(actor);
    }

    public ActorDTO save(ActorDTO actorDTO){
        Actor actor = new Actor();
        if (actorDTO.getId() != null){
            actor = actorRepository.findById(actorDTO.getId()).orElseThrow(()-> new UnexpectedIdException("Ator"));
        }
        actor.setName(actorDTO.getName());
        actor.setFavoriteFood(actorDTO.getFavoriteFood());
        actor.setAge(actorDTO.getAge());
        actor = actorRepository.save(actor);
        return new ActorDTO(actor);
    }

    public void delete(Long id) {

        if (!actorRepository.existsById(id)) {
            throw new EntityDeletionException("Ator", id);
        }
        actorRepository.deleteById(id);
    }
}
