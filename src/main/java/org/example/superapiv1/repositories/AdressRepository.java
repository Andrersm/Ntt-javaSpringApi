package org.example.superapiv1.repositories;

import org.example.superapiv1.entities.Adress;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdressRepository extends JpaRepository<Adress, Long> {
}
