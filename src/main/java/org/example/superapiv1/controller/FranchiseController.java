package org.example.superapiv1.controller;

import jakarta.validation.Valid;
import org.example.superapiv1.DTO.FranchiseDTO;
import org.example.superapiv1.services.FranchiseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/franchise")
public class FranchiseController {

    @Autowired
    private FranchiseService franchiseService;

    @GetMapping("/list")
    public List<FranchiseDTO> findAll() {
        return franchiseService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<FranchiseDTO> findById(@PathVariable Long id) {
        FranchiseDTO franchiseDTO = franchiseService.findById(id);
        return ResponseEntity.ok().body(franchiseDTO);
    }

    @PostMapping
    public ResponseEntity<FranchiseDTO> create(@Valid  @RequestBody FranchiseDTO franchiseDTO) {
        franchiseDTO = franchiseService.save(franchiseDTO);
        return new ResponseEntity<>(franchiseDTO, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FranchiseDTO> update(@PathVariable Long id, @RequestBody FranchiseDTO franchiseDTO) {
        franchiseDTO.setId(id);
        franchiseDTO = franchiseService.save(franchiseDTO);
        return ResponseEntity.ok().body(franchiseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        franchiseService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

