package org.example.superapiv1.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Streaming", description = "Endpoints para gerenciamento das plataformas de streaming")
@RequestMapping(value = "/streaming")
public class StreamingController {

    private StreamingService streamingService;

    @GetMapping("/list")
    @Operation(summary = "Lista todas as plataformas de streaming",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Plataformas listadas com sucesso",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = StreamingDTO.class)))
            })
    public ResponseEntity<List<StreamingDTO>> findAll() {
        List<StreamingDTO> list = streamingService.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Busca uma plataforma de streaming pelo ID",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Plataforma encontrada",
                            content = @Content(schema = @Schema(implementation = StreamingDTO.class))),
                    @ApiResponse(responseCode = "404", description = "Plataforma não encontrada")
            })
    public ResponseEntity<StreamingDTO> findById(@PathVariable Long id) {
        StreamingDTO dto = streamingService.findById(id);
        return ResponseEntity.ok().body(dto);
    }

    @PostMapping("/save/")
    @Operation(summary = "Cria uma nova plataforma de streaming",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Plataforma criada com sucesso",
                            content = @Content(schema = @Schema(implementation = StreamingDTO.class)))
            })
    public ResponseEntity<StreamingDTO> create(@RequestBody StreamingDTO streamingDTO) {
        StreamingDTO newDto = streamingService.create(streamingDTO);
        return ResponseEntity.ok().body(newDto);
    }

    @PutMapping("/update/{id}")
    @Operation(summary = "Atualiza uma plataforma de streaming existente",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Plataforma atualizada com sucesso",
                            content = @Content(schema = @Schema(implementation = StreamingDTO.class))),
                    @ApiResponse(responseCode = "404", description = "Plataforma não encontrada")
            })
    public ResponseEntity<StreamingDTO> update(@PathVariable Long id, @RequestBody StreamingDTO streamingDTO) {
        StreamingDTO updatedDto = streamingService.update(id, streamingDTO);
        return ResponseEntity.ok().body(updatedDto);
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Exclui uma plataforma de streaming",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Plataforma excluída com sucesso"),
                    @ApiResponse(responseCode = "404", description = "Plataforma não encontrada")
            })
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        streamingService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

