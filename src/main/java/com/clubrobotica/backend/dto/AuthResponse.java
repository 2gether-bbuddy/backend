package com.clubrobotica.backend.dto;

public class AuthResponse {
    private String token;
    private String role; // <-- Agregamos el rol
    private String name; // <-- Agregamos el nombre (útil para mostrarlo en el perfil)

    // Constructor vacío
    public AuthResponse() {}

    // Constructor con parámetros
    public AuthResponse(String token, String role, String name) {
        this.token = token;
        this.role = role;
        this.name = name;
    }

    // Getters y Setters
    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
}