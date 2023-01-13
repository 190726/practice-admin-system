package com.sk.manage.web.common.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.sk.manage.core.exception.UserException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class ExceptionController {
	
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(IllegalArgumentException.class)
	public ErrorResult illegalExceptionHandle(IllegalArgumentException e) {
		log.error("[illegalException handle] ", e);
		return new ErrorResult("BAD", e.getMessage());
	}
	
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(UserException.class)
	public ErrorResult userExceptionHandle(UserException e) {
		log.error("[UserException handle] ", e);
		return new ErrorResult("USER", e.getMessage());
	}
}
