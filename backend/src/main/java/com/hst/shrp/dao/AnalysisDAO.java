package com.hst.shrp.dao;

import com.hst.shrp.model.entity.EntitySimulationData;
import com.hst.shrp.model.entity.EntitySimulationDataAggregation;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author dudgns0612@gmail.com
 */
@Repository
public interface AnalysisDAO {
	List<EntitySimulationDataAggregation> findAverageByIndicator(String indicator, Integer simulationNumber);
	List<EntitySimulationData> findAllByIndicator(String indicator, Integer simulationNumber, Integer crossRoadNumber);
}

