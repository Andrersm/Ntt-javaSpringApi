package org.example.superapiv1.repositories;
import org.example.superapiv1.entities.Streaming;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface StreamingRepository extends JpaRepository<Streaming, Long> {
    @EntityGraph(attributePaths = {"catalog"})
    List<Streaming> findAll();

}
