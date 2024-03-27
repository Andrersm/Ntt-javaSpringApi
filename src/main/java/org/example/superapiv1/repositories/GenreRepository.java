package org.example.superapiv1.repositories;

import org.example.superapiv1.entities.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreRepository extends JpaRepository<Genre, Long> {
}