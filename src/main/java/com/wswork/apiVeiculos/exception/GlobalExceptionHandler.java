package com.wswork.apiVeiculos.exception;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.security.SignatureException;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    // Tratamento de falha de login / autenticação
    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<Map<String, String>> handleAuthException(AuthenticationException ex) {
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(Map.of("error", "Credenciais inválidas"));
    }

    @ExceptionHandler(ExpiredJwtException.class)
    public ResponseEntity<Map<String, Object>> handleExpiredJwt(ExpiredJwtException ex, HttpServletRequest request) {
        Map<String, Object> body = new HashMap<>();
        body.put("error", "Token expirado");
        body.put("message", "Seu token expirou, faça login novamente.");
        body.put("path", request.getServletPath());
        return new ResponseEntity<>(body, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler({SignatureException.class, MalformedJwtException.class})
    public ResponseEntity<Map<String, Object>> handleInvalidJwt(Exception ex, HttpServletRequest request) {
        Map<String, Object> body = new HashMap<>();
        body.put("error", "Token inválido");
        body.put("message", "O token fornecido é inválido ou mal formado.");
        body.put("path", request.getServletPath());
        return new ResponseEntity<>(body, HttpStatus.UNAUTHORIZED);
    }

    // Tratamento genérico para RuntimeException
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Map<String, String>> handleRuntimeException(RuntimeException ex) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(Map.of("error", ex.getMessage()));
    }
}

