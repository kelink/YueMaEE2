package com.gdufs.gd.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class YMessageUserPK implements Serializable {

	private static final long serialVersionUID = 1L;

	public YMessageUserPK() {
	}

	private YMessage message;
	private YUser user;

	@ManyToOne
	@JoinColumn(name = "messageId", referencedColumnName = "id")
	public YMessage getMessage() {
		return message;
	}

	public void setMessage(YMessage message) {
		this.message = message;
	}

	@ManyToOne
	@JoinColumn(name = "userId", referencedColumnName = "id")
	public YUser getUser() {
		return user;
	}

	public void setUser(YUser user) {
		this.user = user;
	}

}
