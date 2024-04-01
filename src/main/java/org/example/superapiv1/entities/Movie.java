package org.example.superapiv1.entities;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name= "tb_movie")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private int duration;
    private String imgUrl;

    @Column(columnDefinition = "TEXT")
    private String shortDescription;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "movie_actors",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "actor_id")
    )
    private Set<Actor> actors = new HashSet<>();

    @ManyToOne(fetch = FetchType.EAGER)
    private Director director;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    private Genre genre;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    private Studio studio;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    private Franchise franchise;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    private Streaming streaming;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Writer> writers;

    public Movie(Movie movie) {
        this.id = movie.getId();
        this.title = movie.getTitle();
        this.duration = movie.getDuration();
        this.imgUrl = movie.getImgUrl();
        this.shortDescription = movie.getShortDescription();
        this.actors = new HashSet<>(movie.getActors());
        this.director = movie.getDirector();
        this.genre = movie.getGenre();
        this.studio = movie.getStudio();
        this.franchise = movie.getFranchise();
        this.streaming = movie.getStreaming();
        this.writers = movie.getWriters() != null ? new ArrayList<>(movie.getWriters()) : null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return duration == movie.duration && Objects.equals(id, movie.id) && Objects.equals(title, movie.title) && Objects.equals(imgUrl, movie.imgUrl) && Objects.equals(shortDescription, movie.shortDescription) && Objects.equals(actors, movie.actors) && Objects.equals(director, movie.director) && Objects.equals(genre, movie.genre) && Objects.equals(studio, movie.studio) && Objects.equals(franchise, movie.franchise) && Objects.equals(streaming, movie.streaming) && Objects.equals(writers, movie.writers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, duration, imgUrl, shortDescription, actors, director, genre, studio, franchise, streaming, writers);
    }
}

