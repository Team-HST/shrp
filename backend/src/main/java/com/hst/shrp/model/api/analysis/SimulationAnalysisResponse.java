package com.hst.shrp.model.api.analysis;

import com.hst.shrp.model.api.code.CommonCodesResponse;
import com.hst.shrp.model.entity.EntitySimulationData;
import com.hst.shrp.model.entity.EntitySimulationDataAggregation;

import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toList;

/**
 * @author dlgusrb0808@gmail.com
 */
public class SimulationAnalysisResponse {
	private int simulationNumber;
	private String indicatorCode;
	private List<String> labels;
	private List<Double> values;

	public int getSimulationNumber() {
		return simulationNumber;
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

	public static SimulationAnalysisResponse ofAggregation(SimulationAnalysisRequest request,
														   List<EntitySimulationDataAggregation> aggregations) {
		SimulationAnalysisResponse response = new SimulationAnalysisResponse();
		response.simulationNumber = request.getSimulationNumber();
		response.indicatorCode = request.getIndicator();
		response.labels = aggregations.stream().map(aggr -> String.format("%d교차로", aggr.getCrpNo())).collect(toList());
		response.values = aggregations.stream().map(EntitySimulationDataAggregation::getAggrVal).collect(toList());
		return response;
	}

	public static SimulationAnalysisResponse of(SimulationAnalysisRequest request, List<EntitySimulationData> simulationData) {
		SimulationAnalysisResponse response = new SimulationAnalysisResponse();
		response.simulationNumber = request.getSimulationNumber();
		response.indicatorCode = request.getIndicator();
		response.labels = simulationData.stream().map(EntitySimulationData::getDrcNm).collect(toList());
		response.values = simulationData.stream().map(EntitySimulationData::getVal).collect(toList());
		return response;
	}

}
