package org.example.superapiv1.facade;

import jakarta.persistence.EntityNotFoundException;
import org.example.superapiv1.DTO.AuthenticationDTO;
import org.example.superapiv1.DTO.RegisterDTO;
import org.example.superapiv1.DTO.ResponseDTO;
import org.example.superapiv1.DTO.UserDetailsDTO;
import org.springframework.security.core.Authentication;

import javax.security.auth.login.LoginException;
import java.util.List;

public interface AuthenticationFacade {

    ResponseDTO login(AuthenticationDTO body) throws LoginException;

    ResponseDTO register(RegisterDTO body) throws LoginException;

    String getCurrentUser(Authentication authentication);

    UserDetailsDTO getUserById(String id) throws EntityNotFoundException;

    List<UserDetailsDTO> findAllUsers();
}
