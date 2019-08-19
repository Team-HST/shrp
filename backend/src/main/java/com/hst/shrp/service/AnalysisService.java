package com.hst.shrp.service;

import com.hst.shrp.dao.AnalysisDAO;
import com.hst.shrp.model.api.analysis.SimulationAnalysisRequest;
import com.hst.shrp.model.api.analysis.SimulationAnalysisResponse;
import com.hst.shrp.model.api.code.CommonCodesResponse.CommonCode;
import com.hst.shrp.model.entity.EntityIndicator;
import com.hst.shrp.model.entity.EntityIndicatorAggregation;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author dudgns0612@gmail.com
 */
@Service
public class AnalysisService {

    private static final String INDICATOR_GROUP_CODE = "100";

    private final AnalysisDAO analysisDAO;
    private final CommonCodeService commonCodeService;

    public AnalysisService(AnalysisDAO analysisDAO, CommonCodeService commonCodeService) {
        this.analysisDAO = analysisDAO;
        this.commonCodeService = commonCodeService;
    }

    public SimulationAnalysisResponse analysisSingleSimulation(SimulationAnalysisRequest request) {
        // TODO / 이현규 / request 유효성 검사
        CommonCode indicatorCode = commonCodeService.getCommonCode(INDICATOR_GROUP_CODE, request.getIndicator());

        if (request.isAllCrossRoadAnalyze()) {
            List<EntityIndicatorAggregation> aggregations =
                    analysisDAO.findAverageByIndicator(indicatorCode.getSubName(), request.getSimulationNumber());
            return SimulationAnalysisResponse.ofAggregation(request, aggregations);
        } else {
            // TODO / 이현규 / 방향코드를 방향명으로 매핑해서 내려줄것
            List<EntityIndicator> indicators = analysisDAO.findAllByIndicator(indicatorCode.getSubName(), request.getSimulationNumber(),
                    Integer.parseInt(request.getCrossRoadNumber()));
            return SimulationAnalysisResponse.of(request, indicators);
        }
    }

}
