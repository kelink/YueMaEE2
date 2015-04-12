package com.gdufs.gd.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 二度人脉表
 * 
 * @author Administrator
 *
 */
@Entity
@Table(name = "YRelationSecond")
public class YRelationSecond extends BaseEntity{

	private static final long serialVersionUID = 1L;
	private int id;
	private String hostNum;
	private String friendNum;	
	private String middle;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(length = 11, name = "id")
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
	@Column(name = "middle", length = 20,nullable = false)
	public String getMiddle() {
		return middle;
	}

	public void setMiddle(String middle) {
		this.middle = middle;
	}

	@Override
	public String toString() {
		return "YRelationSecond [id=" + id + ", hostNum=" + hostNum
				+ ", friendNum=" + friendNum + ", middle=" + middle + "]";
	}

}
