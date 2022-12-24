package com.sk.manage;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.sk.manage.domain.system.SystemRepository;
import com.sk.manage.domain.system.System;

@SpringBootApplication
public class App {
	
	@Autowired
	SystemRepository systemRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}
	
	@Bean
	public ApplicationRunner myApplicationRunner() {
		
		//테이블생성(schema.sql) 스크립트보다 빨리 실행됨. 테이블이 미생성되어 있는 경우, 오류
		return args -> {
			System system1 = new System("a0001", LocalDate.now());
			System system2 = new System("b0001", LocalDate.now());
			System system3 = new System("c0001", LocalDate.now());
			systemRepository.save(system1);
			systemRepository.save(system2);
			systemRepository.save(system3);
		};
	}
}
