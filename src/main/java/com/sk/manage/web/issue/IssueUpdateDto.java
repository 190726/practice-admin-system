package com.sk.manage.web.issue;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@NoArgsConstructor
public class IssueUpdateDto {
	
	private Long issueId;
	private String content;

}
