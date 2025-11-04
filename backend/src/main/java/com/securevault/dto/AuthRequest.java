package com.securevault.dto;

public class AuthRequest {

    private String username;
    private String password;

    // === GETTERS (These are the ones Java was looking for!) ===
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    // === SETTERS (Optional, but good to have) ===
    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}