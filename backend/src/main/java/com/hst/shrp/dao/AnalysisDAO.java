package com.hst.shrp.dao;

import com.hst.shrp.model.Sample;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnalysisDAO {
	List<Sample> selectSample();
}
