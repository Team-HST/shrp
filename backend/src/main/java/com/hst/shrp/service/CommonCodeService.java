package com.hst.shrp.service;

import com.hst.shrp.dao.CommonCodeDAO;
import com.hst.shrp.model.api.code.CommonCodesResponse;
import com.hst.shrp.model.entity.EntityCommonCode;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author dlgusrb0808@gmail.com
 */
@Service
public class CommonCodeService {

	private CommonCodeDAO commonCodeDAO;

	public CommonCodeService(CommonCodeDAO commonCodeDAO) {
		this.commonCodeDAO = commonCodeDAO;
	}

	/***
	 * search common code list by group code
	 * @param groupCode the group code
	 * @return common code list in group code
	 */
	public CommonCodesResponse getCommonCodes(String groupCode) {
		// TODO / 이현규 / 그륩코드 발리데이션
		List<EntityCommonCode> entityCommonCodes = commonCodeDAO.findAllCodesByGrpCd(groupCode);
		// TODO / 이현규 / entityCommonCodes 엠티 체크
		return CommonCodesResponse.of(groupCode, entityCommonCodes);
	}

}
