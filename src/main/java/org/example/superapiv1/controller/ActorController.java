package org.example.superapiv1.controller;

import jakarta.validation.Valid;
import org.example.superapiv1.DTO.ActorDTO;
import org.example.superapiv1.services.ActorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/actor")
public class ActorController {

    @Autowired
    private ActorService actorService;

    @GetMapping("/list")
    public List<ActorDTO> findAll(){
        return actorService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ActorDTO> findByid(@PathVariable Long id) {
        ActorDTO actorDTO = actorService.findById(id);
        return ResponseEntity.ok().body(actorDTO);
    }

    @PostMapping("/save/{id}")
    public ResponseEntity<ActorDTO> create(@Valid @RequestBody ActorDTO actorDTO){
        actorDTO = actorService.save(actorDTO);
        return new ResponseEntity<>(actorDTO, HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ActorDTO> update(@PathVariable Long id, @RequestBody ActorDTO actorDTO) {
        actorDTO.setId(id);
        actorDTO = actorService.save(actorDTO);
        return ResponseEntity.ok().body(actorDTO);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        actorService.delete(id);
        return ResponseEntity.noContent().build();

    }

}
