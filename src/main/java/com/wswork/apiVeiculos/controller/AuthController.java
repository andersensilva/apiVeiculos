package com.wswork.apiVeiculos.controller;

import com.wswork.apiVeiculos.security.JwtUtil;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
@Tag(name = "Login", description = "Geração do Token de autenticação")
public class AuthController {

    private final AuthenticationManager authManager;
    private final JwtUtil jwtUtil;

    public AuthController(AuthenticationManager authManager, JwtUtil jwtUtil) {
        this.authManager = authManager;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    public Map<String, String> login(@RequestParam String username, @RequestParam String password) {
        try {
            authManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            String token = jwtUtil.generateToken(username);
            return Map.of("token", token);
        } catch (AuthenticationException e) {
            throw e;
        }
    }
}
