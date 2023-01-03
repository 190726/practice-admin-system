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
public class SystemDBDto {
	
	private String dbType;
	private String url;
	private String userId;
	private String userPwd;

}
