package com.hst.shrp.model.entity;

/**
 * @author dlgusrb0808@gmail.com
 */
public class EntityIndicator {
	private Integer simulNo;
	private Integer crpNo;
	private String drcCd;
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

	public String getDrcCd() {
		return drcCd;
	}

	public void setDrcCd(String drcCd) {
		this.drcCd = drcCd;
	}

	public Double getVal() {
		return val;
	}

	public void setVal(Double val) {
		this.val = val;
	}
}
