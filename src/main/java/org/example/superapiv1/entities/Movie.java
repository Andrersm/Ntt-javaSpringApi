package org.example.superapiv1.entities;
import jakarta.persistence.*;
import java.util.List;
import java.util.Objects;

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

    @ManyToMany
    @JoinTable(
            name = "movie_actors",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "actor_id")
    )
    private List<Actor> actors;

    @ManyToOne
    private Director director;

    @ManyToOne
    private Genre genre;

    @ManyToOne
    private Studio studio;

    @ManyToOne
    private Franchise franchise;

    @ManyToOne
    private Streaming streaming;

    @ManyToMany
    private List<Writer> writers;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public List<Actor> getActors() {
        return actors;
    }

    public void setActors(List<Actor> actors) {
        this.actors = actors;
    }

    public Director getDirector() {
        return director;
    }

    public void setDirector(Director director) {
        this.director = director;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public Studio getStudio() {
        return studio;
    }

    public void setStudio(Studio studio) {
        this.studio = studio;
    }

    public Franchise getFranchise() {
        return franchise;
    }

    public void setFranchise(Franchise franchise) {
        this.franchise = franchise;
    }

    public Streaming getStreaming() {
        return streaming;
    }

    public void setStreaming(Streaming streaming) {
        this.streaming = streaming;
    }

    public List<Writer> getWriters() {
        return writers;
    }

    public void setWriters(List<Writer> writers) {
        this.writers = writers;
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
