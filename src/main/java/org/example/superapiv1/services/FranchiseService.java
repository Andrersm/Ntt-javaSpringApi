package org.example.superapiv1.services;

import jakarta.persistence.EntityNotFoundException;
import org.example.superapiv1.DTO.FranchiseDTO;
import org.example.superapiv1.DTO.MovieDTO;
import org.example.superapiv1.entities.Franchise;
import org.example.superapiv1.entities.Movie;
import org.example.superapiv1.exception.EntityDeletionException;
import org.example.superapiv1.exception.UnexpectedIdException;
import org.example.superapiv1.repositories.FranchiseRepository;
import org.example.superapiv1.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FranchiseService {

    @Autowired
    private FranchiseRepository franchiseRepository;

    @Autowired
    private MovieRepository movieRepository;

    public List<FranchiseDTO> findAll() {
        List<Franchise> listFranchise = franchiseRepository.findAll();
        return listFranchise.stream().map(FranchiseDTO::new).toList();
    }

    public FranchiseDTO findById(Long id) {
        Franchise franchise = franchiseRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Franquia"));
        return new FranchiseDTO(franchise);
    }

    @Transactional
    public FranchiseDTO save(FranchiseDTO franchiseDTO) {
        Franchise franchise;
        if (franchiseDTO.getId() != null) {
            franchise = franchiseRepository.findById(franchiseDTO.getId())
                    .orElseThrow(() -> new UnexpectedIdException("Franquia não encontrada com o ID: " + franchiseDTO.getId()));
        } else {
            franchise = new Franchise();
        }
        franchise.setName(franchiseDTO.getName());


        if (!franchise.getMovies().isEmpty()) {
            franchise.getMovies().forEach(movie -> movie.setFranchise(null));
        }

        if (franchiseDTO.getMoviesIds() != null && !franchiseDTO.getMoviesIds().isEmpty()) {
            List<Movie> movies = movieRepository.findAllById(franchiseDTO.getMoviesIds());
            Franchise finalFranchise = franchise;
            movies.forEach(movie -> movie.setFranchise(finalFranchise));
            franchise.setMovies(movies);
        } else {
            franchise.setMovies(new ArrayList<>()); // Se nenhum ID de filme for fornecido, assegura-se que a lista de filmes seja limpa
        }


        franchise = franchiseRepository.save(franchise);
        // Atualiza o DTO para incluir os filmes associados após salvar
        FranchiseDTO savedFranchiseDTO = new FranchiseDTO(franchise);
        // Pode ser necessário ajustar a criação do FranchiseDTO aqui para refletir as mudanças feitas
        savedFranchiseDTO.setMoviesIds(franchise.getMovies().stream().map(Movie::getId).collect(Collectors.toList()));
        savedFranchiseDTO.setMovies(franchise.getMovies().stream().map(MovieDTO::new).collect(Collectors.toList()));
        return savedFranchiseDTO;
    }

    public void delete(Long id) {
        if (!franchiseRepository.existsById(id)) {
            throw new EntityDeletionException("Ator", id);
        }
        franchiseRepository.deleteById(id);
    }
}
