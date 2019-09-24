package com.hst.shrp.model.api.analysis;

import com.hst.shrp.model.api.code.CommonCodesResponse;
import com.hst.shrp.model.entity.EntityAnalysisHistory;
import com.hst.shrp.utils.JsonUtils;
import com.hst.shrp.utils.SimulationNumberSignatureUtils;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;
import java.util.Map;

import static com.hst.shrp.utils.FunctionalAPI.with;

/**
 * @author dlgusrb0808@gmail.com
 */
public class SimulationAnalysisHistoryResponse {
	@ApiModelProperty(position = 1)
	private List<SimulationAnalysisHistory> simulationAnalysisHistories;

	public List<SimulationAnalysisHistory> getSimulationAnalysisHistories() {
		return simulationAnalysisHistories;
	}

	public static class SimulationAnalysisHistory {
		@ApiModelProperty(position = 1, example = "분석번호")
		private int analysisNumber;
		@ApiModelProperty(position = 2, example = "분석 시뮬레이션번호")
		private List<Integer> simulationNumbers;
		@ApiModelProperty(position = 3, example = "분석 시뮬레이션 파일명")
		private String analysisFileName;
		@ApiModelProperty(position = 4, example = "분석지표")
		private String indicator;
		@ApiModelProperty(position = 5, example = "분석대상")
		private String analysisTarget;
		@ApiModelProperty(position = 6, example = "분석 결과데이터")
		private SimulationAnalysisListResponse analysisData;
		@ApiModelProperty(position = 7, example = "분석일시")
		private String analysisDate;
		@ApiModelProperty(position = 8, example = "분석자명")
		private String userName;

		public int getAnalysisNumber() {
			return analysisNumber;
		}

		public List<Integer> getSimulationNumbers() {
			return simulationNumbers;
		}

		public String getAnalysisDate() {
			return analysisDate;
		}

		public String getIndicator() {
			return indicator;
		}

		public String getAnalysisTarget() {
			return analysisTarget;
		}

		public SimulationAnalysisListResponse getAnalysisData() {
			return analysisData;
		}

		public String getAnalysisFileName() {
			return analysisFileName;
		}

		public String getUserName() {
			return userName;
		}

		public static SimulationAnalysisHistory convert(EntityAnalysisHistory entity, Map<String, String> indicatorCode) {
			SimulationAnalysisHistory analysisHistory = new SimulationAnalysisHistory();
			analysisHistory.analysisNumber = entity.getAnalNo();
			analysisHistory.simulationNumbers = SimulationNumberSignatureUtils.decode(entity.getSimulNos());
			analysisHistory.analysisFileName = entity.getFileNm();
			analysisHistory.indicator = indicatorCode.get(entity.getIxCd());
			analysisHistory.analysisTarget = entity.getTargetCrpNo();
			analysisHistory.analysisData = JsonUtils.fromJson(entity.getAnalData(), SimulationAnalysisListResponse.class);
			analysisHistory.analysisDate = entity.getAnalDt();
			analysisHistory.userName = entity.getUserNm();
			return analysisHistory;
		}
	}

	public static SimulationAnalysisHistoryResponse of(List<EntityAnalysisHistory> resultPage,
													   CommonCodesResponse indicatorCode) {
		Map<String, String> indicatorMap = indicatorCode.getCommonCodeMap();
		SimulationAnalysisHistoryResponse response = new SimulationAnalysisHistoryResponse();
		response.simulationAnalysisHistories = with(resultPage)
				.toList(entity -> SimulationAnalysisHistory.convert(entity, indicatorMap));
		return response;
	}
}
