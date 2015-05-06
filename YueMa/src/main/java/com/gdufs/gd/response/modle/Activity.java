package com.gdufs.gd.response.modle;
import java.util.ArrayList;
import com.gdufs.gd.entity.BaseEntity;

/**
 * 活动类
 * 
 * @author Administrator
 * 
 */
public class Activity extends BaseEntity {
	private static final long serialVersionUID = 1L;
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
	private String category;// 类别
	private String count;// 活动人数
	private String cost;// 活动话费
	private String beginTime;// 开始时间
	private String endTime;// 结束时间
	private String activityAddressLongitude;// 经度
	private String activityAddressLatitude;// 纬度
	private String contactPhoneNum;//联系电话
	private String benifit;//活动好处
	private String addressCity;//活动省市区
	private String addressDetial;//活动的详细地址
	
	private String commentNum;// 评论的数目
	private String likeNum;// 赞的个数(在活动中获取)

	private ArrayList<Joiner> joinerList;// 参与者列表
	private ArrayList<Comment> commentsList;// 评论列表

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

	public String getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getContactPhoneNum() {
		return contactPhoneNum;
	}

	public void setContactPhoneNum(String contactPhoneNum) {
		this.contactPhoneNum = contactPhoneNum;
	}

	public String getBenifit() {
		return benifit;
	}

	public void setBenifit(String benifit) {
		this.benifit = benifit;
	}

	public String getAddressCity() {
		return addressCity;
	}

	public void setAddressCity(String addressCity) {
		this.addressCity = addressCity;
	}

	public String getAddressDetial() {
		return addressDetial;
	}

	public void setAddressDetial(String addressDetial) {
		this.addressDetial = addressDetial;
	}

	@Override
	public String toString() {
		return "Activity [id=" + id + ", activityId=" + activityId
				+ ", creatorId=" + creatorId + ", creator_userName="
				+ creator_userName + ", creator_phoneNum=" + creator_phoneNum
				+ ", creator_facePath=" + creator_facePath
				+ ", createTimeDate=" + createTimeDate + ", picturePath="
				+ picturePath + ", title=" + title + ", introduce=" + introduce
				+ ", category=" + category + ", count=" + count + ", cost="
				+ cost + ", beginTime=" + beginTime + ", endTime=" + endTime
				+ ", activityAddressLongitude=" + activityAddressLongitude
				+ ", activityAddressLatitude=" + activityAddressLatitude
				+ ", contactPhoneNum=" + contactPhoneNum + ", benifit="
				+ benifit + ", addressCity=" + addressCity + ", addressDetial="
				+ addressDetial + ", commentNum=" + commentNum + ", likeNum="
				+ likeNum + ", joinerList=" + joinerList + ", commentsList="
				+ commentsList + "]";
	}

	

}