package com.hst.shrp.model.api.analysis;

import com.hst.shrp.model.api.simulation.SimulationHistoriesResponse.SimulationHistory;
import com.hst.shrp.model.entity.EntitySimulationDirectionData;
import com.hst.shrp.model.entity.EntitySimulationAggregationData;

import java.util.List;

import static com.hst.shrp.utils.FunctionalAPI.*;

/**
 * @author dlgusrb0808@gmail.com
 */
public class SimulationSingleAnalysisResponse extends SimulationAnalysisResponse {
	private int simulationNumber;
	private String fileName;
	private String indicatorName;
	private String indicatorCode;
	private List<String> labels;
	private List<Double> values;

	public int getSimulationNumber() {
		return simulationNumber;
	}

	public String getFileName() {
		return fileName;
	}

	public String getIndicatorName() {
		return indicatorName;
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
																 List<EntitySimulationAggregationData> aggregations,
																 String indicatorName) {
		return of (
			history, request,
			with(aggregations).toList(row -> row.getCrpNo().toString()),
			with(aggregations).toList(EntitySimulationAggregationData::getAggrVal),
			indicatorName
		);
	}

	public static SimulationSingleAnalysisResponse of(SimulationHistory history, SimulationAnalysisRequest request,
													  List<EntitySimulationDirectionData> simulationData, String indicatorName) {
		return of (
			history, request,
			with(simulationData).toList(EntitySimulationDirectionData::getDrcNm),
			with(simulationData).toList(EntitySimulationDirectionData::getVal),
			indicatorName
		);
	}

	public static SimulationSingleAnalysisResponse of(
			SimulationHistory history, SimulationAnalysisRequest request, List<String> labels
			, List<Double> values, String indicatorName
	) {
		SimulationSingleAnalysisResponse response = new SimulationSingleAnalysisResponse();
		response.simulationNumber = history.getSimulationNumber();
		response.fileName = history.getFileName();
		response.indicatorName = indicatorName;
		response.indicatorCode = request.getIndicator();
		response.labels = labels;
		response.values = values;
		return response;
	}
}
