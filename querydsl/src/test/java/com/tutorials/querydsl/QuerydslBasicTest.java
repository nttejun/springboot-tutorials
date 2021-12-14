package com.tutorials.querydsl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.tutorials.querydsl.entity.Member;
import com.tutorials.querydsl.entity.QMember;
import com.tutorials.querydsl.entity.Team;
import javax.persistence.EntityManager;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
public class QuerydslBasicTest {

  @Autowired
  EntityManager entityManager;

  @BeforeEach
  public void before() {
    Team teamA = new Team("teamA");
    Team teamB = new Team("teamB");
    entityManager.persist(teamA);
    entityManager.persist(teamB);

    Member memberA = new Member("memberA", 12, teamA);
    Member memberB = new Member("memberB", 13, teamA);

    Member memberC = new Member("memberC", 14, teamB);
    Member memberD = new Member("memberD", 15, teamB);
    entityManager.persist(memberA);
    entityManager.persist(memberB);
    entityManager.persist(memberC);
    entityManager.persist(memberD);
  }

  @Test
  public void startJPQL() {
    Member findMember = entityManager.createQuery("select m from Member m where m.username = :username", Member.class)
        .setParameter("username", "memberA")
        .getSingleResult();

    org.assertj.core.api.Assertions.assertThat(findMember.getUsername()).isEqualTo("memberA");
  }

  @Test
  public void startQuesydsl() {
    JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);
    QMember m = new QMember("m");
    Member findMember = queryFactory
        .select(m)
        .from(m)
        .where(m.username.eq("memberA"))
        .fetchOne();

    Assertions.assertThat(findMember.getUsername()).isEqualTo("memberA");
  }
}
