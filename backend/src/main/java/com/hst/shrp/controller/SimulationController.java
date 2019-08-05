package com.hst.shrp.controller;

import com.hst.shrp.model.api.ApiResponse;
import com.hst.shrp.model.api.simulation.SimulationHistoriesResponse;
import com.hst.shrp.service.SimulationService;
import org.springframework.beans.factory.annotation.Autowired;
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

	@Autowired
	private SimulationService simulationService;

	/***
	 * GET /api/simulation/histories
	 *
	 * @param page
	 * @param size
	 * @return
	 */
	@GetMapping("/histories")
	public ApiResponse<SimulationHistoriesResponse> getSimulationHistories(
			@RequestParam(defaultValue = "1") int page,
			@RequestParam(defaultValue = "10") int size) {
		SimulationHistoriesResponse response = simulationService.getSimulationHistories(page, size);
		return new ApiResponse<>(response);
	}

}
