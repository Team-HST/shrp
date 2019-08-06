package com.hst.shrp.controller;

import com.hst.shrp.model.api.ApiResponse;
import com.hst.shrp.model.entity.EntityCommonCode;
import com.hst.shrp.service.AnalysisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

    /**
     * 지표 종류 조회
     *
     * @return
     */
    @GetMapping("/simulationType")
    public ApiResponse<List<EntityCommonCode>> getSimulationTypeList() {
        List<EntityCommonCode> response = analysisService.getSimulationTypeList();
        return new ApiResponse<>(response);
    }

    /**
     * Ampm 시간대 종류 조회
     *
     * @return
     */
    @GetMapping("/ampmType")
    public ApiResponse<List<EntityCommonCode>> getAmpmTypeList() {
        List<EntityCommonCode> response = analysisService.getAmpmTypeList();
        return new ApiResponse<>(response);
    }
}
