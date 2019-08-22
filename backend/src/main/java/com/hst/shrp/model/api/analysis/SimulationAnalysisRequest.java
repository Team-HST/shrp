package com.hst.shrp.model.api.analysis;

import com.hst.shrp.model.type.AnalysisType;

/**
 * @author dlgusrb0808@gmail.com
 */
public class SimulationAnalysisRequest {
	private int simulationNumber;
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

	public boolean isAllCrossRoadAnalyze() {
		return AnalysisType.getAnalysisType(this.crossRoadNumber) == AnalysisType.ALL;
	}

	public static SimulationAnalysisRequest of(int simulationNumber, String indicator, String crossRoadNumber) {
		SimulationAnalysisRequest request = new SimulationAnalysisRequest();
		request.simulationNumber = simulationNumber;
		request.indicator = indicator;
		request.crossRoadNumber = crossRoadNumber;
		return request;
	}
}
