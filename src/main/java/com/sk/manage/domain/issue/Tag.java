package com.sk.manage.domain.issue;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.NoArgsConstructor;

@NoArgsConstructor
@Table(name = "TAGS")
@Entity
public class Tag {
	
	@Id @GeneratedValue
	@Column(name = "TAG_ID")
	private Long id;
	
	@Column(name = "TAG_NAME", unique = true)
	private String name;
	
	public Tag(String name) {
		this.name = name;
	}
}