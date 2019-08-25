package com.hst.shrp.service;

import com.hst.shrp.dao.CommonCodeDAO;
import com.hst.shrp.model.api.code.CommonCodesResponse;
import com.hst.shrp.model.api.code.CommonCodesResponse.CommonCode;
import com.hst.shrp.model.entity.EntityCommonCode;
import com.hst.shrp.model.exception.DataNotFoundException;
import com.hst.shrp.model.exception.InvalidParameterException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.function.Function;

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
		if (StringUtils.isEmpty(groupCode)) {
			throw new InvalidParameterException("groupCode is required");
		}
		List<EntityCommonCode> entityCommonCodes = commonCodeDAO.findAllCodesByGrpCd(groupCode);
		if (entityCommonCodes == null || entityCommonCodes.isEmpty()) {
			throw new DataNotFoundException(String.format("groupCode %s has no common code list", groupCode));
		}
		return CommonCodesResponse.of(groupCode, entityCommonCodes);
	}

	/***
	 * search specify common code by group code and sub code
	 * @param groupCode the group code
	 * @param subCode the sub code
	 * @return common code
	 */
	public CommonCode getCommonCode(String groupCode, String subCode) {
		EntityCommonCode commonCode = commonCodeDAO.findCodeByGrpCdAndSubCd(groupCode, subCode);
		if (commonCode == null) {
			throw new DataNotFoundException(String.format("No code founded. groupCode: %s, subCode: %s", groupCode, subCode));
		}
		return CommonCode.convert(commonCode);
	}

	/***
	 * get some value from CommonCode
	 * @param groupCode the group code
	 * @param subCode the sub code
	 * @param gettingFunction value fetching function
	 * @param <R> type of value
	 * @return value
	 */
	public <R> R getCommonCodeAs(String groupCode, String subCode, Function<CommonCode, R> gettingFunction) {
		CommonCode code = getCommonCode(groupCode, subCode);
		return gettingFunction.apply(code);
	}

}
