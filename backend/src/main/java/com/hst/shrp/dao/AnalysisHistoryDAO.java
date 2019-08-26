package com.hst.shrp.dao;

import com.hst.shrp.model.entity.EntityAnalysisHistory;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author dlgusrb0808@gmail.com
 */
@Repository
public interface AnalysisHistoryDAO {
	List<EntityAnalysisHistory> findAllAnalysisHistories();

	void save(EntityAnalysisHistory history);
}
