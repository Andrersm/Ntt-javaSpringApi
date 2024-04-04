package org.example.superapiv1.DTO;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.example.superapiv1.entities.Adress;

@Getter
@Setter
@AllArgsConstructor
public class AdressDTO {

    public Long id;
    public String cep;
    public String logradouro;
    public String bairro;
    public String localidade;
    public String uf;

    public AdressDTO(){}

    public AdressDTO(Adress adress) {
        this.id = adress.getId();
        this.cep = adress.getCep();
        this.logradouro = adress.getLogradouro();
        this.bairro = adress.getBairro();
        this.localidade = adress.getLocalidade();
        this.uf = adress.getUf();
    }
}
