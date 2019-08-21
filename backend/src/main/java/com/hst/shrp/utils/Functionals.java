package com.hst.shrp.utils;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author dlgusrb0808@gmail.com
 */
public class Functionals {

	/***
	 * Convert list's element
	 * @param source the source
	 * @param transformFunction converting function
	 * @return converted function
	 */
	public static <T, R> List<R> transform(List<T> source, Function<T, R> transformFunction) {
		return source.stream().map(transformFunction).collect(Collectors.toList());
	}

	/***
	 * Convert list to map
	 * @param source convert target list
	 * @param keyProvideFunction map's key provide function
	 * @return conveted map
	 */
	public static <T, K> Map<K, T> asMap(List<T> source, Function<T, K> keyProvideFunction) {
		return source.stream().collect(Collectors.toMap(keyProvideFunction, Function.identity()));
	}

	/***
	 * Convert list to map
	 * @param source convert target list
	 * @param keyProvideFunction map's key provide function
	 * @param valueProvideFunction map's value provide function
	 * @return conveted map
	 */
	public static <T, K, V> Map<K, V> asMap(List<T> source, Function<T, K> keyProvideFunction, Function<T, V> valueProvideFunction) {
		return source.stream().collect(Collectors.toMap(keyProvideFunction, valueProvideFunction));
	}

}
