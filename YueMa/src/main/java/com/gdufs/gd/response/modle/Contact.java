package com.gdufs.gd.response.modle;

import com.gdufs.gd.entity.BaseEntity;

public class Contact extends BaseEntity{
	private int id;
	private String hostNum;
	private String friendNum;
	private int version;
	private int isSysUser;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getHostNum() {
		return hostNum;
	}
	public void setHostNum(String hostNum) {
		this.hostNum = hostNum;
	}
	public String getFriendNum() {
		return friendNum;
	}
	public void setFriendNum(String friendNum) {
		this.friendNum = friendNum;
	}
	public int getVersion() {
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
	}
	public int getIsSysUser() {
		return isSysUser;
	}
	public void setIsSysUser(int isSysUser) {
		this.isSysUser = isSysUser;
	}
	@Override
	public String toString() {
		return "Contact [id=" + id + ", hostNum=" + hostNum + ", friendNum="
				+ friendNum + ", version=" + version + ", isSysUser="
				+ isSysUser + "]";
	}
	
	
	
}
