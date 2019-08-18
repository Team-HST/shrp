package com.hst.shrp.dao;

import com.github.pagehelper.Page;
import com.hst.shrp.model.entity.EntityAnalysisHistory;
import org.springframework.stereotype.Repository;

/**
 * @author dlgusrb0808@gmail.com
 */
@Repository
public interface AnalysisHistoryDAO {
	public Page<EntityAnalysisHistory> findAllAnalysisHistories();

	void save(EntityAnalysisHistory history);
}
