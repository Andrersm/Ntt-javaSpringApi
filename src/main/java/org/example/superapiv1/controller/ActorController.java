package org.example.superapiv1.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.example.superapiv1.DTO.ActorDTO;
import org.example.superapiv1.services.ActorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@AllArgsConstructor
@RestController
@RequestMapping(value = "/actor")
public class ActorController {

    private ActorService actorService;


    @Operation(summary = "Lista todos os atores")
    @GetMapping("/list")
    public ResponseEntity<List<ActorDTO>> findAll() {
        List<ActorDTO> list = actorService.findAll();
        return ResponseEntity.ok().body(list);
    }


    @Operation(summary = "Busca um ator pelo ID",
            responses = {
                    @ApiResponse(description = "Ator encontrado", responseCode = "200",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ActorDTO.class))),
                    @ApiResponse(description = "Ator não encontrado", responseCode = "404")
            })
    @GetMapping("/{id}")
    public ResponseEntity<ActorDTO> findById(@PathVariable Long id) {
        ActorDTO dto = actorService.findById(id);
        return ResponseEntity.ok().body(dto);
    }

    @Operation(summary = "Cria um novo ator")
    @PostMapping("/save/")
    public ResponseEntity<ActorDTO> create(@RequestBody ActorDTO actorDTO) {
        ActorDTO newDto = actorService.create(actorDTO);
        return ResponseEntity.ok().body(newDto);
    }

    @Operation(summary = "Atualiza um ator existente pelo ID")
    @PutMapping("/update/{id}")
    public ResponseEntity<ActorDTO> update(@PathVariable Long id, @RequestBody ActorDTO actorDTO) {
        ActorDTO updatedDto = actorService.update(id, actorDTO);
        return ResponseEntity.ok().body(updatedDto);
    }
    @Operation(summary = "Exclui um ator pelo ID")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        actorService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
