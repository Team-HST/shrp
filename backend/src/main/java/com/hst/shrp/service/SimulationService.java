package com.hst.shrp.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hst.shrp.dao.SimulationDAO;
import com.hst.shrp.model.api.simulation.SimulationHistoriesResponse;
import com.hst.shrp.model.entity.EntitySimulationHistory;
import org.springframework.stereotype.Service;

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

}
