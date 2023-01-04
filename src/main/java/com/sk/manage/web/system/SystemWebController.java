package com.sk.manage.web.system;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sk.manage.service.system.SystemService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/system")
public class SystemWebController {
	
	private final SystemService systemService;
	
	@GetMapping("/save")
	public String saveForm() {
		return "system/save";
	}
	
	@GetMapping("/list")
	public String list(Model model) {
		List<SystemResponseDto> systems = systemService.findAll();
		model.addAttribute("systems", systems);
		return "system/list";
	}
	
	@GetMapping("/detail/{sysId}")
	public String detail(@PathVariable Long sysId, Model model) {
		log.info("system detail, system id is {}", sysId);
		model.addAttribute("detailDto", systemService.systemDetailDto(sysId));
		return "system/detail";
	}
}