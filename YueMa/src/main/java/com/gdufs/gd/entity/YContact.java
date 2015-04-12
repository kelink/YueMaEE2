package com.gdufs.gd.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * 用户联系表，也是一度人脉表
 * 
 * @author Administrator
 *
 */
@Entity
@Table(name = "YContact",uniqueConstraints = {@UniqueConstraint(columnNames={"hostNum", "friendNum"})})
public class YContact extends BaseEntity{

	private static final long serialVersionUID = 1L;

	
	private int id;
	private String hostNum;
	private String friendNum;
	private int version;
	private int isSysUser;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", length = 11)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name = "hostNum", length = 20 ,nullable = false)
	public String getHostNum() {
		return hostNum;
	}

	public void setHostNum(String hostNum) {
		this.hostNum = hostNum;
	}
	@Column(name = "friendNum", length = 20,nullable = false)
	public String getFriendNum() {
		return friendNum;
	}

	public void setFriendNum(String friendNum) {
		this.friendNum = friendNum;
	}
	@Column(name = "version", length = 11, nullable = false,columnDefinition="INT default 0")
	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public int getIsSysUser() {
		return isSysUser;
	}
	
	@Column(name = "isSysUser", length = 11, nullable = false,columnDefinition="INT default 0")
	public void setIsSysUser(int isSysUser) {
		this.isSysUser = isSysUser;
	}

	@Override
	public String toString() {
		return "YContact [id=" + id + ", hostNum=" + hostNum + ", friendNum="
				+ friendNum + ", version=" + version + ", isSysUser="
				+ isSysUser + "]";
	}

}
