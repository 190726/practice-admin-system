package com.sk.manage;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.sk.manage.domain.user.DutyStep;
import com.sk.manage.domain.user.User;
import com.sk.manage.domain.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.sk.manage.domain.system.SystemRepository;
import com.sk.manage.domain.system.System;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class App {

	@Autowired
	SystemRepository systemRepository;

	@Autowired
	UserRepository userRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}
	
	@Bean
	public ApplicationRunner myApplicationRunner() {

		
		//테이블생성(schema.sql) 스크립트보다 빨리 실행됨. 테이블이 미생성되어 있는 경우, 오류
		return args -> {

			String sno = "190726";
			String name = "이상국";
			DutyStep dutyStep = DutyStep.MANAGER;
			LocalDateTime enterDate = LocalDateTime.of(2022, 12, 22, 0,0,0);
			User user = User.builder().sno(sno).name(name).dutyStep(dutyStep).enterDate(enterDate).build();

			//when
			userRepository.save(user);

			System system1 = new System("a0001", LocalDate.now());
			System system2 = new System("b0001", LocalDate.now());
			System system3 = new System("c0001", LocalDate.now());
			systemRepository.save(system1);
			systemRepository.save(system2);
			systemRepository.save(system3);
		};
	}
}
