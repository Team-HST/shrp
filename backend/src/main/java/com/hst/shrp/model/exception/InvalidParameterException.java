package com.hst.shrp.model.exception;

import org.springframework.http.HttpStatus;

/**
 * @author dlgusrb0808@gmail.com
 */
public class InvalidParameterException extends RuntimeException {
	public InvalidParameterException() {
		super(HttpStatus.BAD_REQUEST.getReasonPhrase());
	}

	public InvalidParameterException(String message) {
		super(message);
	}

	public InvalidParameterException(String message, Exception exception) {
		super(message, exception);
	}
}
