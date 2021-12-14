package com.tutorials.querydsl.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column
  private String name;

  @Column
  private String nickname;

  @Column
  private String password;

  @Column
  private String phoneNumber;

  @Column
  private String email;

  @Column
  private char gender;

  public Customer(String name, String nickname, String password, String phoneNumber, String email, char gender) {
    this.name = name;
    this.nickname = nickname;
    this.password = password;
    this.phoneNumber = phoneNumber;
    this.email = email;
    this.gender = gender;
  }
}
