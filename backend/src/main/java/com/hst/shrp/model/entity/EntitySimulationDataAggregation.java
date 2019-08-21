package com.hst.shrp.model.entity;

/**
 * @author hyungyu.lee@nhn.com
 */
public class EntitySimulationDataAggregation {
	private Integer simulNo;
	private Integer crpNo;
	private Double aggrVal;

	public int getSimulNo() {
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

	public Double getAggrVal() {
		return aggrVal;
	}

	public void setAggrVal(Double aggrVal) {
		this.aggrVal = aggrVal;
	}
}
