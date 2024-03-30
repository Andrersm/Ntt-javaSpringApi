package org.example.superapiv1.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.example.superapiv1.DTO.ActorDTO;
import org.example.superapiv1.services.ActorService;
import org.example.superapiv1.validation.OnCreate;
import org.example.superapiv1.validation.OnUpdate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@AllArgsConstructor
@RestController
@RequestMapping(value = "/actor")
public class ActorController {

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

    @PostMapping("/save/")
    public ResponseEntity<ActorDTO> create(@Validated(OnCreate.class) @RequestBody ActorDTO actorDTO){
        actorDTO = actorService.save(actorDTO);
        return new ResponseEntity<>(actorDTO, HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ActorDTO> update(@Validated(OnUpdate.class) @PathVariable Long id, @RequestBody ActorDTO actorDTO) {
        actorDTO.setId(id);
        actorDTO = actorService.update(actorDTO);
        return ResponseEntity.ok().body(actorDTO);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        actorService.delete(id);
        return ResponseEntity.noContent().build();

    }

}
