package org.example.superapiv1.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.example.superapiv1.DTO.GenreDTO;
import org.example.superapiv1.entities.Genre;
import org.example.superapiv1.services.GenreService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@AllArgsConstructor
@RestController
@Tag(name = "Gênero", description = "Endpoints para gerenciamento de gêneros de filmes")
@RequestMapping(value = "/genre")
public class GenreController {

    private GenreService genreService;

    @GetMapping("/list")
    @Operation(summary = "Lista todos os gêneros",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Gêneros listados com sucesso",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = GenreDTO.class)))
            })
    public ResponseEntity<List<GenreDTO>> findAllGenres() {
        List<GenreDTO> list = genreService.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Busca gênero pelo ID",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Gênero encontrado",
                            content = @Content(schema = @Schema(implementation = Genre.class))),
                    @ApiResponse(responseCode = "404", description = "Gênero não encontrado")
            })
    public ResponseEntity<Genre> findById(@PathVariable Long id) {
        Genre genre = genreService.findGenreById(id);
        return ResponseEntity.ok().body(genre);
    }

    @PostMapping("/save/")
    @Operation(summary = "Cria um novo gênero",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Gênero criado com sucesso",
                            content = @Content(schema = @Schema(implementation = GenreDTO.class)))
            })
    public ResponseEntity<Genre> createGenre(@RequestBody GenreDTO genreDTO) {
        Genre createdGenre = genreService.create(genreDTO);
        return new ResponseEntity<>(createdGenre, HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    @Operation(summary = "Atualiza um gênero existente",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Gênero atualizado com sucesso",
                            content = @Content(schema = @Schema(implementation = GenreDTO.class))),
                    @ApiResponse(responseCode = "404", description = "Gênero não encontrado")
            })
    public ResponseEntity<GenreDTO> update(@PathVariable Long id, @RequestBody GenreDTO genreDTO) {
        genreDTO.setId(id);
        genreDTO = genreService.update(genreDTO);
        return ResponseEntity.ok().body(genreDTO);
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Exclui um gênero",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Gênero excluído com sucesso"),
                    @ApiResponse(responseCode = "404", description = "Gênero não encontrado")
            })
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        genreService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
