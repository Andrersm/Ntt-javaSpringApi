package org.example.superapiv1.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.example.superapiv1.DTO.StreamingDTO;
import org.example.superapiv1.services.StreamingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/streaming")
public class StreamingController {

    private StreamingService streamingService;

    @GetMapping("/list")
    public List<StreamingDTO> findAll() {
        return streamingService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<StreamingDTO> findById(@PathVariable Long id) {
        StreamingDTO streamingDTO = streamingService.findById(id);
        return ResponseEntity.ok().body(streamingDTO);
    }

    @PostMapping("/save/")
    public ResponseEntity<StreamingDTO> create(@Valid  @RequestBody StreamingDTO streamingDTO) {
        streamingDTO = streamingService.save(streamingDTO);
        return new ResponseEntity<>(streamingDTO, HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<StreamingDTO> update(@PathVariable Long id, @RequestBody StreamingDTO streamingDTO) {
        streamingDTO.setId(id);
        streamingDTO = streamingService.save(streamingDTO);
        return ResponseEntity.ok().body(streamingDTO);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        streamingService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

