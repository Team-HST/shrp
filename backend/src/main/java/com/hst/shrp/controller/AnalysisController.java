package com.hst.shrp.controller;

import com.hst.shrp.model.Sample;
import com.hst.shrp.model.api.ApiResponse;
import com.hst.shrp.service.AnalysisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/analysis")
public class AnalysisController {

	@Autowired
	private AnalysisService analysisService;

	@GetMapping("/all")
	public ApiResponse<Sample> getAllAnalysis() {
		return new ApiResponse<>(analysisService.getSample());
	}

	@GetMapping("/greeting")
	public ApiResponse<String> greeting() {
		return new ApiResponse<>("greeting!");
	}

	@GetMapping("/exception")
	public ApiResponse<Sample> occurExceptionApi() {
		return new ApiResponse<>(analysisService.getSampleWithException());
	}
}
