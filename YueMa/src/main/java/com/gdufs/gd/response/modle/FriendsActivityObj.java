package com.gdufs.gd.response.modle;

import java.util.ArrayList;

import com.gdufs.gd.entity.BaseEntity;



/**
 * 首页圈子信息的列表 (临时存储系信息，便于分页查找和无网络下面的查找)
 * 
 * @author Administrator
 * 
 */
public class FriendsActivityObj extends BaseEntity {
	private static final long serialVersionUID = 1L;
	public static final String COL_ID = "id";// 唯一标志
	public static final String COL_ACTIVITYID = "acitvityId";
	public static final String COL_CREATORID = "creatorId";
	public static final String COL_USERNAME = "creator_userName";
	public static final String COL_PHONENUM = "creator_phoneNum";
	public static final String COL_FACEPATH = "creator_facePath";
	public static final String COL_CREATETIMEDATE = "createTimeDate";
	public static final String COL_PICTUREPATH = "picturePath";
	public static final String COL_TITLE = "title";
	public static final String COL_INTRODUCE = "introduce";
	public static final String COL_COMMENT_NUM = "commentNum";
	public static final String COL_LIKE_NUM = "likeNum";

	private String id;
	private String activityId;// 活动的id号
	private String creatorId;// 创建者用户id
	private String creator_userName;// 创建者的名字
	private String creator_phoneNum;// 创建者的电话号码
	private String creator_facePath;// 创建者的照片
	private String createTimeDate;// 活动创建日期
	private String picturePath;// 活动的相片
	private String title;// 活动的title或者name
	private String introduce;// 活动的简介
	private String category;//类别
	private String count;//活动人数
	private String cost;//活动话费
	private String activityAddress;// 活动地点
	private String activityAddressLongitude;// 经度
	private String activityAddressLatitude;// 纬度
	
	private String commentNum;// 评论的数目
	private String likeNum;// 赞的个数(在活动中获取)

	private ArrayList<Joiner> joinerList;// 参与者列表
	private ArrayList<Comment> commentsList;//评论列表
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
	public String getCreatorId() {
		return creatorId;
	}
	public void setCreatorId(String creatorId) {
		this.creatorId = creatorId;
	}
	public String getCreator_userName() {
		return creator_userName;
	}
	public void setCreator_userName(String creator_userName) {
		this.creator_userName = creator_userName;
	}
	public String getCreator_phoneNum() {
		return creator_phoneNum;
	}
	public void setCreator_phoneNum(String creator_phoneNum) {
		this.creator_phoneNum = creator_phoneNum;
	}
	public String getCreator_facePath() {
		return creator_facePath;
	}
	public void setCreator_facePath(String creator_facePath) {
		this.creator_facePath = creator_facePath;
	}
	public String getCreateTimeDate() {
		return createTimeDate;
	}
	public void setCreateTimeDate(String createTimeDate) {
		this.createTimeDate = createTimeDate;
	}
	public String getPicturePath() {
		return picturePath;
	}
	public void setPicturePath(String picturePath) {
		this.picturePath = picturePath;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getIntroduce() {
		return introduce;
	}
	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getCount() {
		return count;
	}
	public void setCount(String count) {
		this.count = count;
	}
	public String getCost() {
		return cost;
	}
	public void setCost(String cost) {
		this.cost = cost;
	}
	public String getActivityAddress() {
		return activityAddress;
	}
	public void setActivityAddress(String activityAddress) {
		this.activityAddress = activityAddress;
	}
	public String getActivityAddressLongitude() {
		return activityAddressLongitude;
	}
	public void setActivityAddressLongitude(String activityAddressLongitude) {
		this.activityAddressLongitude = activityAddressLongitude;
	}
	public String getActivityAddressLatitude() {
		return activityAddressLatitude;
	}
	public void setActivityAddressLatitude(String activityAddressLatitude) {
		this.activityAddressLatitude = activityAddressLatitude;
	}
	public String getCommentNum() {
		return commentNum;
	}
	public void setCommentNum(String commentNum) {
		this.commentNum = commentNum;
	}
	public String getLikeNum() {
		return likeNum;
	}
	public void setLikeNum(String likeNum) {
		this.likeNum = likeNum;
	}
	public ArrayList<Joiner> getJoinerList() {
		return joinerList;
	}
	public void setJoinerList(ArrayList<Joiner> joinerList) {
		this.joinerList = joinerList;
	}
	public ArrayList<Comment> getCommentsList() {
		return commentsList;
	}
	public void setCommentsList(ArrayList<Comment> commentsList) {
		this.commentsList = commentsList;
	}
	
	
}