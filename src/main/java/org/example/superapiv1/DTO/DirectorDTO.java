package org.example.superapiv1.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.superapiv1.entities.Director;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class DirectorDTO {

    private Long id;
    @NotBlank(message = "O nome não pode estar vazio.")
    @Size(min = 2, max = 100, message = "O nome deve ter entre 2 e 100 caracteres.")
    private String name;
    @NotNull(message = "Todos tem um esporte favorito")
    private String favoriteSport;


    public DirectorDTO(Director director) {
        this.id = director.getId();
        this.name = director.getName();
        this.favoriteSport = director.getFavoriteSport();

    }

}
