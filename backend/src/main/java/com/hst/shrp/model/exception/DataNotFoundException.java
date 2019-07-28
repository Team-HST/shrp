package com.hst.shrp.model.exception;

import org.springframework.http.HttpStatus;

/**
 * @author dlgusrb0808@gmail.com
 */
public class DataNotFoundException extends RuntimeException {
	public DataNotFoundException() {
		super(HttpStatus.BAD_REQUEST.getReasonPhrase());
	}

	public DataNotFoundException(String message) {
		super(message);
	}

	public DataNotFoundException(String message, Exception exception) {
		super(message, exception);
	}
}
