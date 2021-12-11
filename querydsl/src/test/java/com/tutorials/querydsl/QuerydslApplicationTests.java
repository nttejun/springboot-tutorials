package com.tutorials.querydsl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.tutorials.querydsl.entity.Customer;
import com.tutorials.querydsl.entity.QCustomer;
import javax.persistence.EntityManager;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
@Commit
class QuerydslApplicationTests {

	@Autowired
	EntityManager entityManager;

	@Test
	public void 프로젝트_QUERY_DSL_설정_성공_테스트() {
		Customer customer = new Customer();
		entityManager.persist(customer);

		JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);
		QCustomer qCustomer = new QCustomer("hello");

		Customer selectedCustomer = queryFactory
				.selectFrom(qCustomer)
				.fetchOne();

		Assertions.assertThat(selectedCustomer).isEqualTo(customer);
	}

	@Test
	public void 프로젝트_롬복_설정_성공_테스트(){
		Customer customer = new Customer();
		entityManager.persist(customer);

		JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);
		QCustomer qCustomer = new QCustomer("hi");

		Customer selectedCustomer = queryFactory
				.selectFrom(qCustomer)
				.orderBy(qCustomer.id.desc())
				.fetchFirst();

		Assertions.assertThat(selectedCustomer.getId()).isEqualTo(customer.getId());
	}

	@Test
	void contextLoads() {
	}
}
