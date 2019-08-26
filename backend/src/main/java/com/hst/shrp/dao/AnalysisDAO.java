package com.hst.shrp.dao;

import com.hst.shrp.model.entity.EntitySimulationDirectionData;
import com.hst.shrp.model.entity.EntitySimulationAggregationData;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author dudgns0612@gmail.com
 */
@Repository
public interface AnalysisDAO {
	List<EntitySimulationAggregationData> findAverageByIndicator(String indicator, Integer simulationNumber);
	List<EntitySimulationDirectionData> findAllByIndicator(String indicator, Integer simulationNumber, Integer crossRoadNumber);
}

