package com.sk.manage.service.issue;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sk.manage.domain.issue.Issue;
import com.sk.manage.domain.issue.IssueRepository;
import com.sk.manage.domain.issue.Tag;
import com.sk.manage.domain.issue.TagRepository;
import com.sk.manage.web.common.dto.PageResponseDto;
import com.sk.manage.web.issue.IssueDetailDto;
import com.sk.manage.web.issue.IssueResponseDto;
import com.sk.manage.web.issue.IssueSaveRequestDto;
import com.sk.manage.web.issue.IssueUpdateDto;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class IssueService {
	
	private final IssueRepository issueRepository;
	private final TagRepository tagRepository;
	
	//0. 이슈조회
	public IssueDetailDto issueFindById(Long issueId) {
		Issue issue = issueRepository.findById(issueId).orElseThrow(IllegalStateException::new);
		
		return IssueDetailDto.builder()
							.issueId(issue.getId())
							.title(issue.getTitle())
							.content(issue.getContent())
							.tag(issue.getTag().getName())
							.create(issue.getCreateDate())
							.update(issue.getModifiedDate())
							.build();
	}
	
	//1.이슈저장
	@Transactional
	public void issueSave(IssueSaveRequestDto requestDto) {
		String tagName = requestDto.getTagName();
		Tag tag = tagRepository.findByName(tagName).orElseGet(()->tagRepository.save(new Tag(tagName)));
		Issue issue = requestDto.toEntity();
		issue.setTag(tag);
		issueRepository.save(issue);
	}
	
	//2.이슈갱신
	public void issueUpdate() {
		
	}
	
	//3.이슈목록
	//TODO : 페이징으로 전환
	public List<IssueResponseDto> issueList(Pageable pageable, PageResponseDto pageResponseDto) {
		
		Page<Issue> findAll = issueRepository.findAll(pageable);
		
		PageResponseDto page = PageResponseDto.builder()
				.pageNumber(findAll.getPageable().getPageNumber())
				.pageSize(findAll.getPageable().getPageSize())
				.totalPages(findAll.getTotalPages())
				.build();
		
		pageResponseDto.setPageInfo(page);
		
		List<IssueResponseDto> results = findAll.stream()
				.map(issue -> 
				IssueResponseDto.builder().tagName(issue.getTag().getName())
						.id(issue.getId())
						.content(issue.getContent())
						.title(issue.getTitle())
						.createDate(issue.getCreateDate())
						.build()).collect(Collectors.toList());
		return results;
	}
	
	//4.이슈상세
	public void issueDetail() {
		
	}

	//5.이슈수정
	@Transactional
	public void issueUpdate(IssueUpdateDto requestDto) {
		Issue issue = issueRepository.findById(requestDto.getIssueId()).orElseThrow(IllegalStateException::new);
		issue.updateContent(requestDto.getContent());
	}
}
