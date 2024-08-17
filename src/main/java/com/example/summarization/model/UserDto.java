package com.example.summarization.model;

import jakarta.validation.constraints.NotEmpty;

public class UserDto {
    @NotEmpty
    private String username;
    @NotEmpty
    private String password;
    @NotEmpty
    private boolean using2FA;
    @NotEmpty
    private String secret;

    public UserDto() {}
    public UserDto(String username, String password) {
        this.username = username;
        this.password = password;
    }
    public UserDto(String username, String password, boolean using2FA, String secret) {
        this.username = username;
        this.password = password;
        this.using2FA = using2FA;
        this.secret = secret;
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
