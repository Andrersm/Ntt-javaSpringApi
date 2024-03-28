package org.example.superapiv1.repositories;

import org.example.superapiv1.entities.Movie;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Long> {
    @EntityGraph(attributePaths = {"actors"})
    List<Movie> findAllByIdIn(List<Long> ids);
}

