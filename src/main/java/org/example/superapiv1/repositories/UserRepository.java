package org.example.superapiv1.repositories;
import org.example.superapiv1.domain.client.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String>{
    Optional<User> findByLogin(String login);
}
