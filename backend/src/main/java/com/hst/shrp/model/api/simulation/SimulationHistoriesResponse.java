package com.hst.shrp.model.api.simulation;

import com.github.pagehelper.Page;
import com.hst.shrp.model.api.PagedResponse;
import com.hst.shrp.model.entity.EntitySimulationHistory;

import java.time.LocalDateTime;
import java.util.List;

import static com.hst.shrp.utils.FunctionalAPI.from;

/**
 * @author dlgusrb0808@gmail.com
 */
public class SimulationHistoriesResponse extends PagedResponse {
	private List<SimulationHistory> simulationHistories;

	public List<SimulationHistory> getSimulationHistories() {
		return simulationHistories;
	}

	public static class SimulationHistory {
		private int simulationNumber;
		private String fileName;
		private LocalDateTime simulationAt;
		private String experCode;
		private String ampmCode;

		public int getSimulationNumber() {
			return simulationNumber;
		}

		public String getFileName() {
			return fileName;
		}

		public LocalDateTime getSimulationAt() {
			return simulationAt;
		}

		public String getExperCode() {
			return experCode;
		}

		public String getAmpmCode() {
			return ampmCode;
		}

		public static SimulationHistory convert(EntitySimulationHistory entity) {
			SimulationHistory history = new SimulationHistory();
			history.simulationNumber = entity.getSimulNo();
			history.fileName = entity.getFileNm();
			history.simulationAt = entity.getSimulDt();
			history.experCode = entity.getExperCd();
			history.ampmCode = entity.getAmpmCd();
			return history;
		}
	}

	public static SimulationHistoriesResponse of(Page<EntitySimulationHistory> resultPage) {
		SimulationHistoriesResponse response = new SimulationHistoriesResponse();
		response.setPageInformation(resultPage);
		response.simulationHistories = from(resultPage).toList(SimulationHistory::convert);
		return response;
	}
}
