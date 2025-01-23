package com.prueba.farma.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prueba.farma.security.JwtUtil;
import com.prueba.farma.util.AuthRequest;


@RestController
@RequestMapping("/auth")
public class AuthController {

    private final JwtUtil jwtUtil;

    public AuthController( JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    // Usuario y contraseña quemados en el código
    private final String HARDCODED_USERNAME = "admin";
    private final String HARDCODED_PASSWORD = "password123";

    @PostMapping("/login")
    public ResponseEntity<String> authenticate(@RequestBody AuthRequest authRequest) {
        // Verificar credenciales quemadas
        if (HARDCODED_USERNAME.equals(authRequest.getUsername()) &&
            HARDCODED_PASSWORD.equals(authRequest.getPassword())) {
            String token = jwtUtil.generateToken(authRequest.getUsername());
            return ResponseEntity.ok(token);
        } else {
            return ResponseEntity.status(401).body("Credenciales inválidas");
        }
    }

    /*@PostMapping("/login")
    public ResponseEntity<String> authenticate(@RequestBody AuthRequest authRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        String token = jwtUtil.generateToken(((User) authentication.getPrincipal()).getUsername());
        return ResponseEntity.ok(token);
    }*/

}
