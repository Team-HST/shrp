package com.hst.shrp.model.api.analysis;

import com.github.pagehelper.Page;
import com.hst.shrp.model.api.code.CommonCodesResponse;
import com.hst.shrp.model.entity.EntityAnalysisHistory;
import com.hst.shrp.utils.JsonUtils;

import java.util.List;
import java.util.Map;

import static com.hst.shrp.utils.Functionals.*;

/**
 * @author dlgusrb0808@gmail.com
 */
public class SimulationAnalysisHistoryResponse {
	private int page;
	private int size;
	private int totalPages;
	private List<SimulationAnalysisHistory> simulationAnalysisHistories;

	public int getPage() {
		return page;
	}

	public int getSize() {
		return size;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public List<SimulationAnalysisHistory> getSimulationAnalysisHistories() {
		return simulationAnalysisHistories;
	}

	public static class SimulationAnalysisHistory {
		private int analysisNumber;
		private int simulationNumber;
		private String analysisFileName;
		private String indicator;
		private String analysisTarget;
		private SimulationSingleAnalysisResponse analysisData;
		private String analysisDate;

		public int getAnalysisNumber() {
			return analysisNumber;
		}

		public int getSimulationNumber() {
			return simulationNumber;
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

		public SimulationSingleAnalysisResponse getAnalysisData() {
			return analysisData;
		}

		public String getAnalysisFileName() {
			return analysisFileName;
		}

		public static SimulationAnalysisHistory convert(EntityAnalysisHistory entity, Map<String, String> indicatorCode) {
			SimulationAnalysisHistory analysisHistory = new SimulationAnalysisHistory();
			analysisHistory.analysisNumber = entity.getAnalNo();
			analysisHistory.simulationNumber = entity.getSimulNo();
			analysisHistory.analysisFileName = entity.getFileNm();
			analysisHistory.indicator = indicatorCode.get(entity.getIxCd());
			analysisHistory.analysisTarget = entity.getTargetCrpNo();
			analysisHistory.analysisData = JsonUtils.fromJson(entity.getAnalData(), SimulationSingleAnalysisResponse.class);
			analysisHistory.analysisDate = entity.getAnalDt();
			return analysisHistory;
		}
	}

	public static SimulationAnalysisHistoryResponse of(Page<EntityAnalysisHistory> resultPage,
													   CommonCodesResponse indicatorCode) {
		Map<String, String> indicatorMap = indicatorCode.getCommonCodeMap();
		SimulationAnalysisHistoryResponse response = new SimulationAnalysisHistoryResponse();
		response.page = resultPage.getPageNum();
		response.size = resultPage.getPageSize();
		response.totalPages = resultPage.getPages();
		response.simulationAnalysisHistories = transform(resultPage,
				entity -> SimulationAnalysisHistory.convert(entity, indicatorMap));
		return response;
	}
}
