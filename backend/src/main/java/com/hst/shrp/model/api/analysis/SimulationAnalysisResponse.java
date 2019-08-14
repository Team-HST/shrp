package com.hst.shrp.model.api.analysis;

import com.hst.shrp.model.entity.EntityIndicator;
import com.hst.shrp.model.entity.EntityIndicatorAggregation;

import java.util.List;
import java.util.stream.Collectors;

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
														   List<EntityIndicatorAggregation> aggregations) {
		SimulationAnalysisResponse response = new SimulationAnalysisResponse();
		response.simulationNumber = request.getSimulationNumber();
		response.indicatorCode = request.getIndicator();
		response.labels = aggregations.stream().map(aggr -> String.format("%d교차로", aggr.getCrpNo())).collect(Collectors.toList());
		response.values = aggregations.stream().map(EntityIndicatorAggregation::getAggrVal).collect(Collectors.toList());
		return response;
	}

	public static SimulationAnalysisResponse of(SimulationAnalysisRequest request, List<EntityIndicator> indicators) {
		SimulationAnalysisResponse response = new SimulationAnalysisResponse();
		response.simulationNumber = request.getSimulationNumber();
		response.indicatorCode = request.getIndicator();
		response.labels = indicators.stream().map(EntityIndicator::getDrcCd).collect(Collectors.toList());
		response.values = indicators.stream().map(EntityIndicator::getVal).collect(Collectors.toList());
		return response;
	}

}
