package com.example.summarization.config;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.example.summarization.model.User;
import com.example.summarization.service.UserService;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
  @Autowired
  private UserService userService;
  @Override
  public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
    User user = userService.findByUsername(authentication.getName());
    if (user.isUsing2FA()) {
      response.sendRedirect("/2fa/");
    } else {
      response.sendRedirect("/index");
    }
  }
}
