package com.sk.manage.domain.issue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;

@DataJpaTest
class IssueTest {
	
	@Autowired
	private IssueRepository repository;

	@Test
	@DisplayName("issue basic test")
	void test() {
		Issue issue = new Issue(1L, "title", "content");
		repository.save(issue);
	}

}
