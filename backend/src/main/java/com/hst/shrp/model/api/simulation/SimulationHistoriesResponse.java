package com.hst.shrp.model.api.simulation;

import com.github.pagehelper.Page;
import com.hst.shrp.model.api.PagedResponse;
import com.hst.shrp.model.entity.EntitySimulationHistory;
import io.swagger.annotations.ApiModelProperty;

import java.time.LocalDateTime;
import java.util.List;

import static com.hst.shrp.utils.FunctionalAPI.with;

/**
 * @author dlgusrb0808@gmail.com
 */
public class SimulationHistoriesResponse extends PagedResponse {
	@ApiModelProperty(position = 1)
	private List<SimulationHistory> simulationHistories;

	public List<SimulationHistory> getSimulationHistories() {
		return simulationHistories;
	}

	public static class SimulationHistory {
		@ApiModelProperty(position = 1, example = "시뮬레이션 번호")
		private int simulationNumber;
		@ApiModelProperty(position = 2, example = "시뮬레이션 파일명")
		private String fileName;
		@ApiModelProperty(position = 3, example = "시뮬레이션 일시")
		private LocalDateTime simulationAt;
		@ApiModelProperty(position = 4, example = "TOD/TRC 구분코드")
		private String experCode;
		@ApiModelProperty(position = 5, example = "AM/PM 구분코드")
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
		response.simulationHistories = with(resultPage).toList(SimulationHistory::convert);
		return response;
	}
}
