package com.hst.shrp.model.api.analysis;

import com.hst.shrp.model.type.AnalysisType;
import com.hst.shrp.utils.SimulationNumberSignatureUtils;

import java.util.List;

/**
 * @author dlgusrb0808@gmail.com
 */
public class SimulationAnalysisRequest {
	private String simulationNumberSignature;
	private List<Integer> simulationNumbers;
	private String indicator;
	private String crossRoadNumber;
	private String userNm;

	public String getSimulationNumberSignature() {
		return simulationNumberSignature;
	}

	public List<Integer> getSimulationNumbers() {
		return simulationNumbers;
	}

	public String getIndicator() {
		return indicator;
	}

	public String getCrossRoadNumber() {
		return crossRoadNumber;
	}

	public String getUserNm() {
		return userNm;
	}

	public boolean isAllCrossRoadAnalyze() {
		return AnalysisType.getAnalysisType(this.crossRoadNumber) == AnalysisType.ALL;
	}

	public static SimulationAnalysisRequest of(String simulationNumberSignature, String indicator, String userNm, String crossRoadNumber) {
		SimulationAnalysisRequest request = new SimulationAnalysisRequest();
		request.simulationNumberSignature = simulationNumberSignature;
		request.simulationNumbers = SimulationNumberSignatureUtils.decode(simulationNumberSignature);
		request.indicator = indicator;
		request.crossRoadNumber = crossRoadNumber;
		request.userNm = userNm;
		return request;
	}
}
