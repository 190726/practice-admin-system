package com.sk.manage.web.issue;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IssueResponseDto {
	
	private Long id;
	private String title;
	private String tagName;
	private String content;
	private LocalDateTime createDate;
	private LocalDateTime updateDate;
	
	@Builder
	public IssueResponseDto(Long id, String title, String tagName, String content, LocalDateTime createDate, LocalDateTime updateDate) {
		this.id = id;
		this.title = title;
		this.tagName = tagName;
		this.content = content;
		this.createDate = createDate;
		this.updateDate = updateDate;
	}
}