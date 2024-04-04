package org.example.superapiv1.controller;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.superapiv1.DTO.AuthenticationDTO;
import org.example.superapiv1.DTO.RegisterDTO;
import org.example.superapiv1.DTO.ResponseDTO;
import org.example.superapiv1.DTO.UserDetailsDTO;
import org.example.superapiv1.facade.AuthenticationFacade;
import org.example.superapiv1.impl.AuthenticationFacadeImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.login.LoginException;
import java.util.List;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationFacadeImpl authenticationFacadeImpl;

    @PostMapping("/login")
    @Operation(summary = "Login do usuário",
            description = "Autentica o usuário e retorna um token JWT.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Usuário autenticado com sucesso",
                            content = @Content(schema = @Schema(implementation = ResponseDTO.class))),
                    @ApiResponse(responseCode = "400", description = "Credenciais inválidas")
            })
    public ResponseEntity<ResponseDTO> login(@Valid @RequestBody AuthenticationDTO body) throws LoginException {
        ResponseDTO response = authenticationFacadeImpl.login(body);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/register")
    @Operation(summary = "Registro de novo usuário",
            description = "Registra um novo usuário no sistema",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Usuário registrado com sucesso",
                            content = @Content(schema = @Schema(implementation = ResponseDTO.class))),
                    @ApiResponse(responseCode = "400", description = "Usuário já existe")
            })
    public ResponseEntity<ResponseDTO> register(@Valid @RequestBody RegisterDTO body) throws LoginException {
        ResponseDTO response = authenticationFacadeImpl.register(body);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/current-user")
    @Operation(summary = "Usuário atual",
            description = "Retorna o usuário atualmente autenticado.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Usuário autenticado",
                            content = @Content(schema = @Schema(implementation = String.class)))
            })
    public ResponseEntity<String> getCurrentUser(Authentication authentication) {
        String response = authenticationFacadeImpl.getCurrentUser(authentication);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/user/{id}")
    @Operation(summary = "Buscar usuário por ID",
            description = "Retorna detalhes de um usuário específico baseado no ID fornecido.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Detalhes do usuário encontrado",
                            content = @Content(schema = @Schema(implementation = UserDetailsDTO.class))),
                    @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
            })
    public ResponseEntity<UserDetailsDTO> getUserById(@PathVariable String id) {
        UserDetailsDTO userDetailsDTO = authenticationFacadeImpl.getUserById(id);
        return ResponseEntity.ok(userDetailsDTO);
    }

    @GetMapping
    @Operation(summary = "Listar todos os usuários",
            description = "Retorna uma lista de todos os usuários cadastrados.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Lista de usuários",
                            content = @Content(array = @ArraySchema(schema = @Schema(implementation = UserDetailsDTO.class))))
            })
    public ResponseEntity<List<UserDetailsDTO>> getAllUsers() {
        List<UserDetailsDTO> users = authenticationFacadeImpl.findAllUsers();
        return ResponseEntity.ok(users);
    }


}
