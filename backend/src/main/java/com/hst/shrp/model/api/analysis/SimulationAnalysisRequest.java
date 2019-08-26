package com.hst.shrp.model.api.analysis;

import com.hst.shrp.model.exception.InvalidParameterException;
import com.hst.shrp.model.type.AnalysisType;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author dlgusrb0808@gmail.com
 */
public class SimulationAnalysisRequest {
	private int simulationNumber;
	private int compareSimulationNumber;
	private String indicator;
	private String crossRoadNumber;

	public int getSimulationNumber() {
		return simulationNumber;
	}

	public String getIndicator() {
		return indicator;
	}

	public String getCrossRoadNumber() {
		return crossRoadNumber;
	}

	public int getCompareSimulationNumber() {
		return compareSimulationNumber;
	}

	public boolean isAllCrossRoadAnalyze() {
		return AnalysisType.getAnalysisType(this.crossRoadNumber) == AnalysisType.ALL;
	}

	public boolean isCompareAnalyzeRequest() {
		return this.compareSimulationNumber != 0;
	}

	public static SimulationAnalysisRequest of(String simulationNumbers, String indicator, String crossRoadNumber) {
		List<Integer> numbers =
				Arrays.stream(simulationNumbers.split("_")).map(Integer::parseInt).collect(Collectors.toList());
		if (numbers.size() == 1) {
			return of(numbers.get(0), 0, indicator, crossRoadNumber);
		} else if(numbers.size() == 2) {
			return of(numbers.get(0), numbers.get(1), indicator, crossRoadNumber);
		} else {
			throw new InvalidParameterException(String.format("Invalid simulation number. %s", simulationNumbers));
		}
	}

	public static SimulationAnalysisRequest of(int simulationNumber, int compareSimulationNumber, String indicator, String crossRoadNumber) {
		SimulationAnalysisRequest request = new SimulationAnalysisRequest();
		request.simulationNumber = simulationNumber;
		request.compareSimulationNumber = compareSimulationNumber;
		request.indicator = indicator;
		request.crossRoadNumber = crossRoadNumber;
		return request;
	}
}
