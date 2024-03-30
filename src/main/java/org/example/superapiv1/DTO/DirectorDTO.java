package org.example.superapiv1.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.example.superapiv1.entities.Director;
import org.example.superapiv1.entities.Movie;
import java.util.List;

public class DirectorDTO {

    private Long id;
    @NotBlank(message = "O nome não pode estar vazio.")
    @Size(min = 2, max = 100, message = "O nome deve ter entre 2 e 100 caracteres.")
    private String name;
    @NotNull(message = "Todos tem um esporte favorito")
    private String favoriteSport;
    private List<Long> movieIds;
    private List<MovieDTO> directedMovies;

    public DirectorDTO(){
    }

    public DirectorDTO(Director director) {
        this.id = director.getId();
        this.name = director.getName();
        this.movieIds = director.getDirectedMovies().stream()
                .map(Movie::getId)
                .toList();
        this.directedMovies = director.getDirectedMovies().stream()
                .map(MovieDTO::new)
                .toList();
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

    public String getFavoriteSport() {
        return favoriteSport;
    }

    public void setFavoriteSport(String favoriteSport) {
        this.favoriteSport = favoriteSport;
    }

    public List<Long> getMovieIds() {
        return movieIds;
    }

    public void setMovieIds(List<Long> movieIds) {
        this.movieIds = movieIds;
    }

    public List<MovieDTO> getDirectedMovies() {
        return directedMovies;
    }

    public void setDirectedMovies(List<MovieDTO> directedMovies) {
        this.directedMovies = directedMovies;
    }
}
