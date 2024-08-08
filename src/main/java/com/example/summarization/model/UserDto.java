package com.example.summarization.model;

import jakarta.validation.constraints.NotEmpty;

public class UserDto {
    @NotEmpty
    private String username;
    @NotEmpty
    private String password;

    public UserDto() {}
    public UserDto(String username, String password) {
        this.username = username;
        this.password = password;
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
}
