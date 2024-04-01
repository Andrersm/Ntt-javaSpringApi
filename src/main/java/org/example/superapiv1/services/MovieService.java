package org.example.superapiv1.services;


import org.example.superapiv1.DTO.MovieDTO;
import org.example.superapiv1.DTO.MovieDetailDTO;
import org.example.superapiv1.entities.*;
import org.example.superapiv1.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MovieService {

    @Autowired
    private StreamingRepository streamingRepository;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private ActorRepository actorRepository;

    @Autowired
    private DirectorRepository directorRepository;

    @Autowired
    private GenreRepository genreRepository;

    @Autowired
    private StudioRepository studioRepository;

    @Autowired
    private FranchiseRepository franchiseRepository;

    @Autowired
    private WriterRepository writerRepository;

    public MovieDTO createMovie(MovieDTO movieDTO) {
        Movie newMovie = new Movie();
        newMovie.setTitle(movieDTO.getTitle());
        newMovie.setDuration(movieDTO.getDuration());
        newMovie.setShortDescription(movieDTO.getShortDescription());

        Movie savedMovie = movieRepository.save(newMovie);
        return new MovieDTO(savedMovie);
    }

    public MovieDetailDTO getMovieById(Long id) {
        Movie movie = movieRepository.findById(id).orElse(null);
        if (movie != null) {
            return new MovieDetailDTO(movie);
        }
        return null;
    }

    public List<MovieDTO> getAllMovies() {
        Iterable<Movie> movies = movieRepository.findAll();
        List<MovieDTO> movieDTOs = new ArrayList<>();
        for (Movie movie : movies) {
            movieDTOs.add(new MovieDTO(movie));
        }
        return movieDTOs;
    }

    public Movie updateMovie(Long id, Movie updatedMovie) {
        Movie existingMovie = movieRepository.findById(id).orElse(null);
        if (existingMovie != null) {
            existingMovie.setTitle(updatedMovie.getTitle());
            existingMovie.setDuration(updatedMovie.getDuration());
            existingMovie.setImgUrl(updatedMovie.getImgUrl());
            existingMovie.setShortDescription(updatedMovie.getShortDescription());
            existingMovie.setActors(updatedMovie.getActors());
            existingMovie.setDirector(updatedMovie.getDirector());
            existingMovie.setGenre(updatedMovie.getGenre());
            existingMovie.setStudio(updatedMovie.getStudio());
            existingMovie.setFranchise(updatedMovie.getFranchise());
            existingMovie.setStreaming(updatedMovie.getStreaming());
            existingMovie.setWriters(updatedMovie.getWriters());
            return movieRepository.save(existingMovie);
        }
        return null;
    }

    public void deleteMovie(Long id) {
        movieRepository.deleteById(id);
    }

    public MovieDetailDTO addActorToMovie(Long movieId, Long actorId) {
        Optional<Movie> optionalMovie = movieRepository.findById(movieId);
        Optional<Actor> optionalActor = actorRepository.findById(actorId);

        if (optionalMovie.isPresent() && optionalActor.isPresent()) {
            Movie movie = optionalMovie.get();
            Actor actor = optionalActor.get();
            movie.getActors().add(actor);
            Movie savedMovie = movieRepository.save(movie);
            return new MovieDetailDTO(savedMovie);
        } else {
            return null;
        }
    }

    public MovieDetailDTO addDirectorToMovie(Long movieId, Long directorId) {
        Optional<Movie> optionalMovie = movieRepository.findById(movieId);
        Optional<Director> optionalDirector = directorRepository.findById(directorId);

        if (optionalMovie.isPresent() && optionalDirector.isPresent()) {
            Movie movie = optionalMovie.get();
            Director director = optionalDirector.get();
            movie.setDirector(director);
            Movie savedMovie = movieRepository.save(movie);
            return new MovieDetailDTO(savedMovie);
        } else {
            return null;
        }
    }

    public MovieDetailDTO addGenreToMovie(Long movieId, Long genreId) {
        Optional<Movie> optionalMovie = movieRepository.findById(movieId);
        Optional<Genre> optionalGenre = genreRepository.findById(genreId);

        if (optionalMovie.isPresent() && optionalGenre.isPresent()) {
            Movie movie = optionalMovie.get();
            Genre genre = optionalGenre.get();
            movie.setGenre(genre);
            Movie savedMovie = movieRepository.save(movie);
            return new MovieDetailDTO(savedMovie);
        } else {
            return null;
        }
    }

    public MovieDetailDTO addStudioToMovie(Long movieId, Long studioId) {
        Optional<Movie> optionalMovie = movieRepository.findById(movieId);
        Optional<Studio> optionalStudio = studioRepository.findById(studioId);

        if (optionalMovie.isPresent() && optionalStudio.isPresent()) {
            Movie movie = optionalMovie.get();
            Studio studio = optionalStudio.get();
            movie.setStudio(studio);
            Movie savedMovie = movieRepository.save(movie);
            return new MovieDetailDTO(savedMovie);
        } else {
            return null;
        }
    }

    public MovieDetailDTO addFranchiseToMovie(Long movieId, Long franchiseId) {
        Optional<Movie> optionalMovie = movieRepository.findById(movieId);
        Optional<Franchise> optionalFranchise = franchiseRepository.findById(franchiseId);

        if (optionalMovie.isPresent() && optionalFranchise.isPresent()) {
            Movie movie = optionalMovie.get();
            Franchise franchise = optionalFranchise.get();
            movie.setFranchise(franchise);
            Movie savedMovie = movieRepository.save(movie);
            return new MovieDetailDTO(savedMovie);
        } else {
            return null;
        }
    }

    public MovieDetailDTO addStreamingToMovie(Long movieId, Long streamingId) {
        Optional<Movie> optionalMovie = movieRepository.findById(movieId);
        Optional<Streaming> optionalStreaming = streamingRepository.findById(streamingId);

        if (optionalMovie.isPresent() && optionalStreaming.isPresent()) {
            Movie movie = optionalMovie.get();
            Streaming streaming = optionalStreaming.get();
            movie.setStreaming(streaming);
            movieRepository.save(movie);

            return new MovieDetailDTO(movie);
        } else {
            return null;
        }
    }

    public MovieDetailDTO addWriterToMovie(Long movieId, Long writerId) {
        Optional<Movie> optionalMovie = movieRepository.findById(movieId);
        Optional<Writer> optionalWriter = writerRepository.findById(writerId);

        if (optionalMovie.isPresent() && optionalWriter.isPresent()) {
            Movie movie = optionalMovie.get();
            Writer writer = optionalWriter.get();
            movie.getWriters().add(writer);
            movieRepository.save(movie);

            return new MovieDetailDTO(movie);
        } else {
            return null;
        }
    }


}
