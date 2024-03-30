package org.example.superapiv1.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.example.superapiv1.entities.Movie;
import org.example.superapiv1.entities.Studio;
import java.util.List;
import java.util.stream.Collectors;

public class StudioDTO {

    private Long id;
    @NotBlank(message = "Todo estudio tem um nome MALUCO.")
    @Size(min = 2, max = 100, message = "O nome deve ter entre 2 e 100 caracteres.")
    private String name;
    private List<MovieDTO> movies;
    private List<Long> moviesIds;

    public StudioDTO(){
    }

    public StudioDTO(Studio studio) {
        this.id = studio.getId();
        this.name = studio.getName();
        this.moviesIds = studio.getMovies().stream()
                .map(Movie::getId)
                .collect(Collectors.toList());
        this.movies = studio.getMovies().stream()
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

    public List<MovieDTO> getMovies() {
        return movies;
    }

    public void setMovies(List<MovieDTO> movies) {
        this.movies = movies;
    }

    public List<Long> getMoviesIds() {
        return moviesIds;
    }

    public void setMoviesIds(List<Long> moviesIds) {
        this.moviesIds = moviesIds;
    }
}
