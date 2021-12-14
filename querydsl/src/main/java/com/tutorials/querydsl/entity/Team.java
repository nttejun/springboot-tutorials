package com.tutorials.querydsl.entity;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Team {

  @Id @GeneratedValue
  private Long id;
  private String name;

  @OneToMany(mappedBy = "team")
  private List<Member> members = new ArrayList<>();

  protected Team(String name) {
    this.name = name;
  }
}
