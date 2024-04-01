package org.example.superapiv1.DTO;

import lombok.Getter;
import lombok.Setter;
import org.example.superapiv1.entities.Movie;

import java.util.List;

@Getter
@Setter
public class MovieDetailDTO {

    private Long id;
    private String title;
    private int duration;
    private String imgUrl;
    private String shortDescription;
    private GenreDTO genre;
    private DirectorDTO director;
    private List<ActorDTO> actors;
    private FranchiseDTO franchise;
    private StudioDTO studio;
    private StreamingDTO streaming;
    private List<WriterDTO> writers;

    public MovieDetailDTO() {
    }

    public MovieDetailDTO(Movie movie) {
        this.id = movie.getId();
        this.title = movie.getTitle();
        this.duration = movie.getDuration();
        this.imgUrl = movie.getImgUrl();
        this.shortDescription = movie.getShortDescription();
        this.genre = movie.getGenre() != null ? new GenreDTO(movie.getGenre()) : null;
        this.director = movie.getDirector() != null ? new DirectorDTO(movie.getDirector()) : null;
        this.actors = movie.getActors() != null ? movie.getActors().stream().map(ActorDTO::new).toList() : null;
        this.franchise = movie.getFranchise() != null ? new FranchiseDTO(movie.getFranchise()) : null;
        this.studio = movie.getStudio() != null ? new StudioDTO(movie.getStudio()) : null;
        this.streaming = movie.getStreaming() != null ? new StreamingDTO(movie.getStreaming()) : null;
        this.writers = movie.getWriters() != null ? movie.getWriters().stream().map(WriterDTO::new).toList() : null;
    }

}

