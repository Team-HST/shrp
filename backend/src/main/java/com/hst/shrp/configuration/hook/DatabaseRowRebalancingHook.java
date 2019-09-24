package com.hst.shrp.configuration.hook;

import com.hst.shrp.configuration.annotation.InitializingHook;
import com.hst.shrp.service.AnalysisService;
import com.hst.shrp.service.SimulationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.util.List;

/**
 * @author dlgusrb0808@gmail.com
 */
@InitializingHook
@Order(2)
public class DatabaseRowRebalancingHook extends ApplicationInitializingHook {

	private static final String DELETE_TARGET_SIMULATION_NUMBERS_ATTRIBUTE_NAME = "DELETE_TARGET_SIMULATION_NUMBERS";

	@Value("${shrp.data.number-of-keey-recent-simulations}")
	private int recentSimulationNumberLimitBound;

	@Autowired
	private PlatformTransactionManager transactionManager;	// commit control transaction manually

	private final SimulationService simulationService;
	private final AnalysisService analysisService;

	public DatabaseRowRebalancingHook(SimulationService simulationService, AnalysisService analysisService) {
		this.simulationService = simulationService;
		this.analysisService = analysisService;
	}

	@Override
	protected boolean isNecessaryExecuteHook() {
		List<Integer> deleteTargetSimulationNumbers = simulationService.getNonRecentlySimulationNumbers(recentSimulationNumberLimitBound);
		setContextAttribute(DELETE_TARGET_SIMULATION_NUMBERS_ATTRIBUTE_NAME, deleteTargetSimulationNumbers);
		return deleteTargetSimulationNumbers.size() != 0;
	}

	@Override
	protected void executeHook() {
		List<Integer> deleteTargetSimulationNumbers = (List<Integer>) getContextAttribute(DELETE_TARGET_SIMULATION_NUMBERS_ATTRIBUTE_NAME);

		logger.info("Need to delete old simulation {} rows", deleteTargetSimulationNumbers.size());

		TransactionDefinition definition = new DefaultTransactionDefinition();
		TransactionStatus status = transactionManager.getTransaction(definition);

		try {
			// delete old simulations
			simulationService.removeNonRecentlySimulations(deleteTargetSimulationNumbers);

			// delete old simulation analysis histories
			analysisService.removeOldSimulationAnalysisHistories(deleteTargetSimulationNumbers);
		} catch (Exception e) {
			transactionManager.rollback(status);
			throw e;
		}

		transactionManager.commit(status);
	}
}
