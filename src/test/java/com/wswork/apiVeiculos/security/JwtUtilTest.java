package com.wswork.apiVeiculos.security;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JwtUtilTest {

    private final JwtUtil jwtUtil = new JwtUtil();

    @Test
    void deveGerarETestarToken() {
        String token = jwtUtil.generateToken("admin");

        assertNotNull(token);
        assertEquals("admin", jwtUtil.extractUsername(token));
        assertTrue(jwtUtil.validateToken(token));
    }
}
