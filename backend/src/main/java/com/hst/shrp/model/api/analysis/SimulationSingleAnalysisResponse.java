package com.hst.shrp.model.api.analysis;

import com.hst.shrp.model.api.simulation.SimulationHistoriesResponse.SimulationHistory;
import com.hst.shrp.model.entity.EntitySimulationData;
import com.hst.shrp.model.entity.EntitySimulationDataAggregation;

import java.util.List;

import static com.hst.shrp.utils.Functionals.transform;

/**
 * @author dlgusrb0808@gmail.com
 */
public class SimulationSingleAnalysisResponse implements SimulationAnalysisResponse {
	private int simulationNumber;
	private String fileName;
	private String indicatorCode;
	private List<String> labels;
	private List<Double> values;

	public int getSimulationNumber() {
		return simulationNumber;
	}

	public String getFileName() {
		return fileName;
	}

	public String getIndicatorCode() {
		return indicatorCode;
	}

	public List<String> getLabels() {
		return labels;
	}

	public List<Double> getValues() {
		return values;
	}

	public static SimulationSingleAnalysisResponse ofAggregation(SimulationHistory history,
																 SimulationAnalysisRequest request,
																 List<EntitySimulationDataAggregation> aggregations) {
		return of (
			history, request,
			transform(aggregations, aggr -> String.format("%d교차로", aggr.getCrpNo())),
			transform(aggregations, EntitySimulationDataAggregation::getAggrVal)
		);
	}

	public static SimulationSingleAnalysisResponse of(SimulationHistory history, SimulationAnalysisRequest request,
													  List<EntitySimulationData> simulationData) {
		return of (
			history, request,
			transform(simulationData, EntitySimulationData::getDrcNm),
			transform(simulationData, EntitySimulationData::getVal)
		);
	}

	public static SimulationSingleAnalysisResponse of(
			SimulationHistory history, SimulationAnalysisRequest request, List<String> labels, List<Double> values
	) {
		SimulationSingleAnalysisResponse response = new SimulationSingleAnalysisResponse();
		response.simulationNumber = history.getSimulationNumber();
		response.fileName = history.getFileName();
		response.indicatorCode = request.getIndicator();
		response.labels = labels;
		response.values = values;
		return response;
	}
}
