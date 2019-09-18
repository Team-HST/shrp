package com.hst.shrp.model.entity;

import java.time.LocalDateTime;

/**
 * @author dlgusrb0808@gmail.com
 */
public class EntityUser {
	private String userName;
	private LocalDateTime regDt;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public LocalDateTime getRegDt() {
		return regDt;
	}

	public void setRegDt(LocalDateTime regDt) {
		this.regDt = regDt;
	}
}
