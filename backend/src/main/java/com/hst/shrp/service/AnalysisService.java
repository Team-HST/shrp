package com.hst.shrp.service;

import com.hst.shrp.dao.AnalysisDAO;
import com.hst.shrp.model.api.analysis.SimulationAnalysisRequest;
import com.hst.shrp.model.api.analysis.SimulationAnalysisResponse;
import com.hst.shrp.model.api.code.CommonCodesResponse.CommonCode;
import com.hst.shrp.model.entity.EntityIndicator;
import com.hst.shrp.model.entity.EntityIndicatorAggregation;
import com.hst.shrp.model.exception.InvalidParameterException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author dudgns0612@gmail.com
 */
@Service
public class AnalysisService {

    private static final String INDICATOR_GROUP_CODE = "100";
    private static final String DIRECTION_GROUP_CODE = "500";

    private final AnalysisDAO analysisDAO;
    private final CommonCodeService commonCodeService;

    public AnalysisService(AnalysisDAO analysisDAO, CommonCodeService commonCodeService) {
        this.analysisDAO = analysisDAO;
        this.commonCodeService = commonCodeService;
    }

    /***
     * analyze simulation
     * @param request simulation analysis request
     * @return result of analysis
     */
    public SimulationAnalysisResponse analysisSimulation(SimulationAnalysisRequest request) {
        if (!commonCodeService.containsCode(INDICATOR_GROUP_CODE, request.getIndicator())) {
            throw new InvalidParameterException(String.format("Invalid indicator %s", request.getIndicator()));
        }

        List<CommonCode> directionCodes = commonCodeService.getCommonCodes(DIRECTION_GROUP_CODE).getCommonCodes();
        CommonCode indicatorCode = commonCodeService.getCommonCode(INDICATOR_GROUP_CODE, request.getIndicator());

        if (request.isAllCrossRoadAnalyze()) {
            List<EntityIndicatorAggregation> aggregations =
                    analysisDAO.findAverageByIndicator(indicatorCode.getSubName(), request.getSimulationNumber());
            return SimulationAnalysisResponse.ofAggregation(request, aggregations);
        } else {
            List<EntityIndicator> indicators = analysisDAO.findAllByIndicator(indicatorCode.getSubName(),
                    request.getSimulationNumber(), Integer.parseInt(request.getCrossRoadNumber()));
            return SimulationAnalysisResponse.of(request, indicators, directionCodes);
        }
    }

}
