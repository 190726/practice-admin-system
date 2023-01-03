package com.sk.manage.web.system;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.time.LocalDate;
import java.util.Collections;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.sk.manage.domain.system.SystemRepository;
import com.sk.manage.domain.user.UserRepository;
import com.sk.manage.service.system.SystemService;


@MockBean(value = JpaMetamodelMappingContext.class)
@WebMvcTest(value = SystemWebController.class)
class SystemWebControllerTest {

	@Autowired
	private MockMvc mvc;
	
	@MockBean
	private SystemService systemService;
	
	@MockBean
	private SystemRepository systemRepository;
	
	@MockBean
	private UserRepository userRepository;
	
	@Test
	void test() throws Exception {
		SystemResponseDto dto1 = new SystemResponseDto(1L, "mobile", LocalDate.now(), "desc");
		BDDMockito.given(systemService.findAll()).willReturn(Collections.singletonList(dto1));
		
		mvc.perform(MockMvcRequestBuilders.get("/system/list"))
		.andExpect(status().isOk())
		.andExpect(view().name("system/list"))
		.andExpect(model().attributeExists("systems"))
		.andExpect(model().attribute("systems", Matchers.contains(dto1)));
	}
}