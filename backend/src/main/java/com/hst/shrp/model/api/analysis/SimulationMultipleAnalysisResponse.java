package com.hst.shrp.model.api.analysis;

import java.util.List;

/**
 * @author dlgusrb0808@gmail.com
 */
public class SimulationMultipleAnalysisResponse implements SimulationAnalysisResponse {
	private List<SimulationSingleAnalysisResponse> dataset;

	public List<SimulationSingleAnalysisResponse> getDataset() {
		return dataset;
	}

	public static SimulationMultipleAnalysisResponse of(List<SimulationSingleAnalysisResponse> dataset) {
		SimulationMultipleAnalysisResponse response = new SimulationMultipleAnalysisResponse();
		response.dataset = dataset;
		return response;
	}
}
