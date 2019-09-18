package com.hst.shrp.controller;

import com.hst.shrp.model.api.ApiResponse;
import com.hst.shrp.model.api.user.UserListResponse;
import com.hst.shrp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author dlgusrb0808@gmail.com
 */
@RestController
@RequestMapping("/api/user")
public class UserController {

	@Autowired
	private UserService userService;


	@GetMapping
	public ApiResponse<UserListResponse> getUserList() {
		return new ApiResponse<>(userService.getUsers());
	}

	@PostMapping("{userName}")
	public ApiResponse createUser(@PathVariable String userName) {
		userService.createUser(userName);
		return new ApiResponse();
	}

	@DeleteMapping("{userName}")
	public ApiResponse deleteUser(@PathVariable String userName) {
		userService.deleteUser(userName);
		return new ApiResponse();
	}

}
