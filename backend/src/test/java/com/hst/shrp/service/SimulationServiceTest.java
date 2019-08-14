package com.hst.shrp.service;

import com.hst.shrp.dao.SimulationDAO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author dlgusrb0808@gmail.com
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class SimulationServiceTest {

	@Mock
	private SimulationDAO mockSimulationDAO;

	@InjectMocks
	private SimulationService simulationService = new SimulationService(mockSimulationDAO);

	@Test
	public void getSimulationHistoriesTest() {
		// TODO / 이현규 / 테스트 케이스 작성
	}

}
