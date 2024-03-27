package org.example.superapiv1.repositories;

import org.example.superapiv1.entities.Franchise;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FranchiseRepository extends JpaRepository<Franchise, Long> {
}
