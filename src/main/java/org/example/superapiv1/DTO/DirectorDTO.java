package org.example.superapiv1.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.example.superapiv1.entities.Director;
import org.example.superapiv1.entities.Movie;
import java.util.List;

@Setter
@Getter
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
        this.favoriteSport = director.getFavoriteSport();
        this.movieIds = director.getDirectedMovies().stream()
                .map(Movie::getId)
                .toList();
        this.directedMovies = director.getDirectedMovies().stream()
                .map(MovieDTO::new)
                .toList();
    }

}
