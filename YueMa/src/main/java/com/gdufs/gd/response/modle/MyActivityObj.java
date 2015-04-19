package com.gdufs.gd.response.modle;


/**
 * 我的动态里面的历史
 * 
 * @author Administrator
 * 
 */
public class MyActivityObj {
	public static final String COL_HISTORY_ID = "createTime";
	public static final String COL_HISTORY_CREATETIME = "createTime";
	public static final String COL_HISTORY_CREATORID = "creatorId";
	public static final String COL_HISTORY_ACTIVITYID = "activityId";
	public static final String COL_HISTORY_TYPE = "type";
	public static final String COL_HISTORY_CATEGORY = "category";

	private String id;// 历史的Id号
	private String creatorId;// 活动创建者
	private String activityPicture;// 活动的图片
	private String activityId;// 活动的Id
	private String createTime;// 创建日期
	private String typeText;// 历史类型，0表示我发起的，1表示我参与的
	private String title;// 活动的titile
	private String category;// 类型
	private String introduce;//简介
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCreatorId() {
		return creatorId;
	}
	public void setCreatorId(String creatorId) {
		this.creatorId = creatorId;
	}
	public String getActivityPicture() {
		return activityPicture;
	}
	public void setActivityPicture(String activityPicture) {
		this.activityPicture = activityPicture;
	}
	public String getActivityId() {
		return activityId;
	}
	public void setActivityId(String activityId) {
		this.activityId = activityId;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getTypeText() {
		return typeText;
	}
	public void setTypeText(String typeText) {
		this.typeText = typeText;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getIntroduce() {
		return introduce;
	}
	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}
	@Override
	public String toString() {
		return "MyActivityObj [id=" + id + ", creatorId=" + creatorId
				+ ", activityPicture=" + activityPicture + ", activityId="
				+ activityId + ", createTime=" + createTime + ", typeText="
				+ typeText + ", title=" + title + ", category=" + category
				+ ", introduce=" + introduce + "]";
	}


}