package com.hst.shrp.dao;

import com.hst.shrp.model.entity.EntityUser;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author dlgusrb0808@gmail.com
 */
@Repository
public interface UserDAO {

	List<EntityUser> findAll();

	EntityUser findOne(String userNm);

	void save(EntityUser entityUser);

	void delete(String userNm);

}
