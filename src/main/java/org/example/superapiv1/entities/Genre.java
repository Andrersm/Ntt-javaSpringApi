package org.example.superapiv1.entities;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name= "tb_genre")
public class Genre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @OneToMany(mappedBy = "genre")
    private List<Movie> movies = new ArrayList<>();

    public Genre(Genre newgenre) {
        this.id = newgenre.getId();
        this.name = newgenre.getName();
        this.movies = newgenre.getMovies() != null ? new ArrayList<>(newgenre.getMovies()) : null;

    }


}
