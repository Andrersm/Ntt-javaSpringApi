package org.example.superapiv1.entities;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Entity
@Table(name= "tb_director")
public class Director {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String favoriteSport;

    @OneToMany(mappedBy = "director")
    @Fetch(FetchMode.JOIN)
    private List<Movie> directedMovies = new ArrayList<>();

}
