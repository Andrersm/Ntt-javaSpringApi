package org.example.superapiv1.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.superapiv1.DTO.AuthenticationDTO;
import org.example.superapiv1.DTO.LoginResponseDTO;
import org.example.superapiv1.DTO.RegisterDTO;
import org.example.superapiv1.DTO.ResponseDTO;
import org.example.superapiv1.domain.client.User;
import org.example.superapiv1.infra.security.TokenService;
import org.example.superapiv1.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final TokenService tokenService;


    @Autowired
    private AuthenticationManager authenticationManager;




    @PostMapping("/login")
    public ResponseEntity login(@RequestBody  AuthenticationDTO body){
            User user = this.userRepository.findByLogin(body.login()).orElseThrow(()-> new RuntimeException("Não achou o login"));
            if (passwordEncoder.matches(body.password(), user.getPassword())){
                String token = this.tokenService.generateToken(user);
                return ResponseEntity.ok(new ResponseDTO(user.getLogin(), token));
            }
            return  ResponseEntity.badRequest().build();
        }


    @PostMapping("/register")
    public ResponseEntity register(@RequestBody  RegisterDTO body) {
        Optional<User> user = this.userRepository.findByLogin(body.login());

        if (user.isEmpty()) {
            User newUser = new User();
            newUser.setPassword(passwordEncoder.encode(body.password()));
            newUser.setLogin(body.login());
            this.userRepository.save(newUser);
            String token = this.tokenService.generateToken(newUser);
            return ResponseEntity.ok(new ResponseDTO(newUser.getLogin(), token));
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/current-user")
    public ResponseEntity<?> getCurrentUser(Authentication authentication) {
        if (authentication != null && authentication.isAuthenticated() && !(authentication instanceof AnonymousAuthenticationToken)) {
            return ResponseEntity.ok(authentication.getName() + " está logado.");
        } else {
            return ResponseEntity.ok("Nenhum usuário está logado.");
        }
    }

}
