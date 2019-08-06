package com.hst.shrp.dao;

import com.github.pagehelper.Page;
import com.hst.shrp.model.entity.EntitySimulationHistory;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author dlgusrb0808@gmail.com
 */
@Repository
public interface SimulationDAO {
	public Page<EntitySimulationHistory> findAllSimulationHistories();
}
