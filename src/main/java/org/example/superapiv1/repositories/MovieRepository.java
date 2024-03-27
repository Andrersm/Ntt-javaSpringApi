package org.example.superapiv1.repositories;

import org.example.superapiv1.entities.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Long> {
}

