package com.sk.manage.web.system;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/system")
public class SystemWebController {
	
	@GetMapping("/save")
	public String saveForm() {
		return "system/save";
	}
}