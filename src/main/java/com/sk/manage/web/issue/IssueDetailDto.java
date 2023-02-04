package com.sk.manage.web.issue;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class IssueDetailDto {
	
	private Long issueId;
	private String title;
	private String content;
	private String tag;
	private LocalDateTime create;
	private LocalDateTime update;
	
	@Builder
	public IssueDetailDto(Long issueId,String title,
			String content, String tag, LocalDateTime create, LocalDateTime update) {
		this.issueId = issueId;
		this.title = title;
		this.content = content;
		this.tag = tag;
		this.create = create;
		this.update = update;
	}
	
	public String replaceNewLineContent() {
		return content.replaceAll("(\n|\r|\r\n|\n\r)", "<br/>");
	}
}