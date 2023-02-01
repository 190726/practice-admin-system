package com.sk.manage.domain.issue;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.sk.manage.domain.BaseTimeEntity;

import lombok.NoArgsConstructor;

@NoArgsConstructor
@Table(name = "ISSUES")
@Entity
public class Issue extends BaseTimeEntity{
	
	@Id @GeneratedValue
	@Column(name = "ISSUE_ID")
	private Long id;
	
	@Column(name = "ISSUE_TITLE")
	private String title;
	
	@Lob
	@Column(name = "ISSUE_CONTENT")
	private String content;
	
	@ManyToOne
	@JoinColumn(name = "TAG_ID")
	private Tag tag;
	
	@ElementCollection(fetch = FetchType.LAZY)
	@CollectionTable(name = "TIME_LINES",
			         joinColumns = @JoinColumn(name="ISSUE_ID"))
	private List<TimeLine> timeLines = new ArrayList<>();
	
	@ElementCollection(fetch = FetchType.LAZY)
	@CollectionTable(name = "ISSUE_ATTACH",
			         joinColumns = @JoinColumn(name="ISSUE_ID"))
	private List<Attach> attachs = new ArrayList<>();
	
	public Issue(String title, String content) {
		this.title = title;
		this.content = content;
	}

	public void addAttach(Attach attach) {
		attachs.add(attach);
	}
	
	public void addTimeLine(TimeLine timeLine) {
		timeLines.add(timeLine);
	}

	public void setTag(Tag tag) {
		this.tag = tag;
	}

	public Long getId() {
		return this.id;
	}
	
	public String getTitle() {
		return this.title;
	}
	
	public String getContent() {
		return this.content;
	}
	
	public Tag getTag() {
		return this.tag;
	}
	
	public void updateContent(String content) {
		this.content = content;
	}
}