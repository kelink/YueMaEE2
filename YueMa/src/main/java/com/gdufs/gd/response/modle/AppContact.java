package com.gdufs.gd.response.modle;

import com.gdufs.gd.entity.BaseEntity;

public class AppContact extends BaseEntity {

	private static final long serialVersionUID = 1L;

	private String friendNum;
	private String friendName;
	private String isSysUser = "0";// 是否为系统用户,0不是，1是
	private String userId="";//用户的id,默认无

	public String getFriendNum() {
		return friendNum;
	}

	public void setFriendNum(String friendNum) {
		this.friendNum = friendNum;
	}

	public String getFriendName() {
		return friendName;
	}

	public void setFriendName(String friendName) {
		this.friendName = friendName;
	}

	public String getIsSysUser() {
		return isSysUser;
	}

	public void setIsSysUser(String isSysUser) {
		this.isSysUser = isSysUser;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "AppContact [friendNum=" + friendNum + ", friendName="
				+ friendName + ", isSysUser=" + isSysUser + ", userId="
				+ userId + "]";
	}


}
