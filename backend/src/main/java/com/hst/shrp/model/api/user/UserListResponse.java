package com.hst.shrp.model.api.user;

import com.hst.shrp.model.entity.EntityUser;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

import static com.hst.shrp.utils.FunctionalAPI.with;

/**
 * @author dlgusrb0808@gmail.com
 */
public class UserListResponse {
	@ApiModelProperty(position = 1)
	private List<UserResponse> users;

	public List<UserResponse> getUsers() {
		return users;
	}

	public static class UserResponse {
		@ApiModelProperty(position = 1, example = "사용자명")
		private String userName;
		@ApiModelProperty(position = 2, example = "등록일자")
		private String registrationDate;

		public String getUserName() {
			return userName;
		}

		public String getRegistrationDate() {
			return registrationDate;
		}

		public static UserResponse of(EntityUser entity) {
			UserResponse user = new UserResponse();
			user.userName = entity.getUserNm();
			user.registrationDate = entity.getRegDt();
			return user;
		}
	}

	public static UserListResponse of(List<EntityUser> userEntityList) {
		UserListResponse response = new UserListResponse();
		response.users = with(userEntityList).toList(UserResponse::of);
		return response;
	}

}
