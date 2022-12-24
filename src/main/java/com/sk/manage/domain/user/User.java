package com.sk.manage.domain.user;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class User {
	
	@Id
	private int sno;
	
	private String name;
	
	private String dutyStep;

}
