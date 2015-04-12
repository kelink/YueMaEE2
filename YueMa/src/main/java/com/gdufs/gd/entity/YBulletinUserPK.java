package com.gdufs.gd.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * 公告和用户的对应表的PK
 * 
 * @author Administrator
 *
 */
@Embeddable
public class YBulletinUserPK implements Serializable {

	private static final long serialVersionUID = 1L;
	private YBulletin bulletin;
	private YUser user;

	@ManyToOne
	@JoinColumn(name = "bulletinId", referencedColumnName = "id")
	public YBulletin getBulletin() {
		return bulletin;
	}

	public void setBulletin(YBulletin bulletin) {
		this.bulletin = bulletin;
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
