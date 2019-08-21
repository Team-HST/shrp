package com.hst.shrp.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hst.shrp.dao.AnalysisDAO;
import com.hst.shrp.dao.AnalysisHistoryDAO;
import com.hst.shrp.model.api.analysis.SimulationAnalysisHistoryResponse;
import com.hst.shrp.model.api.analysis.SimulationAnalysisRequest;
import com.hst.shrp.model.api.analysis.SimulationAnalysisResponse;
import com.hst.shrp.model.api.code.CommonCodesResponse.CommonCode;
import com.hst.shrp.model.entity.EntityAnalysisHistory;
import com.hst.shrp.model.entity.EntitySimulationData;
import com.hst.shrp.model.entity.EntitySimulationDataAggregation;
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
    private final AnalysisHistoryDAO analysisHistoryDAO;
    private final CommonCodeService commonCodeService;

    /***
     * AnalysisService Required Dependency
     *
     * @param analysisDAO DAO for analysis
     * @param analysisHistoryDAO DAO for analysis history
     * @param commonCodeService Service for common code information
     */
    public AnalysisService(AnalysisDAO analysisDAO, AnalysisHistoryDAO analysisHistoryDAO, CommonCodeService commonCodeService) {
        this.analysisDAO = analysisDAO;
        this.analysisHistoryDAO = analysisHistoryDAO;
        this.commonCodeService = commonCodeService;
    }

    /***
     * analyze simulation
     * @param request simulation analysis request
     * @return result of analysis
     */
    public SimulationAnalysisResponse analysisSimulation(SimulationAnalysisRequest request) {
        SimulationAnalysisResponse response;
        CommonCode indicatorCode = commonCodeService.getCommonCode(INDICATOR_GROUP_CODE, request.getIndicator());

        if (request.isAllCrossRoadAnalyze()) {
            List<EntitySimulationDataAggregation> aggregations =
                    analysisDAO.findAverageByIndicator(indicatorCode.getSubName(), request.getSimulationNumber());
            response = SimulationAnalysisResponse.ofAggregation(request, aggregations);
        } else {
            List<EntitySimulationData> simulationData = analysisDAO.findAllByIndicator(indicatorCode.getSubName(),
                    request.getSimulationNumber(), Integer.parseInt(request.getCrossRoadNumber()));
            response = SimulationAnalysisResponse.of(request, simulationData);
        }

        // TODO / 이현규 / 로그 쌓은 컬럼 추가한거 반영 필요
        insertAnalysisHistory(request);

        return response;
    }

    /***
     * get simulation analysis history
     *
     * @param page the page
     * @param size the size
     * @return histories
     */
    public SimulationAnalysisHistoryResponse getAnalysisHistories(int page, int size) {
        PageHelper.startPage(page, size);
        Page<EntityAnalysisHistory> resultPage = analysisHistoryDAO.findAllAnalysisHistories();
        return SimulationAnalysisHistoryResponse.of(resultPage, commonCodeService.getCommonCodes(INDICATOR_GROUP_CODE));
    }

    /***
     * insert analysis history
     * @param request
     */
    public void insertAnalysisHistory(SimulationAnalysisRequest request) {
        EntityAnalysisHistory entityAnalysisHistory = new EntityAnalysisHistory();
        entityAnalysisHistory.setSimulNo(request.getSimulationNumber());
        entityAnalysisHistory.setIxCd(request.getIndicator());

        analysisHistoryDAO.save(entityAnalysisHistory);
    }

}
