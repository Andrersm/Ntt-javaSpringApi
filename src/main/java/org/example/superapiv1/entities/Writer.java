package org.example.superapiv1.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name= "tb_writer")
public class Writer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @ManyToMany(mappedBy = "writers")
    private List<Movie> writedMovies;

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

    public List<Movie> getWritedMovies() {
        return writedMovies;
    }

    public void setWritedMovies(List<Movie> writedMovies) {
        this.writedMovies = writedMovies;
    }
}
