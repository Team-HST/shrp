package com.hst.shrp.model.entity;

/**
 * @author dlgusrb0808@gmail.com
 */
public class EntityAnalysisHistory {
	private Integer analNo;
	private Integer simulNo;
	private Integer compSimulNo;
	private String ixCd;
	private String analData;
	private String targetCrpNo;
	private String analDt;
	private String fileNm;

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

	public Integer getCompSimulNo() {
		return compSimulNo;
	}

	public void setCompSimulNo(Integer compSimulNo) {
		this.compSimulNo = compSimulNo;
	}

	public String getAnalData() {
		return analData;
	}

	public void setAnalData(String analData) {
		this.analData = analData;
	}

	public String getTargetCrpNo() {
		return targetCrpNo;
	}

	public void setTargetCrpNo(String targetCrpNo) {
		this.targetCrpNo = targetCrpNo;
	}

	public String getFileNm() {
		return fileNm;
	}

	public void setFileNm(String fileNm) {
		this.fileNm = fileNm;
	}
}
