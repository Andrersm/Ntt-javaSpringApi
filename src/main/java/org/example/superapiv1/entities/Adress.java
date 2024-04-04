package org.example.superapiv1.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.superapiv1.DTO.AdressDTO;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Table(name= "tb_adress")
@Entity
public class Adress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    public String cep;
    public String logradouro;
    public String bairro;
    public String localidade;
    public String uf;

    public Adress(AdressDTO adressDTO) {
        this.id = adressDTO.getId();
        this.cep = adressDTO.getCep();
        this.logradouro = adressDTO.getLogradouro();
        this.bairro = adressDTO.getBairro();
        this.localidade = adressDTO.getLocalidade();
        this.uf = adressDTO.getUf();
    }

}
