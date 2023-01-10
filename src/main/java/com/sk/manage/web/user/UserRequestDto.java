package com.sk.manage.web.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class UserRequestDto {
	
	private String sno;
    private String name;
    private String dutyStep;
    private String enterDate;

}
