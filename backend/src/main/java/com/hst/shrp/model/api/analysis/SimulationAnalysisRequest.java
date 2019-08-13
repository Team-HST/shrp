package com.hst.shrp.model.api.analysis;

/**
 * @author dlgusrb0808@gmail.com
 */
public class SimulationAnalysisRequest {
	private static final String ALL_CROSSROAD_ANALYZE_FLAG = "all";
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
		return ALL_CROSSROAD_ANALYZE_FLAG.equals(this.crossRoadNumber);
	}

	public static SimulationAnalysisRequest of(int simulationNumber, String indicator, String crossRoadNumber) {
		SimulationAnalysisRequest request = new SimulationAnalysisRequest();
		request.simulationNumber = simulationNumber;
		request.indicator = indicator;
		request.crossRoadNumber = crossRoadNumber;
		return request;
	}
}
