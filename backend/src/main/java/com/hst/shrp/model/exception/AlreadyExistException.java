package com.hst.shrp.model.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author dlgusrb0808@gmail.com
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class AlreadyExistException extends RuntimeException {
	public AlreadyExistException() {
		super(HttpStatus.BAD_REQUEST.getReasonPhrase());
	}

	public AlreadyExistException(String message) {
		super(message);
	}

	public AlreadyExistException(String message, Exception exception) {
		super(message, exception);
	}
}
