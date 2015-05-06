package com.gdufs.gd.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "YActivity")
public class YActivity implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private String picturePath;// 活动的相片
	private String category;// 活动的类别
	private String creatorPhoneNum;// 创建者的电话(便于后面的关系更新)
	private String title;// 活动的title或者name
	private String introduce;// 活动的简介
	private Date createTimeDate = new Date();// 活动创建日期
	private Date beginTime = new Date();// 活动开始时间
	private Date endTime = new Date();// 活动结束时间
	private double cost;// 活动预期花费每个人
	private String activityAddressLongitude;// 经度
	private String activityAddressLatitude;// 纬度
	private int count;// 最多人数
	
	private String contactPhoneNum;//联系电话
	private String benifit;//活动好处
	private String addressCity;//活动省市区
	private String addressDetial;//活动的详细地址

	private YUser creator;// 创建者
	private Set<YActivityUser> activityUsers = new HashSet<YActivityUser>();// 参与者
	private Set<YComment> comments = new HashSet<YComment>();// 活动的评论
//	private Set<YLabel> labels = new HashSet<YLabel>();// 活动归属的标签
//	private Set<YPicture> pictures = new HashSet<YPicture>();// 活动的全部图片	
	private Set<YLike> likes=new HashSet<YLike>();

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(length = 11, name = "id")
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@OneToMany(mappedBy = "activity", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	public Set<YActivityUser> getActivityUsers() {
		return activityUsers;
	}

	public void setActivityUsers(Set<YActivityUser> activityUsers) {
		this.activityUsers = activityUsers;
	}

	@OneToMany(mappedBy = "activity", cascade = CascadeType.ALL, fetch = FetchType.LAZY,targetEntity = YComment.class) 
	public Set<YComment> getComments() {
		return comments;
	}

	public void setComments(Set<YComment> comments) {
		this.comments = comments;
	}

//	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, targetEntity = YLabel.class)
//	@JoinColumn(name = "activityId")
//	public Set<YLabel> getLabels() {
//		return labels;
//	}
//
//	public void setLabels(Set<YLabel> labels) {
//		this.labels = labels;
//	}
//
//	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, targetEntity = YPicture.class)
//	@JoinColumn(name = "activityId")
//	public Set<YPicture> getPictures() {
//		return pictures;
//	}
//
//	public void setPictures(Set<YPicture> pictures) {
//		this.pictures = pictures;
//	}
	

	@Column(name = "title", length = 50, nullable = false)
	public String getTitle() {
		return title;
	}

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, targetEntity = YLike.class)
	@JoinColumn(name = "activityId")
	public Set<YLike> getLikes() {
		return likes;
	}

	public void setLikes(Set<YLike> likes) {
		this.likes = likes;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "introduce", length = 100, nullable = false)
	public String getIntroduce() {
		return introduce;
	}

	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "createTimeDate", length = 50, nullable = true)
	@DateTimeFormat(pattern = "yyyy-mm-dd hh:mm:ss")
	public Date getCreateTimeDate() {
		return createTimeDate;
	}

	public void setCreateTimeDate(Date createTimeDate) {
		this.createTimeDate = createTimeDate;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "beginTime", length = 50, nullable = false)
	@DateTimeFormat(pattern = "yyyy-mm-dd hh:mm:ss")
	public Date getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "endTime", length = 50, nullable = false)
	@DateTimeFormat(pattern = "yyyy-mm-dd hh:mm:ss")
	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	@Column(name = "cost", length = 20, nullable = false)
	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	
	@Column(name = "contactPhoneNum", length = 20, nullable = false)
	public String getContactPhoneNum() {
		return contactPhoneNum;
	}

	public void setContactPhoneNum(String contactPhoneNum) {
		this.contactPhoneNum = contactPhoneNum;
	}

	@Column(name = "benifit", length = 100, nullable = true)
	public String getBenifit() {
		return benifit;
	}

	public void setBenifit(String benifit) {
		this.benifit = benifit;
	}
	@Column(name = "addressCity", length = 100, nullable = false)
	public String getAddressCity() {
		return addressCity;
	}

	public void setAddressCity(String addressCity) {
		this.addressCity = addressCity;
	}

	@Column(name = "addressDetial", length = 100, nullable = false)
	public String getAddressDetial() {
		return addressDetial;
	}

	public void setAddressDetial(String addressDetial) {
		this.addressDetial = addressDetial;
	}

	@Column(name = "activityAddressLongitude", length = 50, nullable = false)
	public String getActivityAddressLongitude() {
		return activityAddressLongitude;
	}

	public void setActivityAddressLongitude(String activityAddressLongitude) {
		this.activityAddressLongitude = activityAddressLongitude;
	}

	@Column(name = "activityAddressLatitude", length = 50, nullable = false)
	public String getActivityAddressLatitude() {
		return activityAddressLatitude;
	}

	public void setActivityAddressLatitude(String activityAddressLatitude) {
		this.activityAddressLatitude = activityAddressLatitude;
	}

	@Column(name = "count", length = 11, nullable = false)
	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	@ManyToOne
	@JoinColumn(name = "creatorId")
	public YUser getCreator() {
		return creator;
	}

	public void setCreator(YUser creator) {
		this.creator = creator;
	}

	@Column(name = "creatorPhoneNum", length = 20, nullable = false)
	public String getCreatorPhoneNum() {
		return creatorPhoneNum;
	}

	public void setCreatorPhoneNum(String creatorPhoneNum) {
		this.creatorPhoneNum = creatorPhoneNum;
	}


	@Column(name = "picturePath", length = 100, nullable = false)
	public String getPicturePath() {
		return picturePath;
	}

	public void setPicturePath(String picturePath) {
		this.picturePath = picturePath;
	}

	@Column(name = "category", length = 50, nullable = false)
	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		YActivity other = (YActivity) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "YActivity [id=" + id + ", picturePath=" + picturePath
				+ ", category=" + category + ", creatorPhoneNum="
				+ creatorPhoneNum + ", title=" + title + ", introduce="
				+ introduce + ", createTimeDate=" + createTimeDate
				+ ", beginTime=" + beginTime + ", endTime=" + endTime
				+ ", cost=" + cost + ", activityAddressLongitude="
				+ activityAddressLongitude + ", activityAddressLatitude="
				+ activityAddressLatitude + ", count=" + count
				+ ", contactPhoneNum=" + contactPhoneNum + ", benifit="
				+ benifit + ", addressCity=" + addressCity + ", addressDetial="
				+ addressDetial + "]";
	}



	


}