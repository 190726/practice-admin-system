package com.sk.manage.domain.issue;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag, Long>{
	Optional<Tag> findByName(String name);
}