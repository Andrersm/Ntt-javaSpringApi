package org.example.superapiv1.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.example.superapiv1.entities.Genre;
import org.example.superapiv1.entities.Studio;

public class StudioDTO {

    private Long id;
    @NotBlank(message = "Todo estudio tem um nome MALUCO.")
    @Size(min = 2, max = 100, message = "O nome deve ter entre 2 e 100 caracteres.")
    private String name;


    public StudioDTO(){
    }

    public StudioDTO(Studio studio) {

        id = studio.getId();
        name = studio.getName();
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
}
