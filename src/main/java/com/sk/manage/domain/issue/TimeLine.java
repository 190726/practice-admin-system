package com.sk.manage.domain.issue;

import javax.persistence.Embeddable;
import javax.persistence.Table;

import com.sk.manage.domain.BaseTimeEntity;

import lombok.Builder;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Table(name = "TIME_LINES")
@Embeddable
public class TimeLine{
	
	private String content;
	
	public String content() {
		return this.content;
	}
	
	public TimeLine(String content) {
		this.content = content;
	}
	
}