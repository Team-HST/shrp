package com.hst.shrp.controller;

import com.hst.shrp.model.api.ApiResponse;
import com.hst.shrp.model.api.analysis.SimulationAnalysisHistoryResponse;
import com.hst.shrp.model.api.analysis.SimulationAnalysisRequest;
import com.hst.shrp.model.api.analysis.SimulationAnalysisResponse;
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
    private AnalysisService analysisService;

    @GetMapping("{simulationNumbers}/{indicator}")
    public ApiResponse<SimulationAnalysisResponse> analyzeSimulation(
            @PathVariable String simulationNumbers,
            @PathVariable String indicator,
            @RequestParam("crossRoadNumber") String crossRoadNumber
    ) {
        SimulationAnalysisRequest request = SimulationAnalysisRequest.of(simulationNumbers, indicator, crossRoadNumber);
        return new ApiResponse<>(analysisService.executeAnalysis(request));
    }

    @GetMapping("histories")
    public ApiResponse<SimulationAnalysisHistoryResponse> getAnalysisHistories(
        @RequestParam(defaultValue = "1") int page,
        @RequestParam(defaultValue = "10") int size
    ) {

        return new ApiResponse<>(analysisService.getAnalysisHistories(page, size));
    }

}
