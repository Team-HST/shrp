package com.hst.shrp.controller;

import com.hst.shrp.model.api.ApiResponse;
import org.springframework.web.bind.annotation.*;

/**
 * @author dlgusrb0808@gmail.com
 */
@RestController
@RequestMapping("/api/user")
public class UserController {

	// TODO / User 등록, 삭제, 목록 조회

	@GetMapping
	public ApiResponse getUserList() {
		return new ApiResponse();
	}

	@PostMapping("{userName}")
	public ApiResponse createUser(@PathVariable String userName) {
		return new ApiResponse();
	}

	@DeleteMapping("{userName}")
	public ApiResponse deleteUser(@PathVariable String userName) {
		return new ApiResponse();
	}

}
