package com.hst.shrp.model.type;

/**
 * @author dlgusrb0808@gmail.com
 */
public enum AnalysisType {
	ALL,
	SINGLE;

	public static final String ALL_TYPE_SIGNATURE = "all";

	public static AnalysisType getAnalysisType(String crossRoadNumber) {
		if (ALL_TYPE_SIGNATURE.equals(crossRoadNumber)) {
			return ALL;
		}
		return SINGLE;
	}

}
