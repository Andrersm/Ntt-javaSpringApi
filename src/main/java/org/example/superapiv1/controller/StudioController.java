package org.example.superapiv1.controller;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Estúdio", description = "Endpoints para gerenciamento de estúdios de filmes")
@RequestMapping("/studio")
public class StudioController {

    private StudioService studioService;

    @GetMapping("/list")
    @Operation(summary = "Lista todos os estúdios",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Estúdios listados com sucesso",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = StudioDTO.class)))
            })
    public ResponseEntity<List<StudioDTO>> findAll() {
        List<StudioDTO> list = studioService.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Busca estúdio pelo ID",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Estúdio encontrado",
                            content = @Content(schema = @Schema(implementation = StudioDTO.class))),
                    @ApiResponse(responseCode = "404", description = "Estúdio não encontrado")
            })
    public ResponseEntity<StudioDTO> findById(@PathVariable Long id) {
        StudioDTO dto = studioService.findById(id);
        return ResponseEntity.ok().body(dto);
    }

    @PostMapping("/save/")
    @Operation(summary = "Cria um novo estúdio",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Estúdio criado com sucesso",
                            content = @Content(schema = @Schema(implementation = StudioDTO.class)))
            })
    public ResponseEntity<StudioDTO> create(@RequestBody StudioDTO studioDTO) {
        StudioDTO newDto = studioService.create(studioDTO);
        return ResponseEntity.ok().body(newDto);
    }

    @PutMapping("/update/{id}")
    @Operation(summary = "Atualiza um estúdio existente",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Estúdio atualizado com sucesso",
                            content = @Content(schema = @Schema(implementation = StudioDTO.class))),
                    @ApiResponse(responseCode = "404", description = "Estúdio não encontrado")
            })
    public ResponseEntity<StudioDTO> update(@PathVariable Long id, @RequestBody StudioDTO studioDTO) {
        StudioDTO updatedDto = studioService.update(id, studioDTO);
        return ResponseEntity.ok().body(updatedDto);
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Exclui um estúdio",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Estúdio excluído com sucesso"),
                    @ApiResponse(responseCode = "404", description = "Estúdio não encontrado")
            })
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        studioService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

