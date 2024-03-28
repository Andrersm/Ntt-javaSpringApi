package org.example.superapiv1.controller;

import jakarta.validation.Valid;
import org.example.superapiv1.DTO.MovieDTO;
import org.example.superapiv1.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value= "/movie")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @GetMapping("/list")
    public List<MovieDTO> findAll(){
        List<MovieDTO> listMovies = movieService.findAll();
        return listMovies;
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovieDTO> findById(@PathVariable Long id) {
        MovieDTO movieDTO = movieService.findById(id);
        return ResponseEntity.ok().body(movieDTO);
    }

    @PostMapping("/save/{id}")
    public ResponseEntity<MovieDTO> create(@Valid @RequestBody MovieDTO movieDTO){
        movieDTO = movieService.save(movieDTO);
        return new ResponseEntity<>(movieDTO, HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<MovieDTO> update(@PathVariable Long id, @RequestBody MovieDTO movieDTO) {
        movieDTO.setId(id);
        movieDTO = movieService.save(movieDTO);
        return ResponseEntity.ok().body(movieDTO);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        movieService.delete(id);
        return ResponseEntity.noContent().build();

    }





}
