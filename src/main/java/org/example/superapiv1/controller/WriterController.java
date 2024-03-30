package org.example.superapiv1.controller;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.example.superapiv1.DTO.WriterDTO;
import org.example.superapiv1.services.WriterService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/writer")
public class WriterController {

    private WriterService writerService;

    @GetMapping("/list")
    public List<WriterDTO> findAll() {
        return writerService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<WriterDTO> findById(@PathVariable Long id) {
        WriterDTO writerDTO = writerService.findById(id);
        return ResponseEntity.ok().body(writerDTO);
    }

    @PostMapping("/save/")
    public ResponseEntity<WriterDTO> create(@Valid @RequestBody WriterDTO writerDTO) {
        writerDTO = writerService.save(writerDTO);
        return new ResponseEntity<>(writerDTO, HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<WriterDTO> update(@PathVariable Long id, @RequestBody WriterDTO writerDTO) {
        writerDTO.setId(id);
        writerDTO = writerService.save(writerDTO);
        return ResponseEntity.ok().body(writerDTO);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        writerService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
