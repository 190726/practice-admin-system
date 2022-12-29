package com.sk.manage.web.system;

import java.util.List;

import com.sk.manage.domain.system.System;
import com.sk.manage.domain.system.SystemRepository;
import com.sk.manage.domain.system.SystemUser;
import com.sk.manage.domain.user.User;
import com.sk.manage.domain.user.UserRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sk.manage.service.system.SystemService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RestController
public class SystemApiController {

	private final SystemService systemService;
	
	@GetMapping("/systems")
	public List<SystemResponseDto> findAll(){
		return systemService.findAll();
	}
	
	@PostMapping("/system/save")
	public Long save(@RequestBody SystemRequestDto requestDto) {
		log.debug("saving system data is {}", requestDto);
		return systemService.save(requestDto);
	}
}