package com.hst.shrp.handler;

import com.hst.shrp.model.api.ApiHeader;
import com.hst.shrp.model.api.ApiResponse;
import com.hst.shrp.model.exception.DataNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestExceptionHandler {

	@ExceptionHandler(DataNotFoundException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ApiResponse<?> handleDataNotFound(DataNotFoundException e) {
		return new ApiResponse<>(HttpStatus.BAD_REQUEST.value(), e.getMessage());
	}

}
