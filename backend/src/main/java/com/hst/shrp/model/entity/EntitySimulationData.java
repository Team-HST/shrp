package com.hst.shrp.model.entity;

/**
 * @author hyungyu.lee@nhn.com
 */
public class EntitySimulationData {
	private Integer simulNo;
	private Integer crpNo;
	private String drcNm;
	private Double val;

	public Integer getSimulNo() {
		return simulNo;
	}

	public void setSimulNo(Integer simulNo) {
		this.simulNo = simulNo;
	}

	public Integer getCrpNo() {
		return crpNo;
	}

	public void setCrpNo(Integer crpNo) {
		this.crpNo = crpNo;
	}

	public String getDrcNm() {
		return drcNm;
	}

	public void setDrcNm(String drcNm) {
		this.drcNm = drcNm;
	}

	public Double getVal() {
		return val;
	}

	public void setVal(Double val) {
		this.val = val;
	}
}
