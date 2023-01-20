package com.sk.manage.domain.issue;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class TagTest {
	
	@Autowired
	TagRepository repository;
	
	@Test
	@DisplayName("tag save")
	public void tagSave() {
		Tag tag = new Tag("spring");
		Tag saveTag = repository.save(tag);
		Tag findTag = repository.findById(saveTag.getId()).orElseThrow(IllegalStateException::new);
		Assertions.assertThat(findTag.getName()).isEqualTo(tag.getName());
	}
	
	@Test
	@DisplayName("tag name overap save")
	public void overapSave() {
		Tag tag = new Tag("spring");
		repository.save(tag);
		
		Tag tag2 = new Tag("spring2");
		
		Tag findTag = repository.findByName("spring2").orElseGet(() -> repository.save(tag2));
		
		Assertions.assertThat(findTag.getName()).isEqualTo(tag2.getName());
		
		
	}

}
