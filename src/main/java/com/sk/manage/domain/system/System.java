package com.sk.manage.domain.system;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.*;

import com.sk.manage.domain.BaseTimeEntity;
import com.sk.manage.domain.user.User;
import lombok.ToString;
import org.springframework.util.StringUtils;

import lombok.NoArgsConstructor;

@Table(name = "SYSTEMS")
@Entity
@NoArgsConstructor
public class System extends BaseTimeEntity {

	@Id @GeneratedValue
	@Column(name = "system_id")
	private Long id;
	
	@Column(nullable = false, length = 120)
	private String name;
	
	private LocalDate openDate;
	
	@Column(name = "description")
	private String desc;

	@OneToMany(mappedBy = "system",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<SystemUser> systemUsers = new ArrayList<>();
	
	@ElementCollection(fetch = FetchType.LAZY)
	@CollectionTable(name = "SYSTEM_DB",
			         joinColumns = @JoinColumn(name="SYSTEM_DB_ID"))
	private List<SystemDB> systemDbs = new ArrayList<>();
	
	@Embedded
	private SystemDetail systemDetail;

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
	
	public List<SystemDB> getSystemDbs(){
		return systemDbs;
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
	
	public String getDesc() {
		return desc;
	}
	
	public void updateDesc(String desc) {
		this.desc = desc;
	}

	public System enrolledSystemUser(SystemUser systemUser){
		//CascadeType.ALL 이어야, System_user 가 반영됨.
		systemUsers.add(systemUser);
		return this;
	}
	
	public System addSystemDbInfo(SystemDB systemDb) {
		systemDbs.add(systemDb);
		return this;
	}
	
	public void registSystemDetail(SystemDetail systemDetail) {
		this.systemDetail = systemDetail;
	}
	
	public Optional<SystemDetail> getSystemDetail() {
		return Optional.ofNullable(systemDetail);
		//return this.systemDetail;
	}

	private void validateName(String name) {
		if(StringUtils.isEmpty(name)) throw new IllegalArgumentException("System name is Empty!");
	}
}