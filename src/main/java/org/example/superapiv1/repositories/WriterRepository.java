package org.example.superapiv1.repositories;

import org.example.superapiv1.entities.Writer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WriterRepository extends JpaRepository<Writer, Long> {
}
