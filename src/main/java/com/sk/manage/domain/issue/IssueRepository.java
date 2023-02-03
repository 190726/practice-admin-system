package com.sk.manage.domain.issue;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IssueRepository extends JpaRepository<Issue, Long>{
	
	@Query(value = "select p from Issue p inner join p.tag t where p.title like %:title% and p.content like %:content% and t.name like %:tagName%", 
			countQuery = "select count(p.id) from Issue p inner join p.tag t where p.title like %:title% and p.content like %:content% and t.name like %:tagName%")
	Page<Issue> findAllSearch(String title, String content,String tagName, Pageable pageable);

}
