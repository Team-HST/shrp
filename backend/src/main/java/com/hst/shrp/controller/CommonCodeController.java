package com.hst.shrp.controller;

import com.hst.shrp.model.api.ApiResponse;
import com.hst.shrp.model.api.code.CommonCodesResponse;
import com.hst.shrp.service.CommonCodeService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author dlgusrb0808@gmail.com
 */
@Api( tags = "0. 공통코드 API", description = "공통코드 관련 기능을 제공합니다.")
@RestController
@RequestMapping("/api/codes")
public class CommonCodeController {

	@Autowired
	private CommonCodeService commonCodeService;

	@ApiOperation(value = "공통코드 조회", notes = "공통그룹코드 및 공통코드 목록를 조회합니다.")
	@ApiImplicitParams({
			@ApiImplicitParam(
					name = "groupCode",
					value = "공통그룹코드 (100, 200, ...)",
					paramType = "path",
					dataType = "string",
					required = true
			)
	})
	@ApiResponses({
			@io.swagger.annotations.ApiResponse(code = 200, message = "성공")
	})
	@GetMapping("{groupCode}")
	public ApiResponse<CommonCodesResponse> getCodes(@PathVariable String groupCode) {
		CommonCodesResponse commonCodesResponse = commonCodeService.getCommonCodes(groupCode);
		return new ApiResponse<>(commonCodesResponse);
	}

}
