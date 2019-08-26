package com.hst.shrp.model.api.analysis;

import com.hst.shrp.model.api.simulation.SimulationHistoriesResponse.SimulationHistory;
import com.hst.shrp.model.entity.EntitySimulationDirectionData;
import com.hst.shrp.model.entity.EntitySimulationAggregationData;

import java.util.List;

import static com.hst.shrp.utils.FunctionalAPI.*;

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
																 List<EntitySimulationAggregationData> aggregations) {
		return of (
			history, request,
			from(aggregations).toList(aggr -> String.format("%d교차로", aggr.getCrpNo())),
			from(aggregations).toList(EntitySimulationAggregationData::getAggrVal)
		);
	}

	public static SimulationSingleAnalysisResponse of(SimulationHistory history, SimulationAnalysisRequest request,
													  List<EntitySimulationDirectionData> simulationData) {
		return of (
			history, request,
			from(simulationData).toList(EntitySimulationDirectionData::getDrcNm),
			from(simulationData).toList(EntitySimulationDirectionData::getVal)
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
