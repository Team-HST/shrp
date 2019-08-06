package com.hst.shrp.controller;

import com.hst.shrp.model.api.ApiResponse;
import com.hst.shrp.model.api.code.CommonCodesResponse;
import com.hst.shrp.service.CommonCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author dlgusrb0808@gmail.com
 */
@RestController
@RequestMapping("/api/codes")
public class CommonCodeController {

	@Autowired
	private CommonCodeService commonCodeService;

	/***
	 * GET /api/codes/{groupCode}
	 *
	 * @param groupCode the groupCode
	 * @return
	 */
	@GetMapping("{groupCode}")
	public ApiResponse<CommonCodesResponse> getCodes(@PathVariable String groupCode) {
		CommonCodesResponse commonCodesResponse = commonCodeService.getCommonCodes(groupCode);
		return new ApiResponse<>(commonCodesResponse);
	}

}
