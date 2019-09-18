package com.hst.shrp.service;

import com.hst.shrp.dao.UserDAO;
import com.hst.shrp.model.api.user.UserListResponse;
import com.hst.shrp.model.api.user.UserListResponse.UserResponse;
import com.hst.shrp.model.entity.EntityUser;
import com.hst.shrp.model.exception.AlreadyExistException;
import com.hst.shrp.model.exception.DataNotFoundException;
import com.hst.shrp.model.exception.InvalidParameterException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author dlgusrb0808@gmail.com
 */
@Service
public class UserService {

	private final UserDAO userDAO;

	@Autowired
	public UserService(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	/***
	 * get users
	 * @return users
	 */
	public UserListResponse getUsers() {
		return UserListResponse.of(userDAO.findAll());
	}

	/***
	 * get user
	 * @param userName the userName
	 * @return user
	 */
	public UserResponse getUser(String userName) {
		EntityUser user = userDAO.findOne(userName);
		if (user == null) {
			return null;
		}

		return UserResponse.of(user);
	}

	/***
	 * create new user
	 * @param userName the userName
	 */
	public void createUser(String userName) {
		if (StringUtils.isEmpty(userName)) {
			throw new InvalidParameterException("userName must not be null");
		} else if (getUser(userName) != null) {
			throw new AlreadyExistException(String.format("userName %s is already exist", userName));
		}

		userDAO.save(new EntityUser(userName));
	}

	/***
	 * remove user
	 * @param userName the userName
	 */
	public void deleteUser(String userName) {
		 if (getUser(userName) == null) {
			throw new DataNotFoundException(String.format("userName %s is not exist", userName));
		}

		userDAO.delete(userName);
	}

}
