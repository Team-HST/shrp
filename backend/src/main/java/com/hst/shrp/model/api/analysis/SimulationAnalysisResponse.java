package com.hst.shrp.model.api.analysis;

import com.hst.shrp.model.api.code.CommonCodesResponse.CommonCode;
import com.hst.shrp.model.entity.EntityIndicator;
import com.hst.shrp.model.entity.EntityIndicatorAggregation;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import static java.util.stream.Collectors.*;

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
		response.labels = aggregations.stream().map(aggr -> String.format("%d교차로", aggr.getCrpNo())).collect(toList());
		response.values = aggregations.stream().map(EntityIndicatorAggregation::getAggrVal).collect(toList());
		return response;
	}

	public static SimulationAnalysisResponse of(SimulationAnalysisRequest request, List<EntityIndicator> indicators,
												List<CommonCode> directionCodes) {
		Map<String, String> directionInfo = directionCodes.stream().collect(toMap(CommonCode::getSubCode,
				CommonCode::getSubName));
		SimulationAnalysisResponse response = new SimulationAnalysisResponse();
		response.simulationNumber = request.getSimulationNumber();
		response.indicatorCode = request.getIndicator();
		response.labels = indicators.stream().map(EntityIndicator::getDrcCd).map(directionInfo::get).collect(toList());
		response.values = indicators.stream().map(EntityIndicator::getVal).collect(toList());
		return response;
	}

}
