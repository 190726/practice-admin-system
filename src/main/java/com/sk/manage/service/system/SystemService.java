package com.sk.manage.service.system;

import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sk.manage.domain.system.System;
import com.sk.manage.domain.system.SystemRepository;
import com.sk.manage.domain.system.SystemUser;
import com.sk.manage.domain.user.User;
import com.sk.manage.domain.user.UserRepository;
import com.sk.manage.web.system.SystemDBDto;
import com.sk.manage.web.system.SystemDetailDto;
import com.sk.manage.web.system.SystemRequestDto;
import com.sk.manage.web.system.SystemResponseDto;
import com.sk.manage.web.user.UserSimpleDto;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

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
	public System enrolledSystemUser(Long systemId, String userId) {
		User user = userRepository.findById(userId).orElseThrow(throwEx(userId));
		System system = systemRepository.findById(systemId).orElseThrow(throwEx(userId));
		SystemUser systemUser = SystemUser.createSystemUser(system, user);
		return system.enrolledSystemUser(systemUser);
	}
	
	@Transactional
	public SystemDetailDto systemDetailDto(Long systemId) {
		System findSystem = systemRepository.findDetailById(systemId);
		
		List<UserSimpleDto> users = findSystem.getSystemUsers().stream()
				.map(user -> {
					User u = user.getUser();
					return new UserSimpleDto(u.getSno(), u.getName());
				}).collect(Collectors.toList());
		
		List<SystemDBDto> dbs = findSystem.getSystemDbs().stream()
				.map(sdb -> {
					return new SystemDBDto(sdb.getDbtype().name()
						,sdb.getUrl()
						,sdb.getUserId()
						,sdb.getUserPwd());
		}).collect(Collectors.toList());
		
		SystemDetailDto resultDto = SystemDetailDto.builder()
				.id(systemId)
				.name(findSystem.getSystemName())
				.users(users)
				.dbs(dbs)
				.build();
		return resultDto;
	}

	private SystemResponseDto mappedDto(System system) {
		return new SystemResponseDto(system.getSystemId(),
				system.getSystemName(),
				system.getSystemOpenDate(),
				system.getDesc());
	}
	
	private Supplier<IllegalStateException> throwEx(String id){
		return () -> new IllegalStateException("no exist data, pk:" + id);
	}
}