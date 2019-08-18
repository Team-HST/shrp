package com.hst.shrp.model.entity;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author dlgusrb0808@gmail.com
 */
public class EntityAnalysisHistory {
	private Integer analNo;
	private Integer simulNo;
	private String analDt;
	private String ixCd;

	public Integer getAnalNo() {
		return analNo;
	}

	public void setAnalNo(Integer analNo) {
		this.analNo = analNo;
	}

	public Integer getSimulNo() {
		return simulNo;
	}

	public void setSimulNo(Integer simulNo) {
		this.simulNo = simulNo;
	}

	public String getAnalDt() {
		return analDt;
	}

	public void setAnalDt(String analDt) {
		this.analDt = analDt;
	}

	public String getIxCd() {
		return ixCd;
	}

	public void setIxCd(String ixCd) {
		this.ixCd = ixCd;
	}
}
