package com.example.summarization.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.summarization.model.User;
import com.example.summarization.service.UserService;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import jakarta.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/2fa")
public class TwoFactorAuthenticationController {
  @Autowired
  private UserService userService;

  @GetMapping("/")
  public String twoFactorAuthentication(Model model) {
    return "2fa";
  }

  @PostMapping("/")
  public String verifyCode(int code, Model model, RedirectAttributes redirectAttributes) {
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    User user = userService.findByUsername(auth.getName());
    if (userService.verifyCode(user.getSecret(), code)) {
      return "redirect:/index";
    } else {
      redirectAttributes.addFlashAttribute("error", "Invalid 2FA code. Please try again.");
      return "redirect:/2fa/";
    }
  }

  @GetMapping("/enable")
  public String enableTwoFactorAuth(Model model) {
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    User user = userService.findByUsername(auth.getName());
    userService.enableUser2FA(user);
    model.addAttribute("secret", user.getSecret());
    return "enable2fa";
  }

  @GetMapping("/disable")
  public String disableTwoFactorAuth() {
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    User user = userService.findByUsername(auth.getName());
    userService.disableUser2FA(user);
    return "redirect:/index";
  }

  @GetMapping("/qrcode")
  public void generateQRCode(HttpServletResponse response) throws Exception {
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    User user = userService.findByUsername(auth.getName());
    String otpAuthURL = String.format(
      "otpauth://totp/%s:%s?secret=%s&issuer=%s",
     "Summarization", user.getUsername(), user.getSecret(), "Summarization Inc."
    );

    QRCodeWriter qrCodeWriter = new QRCodeWriter();
    BitMatrix bitMatrix = qrCodeWriter.encode(otpAuthURL, BarcodeFormat.QR_CODE, 200, 200);
    response.setContentType("image/png");
    MatrixToImageWriter.writeToStream(bitMatrix, "PNG", response.getOutputStream());
  }
}
