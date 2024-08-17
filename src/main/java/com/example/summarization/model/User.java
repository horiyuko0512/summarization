package com.example.summarization.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity(name = "Users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String username;
    private String password;
    private boolean using2FA;
    private String secret;

    public User() {}
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
    public User(String username, String password, boolean using2FA, String secret) {
        this.username = username;
        this.password = password;
        this.using2FA = using2FA;
        this.secret = secret;
    }

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
    public boolean isUsing2FA() {
      return using2FA;
    }
    public void setUsing2FA(boolean using2FA) {
      this.using2FA = using2FA;
    }
    public String getSecret() {
      return secret;
    }
    public void setSecret(String secret) {
      this.secret = secret;
    }
}
