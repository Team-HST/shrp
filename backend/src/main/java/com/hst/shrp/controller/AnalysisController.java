package com.hst.shrp.controller;

import com.hst.shrp.model.api.ApiResponse;
import com.hst.shrp.model.api.analysis.SimulationAnalysisRequest;
import com.hst.shrp.service.AnalysisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author dlgusrb0808@gmail.com
 */
@RestController
@RequestMapping("/api/analysis")
public class AnalysisController {

    @Autowired
    AnalysisService analysisService;

    public AnalysisController() {
    }

    @GetMapping("{simulationNumber}/{indicator}")
    public ApiResponse<Object> analyzeSingleSimulation(
            @PathVariable  int simulationNumber,
            @PathVariable String indicator,
            @RequestParam("crossRoadNumber") String crossRoadNumber
    ) {
        SimulationAnalysisRequest request = SimulationAnalysisRequest.of(simulationNumber, indicator, crossRoadNumber);
        return new ApiResponse<>(analysisService.analysisSimulation(request));
    }

}
