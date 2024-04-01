package org.example.superapiv1.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.example.superapiv1.entities.Movie;
import org.example.superapiv1.entities.Studio;
import java.util.List;
import java.util.stream.Collectors;

@Setter
@Getter
public class StudioDTO {

    private Long id;
    @NotBlank(message = "Todo estudio tem um nome MALUCO.")
    @Size(min = 2, max = 100, message = "O nome deve ter entre 2 e 100 caracteres.")
    private String name;

    public StudioDTO(){
    }

    public StudioDTO(Studio studio) {
        this.id = studio.getId();
        this.name = studio.getName();

    }

}
