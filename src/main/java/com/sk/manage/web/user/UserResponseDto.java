package com.sk.manage.web.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDto {

    private String sno;
    private String name;
    private String dutyStep;
    private String enterDate;
    private String retireDate;
}
