package org.example.superapiv1.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.example.superapiv1.entities.Franchise;
import org.example.superapiv1.entities.Movie;
import java.util.List;
import java.util.stream.Collectors;

@Setter
@Getter
public class FranchiseDTO {

    private Long id;
    @NotBlank(message = "O nome não pode estar vazio.")
    @Size(min = 2, max = 100, message = "O nome deve ter entre 2 e 100 caracteres.")
    private String name;


    public FranchiseDTO(){
    }

    public FranchiseDTO(Franchise franchise) {
        this.id = franchise.getId();
        this.name = franchise.getName();
    }


}
