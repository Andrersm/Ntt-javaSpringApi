package org.example.superapiv1.entities;
import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name= "tb_actor")
public class Actor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    public String name;
    public String favoriteFood;
    public int age;

    public Set<Movie> getActedMovies() {
        return actedMovies;
    }

    public void setActedMovies(Set<Movie> actedMovies) {
        this.actedMovies = actedMovies;
    }

    @ManyToMany(mappedBy = "actors")
    private Set<Movie> actedMovies = new HashSet<>();

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

    public String getFavoriteFood() {
        return favoriteFood;
    }

    public void setFavoriteFood(String favoriteFood) {
        this.favoriteFood = favoriteFood;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

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
