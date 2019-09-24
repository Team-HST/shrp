package com.hst.shrp.controller;

import com.hst.shrp.model.api.ApiResponse;
import com.hst.shrp.model.api.analysis.SimulationAnalysisHistoryResponse;
import com.hst.shrp.model.api.analysis.SimulationAnalysisListResponse;
import com.hst.shrp.model.api.analysis.SimulationAnalysisRequest;
import com.hst.shrp.service.AnalysisService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author dlgusrb0808@gmail.com
 */
@Api( tags = "3. 분석 API", description = "시뮬레이션 분석 기능을 제공합니다.")
@RestController
@RequestMapping("/api/analysis")
public class AnalysisController {

    @Autowired
    private AnalysisService analysisService;

    @ApiOperation(value = "분석 실행", notes = "조건에 맞는 시뮬레이션을 분석합니다. 매 호출 시마다 분석 이력이 생성됩니다.")
    @ApiImplicitParams({
            @ApiImplicitParam(
                    name = "simulationNumbers",
                    value = "분석할 시뮬레이션 번호, 다수일경우 언더바(_)로 연결, 최대 3개",
                    paramType = "path",
                    dataType = "string",
                    required = true
            ),
            @ApiImplicitParam(
                    name = "indicator",
                    value = "분석지표코드 (101: VEHS | 102: SPEEDAVGHARM | 103: STOPDELAY | 104: VEHDELAY | 105: QLEN | 106: TRAVTM, 107: STOPS)",
                    paramType = "path",
                    dataType = "string",
                    required = true
            ),
            @ApiImplicitParam(
                    name = "userNm",
                    value = "분석 수행한 사용자명",
                    paramType = "query",
                    dataType = "string",
                    required = true
            ),
            @ApiImplicitParam(
                    name = "crossRoadNumber",
                    value = "분석 교차로 번호 (전체: all, 단일: 해당 교차로 번호)",
                    paramType = "query",
                    dataType = "string",
                    required = true
            )
    })
    @ApiResponses({
            @io.swagger.annotations.ApiResponse(code = 200, message = "성공")
    })
    @GetMapping("{simulationNumbers}/{indicator}")
    public ApiResponse<SimulationAnalysisListResponse> analyzeSimulation(
            @PathVariable String simulationNumbers,
            @PathVariable String indicator,
            @RequestParam("userNm") String userNm,
            @RequestParam("crossRoadNumber") String crossRoadNumber
    ) {
        SimulationAnalysisRequest request = SimulationAnalysisRequest.of(simulationNumbers, indicator, userNm, crossRoadNumber);
        return new ApiResponse<>(analysisService.executeAnalysis(request));
    }

    @ApiOperation(value = "분석 이력 조회", notes = "분석 수행한 이력을 조회합니다.")
    @ApiResponses({
            @io.swagger.annotations.ApiResponse(code = 200, message = "성공")
    })
    @GetMapping("histories")
    public ApiResponse<SimulationAnalysisHistoryResponse> getAnalysisHistories() {
        return new ApiResponse<>(analysisService.getAnalysisHistories());
    }

}
