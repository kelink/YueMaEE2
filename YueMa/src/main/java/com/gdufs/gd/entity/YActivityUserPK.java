package com.gdufs.gd.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class YActivityUserPK implements Serializable {

	private static final long serialVersionUID = 1L;
	private YActivity activity;
	private YUser user;

	@ManyToOne
	@JoinColumn(name = "userId", referencedColumnName = "id")
	public YUser getUser() {
		return user;
	}

	public void setUser(YUser user) {
		this.user = user;
	}

	public void setActivity(YActivity activity) {
		this.activity = activity;
	}

	@ManyToOne
	@JoinColumn(name = "activityId", referencedColumnName = "id")
	public YActivity getActivity() {
		return activity;
	}

}