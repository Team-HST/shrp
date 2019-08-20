package com.hst.shrp.model.api.analysis;

import com.hst.shrp.model.entity.EntitySimulationData;
import com.hst.shrp.model.entity.EntitySimulationDataAggregation;
import static com.hst.shrp.utils.Functionals.*;

import java.util.List;

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
		response.labels = transform(aggregations, aggr -> String.format("%d교차로", aggr.getCrpNo()));
		response.values = transform(aggregations, EntitySimulationDataAggregation::getAggrVal);
		return response;
	}

	public static SimulationAnalysisResponse of(SimulationAnalysisRequest request, List<EntitySimulationData> simulationData) {
		SimulationAnalysisResponse response = new SimulationAnalysisResponse();
		response.simulationNumber = request.getSimulationNumber();
		response.indicatorCode = request.getIndicator();
		response.labels = transform(simulationData, EntitySimulationData::getDrcNm);
		response.values = transform(simulationData, EntitySimulationData::getVal);
		return response;
	}

}
