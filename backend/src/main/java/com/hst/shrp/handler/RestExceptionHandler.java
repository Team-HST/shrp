package com.hst.shrp.handler;

import com.hst.shrp.model.api.ApiHeader;
import com.hst.shrp.model.api.ApiResponse;
import com.hst.shrp.model.exception.DataNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

@RestControllerAdvice
public class RestExceptionHandler {

	private static final Logger logger = LoggerFactory.getLogger(RestExceptionHandler.class);

	@ExceptionHandler(DataNotFoundException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ApiResponse<?> handleDataNotFound(DataNotFoundException e) {
		logger.warn("Handle DataNotFoundException. cause: {}", e.getMessage());
		return new ApiResponse<>(HttpStatus.BAD_REQUEST.value(), e.getMessage());
	}

	@ExceptionHandler(NoHandlerFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ApiResponse<?> handleNoHandlerFound(NoHandlerFoundException e) {
		return new ApiResponse<>(HttpStatus.NOT_FOUND.value(), e.getMessage());
	}

}
