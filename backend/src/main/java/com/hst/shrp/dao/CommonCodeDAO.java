package com.hst.shrp.dao;

import com.hst.shrp.model.entity.EntityCommonCode;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author dlgusrb0808@gmail.com
 */
@Repository
public interface CommonCodeDAO {
	List<EntityCommonCode> findAllCodesByGrpCd(String grpCd);
}
