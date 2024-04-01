package org.example.superapiv1.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.superapiv1.DTO.MovieDTO;
import org.example.superapiv1.services.MovieSearcheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "Search", description = "Endpoints para Fazer buscas")
@RestController
public class SearchController {

    @Autowired
    private MovieSearcheService movieSearcheService;

    @GetMapping("/search")
    @Operation(summary = "Buscar filmes por título", description = "Retorna filmes que contêm o título fornecido " +
            "Exemplo de uso: /search '?title='nome do filme que quer buscar'' algo como /search?title=naruto ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Filmes encontrados"),
            @ApiResponse(responseCode = "404", description = "Nenhum filme encontrado com o título fornecido")
    })
    public ResponseEntity<List<MovieDTO>> searchMoviesByTitle(
            @Parameter(description = "Título do filme a ser buscado", required = true)
            @RequestParam("title") String title) {

        List<MovieDTO> movies = movieSearcheService.searchMoviesByTitle(title);
        if (movies.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(movies);
        }
    }
}
