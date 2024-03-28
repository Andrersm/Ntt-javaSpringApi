package org.example.superapiv1.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.example.superapiv1.entities.Actor;
import org.example.superapiv1.entities.Franchise;
import org.example.superapiv1.entities.Movie;

import java.util.List;
import java.util.stream.Collectors;

public class FranchiseDTO {

    private Long id;
    @NotBlank(message = "O nome não pode estar vazio.")
    @Size(min = 2, max = 100, message = "O nome deve ter entre 2 e 100 caracteres.")
    private String name;;
    private List<MovieDTO> movies;
    private List<Long> moviesIds;


    public FranchiseDTO(){
    }

    public FranchiseDTO(Franchise franchise) {
        this.id = franchise.getId();
        this.name = franchise.getName();
        this.moviesIds = franchise.getMovies().stream()
                .map(Movie::getId)
                .collect(Collectors.toList());
        this.movies = franchise.getMovies().stream()
                .map(MovieDTO::new)
                .collect(Collectors.toList());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    public List<Long> getMoviesIds() {
        return moviesIds;
    }

    public void setMoviesIds(List<Long> moviesIds) {
        this.moviesIds = moviesIds;
    }

    public List<MovieDTO> getMovies() {
        return movies;
    }

    public void setMovies(List<MovieDTO> movies) {
        this.movies = movies;
    }
}
