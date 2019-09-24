package com.hst.shrp.model.api.analysis;

import java.util.List;

/**
 * @author dlgusrb0808@gmail.com
 */
public class SimulationAnalysisListResponse {
	private List<SimulationAnalysisResponse> dataset;

	public List<SimulationAnalysisResponse> getDataset() {
		return dataset;
	}

	public static SimulationAnalysisListResponse of(List<SimulationAnalysisResponse> dataset) {
		SimulationAnalysisListResponse response = new SimulationAnalysisListResponse();
		response.dataset = dataset;
		return response;
	}
}
