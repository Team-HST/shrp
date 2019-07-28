package com.hst.shrp.service;

import com.hst.shrp.dao.AnalysisDAO;
import com.hst.shrp.model.Sample;
import com.hst.shrp.model.exception.DataNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author dlgusrb0808@gmail.com
 */
@Service
public class AnalysisService {

	@Autowired
	private AnalysisDAO analysisDAO;

	public Sample getSample() {
		Sample sample = new Sample();
		sample.setName("이현규");
		sample.setAge(28);
		return sample;
	}

	public Sample getSampleWithException() {
		throw new DataNotFoundException();
	}
}
