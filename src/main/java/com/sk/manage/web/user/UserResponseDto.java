package com.sk.manage.web.user;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class UserResponseDto {

    private Integer sno;
    private String name;
    private String dutyStep;
    private String enterDate;
    private String retireDate;
    
    @Builder
    public UserResponseDto(Integer sno, String name, String dutyStep,
    		String enterDate, String retireDate) {
    	this.sno = sno;
    	this.name = name;
    	this.dutyStep = dutyStep;
    	this.enterDate = enterDate;
    	this.retireDate = retireDate;
    }
}