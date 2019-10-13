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
import com.hst.shrp.utils.SimulationNumberSignatureUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

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
    public SimulationAnalysisListResponse executeAnalysis(SimulationAnalysisRequest request) {
        SimulationAnalysisListResponse response = doAnalysis(request);

        insertAnalysisHistory(request, response);

        return response;
    }

    // execute simulation analysis loop
    private SimulationAnalysisListResponse doAnalysis(SimulationAnalysisRequest request) {
        List<SimulationAnalysisResponse> dataset = new ArrayList<>();

        for (int simulationNumber : request.getSimulationNumbers()) {
            dataset.add(doAnalyzeSimulation(simulationNumber, request));
        }

        return SimulationAnalysisListResponse.of(dataset);
    }

    // core analysis for single simulation
    private SimulationAnalysisResponse doAnalyzeSimulation(int targetSimulationNumber, SimulationAnalysisRequest request) {
        String indicatorName = commonCodeService.getCommonCodeAs(INDICATOR_GROUP_CODE, request.getIndicator(), CommonCode::getSubName);
        SimulationHistory history = simulationService.getSimulationHistory(targetSimulationNumber);
        if (request.isAllCrossRoadAnalyze()) {
            List<EntitySimulationAggregationData> aggregations =
                    analysisDAO.findAverageByIndicator(indicatorName, targetSimulationNumber);
            return SimulationAnalysisResponse.ofAggregation(history, request, aggregations, indicatorName);
        } else {
            List<EntitySimulationDirectionData> simulationData = analysisDAO.findAllByIndicator(indicatorName,
                    targetSimulationNumber, Integer.parseInt(request.getCrossRoadNumber()));
            return SimulationAnalysisResponse.of(history, request, simulationData, indicatorName);
        }
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
     * Simulation Sample Data generator
     * @param request
     */
    public void generateSimulationData(SimulationSampleDataGenerateRequest request) {
        String[] directions = {
            "NB직진", "SB직진", "EB직진", "WB직진"
        };

        for (int crossRoadNumber = 1; crossRoadNumber <= 19; crossRoadNumber++) {
            for (String direction : directions) {
                analysisDAO.generateSimulationData(request.getIndicator(), request.getSimulationNumber(),
                        crossRoadNumber, direction, ThreadLocalRandom.current().nextDouble(10, 3000));
            }
        }
    }

    /***
     * insert analysis history
     * @param request the request
     */
    public void insertAnalysisHistory(SimulationAnalysisRequest request, Object analysisResult) {
        String signature = request.getSimulationNumberSignature();

        EntityAnalysisHistory entityAnalysisHistory = new EntityAnalysisHistory();
        entityAnalysisHistory.setSimulNos(signature);
        entityAnalysisHistory.setIxCd(request.getIndicator());
        if (request.isAllCrossRoadAnalyze()) {
            entityAnalysisHistory.setTargetCrpNo("전체");
        } else {
            entityAnalysisHistory.setTargetCrpNo(String.format("%s교차로", request.getCrossRoadNumber()));
        }
        entityAnalysisHistory.setAnalData(JsonUtils.asJson(analysisResult));
        entityAnalysisHistory.setUserNm(request.getUserNm());
        entityAnalysisHistory.setBasisSimulationNumber(SimulationNumberSignatureUtils.getBasisSimulationNumber(signature));
        analysisHistoryDAO.save(entityAnalysisHistory);
    }

    public void removeOldSimulationAnalysisHistories(List<Integer> deleteTargetSimulationNumbers) {
        analysisHistoryDAO.deleteAllBySimulationNumbers(deleteTargetSimulationNumbers);
    }
}
