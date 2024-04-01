package org.example.superapiv1.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.example.superapiv1.DTO.DirectorDTO;
import org.example.superapiv1.entities.Director;
import org.example.superapiv1.services.DirectorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@Tag(name = "Diretor", description = "Endpoints para gerenciamento de diretores")
@RequestMapping(value = "/director")
public class DirectorController {

    private DirectorService directorService;

    @GetMapping("/list")
    @Operation(summary = "Lista todos os diretores",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Diretores listados com sucesso",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = DirectorDTO.class)))
            })
    public ResponseEntity<List<DirectorDTO>> findAll() {
        List<DirectorDTO> directors = directorService.findAll();
        return ResponseEntity.ok().body(directors);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Busca diretor pelo ID",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Diretor encontrado",
                            content = @Content(schema = @Schema(implementation = DirectorDTO.class))),
                    @ApiResponse(responseCode = "404", description = "Diretor não encontrado")
            })
    public ResponseEntity<Director> findById(@PathVariable Long id) {
        Director director = directorService.findById(id);
        return ResponseEntity.ok().body(director);
    }

    @PostMapping("/save/")
    @Operation(summary = "Cria um novo diretor",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Diretor criado com sucesso",
                            content = @Content(schema = @Schema(implementation = DirectorDTO.class)))
            })
    public ResponseEntity<DirectorDTO> create(@RequestBody DirectorDTO directorDTO) {
        DirectorDTO createdDirector = directorService.create(directorDTO);
        return ResponseEntity.ok(createdDirector);
    }

    @PutMapping("/update/{id}")
    @Operation(summary = "Atualiza um diretor existente",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Diretor atualizado com sucesso",
                            content = @Content(schema = @Schema(implementation = DirectorDTO.class))),
                    @ApiResponse(responseCode = "404", description = "Diretor não encontrado")
            })
    public ResponseEntity<DirectorDTO> update(@PathVariable Long id, @RequestBody DirectorDTO directorDTO) {
        DirectorDTO updatedDirector = directorService.update(id, directorDTO);
        return ResponseEntity.ok(updatedDirector);
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Exclui um diretor",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Diretor excluído com sucesso"),
                    @ApiResponse(responseCode = "404", description = "Diretor não encontrado")
            })
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        directorService.delete(id);
        return ResponseEntity.ok().build();
    }
}
