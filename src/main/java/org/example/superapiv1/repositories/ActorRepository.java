package org.example.superapiv1.repositories;

import org.example.superapiv1.entities.Actor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActorRepository extends JpaRepository<Actor, Long> {
}
