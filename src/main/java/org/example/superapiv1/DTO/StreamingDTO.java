package org.example.superapiv1.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.example.superapiv1.entities.Genre;
import org.example.superapiv1.entities.Movie;
import org.example.superapiv1.entities.Streaming;

import java.util.List;
import java.util.stream.Collectors;

public class StreamingDTO {


    private Long id;
    @NotBlank(message = "O nome não pode estar vazio.")
    @Size(min = 2, max = 100, message = "O nome deve ter entre 2 e 100 caracteres.")
    private String name;
    private List<MovieDTO> catolog;
    private List<Long> moviesIds;


    public StreamingDTO(){
    }

    public StreamingDTO(Streaming streaming) {
        this.id = streaming.getId();
        this.name = streaming.getName();
        this.moviesIds = streaming.getCatalog().stream()
                .map(Movie::getId)
                .collect(Collectors.toList());
        this.catolog = streaming.getCatalog().stream()
                .map(MovieDTO::new)
                .collect(Collectors.toList());
    }

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



    public List<Long> getMoviesIds() {
        return moviesIds;
    }

    public void setMoviesIds(List<Long> moviesIds) {
        this.moviesIds = moviesIds;
    }

    public List<MovieDTO> getCatolog() {
        return catolog;
    }

    public void setCatolog(List<MovieDTO> catolog) {
        this.catolog = catolog;
    }
}
