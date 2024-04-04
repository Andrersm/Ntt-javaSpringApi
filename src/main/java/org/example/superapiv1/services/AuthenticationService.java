package org.example.superapiv1.services;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.example.superapiv1.DTO.*;
import org.example.superapiv1.domain.client.User;
import org.example.superapiv1.entities.Adress;
import org.example.superapiv1.repositories.AdressRepository;
import org.example.superapiv1.repositories.UserRepository;
import org.example.superapiv1.infra.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.security.auth.login.LoginException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    @Autowired
    private AdressRepository adressRepository;

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final TokenService tokenService;

    public ResponseDTO login(AuthenticationDTO body) throws LoginException {
        User user = this.userRepository.findByLogin(body.login())
                .orElseThrow(() -> new LoginException("Login ou senha Incorretos"));
        if (passwordEncoder.matches(body.password(), user.getPassword())) {
            String token = this.tokenService.generateToken(user);
            return new ResponseDTO(user.getLogin(), "HTTP:200 / tokenJWT: " + token );
        } else {
            throw new LoginException();
        }
    }

    public ResponseDTO register(RegisterDTO body, AdressDTO adressDTO) throws LoginException {
        Optional<User> existingUser = this.userRepository.findByLogin(body.login());
        if (existingUser.isEmpty()) {
            User newUser = new User();
            newUser.setPassword(passwordEncoder.encode(body.password()));
            newUser.setLogin(body.login());
            Adress address = new Adress(adressDTO);
            adressRepository.save(address);
            newUser.setAdress(address);
            this.userRepository.save(newUser);
            return new ResponseDTO(newUser.getLogin(), "HTTP:200 Usuário Criado com sucesso");
        } else {
            throw new LoginException("Já existe um usuário com este login");
        }
    }

    public String getCurrentUser(Authentication authentication) {
        if (authentication != null && authentication.isAuthenticated() &&
                !(authentication instanceof AnonymousAuthenticationToken)) {
            return authentication.getName() + " está logado.";
        } else {
            return "Nenhum usuário está logado.";
        }
    }

    public UserDetailsDTO getUserById(String id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuario"));
        return new UserDetailsDTO(user);
    }

    public List<UserDetailsDTO> findAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(UserDetailsDTO::new)
                .toList();
    }


}
