package com.hst.shrp.model.entity;

import java.time.LocalDateTime;

/**
 * @author dlgusrb0808@gmail.com
 */
public class EntityUser {
	private String userNm;
	private String regDt;

	public EntityUser() {
	}

	public EntityUser(String userName) {
		this.userNm = userName;
	}

	public String getUserNm() {
		return userNm;
	}

	public void setUserNm(String userNm) {
		this.userNm = userNm;
	}

	public String getRegDt() {
		return regDt;
	}

	public void setRegDt(String regDt) {
		this.regDt = regDt;
	}
}
