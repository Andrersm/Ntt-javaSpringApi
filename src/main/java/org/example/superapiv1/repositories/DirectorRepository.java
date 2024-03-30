package org.example.superapiv1.repositories;
import org.example.superapiv1.entities.Director;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DirectorRepository extends JpaRepository<Director, Long> {
    @EntityGraph(attributePaths = {"directedMovies"})
    List<Director> findAll();
}
