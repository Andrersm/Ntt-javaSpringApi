package org.example.superapiv1.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.example.superapiv1.DTO.FranchiseDTO;
import org.example.superapiv1.entities.Franchise;
import org.example.superapiv1.services.FranchiseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@AllArgsConstructor
@RestController
@Tag(name = "Franquia", description = "Endpoints para gerenciamento de franquias")
@RequestMapping("/franchise")
public class FranchiseController {

    private FranchiseService franchiseService;

    @GetMapping("/list")
    @Operation(summary = "Lista todas as franquias",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Franquias listadas com sucesso",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = FranchiseDTO.class)))
            })
    public ResponseEntity<List<FranchiseDTO>> findAll() {
        List<FranchiseDTO> allFranchises = franchiseService.findAll();
        return ResponseEntity.ok(allFranchises);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Busca franquia pelo ID",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Franquia encontrada",
                            content = @Content(schema = @Schema(implementation = Franchise.class))),
                    @ApiResponse(responseCode = "404", description = "Franquia não encontrada")
            })
    public ResponseEntity<FranchiseDTO> findById(@PathVariable Long id) {
        FranchiseDTO franchiseDTO = franchiseService.findById(id);
        return ResponseEntity.ok(franchiseDTO);
    }

    @PostMapping("/save/")
    @Operation(summary = "Cria uma nova franquia",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Franquia criada com sucesso",
                            content = @Content(schema = @Schema(implementation = FranchiseDTO.class)))
            })
    public ResponseEntity<FranchiseDTO> create(@RequestBody FranchiseDTO franchiseDTO) {
        FranchiseDTO createdFranchise = franchiseService.create(franchiseDTO);
        return ResponseEntity.ok(createdFranchise);
    }

    @PutMapping("/update/{id}")
    @Operation(summary = "Atualiza uma franquia existente",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Franquia atualizada com sucesso",
                            content = @Content(schema = @Schema(implementation = FranchiseDTO.class))),
                    @ApiResponse(responseCode = "404", description = "Franquia não encontrada")
            })
    public ResponseEntity<FranchiseDTO> update(@PathVariable Long id, @RequestBody FranchiseDTO franchiseDTO) {
        FranchiseDTO updatedFranchise = franchiseService.update(id, franchiseDTO);
        return ResponseEntity.ok(updatedFranchise);
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Exclui uma franquia",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Franquia excluída com sucesso"),
                    @ApiResponse(responseCode = "404", description = "Franquia não encontrada")
            })
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        franchiseService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

