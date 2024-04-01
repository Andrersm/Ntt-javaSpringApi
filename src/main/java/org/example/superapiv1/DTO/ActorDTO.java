package org.example.superapiv1.DTO;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.example.superapiv1.entities.Actor;


@Setter
@Getter
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


    public ActorDTO() {}

    public ActorDTO(Actor actor) {
        this.id = actor.getId();
        this.name = actor.getName();
        this.favoriteFood = actor.getFavoriteFood();
        this.age = actor.getAge();
    }


}
