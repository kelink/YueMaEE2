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
	public static final String COL_JOINER_FACEPATH = "joineFacePath";

	private String id;
	private String activityId;
	private String uId;
	private String joineFacePath;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getActivityId() {
		return activityId;
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

	@Override
	public String toString() {
		return "Joiner [id=" + id + ", activityId=" + activityId + ", uId="
				+ uId + ", joineFacePath=" + joineFacePath + "]";
	}

}
