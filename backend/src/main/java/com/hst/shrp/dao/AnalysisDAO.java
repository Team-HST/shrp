package com.hst.shrp.dao;

import com.hst.shrp.model.entity.EntityIndicator;
import com.hst.shrp.model.entity.EntityIndicatorAggregation;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author dudgns0612@gmail.com
 */
@Repository
public interface AnalysisDAO {
	List<EntityIndicatorAggregation> findAverageByIndicator(String indicator, Integer simulationNumber);
	List<EntityIndicator> findAllByIndicator(String indicator, Integer simulationNumber, Integer crossRoadNumber);
}

