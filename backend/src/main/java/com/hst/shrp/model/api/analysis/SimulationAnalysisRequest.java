package com.hst.shrp.model.api.analysis;

import com.hst.shrp.model.type.AnalysisType;

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

	public static SimulationAnalysisRequest of(int simulationNumber, String indicator, String crossRoadNumber) {
		return of(simulationNumber, 0, indicator, crossRoadNumber);
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
