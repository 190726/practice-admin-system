package com.sk.manage.web.system;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class SystemResponseDto {
	
	private Long id;
	private String name;
	private LocalDate openDate;

}