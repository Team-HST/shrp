package com.hst.shrp.controller;

import com.hst.shrp.model.api.ApiResponse;
import com.hst.shrp.model.api.simulation.SimulationHistoriesResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author dlgusrb0808@gmail.com
 */
@RestController
@RequestMapping("/api/simulation")
public class SimulationController {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@GetMapping("/histories")
	public ApiResponse<SimulationHistoriesResponse> getSimulationHistories(@RequestParam(defaultValue = "0") int page,
																		   @RequestParam(defaultValue = "10") int size) {

		logger.info("page: {},  size: {}", page, size);

		return new ApiResponse<>();
	}

}
