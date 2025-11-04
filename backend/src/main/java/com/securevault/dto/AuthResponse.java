package com.securevault.dto;

public class AuthResponse {

    private String token;
    private String username;

    // Constructor (used in AuthService)
    public AuthResponse(String token, String username) {
        this.token = token;
        this.username = username;
    }

    // === GETTERS ===
    public String getToken() {
        return token;
    }

    public String getUsername() {
        return username;
    }

    // Optional: setters (not needed for response)
}