package com.tutorials.querydsl;

import static com.tutorials.querydsl.entity.QMember.member;

import com.querydsl.core.QueryResults;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.tutorials.querydsl.entity.Member;
import com.tutorials.querydsl.entity.QMember;
import com.tutorials.querydsl.entity.Team;
import java.util.List;
import javax.persistence.EntityManager;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
@Commit
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
    Member memberB = new Member("memberB", 12, teamA);

    Member memberC = new Member("memberC", 14, teamB);
    Member memberD = new Member(null, 12, teamB);
    entityManager.persist(memberA);
    entityManager.persist(memberB);
    entityManager.persist(memberC);
    entityManager.persist(memberD);
  }

  @Test
  public void startJPQL() {
    Member findMember = entityManager
        .createQuery("select m from Member m where m.username = :username", Member.class)
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

  @Test
  public void startQuesydslImportStaticQEntity() {
    JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);
    Member findMember = queryFactory
        .select(member)
        .from(member)
        .where(member.username.eq("memberA"))
        .fetchOne();

    Assertions.assertThat(findMember.getUsername()).isEqualTo("memberA");
  }

  @Test
  public void search() {
    JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);
    Member findMember = queryFactory
        .selectFrom(member)
        .where(member.username.eq("memberA")
            .and(member.age.eq(12)))
        .fetchOne();

    Assertions.assertThat(findMember.getUsername()).isEqualTo("memberA");
  }

  @Test
  public void searchAndParam() {
    JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);
    Member findMember = queryFactory
        .selectFrom(member)
        .where(
            member.username.eq("memberA"),
            member.age.eq(12)
        )
        .fetchOne();

    Assertions.assertThat(findMember.getUsername()).isEqualTo("memberA");
  }

  @Test
  public void resultFetch() {
    JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);
    QueryResults<Member> fetchResults = queryFactory
        .selectFrom(member)
        .fetchResults();

    fetchResults.getTotal();

    List<Member> content = fetchResults.getResults();

    Assertions.assertThat(fetchResults.getTotal() == 4);
    Assertions.assertThat(content.size() == 4);
  }

  @Test
  public void sort() {
    JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);
    List<Member> fetchOrderBy = queryFactory
        .selectFrom(member)
        .where(member.age.eq(12))
        .orderBy(member.age.desc(), member.username.asc().nullsLast())
        .fetch();

    Member memberA = fetchOrderBy.get(0);
    Member memberB = fetchOrderBy.get(1);
    Member memberD = fetchOrderBy.get(2);
    Assertions.assertThat(memberA.getUsername()).isEqualTo("memberA");
    Assertions.assertThat(memberB.getUsername()).isEqualTo("memberB");
    Assertions.assertThat(memberD.getUsername()).isEqualTo(null);
  }

  @Test
  public void paging() {
    JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);
    List<Member> memberList = queryFactory
        .selectFrom(member)
        .orderBy(member.username.desc())
        .offset(1)
        .limit(2)
        .fetch();

    QueryResults<Member> memberResults = queryFactory
        .selectFrom(member)
        .orderBy(member.username.desc())
        .offset(0)
        .limit(4)
        .fetchResults();

    Assertions.assertThat(memberList.size()).isEqualTo(2);
    Assertions.assertThat(memberResults.getTotal()).isEqualTo(4);
    Assertions.assertThat(memberResults.getResults().size()).isEqualTo(4);
    Assertions.assertThat(memberResults.getOffset()).isEqualTo(0);
    Assertions.assertThat(memberResults.getLimit()).isEqualTo(4);
  }
}
