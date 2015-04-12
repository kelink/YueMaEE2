package com.gdufs.gd.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class YRemindUserPK implements Serializable {

	private static final long serialVersionUID = 1L;

	private YRemind remind;
	private YUser user;

	@ManyToOne
	@JoinColumn(name = "remindId", referencedColumnName = "id")
	public YRemind getRemind() {
		return remind;
	}

	public void setRemind(YRemind remind) {
		this.remind = remind;
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
