package com.sk.manage.domain.system;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.springframework.util.StringUtils;

import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
public class System {

	@Id @GeneratedValue
	@Column
	private Long id;
	
	private String name;
	
	private LocalDate openDate;
	
	//private List<SystemUser> systemUsers = new ArrayList<>();
	
	public System(long id, String name, LocalDate openDate) {
		
		validateName(name);
		this.id = id;
		this.name = name;
		this.openDate = openDate;
	}
	
	public System(String name, LocalDate openDate) {
		
		validateName(name);
		this.name = name;
		this.openDate = openDate;
	}

	private void validateName(String name) {
		if(StringUtils.isEmpty(name)) throw new IllegalArgumentException("System name is Empty!");
	}

	public String getSystemName(){
		return name;
	}
	
	public Long getSystemId() {
		return id;
	}
	
	public LocalDate getSystemOpenDate() {
		return openDate;
	}

	@Override
	public String toString() {
		return "System [id=" + id + ", name=" + name + ", openDate=" + openDate + "]";
	}
	
}