package org.example.superapiv1.controller;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.example.superapiv1.DTO.AuthenticationDTO;
import org.example.superapiv1.DTO.RegisterDTO;
import org.example.superapiv1.DTO.ResponseDTO;
import org.example.superapiv1.domain.client.User;
import org.example.superapiv1.infra.security.TokenService;
import org.example.superapiv1.repositories.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@RestController
@Tag(name = "Autenticação", description = "Endpoints para autenticação e registro de usuários")
@RequestMapping("auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final TokenService tokenService;

    @PostMapping("/login")
    @Operation(summary = "Login do usuário",
            description = "Autentica o usuário e retorna um token JWT.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Usuário autenticado com sucesso",
                            content = @Content(schema = @Schema(implementation = ResponseDTO.class))),
                    @ApiResponse(responseCode = "400", description = "Credenciais inválidas")
            })
    public ResponseEntity login(@RequestBody  AuthenticationDTO body){
            User user = this.userRepository.findByLogin(body.login()).orElseThrow(()-> new RuntimeException("Não achou o login"));
            if (passwordEncoder.matches(body.password(), user.getPassword())){
                String token = this.tokenService.generateToken(user);
                return ResponseEntity.ok(new ResponseDTO(user.getLogin(), token));
            }
            return  ResponseEntity.badRequest().build();
        }


    @PostMapping("/register")
    @Operation(summary = "Registro de novo usuário",
            description = "Registra um novo usuário no sistema e retorna um token JWT.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Usuário registrado com sucesso",
                            content = @Content(schema = @Schema(implementation = ResponseDTO.class))),
                    @ApiResponse(responseCode = "400", description = "Usuário já existe")
            })
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
    @Operation(summary = "Usuário atual",
            description = "Retorna o usuário atualmente autenticado.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Usuário autenticado",
                            content = @Content(schema = @Schema(implementation = String.class)))
            })
    public ResponseEntity<?> getCurrentUser(Authentication authentication) {
        if (authentication != null && authentication.isAuthenticated() && !(authentication instanceof AnonymousAuthenticationToken)) {
            return ResponseEntity.ok(authentication.getName() + " está logado.");
        } else {
            return ResponseEntity.ok("Nenhum usuário está logado.");
        }
    }

}
