package org.example.superapiv1.repositories;
import org.example.superapiv1.entities.Movie;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface MovieRepository extends JpaRepository<Movie, Long> {
    @EntityGraph(attributePaths = {"actors"})
    List<Movie> findAllByIdIn(List<Long> ids);

    @EntityGraph(attributePaths = {"actors", "director", "genre", "studio", "franchise", "streaming", "writers"})
    Optional<Movie> findById(Long id);
}

