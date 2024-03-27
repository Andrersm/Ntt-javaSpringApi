package org.example.superapiv1.controller;

import jakarta.validation.Valid;
import org.example.superapiv1.DTO.StreamingDTO;
import org.example.superapiv1.services.StreamingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/streaming")
public class StreamingController {

    @Autowired
    private StreamingService streamingService;

    @GetMapping
    public List<StreamingDTO> findAll() {
        return streamingService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<StreamingDTO> findById(@PathVariable Long id) {
        StreamingDTO streamingDTO = streamingService.findById(id);
        return ResponseEntity.ok().body(streamingDTO);
    }

    @PostMapping
    public ResponseEntity<StreamingDTO> create(@Valid  @RequestBody StreamingDTO streamingDTO) {
        streamingDTO = streamingService.save(streamingDTO);
        return new ResponseEntity<>(streamingDTO, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StreamingDTO> update(@PathVariable Long id, @RequestBody StreamingDTO streamingDTO) {
        streamingDTO.setId(id);
        streamingDTO = streamingService.save(streamingDTO);
        return ResponseEntity.ok().body(streamingDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        streamingService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

