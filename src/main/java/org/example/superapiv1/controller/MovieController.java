package org.example.superapiv1.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;


import jakarta.persistence.EntityNotFoundException;
import org.example.superapiv1.DTO.MovieDTO;
import org.example.superapiv1.DTO.MovieDetailDTO;
import org.example.superapiv1.entities.Movie;
import org.example.superapiv1.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@Tag(name = "Filme", description = "Endpoints para gerenciamento de filmes")
@RequestMapping(value= "/movie")
public class MovieController {

    @Autowired
    MovieService movieService;

    @PostMapping
    @Operation(summary = "Criar um novo filme", description = "Cria um novo filme com os detalhes fornecidos")
    public ResponseEntity<MovieDTO> createMovie(@RequestBody MovieDTO movieDTO) {
        MovieDTO createdMovie = movieService.createMovie(movieDTO);
        return ResponseEntity.ok(createdMovie);
    }

    @PutMapping("/update/{id}")
    @Operation(summary = "Atualizar um filme existente", description = "Atualiza os detalhes de um filme existente")
    public ResponseEntity<Movie> updateMovie(@PathVariable Long id, @RequestBody Movie movie) {
        Movie updatedMovie = movieService.updateMovie(id, movie);
        if (updatedMovie != null) {
            return ResponseEntity.ok(updatedMovie);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @GetMapping("/list")
    @Operation(summary = "Obter todos os filmes", description = "Retorna todos os filmes cadastrados")
    public ResponseEntity<List<MovieDTO>> getAllMovies() {
        List<MovieDTO> movieDTOs = movieService.getAllMovies().stream()
                .map(MovieDTO::new)
                .toList();

        if (movieDTOs.isEmpty()) {
            throw new EntityNotFoundException("Filmes ou Filme");
        }

        return ResponseEntity.ok(movieDTOs);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar filme por ID", description = "Retorna um filme completo pelo seu ID")
    public ResponseEntity<MovieDetailDTO> getMovieById(@PathVariable Long id) {
        MovieDetailDTO movieDetailDTO = movieService.getMovieById(id);
        if (movieDetailDTO == null) {
            throw new EntityNotFoundException("Busca de Filme ");
        }
        return ResponseEntity.ok(movieDetailDTO);
    }

    @PostMapping("/{movieId}/actors/{actorId}")
    @Operation(summary = "Adicionar ator a um filme", description = "Adiciona um ator a um filme pelo ID do filme e do ator")
    public ResponseEntity<MovieDetailDTO> addActorToMovie(@PathVariable Long movieId, @PathVariable Long actorId) {
        MovieDetailDTO movieDetailDTO = movieService.addActorToMovie(movieId, actorId);
        if (movieDetailDTO == null) {
            throw new EntityNotFoundException("Filme ou ator ");
        }
        return ResponseEntity.ok(movieDetailDTO);
    }

    @PostMapping("/{movieId}/directors/{directorId}")
    @Operation(summary = "Adicionar diretor a um filme", description = "Adiciona um diretor a um filme pelo ID do filme e do diretor")
    public ResponseEntity<MovieDetailDTO> addDirectorToMovie(@PathVariable Long movieId, @PathVariable Long directorId) {
        MovieDetailDTO movieDetailDTO = movieService.addDirectorToMovie(movieId, directorId);
        if (movieDetailDTO == null) {
            throw new EntityNotFoundException("Filme ou diretor");
        }
        return ResponseEntity.ok(movieDetailDTO);
    }

    @PostMapping("/{movieId}/genre/{genreId}")
    @Operation(summary = "Adicionar gênero a um filme", description = "Adiciona um gênero a um filme pelo ID do filme e do gênero")
    public ResponseEntity<MovieDetailDTO> addGenreToMovie(@PathVariable Long movieId, @PathVariable Long genreId) {
        MovieDetailDTO movieDetailDTO = movieService.addGenreToMovie(movieId, genreId);
        if (movieDetailDTO == null) {
            throw new EntityNotFoundException("Filme ou genero");
        }
        return ResponseEntity.ok(movieDetailDTO);
    }



    @PostMapping("/{movieId}/studio/{studioId}")
    @Operation(summary = "Adicionar estúdio a um filme", description = "Adiciona um estúdio a um filme pelo ID do filme e do estúdio")
    public ResponseEntity<MovieDetailDTO> addStudioToMovie(@PathVariable Long movieId, @PathVariable Long studioId) {
        MovieDetailDTO movieDetailDTO = movieService.addStudioToMovie(movieId, studioId);
        if (movieDetailDTO == null) {
            throw new EntityNotFoundException("Filme ou estúdio");
        }
        return ResponseEntity.ok(movieDetailDTO);
    }

    @PostMapping("/{movieId}/franchise/{franchiseId}")
    @Operation(summary = "Adicionar franquia a um filme", description = "Adiciona uma franquia a um filme pelo ID do filme e da franquia")
    public ResponseEntity<MovieDetailDTO> addFranchiseToMovie(@PathVariable Long movieId, @PathVariable Long franchiseId) {
        MovieDetailDTO movieDetailDTO = movieService.addFranchiseToMovie(movieId, franchiseId);
        if (movieDetailDTO == null) {
            throw new EntityNotFoundException("Filme ou franquia");
        }
        return ResponseEntity.ok(movieDetailDTO);
    }

    @PostMapping("/{movieId}/streaming/{streamingId}")
    @Operation(summary = "Adicionar serviço de streaming a um filme", description = "Associa um serviço de streaming a um filme pelo ID do filme e do serviço de streaming")
    public ResponseEntity<MovieDetailDTO> addStreamingToMovie(@PathVariable Long movieId, @PathVariable Long streamingId) {
        MovieDetailDTO movieDetailDTO = movieService.addStreamingToMovie(movieId, streamingId);
        if (movieDetailDTO == null) {
            throw new EntityNotFoundException("Filme ou serviço de streaming");
        }
        return ResponseEntity.ok(movieDetailDTO);
    }

    @PostMapping("/{movieId}/writer/{writerId}")
    @Operation(summary = "Adicionar escritor a um filme", description = "Adiciona um escritor a um filme pelo ID do filme e do escritor")
    public ResponseEntity<MovieDetailDTO> addWriterToMovie(@PathVariable Long movieId, @PathVariable Long writerId) {
        MovieDetailDTO movieDetailDTO = movieService.addWriterToMovie(movieId, writerId);
        if (movieDetailDTO == null) {
            throw new EntityNotFoundException("Filme ou escritor");
        }
        return ResponseEntity.ok(movieDetailDTO);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar filme por ID", description = "Deleta um filme pelo seu ID")
    public ResponseEntity<Void> deleteMovie(@PathVariable Long id) {
        MovieDetailDTO movie = movieService.getMovieById(id);
        if (movie != null) {
            movieService.deleteMovie(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
