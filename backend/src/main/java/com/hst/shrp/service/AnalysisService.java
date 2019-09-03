package com.hst.shrp.service;

import com.hst.shrp.dao.AnalysisDAO;
import com.hst.shrp.dao.AnalysisHistoryDAO;
import com.hst.shrp.model.api.analysis.*;
import com.hst.shrp.model.api.code.CommonCodesResponse.CommonCode;
import com.hst.shrp.model.api.simulation.SimulationHistoriesResponse.SimulationHistory;
import com.hst.shrp.model.entity.EntityAnalysisHistory;
import com.hst.shrp.model.entity.EntitySimulationAggregationData;
import com.hst.shrp.model.entity.EntitySimulationDirectionData;
import com.hst.shrp.utils.JsonUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author dudgns0612@gmail.com
 */
@Service
public class AnalysisService {

    private static final String INDICATOR_GROUP_CODE = "100";

    private final AnalysisDAO analysisDAO;
    private final AnalysisHistoryDAO analysisHistoryDAO;
    private final SimulationService simulationService;
    private final CommonCodeService commonCodeService;

    /***
     * AnalysisService Required Dependency
     *
     * @param analysisDAO DAO for analysis
     * @param analysisHistoryDAO DAO for analysis history
     * @param commonCodeService Service for common code information
     */
    public AnalysisService(
        AnalysisDAO analysisDAO,
        AnalysisHistoryDAO analysisHistoryDAO,
        SimulationService simulationService,
        CommonCodeService commonCodeService
    ) {
        this.analysisDAO = analysisDAO;
        this.analysisHistoryDAO = analysisHistoryDAO;
        this.simulationService = simulationService;
        this.commonCodeService = commonCodeService;
    }

    /***
     * analyze simulation
     * @param request simulation analysis request
     * @return result of analysis
     */
    public SimulationAnalysisResponse executeAnalysis(SimulationAnalysisRequest request) {
        SimulationAnalysisResponse response;

        if (request.isCompareAnalyzeRequest()) {
            response = doComparisionAnalyze(request);
        } else {
            response = doAnalyzeSimulation(request.getSimulationNumber(), request);
        }

        insertAnalysisHistory(request, response);

        return response;
    }

    // core analysis for single simulation
    private SimulationSingleAnalysisResponse doAnalyzeSimulation(int targetSimulationNumber, SimulationAnalysisRequest request) {
        String indicatorName = commonCodeService.getCommonCodeAs(INDICATOR_GROUP_CODE, request.getIndicator(), CommonCode::getSubName);
        SimulationHistory history = simulationService.getSimulationHistory(targetSimulationNumber);
        if (request.isAllCrossRoadAnalyze()) {
            List<EntitySimulationAggregationData> aggregations =
                    analysisDAO.findAverageByIndicator(indicatorName, targetSimulationNumber);
            return SimulationSingleAnalysisResponse.ofAggregation(history, request, aggregations, indicatorName);
        } else {
            List<EntitySimulationDirectionData> simulationData = analysisDAO.findAllByIndicator(indicatorName,
                    targetSimulationNumber, Integer.parseInt(request.getCrossRoadNumber()));
            return SimulationSingleAnalysisResponse.of(history, request, simulationData, indicatorName);
        }
    }

    // comparision analysis for two simulations
    private SimulationMultipleAnalysisResponse doComparisionAnalyze(SimulationAnalysisRequest request) {
        int simulationNumber = request.getSimulationNumber();
        int compareSimulationNumber = request.getCompareSimulationNumber();

        List<SimulationSingleAnalysisResponse> dataset = new ArrayList<>();
        dataset.add(doAnalyzeSimulation(simulationNumber, request));
        dataset.add(doAnalyzeSimulation(compareSimulationNumber, request));

        return SimulationMultipleAnalysisResponse.of(dataset);
    }


    /***
     * get simulation analysis history
     * @return histories
     */
    public SimulationAnalysisHistoryResponse getAnalysisHistories() {
        List<EntityAnalysisHistory> analysisHistories = analysisHistoryDAO.findAllAnalysisHistories();
        return SimulationAnalysisHistoryResponse.of(analysisHistories, commonCodeService.getCommonCodes(INDICATOR_GROUP_CODE));
    }

    /***
     * insert analysis history
     * @param request the request
     */
    public void insertAnalysisHistory(SimulationAnalysisRequest request, Object analysisResult) {
        EntityAnalysisHistory entityAnalysisHistory = new EntityAnalysisHistory();
        entityAnalysisHistory.setSimulNo(request.getSimulationNumber());
        entityAnalysisHistory.setCompSimulNo(request.getCompareSimulationNumber());
        entityAnalysisHistory.setIxCd(request.getIndicator());
        if (request.isAllCrossRoadAnalyze()) {
            entityAnalysisHistory.setTargetCrpNo("전체");
        } else {
            entityAnalysisHistory.setTargetCrpNo(String.format("%s교차로", request.getCrossRoadNumber()));
        }
        entityAnalysisHistory.setAnalData(JsonUtils.asJson(analysisResult));
        analysisHistoryDAO.save(entityAnalysisHistory);
    }

    public void removeOldSimulationAnalysisHistories(List<Integer> deleteTargetSimulationNumbers) {
        // TODO / 이현규 / 예전 시뮬레이션 분석 히스토리 이력 삭제
    }
}
