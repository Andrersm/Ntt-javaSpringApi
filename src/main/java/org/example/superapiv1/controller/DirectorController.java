package org.example.superapiv1.controller;

import jakarta.validation.Valid;
import org.example.superapiv1.DTO.DirectorDTO;
import org.example.superapiv1.services.DirectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/director")
public class DirectorController {

    @Autowired
    private DirectorService directorService;

    @GetMapping("/list")
    public List<DirectorDTO> findAll() {
        return directorService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DirectorDTO> findById(@PathVariable Long id) {
        DirectorDTO directorDTO = directorService.findById(id);
        return ResponseEntity.ok().body(directorDTO);
    }

    @PostMapping
    public ResponseEntity<DirectorDTO> create(@Valid @RequestBody DirectorDTO directorDTO) {
        directorDTO = directorService.save(directorDTO);
        return new ResponseEntity<>(directorDTO, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DirectorDTO> update(@PathVariable Long id, @RequestBody DirectorDTO directorDTO) {
        directorDTO.setId(id);
        directorDTO = directorService.save(directorDTO);
        return ResponseEntity.ok().body(directorDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        directorService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
