package org.example.superapiv1.controller;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.example.superapiv1.DTO.StudioDTO;
import org.example.superapiv1.services.StudioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/studio")
public class StudioController {

    private StudioService studioService;

    @GetMapping("/list")
    public List<StudioDTO> findAll() {
        return studioService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudioDTO> findById(@PathVariable Long id) {
        StudioDTO studioDTO = studioService.findById(id);
        return ResponseEntity.ok().body(studioDTO);
    }

    @PostMapping("/save/")
    public ResponseEntity<StudioDTO> create(@Valid @RequestBody StudioDTO studioDTO) {
        studioDTO = studioService.save(studioDTO);
        return new ResponseEntity<>(studioDTO, HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<StudioDTO> update(@PathVariable Long id, @RequestBody StudioDTO studioDTO) {
        studioDTO.setId(id);
        studioDTO = studioService.save(studioDTO);
        return ResponseEntity.ok().body(studioDTO);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        studioService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

