package org.example.superapiv1.DTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.example.superapiv1.entities.Movie;
import org.example.superapiv1.entities.Writer;
import java.util.List;
import java.util.stream.Collectors;

public class WriterDTO {

    private Long id;
    @NotBlank(message = "A parte mais importante do escrito é seu nome.")
    @Size(min = 2, max = 100, message = "O nome deve ter entre 2 e 100 caracteres.")
    private String name;
    private List<MovieDTO> writedMovies;
    private List<Long> moviesId;

    public WriterDTO(){
    }

    public WriterDTO(Writer writer) {
        this.id = writer.getId();
        this.name = writer.getName();
        this.moviesId = writer.getWritedMovies().stream()
                .map(Movie::getId)
                .collect(Collectors.toList());
        this.writedMovies = writer.getWritedMovies().stream()
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

    public List<MovieDTO> getWritedMovies() {
        return writedMovies;
    }

    public void setWritedMovies(List<MovieDTO> writedMovies) {
        this.writedMovies = writedMovies;
    }

    public List<Long> getMoviesId() {
        return moviesId;
    }

    public void setMoviesId(List<Long> moviesId) {
        this.moviesId = moviesId;
    }
}
