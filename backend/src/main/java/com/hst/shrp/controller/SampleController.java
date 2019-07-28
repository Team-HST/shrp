package com.hst.shrp.controller;

import com.hst.shrp.dao.SampleDAO;
import com.hst.shrp.model.Sample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SampleController {

	@Autowired
	private SampleDAO sampleDAO;

	@GetMapping("/test")
	public List<Sample> test() {
		return sampleDAO.selectSample();
	}

}
