package org.example.superapiv1.entities;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;


@Setter
@Getter
@Entity
@Table(name= "tb_actor")
public class Actor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    public String name;
    public String favoriteFood;
    public int age;

    @ManyToMany(mappedBy = "actors")
    private Set<Movie> actedMovies = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Actor actor = (Actor) o;
        return age == actor.age && Objects.equals(id, actor.id) && Objects.equals(name, actor.name) && Objects.equals(favoriteFood, actor.favoriteFood) && Objects.equals(actedMovies, actor.actedMovies);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, favoriteFood, age, actedMovies);
    }
}
