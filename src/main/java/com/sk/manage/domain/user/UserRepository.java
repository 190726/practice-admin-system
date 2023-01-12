package com.sk.manage.domain.user;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {


	Optional<User> findBySno(Integer i);
}
