package org.example.superapiv1.repositories;
import org.example.superapiv1.entities.Actor;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ActorRepository extends JpaRepository<Actor, Long> {
    @EntityGraph(attributePaths = {"actedMovies"})
    List<Actor> findAll();
}
