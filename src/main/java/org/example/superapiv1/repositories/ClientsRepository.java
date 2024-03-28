package org.example.superapiv1.repositories;

import org.example.superapiv1.domain.client.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface ClientsRepository  extends JpaRepository<Client, String>{
    UserDetails findByLogin(String login);
}
