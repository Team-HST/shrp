package com.hst.shrp.model.api.code;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hst.shrp.model.entity.EntityCommonCode;
import io.swagger.annotations.ApiModelProperty;

import static com.hst.shrp.utils.FunctionalAPI.*;

import java.util.List;
import java.util.Map;

/**
 * @author dlgusrb0808@gmail.com
 */
public class CommonCodesResponse {
	@ApiModelProperty(position = 1, example = "그룹코드")
	private String groupCode;
	@ApiModelProperty(position = 2)
	private List<CommonCode> commonCodes;

	public String getGroupCode() {
		return groupCode;
	}

	public List<CommonCode> getCommonCodes() {
		return commonCodes;
	}

	// Use for service layer
	@JsonIgnore
	public Map<String, String> getCommonCodeMap() {
		return with(commonCodes).toMap(CommonCode::getSubCode, CommonCode::getSubName);
	}

	public static class CommonCode {
		@ApiModelProperty(position = 1, example = "공통그룹코드")
		private String groupCode;
		@ApiModelProperty(position = 2, example = "공통코드")
		private String subCode;
		@ApiModelProperty(position = 3, example = "공통코드명")
		private String subName;
		@ApiModelProperty(position = 4, example = "공통코드설명")
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
		response.commonCodes = with(entityCommonCodes).toList(CommonCode::convert);
		return response;
	}

}
