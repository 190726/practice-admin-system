package com.sk.manage.domain.system;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Table(name = "SYSTEM_DB")
@Embeddable
public class SystemDB {
	
	@Enumerated(EnumType.STRING)
	@Column(name = "DB_TYPE")
	private DBType dbtype;
	
	private String url;
	
	@Column(name = "USER_ID")
	private String userId;
	
	@Column(name = "USER_PWD")
	private String userPwd;

}