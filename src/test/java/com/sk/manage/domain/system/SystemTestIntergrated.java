package com.sk.manage.domain.system;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.sk.manage.service.system.SystemService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
class SystemTestIntergrated {
	
	@Autowired
	SystemService systemService;
	
	@Autowired
	SystemRepository systemRepository;

	@Test
	@DisplayName("저장및조회 테스트")
	void saveAndFindById(){
		
		System system = new System("a0001", LocalDate.now());
		System saveSystem = systemRepository.save(system);
		
		log.debug("saveSystem is {}", saveSystem);
		
		Optional<System> findSaveSystem = systemRepository.findById(saveSystem.getSystemId());
		System result = findSaveSystem
				.orElseThrow(()->new IllegalStateException(saveSystem.getSystemId() + " is not found!"));
		
		Assertions.assertThat(result.getSystemName()).isEqualTo("a0001");
	}
	
	@Test
	@DisplayName("이름으로 조회하기")
	void saveAndFindByName() {
		System system = new System("a0001", LocalDate.now());
		System saveSystem = systemRepository.save(system);
		
		log.debug("saveSystem is {}", saveSystem);
		List<System> findByNameContains = systemRepository.findByNameContains("a00");
		long count = findByNameContains.stream()
							.filter(sys -> sys.getSystemName().contains("a00"))
							.count();
		Assertions.assertThat(count).isGreaterThan(0);
	}

}
