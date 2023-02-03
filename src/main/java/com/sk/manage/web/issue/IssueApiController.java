package com.sk.manage.web.issue;

import java.util.stream.IntStream;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sk.manage.service.issue.IssueService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class IssueApiController {
	
	private final IssueService issueService;
	
	@PostMapping("/issue/save")
	public Long save(@RequestBody @Validated IssueSaveRequestDto requestDto) {
		issueService.issueSave(requestDto);
		return 1L;
	}
	
	@PostMapping("/issue/update")
	public Long update(@RequestBody IssueUpdateDto requestDto) {
		System.out.println(requestDto);
		issueService.issueUpdate(requestDto);
		return 1L;
	}
	
	@GetMapping("/issue/sample")
	public Long saveTest() {
		
		IntStream.range(1, 99).forEach(i -> {
			IssueSaveRequestDto dto = new IssueSaveRequestDto("spring" + i,"title" + i,"content" + i);
			issueService.issueSave(dto);
		});
		return 1L;
	}
}