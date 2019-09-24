package com.hst.shrp.model.api.analysis;

/**
 * @author dlgusrb0808@gmail.com
 */
public class SimulationSampleDataGenerateRequest {
	private String indicator;
	private Integer simulationNumber;

	public static SimulationSampleDataGenerateRequest of(String simulationNumber, String indicator) {
		SimulationSampleDataGenerateRequest request = new SimulationSampleDataGenerateRequest();
		request.indicator = indicator;
		request.simulationNumber = Integer.valueOf(simulationNumber);
		return request;
	}

	public String getIndicator() {
		return indicator;
	}

	public Integer getSimulationNumber() {
		return simulationNumber;
	}

}
