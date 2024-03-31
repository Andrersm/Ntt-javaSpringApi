package org.example.superapiv1.DTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.example.superapiv1.entities.Movie;
import org.example.superapiv1.entities.Writer;
import java.util.List;
import java.util.stream.Collectors;

@Setter
@Getter
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
                .toList();
        this.writedMovies = writer.getWritedMovies().stream()
                .map(MovieDTO::new)
                .toList();
    }

}
