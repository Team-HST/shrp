package com.hst.shrp.model.api;

import io.swagger.annotations.ApiModelProperty;
import org.springframework.http.HttpStatus;

/***
 * @author dlgusrb0808@gmail.com
 */
public class ApiHeader {
	@ApiModelProperty(position = 1, example = "응답코드")
	private int code;
	@ApiModelProperty(position = 2, example = "응답 메시지")
	private String message;

	private ApiHeader () {
	}

	public int getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}

	public static ApiHeader of(HttpStatus status) {
		return of(status.value(), status.getReasonPhrase());
	}

	public static ApiHeader of(int code, String message) {
		ApiHeader header = new ApiHeader();
		header.code = code;
		header.message = message;
		return header;
	}

}
