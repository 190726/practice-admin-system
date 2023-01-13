package com.sk.manage.web.user;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class UserRequestDto {
	
	@NotNull(message = "사번은 필수 값입니다.")
	@Range(min = 190000, max = 199999, message = "유효한 숫자값을 벗어났습니다.")
	private Integer sno;
	
	@NotBlank(message = "이름은 필수 값입니다.")
    private String name;
    private String dutyStep;
    
    private String enterDate;
}