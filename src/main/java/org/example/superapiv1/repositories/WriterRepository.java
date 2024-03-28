package org.example.superapiv1.repositories;

import org.example.superapiv1.entities.Writer;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WriterRepository extends JpaRepository<Writer, Long> {
    @EntityGraph(attributePaths = {"writedMovies"})
    List<Writer> findAll();
}
