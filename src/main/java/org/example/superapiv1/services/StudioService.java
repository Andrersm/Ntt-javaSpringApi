package org.example.superapiv1.services;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.example.superapiv1.DTO.MovieDTO;
import org.example.superapiv1.DTO.StudioDTO;
import org.example.superapiv1.entities.Movie;
import org.example.superapiv1.entities.Studio;
import org.example.superapiv1.exception.EntityDeletionException;
import org.example.superapiv1.exception.UnexpectedIdException;
import org.example.superapiv1.repositories.MovieRepository;
import org.example.superapiv1.repositories.StudioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class StudioService {

    @Autowired
    private StudioRepository studioRepository;

    @Autowired
    private MovieRepository movieRepository;

    public List<StudioDTO> findAll(){
        List<Studio> listStudio = studioRepository.findAll();
        return listStudio.stream().map(StudioDTO::new).toList();
    }

    public StudioDTO findById(Long id){
        Studio studio = studioRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("Estudio"));
        return new StudioDTO(studio);

    }

    @Transactional
    public StudioDTO save(StudioDTO studioDTO){
        Studio studio;
        if (studioDTO.getId() != null) {
            studio = studioRepository.findById(studioDTO.getId())
                    .orElseThrow(() -> new UnexpectedIdException("studio não encontrado com o ID: " + studioDTO.getId()));
        } else {
            studio = new Studio();
        }
        studio.setName(studioDTO.getName());

        if(studio.getMovies() != null){
            studio.getMovies().forEach(movie -> movie.setStudio(null));
        }

        if (studioDTO.getMoviesIds() != null && !studioDTO.getMoviesIds().isEmpty()) {
            List<Movie> movies = movieRepository.findAllByIdIn((studioDTO.getMoviesIds()));
            Studio finalStudio = studio;
            movies.forEach(movie -> movie.setStudio(finalStudio));
            studio.setMovies(movies);
        } else {
            studio.setMovies(new ArrayList<>());
        }
        studio = studioRepository.save(studio);

        StudioDTO savedStudioDTO = new StudioDTO(studio);
        savedStudioDTO.setMoviesIds(studio.getMovies().stream().map(Movie::getId).collect(Collectors.toList()));
        savedStudioDTO.setMovies(studio.getMovies().stream().map(MovieDTO::new).collect(Collectors.toList()));
        return savedStudioDTO;
    }

    public void delete(Long id) {

        if (!studioRepository.existsById(id)) {
            throw new EntityDeletionException("Estudio", id);
        }
        studioRepository.deleteById(id);
    }

}

