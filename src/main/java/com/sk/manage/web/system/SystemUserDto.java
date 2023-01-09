package com.sk.manage.web.system;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class SystemUserDto {
	
	private Long systemId;
	private Long systemUserId;
	private String sno;
	private String name;
	
	public SystemUserDto(Long systemUserId, String sno, String name) {
		this.systemUserId = systemUserId;
		this.sno = sno;
		this.name = name;
	}

}
