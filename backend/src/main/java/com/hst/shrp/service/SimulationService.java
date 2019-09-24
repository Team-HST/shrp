package com.hst.shrp.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hst.shrp.dao.SimulationDAO;
import com.hst.shrp.model.api.simulation.SimulationHistoriesResponse;
import com.hst.shrp.model.api.simulation.SimulationHistoriesResponse.SimulationHistory;
import com.hst.shrp.model.entity.EntitySimulationHistory;
import com.hst.shrp.model.exception.DataNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author dlgusrb0808@gmail.com
 */
@Service
public class SimulationService {

	private final SimulationDAO simulationDAO;

	public SimulationService(SimulationDAO simulationDAO) {
		this.simulationDAO = simulationDAO;
	}

	/***
	 * search simulation histories
	 * @param page the page
	 * @param size the size
	 * @return response of simulation histories
	 */
	public SimulationHistoriesResponse getSimulationHistories(int page, int size) {
		PageHelper.startPage(page, size);
		Page<EntitySimulationHistory> resultPage = simulationDAO.findAllSimulationHistories();
		return SimulationHistoriesResponse.of(resultPage);
	}

	/***
	 * search simulation history
	 * @param simulationNumber the number of simulation
	 * @return simulation history
	 */
	public SimulationHistory getSimulationHistory(int simulationNumber) {
		EntitySimulationHistory history = simulationDAO.findOne(simulationNumber);
		if(history == null) {
			throw new DataNotFoundException(String.format("Cannot find simulation history. simulationNumber: %d", simulationNumber));
		}
		return SimulationHistory.convert(history);
	}

	/***
	 * search simulation numbers to be deleted
	 * @param recentLimitBound the number of latest data to keep
	 * @return simulation numbers to be deleted
	 */
	public List<Integer> getNonRecentlySimulationNumbers(int recentLimitBound) {
		return simulationDAO.selectNotRecentlySimulationHistories(recentLimitBound);
	}

	/***
	 * delete old simulations
	 * @param deleteTargetSimulationNumbers simulation number to delete
	 */
	public void removeNonRecentlySimulations(List<Integer> deleteTargetSimulationNumbers) {
		simulationDAO.deleteSimulationBySimulationNumbers(deleteTargetSimulationNumbers);
	}
}
