package com.allvibe.all_vibe.api.controllers;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.allvibe.all_vibe.infrastructure.abstract_services.IUserService;
import com.allvibe.all_vibe.infrastructure.helpers.EmailHelper;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping(path = "/sendEmail")
@AllArgsConstructor
public class EmailController {
  @Autowired
  private final EmailHelper email;

  @Autowired
  private final IUserService user;
  

  @GetMapping(path = "/{id}")
  public String sendEmail(@PathVariable String id) {
    String email = this.user.findByidEmail(id).getEmail();
    String username = this.user.findByidEmail(id).getUsername();
    this.email.sendMail(email, username, LocalDate.now());
    return null;
  }
}
