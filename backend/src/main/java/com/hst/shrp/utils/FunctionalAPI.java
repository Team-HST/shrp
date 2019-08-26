package com.hst.shrp.utils;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author dlgusrb0808@gmail.com
 */
public class FunctionalAPI {
	/***
	 *
	 * @param <T>
	 */
	public static class StreamProcessContext<T> {
		private Collection<T> source;

		StreamProcessContext(Collection<T> source) {
			this.source = source;
		}

		/***
		 * open stream for collection
		 * @return stream
		 */
		public Stream<T> open() {
			return source.stream();
		}

		/***
		 * Convert list's element
		 * @param transformFunction converting function
		 * @return converted function
		 */
		public <R> List<R> toList(Function<T, R> transformFunction) {
			return open().map(transformFunction).collect(Collectors.toList());
		}

		/***
		 * Convert list to map
		 * @param keyProvideFunction map's key provide function
		 * @return conveted map
		 */
		public <K> Map<K, T> toMap(Function<T, K> keyProvideFunction) {
			return toMap(keyProvideFunction, Function.identity());
		}

		/***
		 * Convert list to map
		 * @param keyProvideFunction map's key provide function
		 * @param valueProvideFunction map's value provide function
		 * @return conveted map
		 */
		public <K, V> Map<K, V> toMap(Function<T, K> keyProvideFunction, Function<T, V> valueProvideFunction) {
			return open().collect(Collectors.toMap(keyProvideFunction, valueProvideFunction));
		}
	}

	/***
	 * create stream process context from Collection
	 * @param source instance of Collection
	 * @param <T> rowType of Collection
	 * @return stream process context
	 */
	public static <T> StreamProcessContext<T> from(Collection<T> source) {
		return new StreamProcessContext<>(source);
	}

	public static void main(String[] args) {
		List<String> test = Arrays.asList("a", "b", "c", "d", "e");

		test = from(test).toList(String::toUpperCase);

		System.out.println(test);
	}


}
