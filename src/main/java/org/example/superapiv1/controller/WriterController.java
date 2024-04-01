package org.example.superapiv1.controller;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Escritor", description = "Endpoints para gerenciamento de escritores de filmes")
@RequestMapping("/writer")
public class WriterController {

    private WriterService writerService;

    @GetMapping("/list")
    @Operation(summary = "Lista todos os escritores",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Escritores listados com sucesso",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = WriterDTO.class)))
            })
    public ResponseEntity<List<WriterDTO>> findAll() {
        List<WriterDTO> list = writerService.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Busca um escritor pelo ID",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Escritor encontrado",
                            content = @Content(schema = @Schema(implementation = WriterDTO.class))),
                    @ApiResponse(responseCode = "404", description = "Escritor não encontrado")
            })
    public ResponseEntity<WriterDTO> findById(@PathVariable Long id) {
        WriterDTO dto = writerService.findById(id);
        return ResponseEntity.ok().body(dto);
    }

    @PostMapping("/save/")
    @Operation(summary = "Cria um novo escritor",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Escritor criado com sucesso",
                            content = @Content(schema = @Schema(implementation = WriterDTO.class)))
            })
    public ResponseEntity<WriterDTO> create(@RequestBody WriterDTO writerDTO) {
        WriterDTO newDto = writerService.create(writerDTO);
        return ResponseEntity.ok().body(newDto);
    }

    @PutMapping("/update/{id}")
    @Operation(summary = "Atualiza um escritor existente",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Escritor atualizado com sucesso",
                            content = @Content(schema = @Schema(implementation = WriterDTO.class))),
                    @ApiResponse(responseCode = "404", description = "Escritor não encontrado")
            })
    public ResponseEntity<WriterDTO> update(@PathVariable Long id, @RequestBody WriterDTO writerDTO) {
        WriterDTO updatedDto = writerService.update(id, writerDTO);
        return ResponseEntity.ok().body(updatedDto);
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Exclui um escritor",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Escritor excluído com sucesso"),
                    @ApiResponse(responseCode = "404", description = "Escritor não encontrado")
            })
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        writerService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
