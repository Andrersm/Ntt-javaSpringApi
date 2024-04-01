package org.example.superapiv1.repositories;
import org.example.superapiv1.entities.Genre;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface GenreRepository extends JpaRepository<Genre, Long> {
    @EntityGraph(attributePaths = {"movies"})
    List<Genre> findAll();
    Optional<Genre> findByName(String name);

}
