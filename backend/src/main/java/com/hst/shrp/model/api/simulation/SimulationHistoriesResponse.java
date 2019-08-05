package com.hst.shrp.model.api.simulation;

import com.github.pagehelper.Page;
import com.hst.shrp.model.entity.EntitySimulationHistory;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author dlgusrb0808@gmail.com
 */
public class SimulationHistoriesResponse {
	private int page;
	private int size;
	private int totalPages;
	private List<SimulationHistory> simulationHistories;

	public int getPage() {
		return page;
	}

	public int getSize() {
		return size;
	}

	public int getTotalPages() {
		return totalPages;
	}

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
		response.page = resultPage.getPageNum();
		response.size = resultPage.getPageSize();
		response.totalPages = resultPage.getPages();
		response.simulationHistories = resultPage.stream().map(SimulationHistory::convert).collect(Collectors.toList());
		return response;
	}
}
