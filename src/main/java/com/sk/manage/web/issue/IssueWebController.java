package com.sk.manage.web.issue;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sk.manage.service.issue.IssueService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
@RequestMapping("/web")
public class IssueWebController {
	
	private final IssueService issueService;
	
	@GetMapping("/issue/list")
	public String issueList(Model model, Pageable pageable){
		model.addAttribute("issues", issueService.issueList());
		return "issue/list";
	}
}