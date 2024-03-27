package org.example.superapiv1.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.example.superapiv1.entities.Movie;

public class MovieDTO {
    private Long id;
    @NotBlank(message = "O titulo não pode estar vazio.")
    @Size(min = 2, max = 100, message = "O nome deve ter entre 2 e 100 caracteres.")
    private String title;
    @NotBlank(message = "O duração não pode estar vazio.")
    private int duration;
    private String shortDescription;


    public MovieDTO(){
    }

    public MovieDTO(Movie movie) {

        id = movie.getId();
        title = movie.getTitle();
        duration = movie.getDuration();
        shortDescription = movie.getShortDescription();
    }

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



    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
}
