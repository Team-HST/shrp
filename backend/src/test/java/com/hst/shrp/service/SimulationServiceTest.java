package com.hst.shrp.service;

import com.github.pagehelper.Page;
import com.hst.shrp.dao.SimulationDAO;
import com.hst.shrp.model.api.simulation.SimulationHistoriesResponse;
import com.hst.shrp.model.entity.EntitySimulationHistory;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

/**
 * @author dlgusrb0808@gmail.com
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class SimulationServiceTest {

	@Mock
	private SimulationDAO mockSimulationDAO;

	@InjectMocks
	private SimulationService simulationService;

	@Before
	public void setUp() {
		simulationService = new SimulationService(mockSimulationDAO);
	}

	private static class EntitySimulationHistoryWrapper extends Page<EntitySimulationHistory> {}

	@Test
	public void getSimulationHistoriesTest() {
		// given
		int pageNo = 0;
		int pageSize = 10;
		when(mockSimulationDAO.findAllSimulationHistories()).thenReturn(mock(EntitySimulationHistoryWrapper.class));

		// when
		SimulationHistoriesResponse response = simulationService.getSimulationHistories(pageNo, pageSize);

		// then
		assertThat(response, is(notNullValue()));
		assertNotNull(response);
	}

}
