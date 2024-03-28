package org.example.superapiv1.controller;

import jakarta.validation.Valid;
import org.example.superapiv1.DTO.AuthenticationDTO;
import org.example.superapiv1.DTO.LoginResponseDTO;
import org.example.superapiv1.DTO.RegisterDTO;
import org.example.superapiv1.domain.client.Client;
import org.example.superapiv1.infra.security.TokenService;
import org.example.superapiv1.repositories.ClientsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private ClientsRepository clientsRepository;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDTO data){
        var clientPassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());
        var auth = this.authenticationManager.authenticate(clientPassword);

        var token = tokenService.generateToken((Client) auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDTO(token));

    }
    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid RegisterDTO data){
        if (this.clientsRepository.findByLogin(data.login()) != null) return ResponseEntity.badRequest().build();

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());

        Client client = new Client(data.login(), encryptedPassword, data.role());

        this.clientsRepository.save(client);
        return ResponseEntity.ok().build();

    }
}
