package com.sk.manage.service.system;

import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sk.manage.domain.system.System;
import com.sk.manage.domain.system.SystemDetail;
import com.sk.manage.domain.system.SystemRepository;
import com.sk.manage.domain.system.SystemUser;
import com.sk.manage.domain.system.SystemUserRepository;
import com.sk.manage.domain.user.User;
import com.sk.manage.domain.user.UserRepository;
import com.sk.manage.web.system.SystemDBDto;
import com.sk.manage.web.system.SystemDetailDto;
import com.sk.manage.web.system.SystemRequestDto;
import com.sk.manage.web.system.SystemResponseDto;
import com.sk.manage.web.system.SystemUserDto;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class SystemService {

	private final SystemRepository systemRepository;
	private final SystemUserRepository systemUserRepository;
	private final UserRepository userRepository;
	
	public SystemResponseDto findById(Long id) {
		return systemRepository.findById(id).map(sys -> mappedDto(sys)).orElseThrow(throwEx(id));
		
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
	public System enrolledSystemUser(Long systemId, Integer userId) {
		User user = userRepository.findBySno(userId).orElseThrow(throwEx(userId));
		System system = systemRepository.findById(systemId).orElseThrow(throwEx(userId));
		SystemUser systemUser = SystemUser.createSystemUser(system, user);
		return system.enrolledSystemUser(systemUser);
	}
	
	@Transactional(readOnly = true)
	public SystemDetailDto systemDetailDto(Long systemId) {
		System findSystem = systemRepository.findDetailById(systemId);
		
		List<SystemUserDto> users = findSystem.getSystemUsers().stream()
				.map(user -> {
					User u = user.getUser();
					return new SystemUserDto(user.getId(), u.getSno(), u.getName());
				}).collect(Collectors.toList());
		
		List<SystemDBDto> dbs = findSystem.getSystemDbs().stream()
				.map(sdb -> {
					return new SystemDBDto(sdb.getDbtype().name()
						,sdb.getUrl()
						,sdb.getUserId()
						,sdb.getUserPwd());
		}).collect(Collectors.toList());
		
		SystemDetail systemDetail = findSystem.getSystemDetail().map(t->t).orElse(new SystemDetail("", ""));
		
		SystemDetailDto resultDto = SystemDetailDto.builder()
				.id(systemId)
				.name(findSystem.getSystemName())
				.users(users)
				.dbs(dbs)
				.urlInfo(systemDetail.getUrlInfo())
				.serverInfo(systemDetail.getServerInfo())
				.build();
		return resultDto;
	}
	
	@Transactional
	public void deleteSystemUser(Long systemUserId) {
		SystemUser deleteSystemUser = systemUserRepository.findById(systemUserId).orElseThrow(throwEx(systemUserId));
		systemUserRepository.delete(deleteSystemUser);
	}

	@Transactional
	public void detailSave(SystemDetailDto systemDetailDto) {
		System system = systemRepository.findById(systemDetailDto.getId())
				.orElseThrow(()->new IllegalArgumentException("no system id:" +systemDetailDto.getId() ));
		system.registSystemDetail(new SystemDetail(systemDetailDto.getUrlInfo(), systemDetailDto.getServerInfo()));
	}
	
	private Supplier<IllegalStateException> throwEx(Number id){
		return () -> new IllegalStateException("no exist data, pk:" + id);
	}
	
	private SystemResponseDto mappedDto(System system) {
		return new SystemResponseDto(system.getSystemId(),
				system.getSystemName(),
				system.getSystemOpenDate(),
				system.getDesc());
	}
}