package com.sk.manage;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.sk.manage.domain.system.SystemUser;
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

			String sno1 = "190726";
			String name1 = "이상국";
			DutyStep dutyStep1 = DutyStep.MANAGER;
			LocalDateTime enterDate1 = LocalDateTime.of(2022, 12, 22, 0,0,0);
			User user1 = User.builder().sno(sno1).name(name1).dutyStep(dutyStep1).enterDate(enterDate1).build();

			String sno2 = "190828";
			String name2 = "이윤아";
			DutyStep dutyStep2 = DutyStep.MANAGER;
			LocalDateTime enterDate2 = LocalDateTime.of(2022, 12, 22, 0,0,0);
			User user2 = User.builder().sno(sno2).name(name2).dutyStep(dutyStep2).enterDate(enterDate2).build();

			//when
			userRepository.save(user1);
			userRepository.save(user2);

			System system1 = new System("사이버창구", LocalDate.now());
			System system2 = new System("모바일영업지원", LocalDate.now());
			System system3 = new System("인시스", LocalDate.now());
			systemRepository.save(system1);
			systemRepository.save(system2);
			systemRepository.save(system3);
		};
	}
}
