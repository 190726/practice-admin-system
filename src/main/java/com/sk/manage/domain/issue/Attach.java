package com.sk.manage.domain.issue;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Builder;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Table(name = "ISSUE_ATTACH")
@Embeddable
public class Attach {
	
	@Column(name = "FILE_NAME")
	private String fileName;
	
	@Column(name = "FILE_PATH")
	private String filePath;
	
	@Builder
	public Attach(String fileName, String filePath) {
		this.fileName = fileName;
		this.filePath = filePath;
	}
}