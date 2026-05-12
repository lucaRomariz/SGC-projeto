package br.sgc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import br.sgc.dto.AuthResponse;
import br.sgc.dto.LoginRequest;
import br.sgc.model.Usuario;
import br.sgc.repository.UsuarioRepository;
import br.sgc.security.JwtService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        try {
            authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.email(), request.password())
            );
        } catch (AuthenticationException e) {
            return ResponseEntity.status(401).body("Email ou senha inválidos");
        }
        Usuario usuario = usuarioRepository.findByEmailIgnoreCase(request.email());
        if (usuario == null) {
            return ResponseEntity.status(401).body("Usuário não encontrado");
        }
        String token = jwtService.generateToken(usuario.getEmail());
        return ResponseEntity.ok(new AuthResponse(token, "Bearer"));
    }
}