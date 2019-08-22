package com.hst.shrp.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hst.shrp.model.exception.GeneralServiceException;

import java.io.IOException;

/**
 * @author dlgusrb0808@gmail.com
 */
public class JsonUtils {

	private static final ObjectMapper OBJECT_MAPPER;

	static {
		OBJECT_MAPPER = new ObjectMapper();
	}

	public static String asJson(Object targetObject) {
		try {
			return OBJECT_MAPPER.writeValueAsString(targetObject);
		} catch (JsonProcessingException e) {
			throw new GeneralServiceException(String.format("Cannot write json for provided value %s", targetObject));
		}
	}

	public static <T> T fromJson(String source, Class<T> type) {
		try {
			return OBJECT_MAPPER.readValue(source, type);
		} catch (IOException e) {
			throw new GeneralServiceException(String.format("Cannot parse json string %s to %s", source, type.getName()));
		}
	}

}
