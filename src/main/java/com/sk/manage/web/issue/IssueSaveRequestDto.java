package com.sk.manage.web.issue;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

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
	
	@NotBlank(message = "태그는 필수 값입니다.")
	private String tagName;
	
	@NotBlank(message = "제목은 필수 값입니다.")
	private String title;
	
	@NotBlank(message = "내용은 필수 값입니다.")
	private String content;
	
	public Issue toEntity() {
		Issue issue = new Issue(this.title, this.content);
		return issue;
	}
}