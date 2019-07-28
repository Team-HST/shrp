package com.hst.shrp.model.api;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.springframework.http.HttpStatus;

/***
 * @author dlgusrb0808@gmail.com
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"header", "body"})
public class ApiResponse<T> {
	private ApiHeader header;
	private T body;

	public ApiResponse(ApiHeader header, T body) {
		this.header = header;
		this.body = body;
	}

	public ApiResponse(int code, String message) {
		this.header = ApiHeader.of(code, message);
	}

	public ApiResponse(T body) {
		this.header = ApiHeader.of(HttpStatus.OK);
		this.body = body;
	}

	public ApiHeader getHeader() {
		return header;
	}

	public T getBody() {
		return body;
	}
}
