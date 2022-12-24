package com.sk.manage.domain.system;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SystemRepository extends JpaRepository<System, Long>{

	List<System> findByNameContains(String string);

}
