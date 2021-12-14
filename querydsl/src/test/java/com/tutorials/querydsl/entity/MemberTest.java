package com.tutorials.querydsl.entity;

import java.util.List;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
@Commit
class MemberTest {

  @Autowired
  EntityManager entityManager;

  @Test
  public void testEntityManager(){
    Team teamA = new Team("TeamA");
    Team teamB = new Team("TeamB");
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

    entityManager.flush();
    entityManager.clear();

    List<Member> members = entityManager.createQuery("select m from Member m", Member.class).getResultList();

    for (Member member : members) {
      System.out.println("member : " + member );
      System.out.println("-> member.team.name : " + member.getTeam().getName() );
    }
  }

}