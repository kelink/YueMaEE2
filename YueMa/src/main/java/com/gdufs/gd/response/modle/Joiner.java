package com.gdufs.gd.response.modle;

import com.gdufs.gd.entity.BaseEntity;

/**
 * 参与者的表 (临时存储系信息，便于分页查找和无网络下面的查找)
 * 
 * @author Administrator
 * 
 */
public class Joiner extends BaseEntity {

	private static final long serialVersionUID = 1L;
	public static final String COL_ID = "id";
	public static final String COL_ACTIVITY_ID = "activityId";
	public static final String COL_USER_ID = "uId";
	public static final String COL_JOINER_USERNAME = "joinerUserName";
	public static final String COL_JOINER_FACEPATH = "joineFacePath";
	public static final String COL_JOINER_JOIN_TIME = "joinTime";
	public static final String COL_JOINER_INTRODUCE = "introduce";
	public static final String COL_JOINER_PHONE_NUM = "joinerPhoneNum";

	private String id;
	private String activityId;
	private String uId;
	private String joineFacePath;
	private String joinerUserName;// 姓名
	private String joinTime;// 加入的时间
	private String introduce;// 简介
	private String joinerPhoneNum;// 电话号码

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getActivityId() {
		return activityId;
	}

	public String getJoinerUserName() {
		return joinerUserName;
	}

	public void setJoinerUserName(String joinerUserName) {
		this.joinerUserName = joinerUserName;
	}

	public void setActivityId(String activityId) {
		this.activityId = activityId;
	}

	public String getuId() {
		return uId;
	}

	public void setuId(String uId) {
		this.uId = uId;
	}

	public String getJoineFacePath() {
		return joineFacePath;
	}

	public void setJoineFacePath(String joineFacePath) {
		this.joineFacePath = joineFacePath;
	}

	public String getJoinTime() {
		return joinTime;
	}

	public void setJoinTime(String joinTime) {
		this.joinTime = joinTime;
	}

	public String getIntroduce() {
		return introduce;
	}

	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}

	public String getJoinerPhoneNum() {
		return joinerPhoneNum;
	}

	public void setJoinerPhoneNum(String joinerPhoneNum) {
		this.joinerPhoneNum = joinerPhoneNum;
	}

	@Override
	public String toString() {
		return "Joiner [id=" + id + ", activityId=" + activityId + ", uId="
				+ uId + ", joineFacePath=" + joineFacePath
				+ ", joinerUserName=" + joinerUserName + ", joinTime="
				+ joinTime + ", introduce=" + introduce + ", joinerPhoneNum="
				+ joinerPhoneNum + "]";
	}
}