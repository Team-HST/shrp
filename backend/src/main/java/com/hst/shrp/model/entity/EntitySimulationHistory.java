package com.hst.shrp.model.entity;

import java.time.LocalDateTime;

/**
 * @author hyungyu.lee@nhn.com
 */
public class EntitySimulationHistory {
	private Integer simulNo;
	private String fileNm;
	private LocalDateTime simulDt;
	private String experCd;
	private String ampmCd;

	public Integer getSimulNo() {
		return simulNo;
	}

	public void setSimulNo(Integer simulNo) {
		this.simulNo = simulNo;
	}

	public String getFileNm() {
		return fileNm;
	}

	public void setFileNm(String fileNm) {
		this.fileNm = fileNm;
	}

	public LocalDateTime getSimulDt() {
		return simulDt;
	}

	public void setSimulDt(LocalDateTime simulDt) {
		this.simulDt = simulDt;
	}

	public String getExperCd() {
		return experCd;
	}

	public void setExperCd(String experCd) {
		this.experCd = experCd;
	}

	public String getAmpmCd() {
		return ampmCd;
	}

	public void setAmpmCd(String ampmCd) {
		this.ampmCd = ampmCd;
	}

	@Override
	public String toString() {
		return "EntitySimulationHistory{" + "simulNo=" + simulNo + ", fileNm='" + fileNm + '\'' + ", simulDt=" + simulDt +
				", experCd='" + experCd + '\'' + ", ampmCd='" + ampmCd + '\'' + '}';
	}
}
