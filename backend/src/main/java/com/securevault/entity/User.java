package com.securevault.entity;

import jakarta.persistence.*;  // <-- VERY IMPORTANT (for @Entity, @Id, etc.)

@Entity  // Tells Spring: "This is a database table"
@Table(name = "users")  // Optional: name of table in DB
public class User {

    @Id  // This is the primary key (like ID card number)
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Auto-increment ID
    private Long id;

    @Column(unique = true, nullable = false)  // Username must be unique
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String salt;

    // ==================== GETTERS & SETTERS ====================

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }
}