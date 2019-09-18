package com.hst.shrp.controller;

import com.hst.shrp.model.api.ApiResponse;
import com.hst.shrp.model.api.user.UserListResponse;
import com.hst.shrp.service.UserService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author dlgusrb0808@gmail.com
 */
@Api( tags = "1. 사용자 관리 API", description = "사용자 등록, 조회, 삭제 기능을 제공합니다.")
@RestController
@RequestMapping("/api/user")
public class UserController {

	@Autowired
	private UserService userService;

	@ApiOperation(value = "사용자 목록 조회", notes = "사용자 목록을 조회합니다.")
	@ApiResponses({
			@io.swagger.annotations.ApiResponse(code = 200, message = "성공")
	})
	@GetMapping
	public ApiResponse<UserListResponse> getUserList() {
		return new ApiResponse<>(userService.getUsers());
	}

	@ApiOperation(value = "신규 사용자 등록", notes = "신규 사용자를 등록합니다.")
	@ApiImplicitParams({
			@ApiImplicitParam(
					name = "userName",
					value = "등록할 사용자 이름",
					paramType = "path",
					dataType = "string",
					required = true
			)
	})
	@ApiResponses({
		@io.swagger.annotations.ApiResponse(code = 200, message = "성공"),
		@io.swagger.annotations.ApiResponse(code = 400, message = "동일한 이름의 사용자가 존재")
	})
	@PostMapping("{userName}")
	public ApiResponse createUser(@PathVariable String userName) {
		userService.createUser(userName);
		return new ApiResponse();
	}

	@ApiOperation(value = "신규 사용자 삭제", notes = "사용자를 삭제합니다.")
	@ApiImplicitParams({
			@ApiImplicitParam(
					name = "userName",
					value = "삭제할 사용자 이름",
					paramType = "path",
					dataType = "string",
					required = true
			)
	})
	@ApiResponses({
			@io.swagger.annotations.ApiResponse(code = 200, message = "성공"),
			@io.swagger.annotations.ApiResponse(code = 404, message = "삭제하려는 사용자가 존재하지 않음")
	})
	@DeleteMapping("{userName}")
	public ApiResponse deleteUser(@PathVariable String userName) {
		userService.deleteUser(userName);
		return new ApiResponse();
	}

}
