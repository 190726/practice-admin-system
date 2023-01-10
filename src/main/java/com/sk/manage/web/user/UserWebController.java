package com.sk.manage.web.user;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sk.manage.service.user.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/web/user")
public class UserWebController {
	
	private final UserService userService;
	
	@GetMapping("/new")
	public String newUser() {
		return "user/new";
	}
	
	@GetMapping("/list")
	public String list(Model model) {
		model.addAttribute("users", userService.allUsers());
		return "user/list";
	}

}
