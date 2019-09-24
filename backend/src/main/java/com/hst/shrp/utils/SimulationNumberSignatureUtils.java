package com.hst.shrp.utils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author dlgusrb0808@gmail.com
 */
public class SimulationNumberSignatureUtils {

	private static final String SIMULATION_NUMBER_DIVIDER = "_";

	public static List<Integer> decode(String signature) {
		return Arrays.stream(signature.split(SIMULATION_NUMBER_DIVIDER)).map(Integer::parseInt).collect(Collectors.toList());
	}

	public static String encode(List<Integer> simulationNumbers) {
		return null;
	}

}
