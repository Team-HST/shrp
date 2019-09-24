package com.hst.shrp.dao;

import com.github.pagehelper.Page;
import com.hst.shrp.model.entity.EntitySimulationHistory;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author dlgusrb0808@gmail.com
 */
@Repository
public interface SimulationDAO {
	Page<EntitySimulationHistory> findAllSimulationHistories();

	EntitySimulationHistory findOne(int simulationNumber);

	List<Integer> selectNotRecentlySimulationHistories(int recentLimitBound);

	void deleteSimulationBySimulationNumbers(@Param("simulationNumbers") List<Integer> simulationNumbers);
}
