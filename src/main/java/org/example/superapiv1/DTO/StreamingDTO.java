package org.example.superapiv1.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.example.superapiv1.entities.Streaming;


@Setter
@Getter
public class StreamingDTO {

    private Long id;
    @NotBlank(message = "O nome não pode estar vazio.")
    @Size(min = 2, max = 100, message = "O nome deve ter entre 2 e 100 caracteres.")
    private String name;

    public StreamingDTO(){
    }

    public StreamingDTO(Streaming streaming) {
        this.id = streaming.getId();
        this.name = streaming.getName();
    }

}
