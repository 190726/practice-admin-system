package com.sk.manage.web.issue;

import com.sk.manage.domain.issue.Issue;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class IssueSaveRequestDto {
	
	private String tagName;
	private String title;
	private String content;
	
	public Issue toEntity() {
		Issue issue = new Issue(this.title, this.content);
		return issue;
	}
}