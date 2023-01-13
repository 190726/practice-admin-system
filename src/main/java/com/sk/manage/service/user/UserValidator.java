package com.sk.manage.service.user;

import java.util.Objects;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

import com.sk.manage.core.exception.UserException;
import com.sk.manage.domain.user.User;

@Component
public class UserValidator {
	
	public void validate(User user) {
		Objects.requireNonNull(user);
		
		String name = user.getName();
		if(!Pattern.matches("^[가-힣]*$", name))
			throw new UserException("이름은 한글만 들어갑니다.");
	}

}
