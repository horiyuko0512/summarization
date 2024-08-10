package com.example.summarization.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.summarization.model.UserDto;
import com.example.summarization.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequestMapping("/")
public class UserController{

  @Autowired
  private UserService userService;

  public String redirectToIndex() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    if (authentication != null && authentication.isAuthenticated()) {
      return "redirect:/index";
    }
    return "redirect:/login";
  }

  @GetMapping("/login")
  public String login(){
    return "login";
  }

  @GetMapping("/index")
  public String index(){
    return "index";
  }

  @GetMapping("/register")
  public String registerForm(Model model){
    model.addAttribute("userDto", new UserDto());
    return "register";
  }

  @PostMapping("/register")
  public String register(@ModelAttribute UserDto userDto){
    if (userService.findByUsername(userDto.getUsername()) != null) {
      return "register";
    }
    userService.save(userDto);
    return "redirect:/login";
  }

}