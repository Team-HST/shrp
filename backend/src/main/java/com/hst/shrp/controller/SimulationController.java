package com.hst.shrp.controller;

import com.hst.shrp.model.api.ApiResponse;
import com.hst.shrp.model.api.simulation.SimulationHistoriesResponse;
import com.hst.shrp.service.SimulationService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author dlgusrb0808@gmail.com
 */
@Api( tags = "2. 시뮬레이션 API", description = "시뮬레이션 관련 기능을 제공합니다.")
@RestController
@RequestMapping("/api/simulation")
public class SimulationController {

	@Autowired
	private SimulationService simulationService;

	@ApiOperation(value = "시뮬레이션 이력 조회", notes = "시뮬레이션 이력을 조회합니다.")
	@ApiImplicitParams({
			@ApiImplicitParam(
					name = "page",
					value = "조회할 페이지",
					paramType = "query",
					dataType = "int",
					defaultValue = "1"
			),
			@ApiImplicitParam(
					name = "size",
					value = "페이지 크기",
					paramType = "query",
					dataType = "int",
					defaultValue = "10"
			)
	})
	@ApiResponses({
			@io.swagger.annotations.ApiResponse(code = 200, message = "성공")
	})
	@GetMapping("/histories")
	public ApiResponse<SimulationHistoriesResponse> getSimulationHistories(
			@RequestParam(defaultValue = "1") int page,
			@RequestParam(defaultValue = "10") int size
	) {
		SimulationHistoriesResponse response = simulationService.getSimulationHistories(page, size);
		return new ApiResponse<>(response);
	}

}
