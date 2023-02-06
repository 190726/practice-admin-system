package com.sk.manage.domain.issue;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Transactional;

@DataJpaTest
@Transactional
class IssueTest {
	
	@Autowired
	private IssueRepository repository;
	
	@Autowired
	private TagRepository tagRepository;

	@Test
	@DisplayName("issue basic test")
	void test() {
		Issue issue = new Issue("title", "content");
		Issue saveIssue = repository.save(issue);
		
		Tag tag = new Tag("spring");
		Tag saveTag = tagRepository.save(tag);
		
		saveIssue.setTag(saveTag);
		
		Issue findIssue = repository.findById(saveIssue.getId()).orElseThrow(IllegalStateException::new);
		
		Assertions.assertThat(findIssue.getTitle()).isEqualTo(issue.getTitle());
		Assertions.assertThat(findIssue.getTag().getName()).isEqualTo(tag.getName());
	}
}