package org.example.superapiv1.domain.client;
import jakarta.persistence.*;
import lombok.*;
import org.example.superapiv1.entities.Adress;


@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String login;
    private String password;

    @OneToOne
    private Adress adress;


    public User(String login, String password, Adress adress) {
        this.login = login;
        this.password = password;
        this.adress = adress;
    }

}