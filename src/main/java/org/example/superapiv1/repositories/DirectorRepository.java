package org.example.superapiv1.repositories;

import org.example.superapiv1.entities.Director;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DirectorRepository extends JpaRepository<Director, Long> {
}
