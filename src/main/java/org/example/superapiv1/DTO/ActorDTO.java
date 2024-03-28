package org.example.superapiv1.DTO;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.example.superapiv1.entities.Actor;
import org.example.superapiv1.entities.Movie;

import java.util.List;
import java.util.stream.Collectors;

public class ActorDTO {

    private Long id;

    @NotBlank(message = "O nome não pode estar vazio.")
    @Size(min = 2, max = 100, message = "O nome deve ter entre 2 e 100 caracteres.")
    private String name;

    @NotNull(message = "Todo mundo tem uma comida favorita")
    private String favoriteFood;

    @NotNull(message = "A idade é obrigatória.")
    @Min(value = 0, message = "A idade não pode ser negativa.")
    private int age;

    private List<Long> movieIds;

    private List<MovieDTO> movies;


    public ActorDTO() {}

    public ActorDTO(Actor actor) {
        this.id = actor.getId();
        this.name = actor.getName();
        this.favoriteFood = actor.getFavoriteFood();
        this.age = actor.getAge();
        this.movieIds = actor.getActedMovies().stream()
                .map(Movie::getId)
                .collect(Collectors.toList());
        this.movies = actor.getActedMovies().stream()
                .map(movie -> new MovieDTO(movie))
                .collect(Collectors.toList());
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Long> getMovieIds() {
        return movieIds;
    }

    public void setMovieIds(List<Long> movieIds) {
        this.movieIds = movieIds;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFavoriteFood() {
        return favoriteFood;
    }

    public void setFavoriteFood(String favoriteFood) {
        this.favoriteFood = favoriteFood;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<MovieDTO> getMovies() {
        return movies;
    }

    public void setMovies(List<MovieDTO> movies) {
        this.movies = movies;
    }
}
