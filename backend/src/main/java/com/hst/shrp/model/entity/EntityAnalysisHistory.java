package com.hst.shrp.model.entity;

/**
 * @author dlgusrb0808@gmail.com
 */
public class EntityAnalysisHistory {
	private Integer analNo;
	private String simulNos;
	private String ixCd;
	private String analData;
	private String targetCrpNo;
	private String analDt;
	private String fileNm;
	private String userNm;
	private Integer basisSimulationNumber;

	public Integer getAnalNo() {
		return analNo;
	}

	public void setAnalNo(Integer analNo) {
		this.analNo = analNo;
	}

	public String getSimulNos() {
		return simulNos;
	}

	public void setSimulNos(String simulNos) {
		this.simulNos = simulNos;
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

	public String getUserNm() {
		return userNm;
	}

	public void setUserNm(String userNm) {
		this.userNm = userNm;
	}

	public Integer getBasisSimulationNumber() {
		return basisSimulationNumber;
	}

	public void setBasisSimulationNumber(Integer basisSimulationNumber) {
		this.basisSimulationNumber = basisSimulationNumber;
	}
}
