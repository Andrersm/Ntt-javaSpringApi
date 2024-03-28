package org.example.superapiv1.repositories;

import org.example.superapiv1.entities.Streaming;
import org.example.superapiv1.entities.Studio;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudioRepository extends JpaRepository<Studio, Long> {
    @EntityGraph(attributePaths = {"movies"})
    List<Studio> findAll();

}
