package com.prueba.farma.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String token = request.getHeader("Authorization");

        if (token == null || !token.startsWith("Bearer ")) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setHeader("WWW-Authenticate", "Bearer realm=\"Access to the site\", error=\"invalid_token\", error_description=\"Token not provided\"");
            return;
        }

        try {
            // Eliminar "Bearer " del token
            token = token.substring(7);
            String username = jwtUtil.getUsernameFromToken(token);

            if (username == null || !jwtUtil.validateToken(token, username)) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.setHeader("WWW-Authenticate", "Bearer realm=\"Access to the site\", error=\"invalid_token\", error_description=\"Invalid or expired token\"");
                return;
            }
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setHeader("WWW-Authenticate", "Bearer realm=\"Access to the site\", error=\"invalid_token\", error_description=\"Error processing token\"");
            return;
        }

        filterChain.doFilter(request, response);  // Continúa con el siguiente filtro si el token es válido
    }
}
