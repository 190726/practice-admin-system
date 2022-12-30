package com.sk.manage.service.system;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.sk.manage.domain.user.User;
import com.sk.manage.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sk.manage.domain.system.SystemRepository;
import com.sk.manage.web.system.SystemRequestDto;
import com.sk.manage.web.system.SystemResponseDto;
import com.sk.manage.domain.system.System;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
public class SystemService {

	private final SystemRepository systemRepository;
	private final UserRepository userRepository;
	
	public System findById(Long id) {
		return systemRepository
				.findById(id)
				.orElseThrow(()->new IllegalStateException("NotFound at systemId: " + id));
	}
	
	public Long save(SystemRequestDto requestDto) {
		return systemRepository.save(requestDto.toEntity()).getSystemId();
	}
	
	public void delete(Long systemId) {
		systemRepository.deleteById(systemId);
	}
	
	public List<SystemResponseDto> findAll(){
		List<System> systems = systemRepository.findAll();
		return systems.stream().map(sys -> mappedDto(sys)).collect(Collectors.toList());
	}

	public List<System> findByName(String name){
		return systemRepository.findByNameContains(name);
	}

	@Transactional
	public void enrolledSystemUser(Long systemId, String userId) {
		User user = userRepository.findById(userId).orElseThrow(() -> new IllegalStateException("no exist user, id:" + userId));
		System system = systemRepository.findById(systemId).orElseThrow(() -> new IllegalStateException("no exist system, id:" + systemId));
		System saveSystem = system.enrolledSystemUser(user);
		systemRepository.save(saveSystem);
	}

	private SystemResponseDto mappedDto(System system) {
		return new SystemResponseDto(system.getSystemId(), system.getSystemName(), system.getSystemOpenDate());
	}
}