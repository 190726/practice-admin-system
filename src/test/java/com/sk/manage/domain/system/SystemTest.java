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
import org.springframework.test.context.ActiveProfiles;

@Slf4j
@ActiveProfiles("test")
@SpringBootTest
class SystemTest {

	@Autowired
	SystemRepository systemRepository;

	@Test
	@DisplayName("save and find by ID")
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
	@DisplayName("find system by name")
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
