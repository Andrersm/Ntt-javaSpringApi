package org.example.superapiv1.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.superapiv1.domain.client.User;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDetailsDTO {

    private String id;
    private String login;
    private Long adressId;
    private String cep;
    private String logradouro;
    private String bairro;
    private String localidade;
    private String uf;

    public UserDetailsDTO(User user) {
        this.id = user.getId();
        this.login = user.getLogin();
        if (user.getAdress() != null) {
            this.adressId = user.getAdress().getId();
            this.cep = user.getAdress().getCep();
            this.logradouro = user.getAdress().getLogradouro();
            this.bairro = user.getAdress().getBairro();
            this.localidade = user.getAdress().getLocalidade();
            this.uf = user.getAdress().getUf();
        }
    }

}
