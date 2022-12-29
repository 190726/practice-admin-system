package com.sk.manage.domain.user;

import com.sk.manage.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@ToString
@NoArgsConstructor
@Entity
public class User extends BaseTimeEntity {

	@Id
	@Column(name = "user_id")
	private String sno;

	private String name;

	@Enumerated(EnumType.STRING)
	private DutyStep dutyStep;

	private LocalDateTime enterDate;

	private LocalDateTime retireDate;
	@Builder
	public User(String sno, String name, DutyStep dutyStep,
				LocalDateTime enterDate, LocalDateTime retireDate){
		this.sno = sno;
		this.name = name;
		this.dutyStep = dutyStep;
		this.enterDate = enterDate;
		this.retireDate = retireDate;
	}
}