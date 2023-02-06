package com.sk.manage.web.issue;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sk.manage.service.issue.IssueService;
import com.sk.manage.web.common.dto.PageResponseDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
@RequestMapping("/web")
public class IssueWebController {
	
	private final IssueService issueService;
	
	@GetMapping("/issue/new")
	public String register(Model model) {
		return "issue/new";
	}
	
	@GetMapping("/issue/list")
	public String list(Model model, 
			@ModelAttribute IssueSaveRequestDto searching,
			@PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.DESC) Pageable pageable){
		
		PageResponseDto pageResponseDto = new PageResponseDto();
		
		model.addAttribute("issues", issueService.issueList(pageable, pageResponseDto, searching));
		model.addAttribute("pages", pageResponseDto);
		model.addAttribute("issueSaveRequestDto", searching);
		model.addAttribute("tagList", issueService.tagList());
		
		System.out.println(pageResponseDto);
		
		return "issue/list";
	}
	
	@GetMapping("/issue/detail/{issueId}")
	public String detail(Model model, @PathVariable Long issueId) {
		IssueDetailDto detailDto = issueService.issueFindById(issueId);
		
		System.out.println(detailDto);
		
		model.addAttribute("issueDetailDto", detailDto);
		model.addAttribute("replaceBrContent", detailDto.replaceNewLineContent());
		
		return "issue/detail";
	}
	
}