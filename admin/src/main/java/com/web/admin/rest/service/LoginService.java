package com.web.admin.rest.service;

import org.springframework.stereotype.Service;

@Service
public class LoginService {

  private String USER = "user";

  public boolean isValidUser(String user) {
    if (USER == user) {
      return true;
    }
    return false;
  }
}
