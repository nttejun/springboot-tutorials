package com.tutorials.querydsl;

import static com.tutorials.querydsl.entity.QMember.member;
import static com.tutorials.querydsl.entity.QTeam.team;

import com.querydsl.core.QueryResults;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.tutorials.querydsl.entity.Member;
import com.tutorials.querydsl.entity.QMember;
import com.tutorials.querydsl.entity.Team;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
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
    Team teamE = new Team("teamE");
    entityManager.persist(teamA);
    entityManager.persist(teamB);
    entityManager.persist(teamE);

    Member memberA = new Member("memberA", 12, teamA);
    Member memberB = new Member("memberB", 12, teamA);

    Member memberC = new Member("memberC", 14, teamB);
    Member memberD = new Member(null, 12, teamB);
    Member memberE = new Member("teamE", 12, teamE);
    entityManager.persist(memberA);
    entityManager.persist(memberB);
    entityManager.persist(memberC);
    entityManager.persist(memberD);
    entityManager.persist(memberE);
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

  @Test
  public void aggregation() {
    JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);
    List<Tuple> result = queryFactory
        .select(
            member.count(),
            member.age.sum(),
            member.age.avg(),
            member.age.max(),
            member.age.min()
        )
        .from(member)
        .fetch();

    Tuple tuple = result.get(0);
    Assertions.assertThat(tuple.get(member.age.max())).isEqualTo(14);
    Assertions.assertThat(tuple.get(member.count())).isEqualTo(4);
    Assertions.assertThat(tuple.get(member.age.min())).isEqualTo(12);
  }

  @Test
  public void group() {
    JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);
    List<Tuple> result = queryFactory
        .select(team.name, member.age.avg())
        .from(member)
        .join(member.team, team)
        .groupBy(team.name)
        .fetch();

    Tuple teamA = result.get(0);
    Tuple teamB = result.get(1);

    Assertions.assertThat(teamA.get(team.name)).isEqualTo("teamA");
    Assertions.assertThat(teamA.get(member.age.avg())).isEqualTo(12);

    Assertions.assertThat(teamB.get(team.name)).isEqualTo("teamB");
    Assertions.assertThat(teamB.get(member.age.avg())).isEqualTo(13);
  }

  @Test
  public void join() {
    JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);
    List<Member> result = queryFactory
        .selectFrom(member)
        .join(member.team, team)
        .where(team.name.eq("teamA"))
        .fetch();

    Assertions.assertThat(result)
        .extracting("username")
        .containsExactly("memberA", "memberB");
  }

  @Test
  public void leftJoin() {
    JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);
    entityManager.persist(new Member("teamB", 15));
    List<Member> result = queryFactory
        .selectFrom(member)
        .leftJoin(member.team, team)
        .where(team.name.eq("teamA"))
        .fetch();

    Assertions.assertThat(result)
        .extracting("username")
        .containsExactly("memberA", "memberB");
  }

  /***
   * 세타 조인 : 연관관계 없는 상태에서 조인 (모든 회원, 모든 팀을 조회해서 where 조건에 맞는 값을 찾습니다)
   * 회원 이름이 팀 이름과 같은 회원을 조회
   * 세타 조인은 외부(outer) 조인 불가능 > join on 사용하면 외부(outer) 조인 가능
   */
  @Test
  public void thetaJoin() {
    JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);
    entityManager.persist(new Member("teamA"));
    entityManager.persist(new Member("teamB"));
    List<Member> result = queryFactory
        .select(member)
        .from(member, team)
        .where(member.username.eq(team.name))
        .fetch();

    Assertions.assertThat(result)
        .extracting("username")
        .containsExactly("teamA", "teamB");
  }

  /***
   * JOIN ON (JPA 2.1 부터 지원)
   * 조인 대상 필터링
   * 연관관계 없는 엔티티 외부(outer) 조인
   * 조인 대상을 할 때 외부조인이 아니라
   * 내부조인(inner join)을 사용하면 where 절에서 필터링 하는 것과 기능이 동일하므로 익숙한 where 절을 사용하면 된다
   * 회원과 팀을 조인하면서, 팀 이름이 teamA인 팀만 조인, 회원은 모두 조회한다
   */
  @Test
  public void joinOn() {
    JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);
    List<Tuple> result = queryFactory
        .select(member, team)
        .from(member)
        .leftJoin(member.team, team).on(team.name.eq("teamA"))
        .fetch();

    StringBuffer resultPrint = new StringBuffer();
    for (Tuple tuple : result) {
      resultPrint.append("tuple : " + tuple+"\n");
    }

    String expectedResult =
        "tuple : [Member(id=4, username=memberA, age=12), Team(id=1, name=teamA)]\n"
            + "tuple : [Member(id=5, username=memberB, age=12), Team(id=1, name=teamA)]\n"
            + "tuple : [Member(id=6, username=memberC, age=14), null]\n"
            + "tuple : [Member(id=7, username=null, age=12), null]\n"
            + "tuple : [Member(id=8, username=teamE, age=12), null]\n"
            + "";

    Assertions.assertThat(resultPrint.toString()).isEqualTo(expectedResult);
  }

  /***
   * 연관관계 없는 엔티티 외부(outer) 조인
   * 회원 이름과 팀 이름이 같은 대상을 외부 조인
   */
  @Test
  public void joinOnNoRelation() {
    JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);
    List<Tuple> result = queryFactory
        .select(member, team)
        .from(member)
        .leftJoin(team).on(member.username.eq(team.name))
        .fetch();

    StringBuffer resultPrint = new StringBuffer();

    for (Tuple tuple : result) {
      resultPrint.append("tuple : " + tuple +"\n");
    }

    String expected = "tuple : [Member(id=4, username=memberA, age=12), null]\n"
        + "tuple : [Member(id=5, username=memberB, age=12), null]\n"
        + "tuple : [Member(id=6, username=memberC, age=14), null]\n"
        + "tuple : [Member(id=7, username=null, age=12), null]\n"
        + "tuple : [Member(id=8, username=teamE, age=12), Team(id=3, name=teamE)]\n"
        + "";

    Assertions.assertThat(resultPrint.toString()).isEqualTo(expected);
  }

  @PersistenceUnit
  EntityManagerFactory emf;

  @Test
  public void fetchJoinNo() {
    JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);
    entityManager.flush();
    entityManager.clear();

    Member findMember = queryFactory
        .selectFrom(member)
        .where(member.username.eq("memberA"))
        .fetchOne();

    boolean loaded = emf.getPersistenceUnitUtil().isLoaded(findMember.getTeam());
    Assertions.assertThat(loaded).as("패치 조인 미적용").isFalse();
  }

  /***
   * fetch 조인을 사용하면 내부 조인을 사용할 수 있다
   * inner join
   *   team team1_
   *     on member0_.team_id=team1_.team_id
   */
  @Test
  public void fetchJoinUse() {
    JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);
    entityManager.flush();
    entityManager.clear();

    Member findMember = queryFactory
        .selectFrom(member)
        .join(member.team, team).fetchJoin()
        .where(member.username.eq("memberA"))
        .fetchOne();

    boolean loaded = emf.getPersistenceUnitUtil().isLoaded(findMember.getTeam());
    Assertions.assertThat(loaded).as("패치 조인 적용").isTrue();
  }
}
