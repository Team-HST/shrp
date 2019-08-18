package com.hst.shrp.model.api.code;

import com.hst.shrp.model.entity.EntityCommonCode;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author dlgusrb0808@gmail.com
 */
public class CommonCodesResponse {
	private String groupCode;
	private List<CommonCode> commonCodes;

	public String getGroupCode() {
		return groupCode;
	}

	public List<CommonCode> getCommonCodes() {
		return commonCodes;
	}

	public Map<String, String> getCommonCodeMap() {
		return commonCodes.stream().collect(Collectors.toMap(CommonCode::getSubCode, CommonCode::getSubName));
	}

	public static class CommonCode {
		private String groupCode;
		private String subCode;
		private String subName;
		private String description;

		public String getGroupCode() {
			return groupCode;
		}

		public String getSubCode() {
			return subCode;
		}

		public String getSubName() {
			return subName;
		}

		public String getDescription() {
			return description;
		}

		public static CommonCode convert(EntityCommonCode entity) {
			CommonCode commonCode = new CommonCode();
			commonCode.groupCode = entity.getGrpCd();
			commonCode.subCode = entity.getSubCd();
			commonCode.subName = entity.getSubNm();
			commonCode.description = entity.getDesc();
			return commonCode;
		}
	}

	public static CommonCodesResponse of(String groupCode, List<EntityCommonCode> entityCommonCodes) {
		CommonCodesResponse response = new CommonCodesResponse();
		response.groupCode = groupCode;
		response.commonCodes = entityCommonCodes.stream().map(CommonCode::convert).collect(Collectors.toList());
		return response;
	}

}
