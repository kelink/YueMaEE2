package com.gdufs.gd.response.modle;

import java.util.ArrayList;
import java.util.Date;

/**
 * 首页圈子信息的列表
 * 
 * @author Administrator
 * 
 */
public class FriendsActivityObj {
	public static final String USERID = "uId";
	public static final String USERNAME = "userName";
	public static final String PHONENUM = "phoneNum";
	public static final String FACEPATH = "facePath";
	public static final String CREATETIMEDATE = "createTimeDate";
	public static final String PICTUREPATH = "picturePath";
	public static final String TITLE = "title";
	public static final String INTRODUCE = "introduce";

	private int userId;// 用户id
	private String userName;// 用户名字
	private String phoneNum;// 用户电话号码
	private String facePath;// 用户的照片
	private Date createTimeDate;// 活动创建日期
	private String picturePath;// 活动的相片
	private String title;// 活动的title或者name
	private String introduce;// 活动的简介
	
	private ArrayList<Integer> joinerIdList;//参与者的Id
	private ArrayList<String> joinerFaceList;//参与者的facepath

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public String getFacePath() {
		return facePath;
	}

	public void setFacePath(String facePath) {
		this.facePath = facePath;
	}

	public Date getCreateTimeDate() {
		return createTimeDate;
	}

	public void setCreateTimeDate(Date createTimeDate) {
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

	public ArrayList<Integer> getJoinerIdList() {
		return joinerIdList;
	}

	public void setJoinerIdList(ArrayList<Integer> joinerIdList) {
		this.joinerIdList = joinerIdList;
	}

	public ArrayList<String> getJoinerFaceList() {
		return joinerFaceList;
	}

	public void setJoinerFaceList(ArrayList<String> joinerFaceList) {
		this.joinerFaceList = joinerFaceList;
	}

	@Override
	public String toString() {
		return "FriendsActivityObj [userId=" + userId + ", userName="
				+ userName + ", phoneNum=" + phoneNum + ", facePath="
				+ facePath + ", createTimeDate=" + createTimeDate
				+ ", picturePath=" + picturePath + ", title=" + title
				+ ", introduce=" + introduce + ", joinerIdList=" + joinerIdList
				+ ", joinerFaceList=" + joinerFaceList + "]";
	}


}
