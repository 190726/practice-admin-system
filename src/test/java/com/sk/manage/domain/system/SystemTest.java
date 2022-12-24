package com.sk.manage.domain.system;

import java.time.LocalDate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import lombok.extern.slf4j.Slf4j;

@Slf4j
class SystemTest {

	@Test
	@DisplayName("System객체생성오류")
	void test() {
		log.debug("--------------");
		Assertions.assertThrows(IllegalArgumentException.class, ()->{
			new System("", LocalDate.now());
		});
	}
}
