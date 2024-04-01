package org.example.superapiv1.DTO;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.superapiv1.entities.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class MovieDTO {
    private Long id;
    @NotBlank(message = "O titulo não pode estar vazio.")
    @Size(min = 2, max = 100, message = "O nome deve ter entre 2 e 100 caracteres.")
    private String title;
    @Min(value = 1, message = "A duração deve ser pelo menos 1 minuto.")
    private int duration;
    private String shortDescription;



    public MovieDTO(Movie movie) {
        this.id = movie.getId();
        this.title = movie.getTitle();
        this.shortDescription = movie.getShortDescription();
        this.duration = movie.getDuration();
    }


    public MovieDTO(MovieDTO movieDTO) {
        this.id = movieDTO.getId();
        this.title = movieDTO.getTitle();
        this.duration = movieDTO.getDuration();
        this.shortDescription = movieDTO.getShortDescription();
    }
}
