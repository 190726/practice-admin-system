package com.sk.manage.domain.system;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SystemRepository extends JpaRepository<System, Long>{

	List<System> findByNameContains(String string);
	
	@Query(value = "select m from System m "
			+ "left join fetch m.systemDbs "
			+ "where m.id = :id")
	System findDetailById(Long id);
}