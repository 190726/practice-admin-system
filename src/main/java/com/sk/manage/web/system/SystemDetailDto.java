package com.sk.manage.web.system;

import java.util.List;

import com.sk.manage.web.user.UserResponseDto;
import com.sk.manage.web.user.UserSimpleDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class SystemDetailDto {
	
	private Long id;
	private String name;
	private List<SystemUserDto> users;
	private List<SystemDBDto> dbs;
	
	@Builder
	public SystemDetailDto(Long id, String name,
			List<SystemUserDto> users, List<SystemDBDto> dbs) {
		this.id = id;
		this.name = name;
		this.users = users;
		this.dbs = dbs;
	}
}
