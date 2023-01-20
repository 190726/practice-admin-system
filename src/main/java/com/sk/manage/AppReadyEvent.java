package com.sk.manage;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import com.sk.manage.domain.issue.Issue;
import com.sk.manage.domain.issue.IssueRepository;
import com.sk.manage.domain.issue.Tag;
import com.sk.manage.domain.issue.TagRepository;
import com.sk.manage.domain.issue.TimeLine;
import com.sk.manage.domain.system.DBType;
import com.sk.manage.domain.system.System;
import com.sk.manage.domain.system.SystemDB;
import com.sk.manage.domain.system.SystemDetail;
import com.sk.manage.domain.system.SystemRepository;
import com.sk.manage.domain.system.SystemUser;
import com.sk.manage.domain.user.DutyStep;
import com.sk.manage.domain.user.User;
import com.sk.manage.domain.user.UserRepository;

@Configuration
public class AppReadyEvent {
	
	@Autowired
	SystemRepository systemRepository;

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	IssueRepository issueRepository;
	
	@Autowired
	TagRepository tagRepository;
	
	@Autowired
	Environment environment;
	
	@EventListener(ApplicationReadyEvent.class)
	public void init() {
		String[] activeProfiles = environment.getActiveProfiles();
		boolean isTest = Arrays.stream(activeProfiles).anyMatch((profile)->profile.equals("test"));
		if(!isTest) dataInit();
	}

	public void dataInit() {
		//테이블생성(schema.sql) 스크립트보다 빨리 실행됨. 테이블이 미생성되어 있는 경우, 오류

		Integer sno1 = 190726;
		String name1 = "이상국";
		DutyStep dutyStep1 = DutyStep.MANAGER;
		LocalDateTime enterDate1 = LocalDateTime.of(2016, 03, 07, 0,0,0);
		User user1 = User.builder().sno(sno1).name(name1).dutyStep(dutyStep1).enterDate(enterDate1).build();

		Integer sno2 = 191764;
		String name2 = "신제원";
		DutyStep dutyStep2 = DutyStep.MANAGER;
		LocalDateTime enterDate2 = LocalDateTime.of(2022, 04, 22, 0,0,0);
		User user2 = User.builder().sno(sno2).name(name2).dutyStep(dutyStep2).enterDate(enterDate2).build();

		//when
		userRepository.save(user1);
		userRepository.save(user2);

		System system1 = new System("사이버창구", LocalDate.of(2016, 8, 15));
		system1.enrolledSystemUser(SystemUser.createSystemUser(system1, user1));
		system1.enrolledSystemUser(SystemUser.createSystemUser(system1, user2));
		system1.updateDesc("홈페이지 대고객 금융거래 서비스");
		SystemDB system1Dbdev = new SystemDB();
		system1Dbdev.setDbtype(DBType.DEV);
		system1Dbdev.setUrl("jdbc:oracle:thin:@172.20.14.206:1521:HOMEDEV");
		system1Dbdev.setUserId("dongbuweb");
		system1Dbdev.setUserPwd("dongbuweb");
		system1.addSystemDbInfo(system1Dbdev);
		
		SystemDetail systemDetail1 = new SystemDetail("http://systemDetail.home", null, "기타");
		system1.registSystemDetail(systemDetail1);
		
		SystemDB system1DbReal = new SystemDB();
		system1DbReal.setDbtype(DBType.REAL);
		system1DbReal.setUrl("jdbc:oracle:thin:@172.18.1.171:1521:HOMEDB");
		system1DbReal.setUserId("dongbuweb");
		system1DbReal.setUserPwd("dongbu1234");
		system1.addSystemDbInfo(system1DbReal);
		
		System system2 = new System("모바일창구(앱)", LocalDate.of(2013, 8, 1));
		system2.updateDesc("모바일 대고객 금융거래 서비스 앱");
		System system3 = new System("모바일창구(웹)", LocalDate.of(2019, 9, 1));
		system3.updateDesc("모바일 대고객 금융거래 서비스 웹");
		systemRepository.save(system1);
		systemRepository.save(system2);
		systemRepository.save(system3);
		
		Issue issue = new Issue(1L, "title", "content");
		Tag tag = new Tag("spring");
		tagRepository.save(tag);
		
		issue.setTag(tag);
		
		TimeLine timeLine1 = new TimeLine("timeline1");
		issue.addTimeLine(timeLine1);
		
		TimeLine timeLine2 = new TimeLine("timeline2");
		issue.addTimeLine(timeLine2);
		
		issueRepository.save(issue);
	}

}
