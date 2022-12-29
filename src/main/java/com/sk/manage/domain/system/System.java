package com.sk.manage.domain.system;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import com.sk.manage.domain.BaseTimeEntity;
import com.sk.manage.domain.user.User;
import lombok.ToString;
import org.springframework.util.StringUtils;

import lombok.NoArgsConstructor;

@Entity
@ToString
@NoArgsConstructor
public class System extends BaseTimeEntity {

	@Id @GeneratedValue
	@Column(name = "system_id")
	private Long id;
	
	private String name;
	
	private LocalDate openDate;

	@OneToMany(mappedBy = "system",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<SystemUser> systemUsers = new ArrayList<>();

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

	public List<SystemUser> getSystemUsers(){
		return systemUsers;
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

	public System enrolledSystemUser(User user){
		//CascadeType.ALL 이어야, System_user 가 반영됨.
		systemUsers.add(SystemUser.createSystemUser(this, user));
		return this;
	}

	private void validateName(String name) {
		if(StringUtils.isEmpty(name)) throw new IllegalArgumentException("System name is Empty!");
	}

	@Override
	public String toString() {
		return "System [id=" + id + ", name=" + name + ", openDate=" + openDate + "]";
	}
	
}