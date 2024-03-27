package org.example.superapiv1.entities;

import jakarta.persistence.*;

import java.util.List;


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
    public List<Movie> actedMovies;

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

    public List<Movie> getActedMovies() {
        return actedMovies;
    }

    public void setActedMovies(List<Movie> actedMovies) {
        this.actedMovies = actedMovies;
    }
}
