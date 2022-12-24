package com.sk.manage.web.system;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

import com.sk.manage.domain.system.System;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SystemRequestDto {
	
	private String name;
	private String openDate;
	
	public System toEntity() {
		return new System(name, LocalDate.parse(openDate));
	}
}
