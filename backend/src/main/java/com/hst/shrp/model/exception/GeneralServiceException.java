package com.hst.shrp.model.exception;

import org.springframework.core.NestedRuntimeException;

/**
 * @author dlgusrb0808@gmail.com
 */
public class GeneralServiceException extends NestedRuntimeException {

	public GeneralServiceException(String message, Exception causeException) {
		super(message, causeException);
	}
}
