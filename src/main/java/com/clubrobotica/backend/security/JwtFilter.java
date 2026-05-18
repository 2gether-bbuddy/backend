package com.clubrobotica.backend.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserDetailsService userDetailsService;

    // =================================================================
    // ¡ESTE ES EL ESCUDO ANTI-BLOQUEOS PARA LOS WEBSOCKETS!
    // =================================================================
    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        String path = request.getRequestURI();
        // Si la petición va para abrir el túnel del chat, el filtro se hace a un lado
        return path.startsWith("/chat");
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        // 1. Obtenemos el encabezado que React Native nos mandó
        final String authorizationHeader = request.getHeader("Authorization");

        String controlNumber = null;
        String jwt = null;

        // 2. Revisamos si trae el pase VIP (empieza con "Bearer ")
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            jwt = authorizationHeader.substring(7); // Quitamos la palabra "Bearer " para dejar solo el token
            try {
                // Sacamos la matrícula del token usando tu clase JwtUtil
                controlNumber = jwtUtil.extractUsername(jwt);
            } catch (Exception e) {
                System.out.println("Error extrayendo o validando el token: " + e.getMessage());
            }
        }

        // 3. Si encontramos la matrícula y el usuario aún no está autenticado en este contexto
        if (controlNumber != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            // Buscamos al usuario en la base de datos (por su matrícula)
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(controlNumber);

            // Verificamos si el token todavía es válido y pertenece a ese usuario
            if (jwtUtil.validateToken(jwt)) {

                // 4. ¡El token es válido! Le damos luz verde en Spring Security
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());

                usernamePasswordAuthenticationToken
                        .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                // Guardamos la autorización en el contexto de seguridad
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }

        // 5. Dejamos que la petición continúe su camino
        filterChain.doFilter(request, response);
    }
}