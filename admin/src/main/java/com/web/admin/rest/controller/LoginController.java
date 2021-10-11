package com.web.admin.rest.controller;

import com.web.admin.rest.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

  @Autowired
  LoginService loginService;

  @Primary
  @GetMapping("/user")
  public String loginUser() {
    if (loginService.isValidUser("user")) {
      return "user";
    }
    return "login";
  }
}
