package com.gdufs.gd.entity;

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
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "YUser")
public class YUser extends BaseEntity {
	private static final long serialVersionUID = 1L;
	private int id;
	private String userName;
	private String phoneNum;
	private String password;
	private String facePath;
	private String introduce;
	private Date createTime;
	private Date lastLoginTimeDate;
	private String lastLoginIp;
	private String gender;//性别
	private String origin;//家乡
	private Set<YActivity> activities;//创建的活动(多对一的关系，和activity双向)
	private Set<YComment> comments=new HashSet<YComment>();//用户发出的关于活动的评论
	private Set<YActivityUser> activityUsers=new HashSet<YActivityUser>();//参与关系，表示参与的活动
	private Set<YBulletinUser> bulletinUsers=new HashSet<YBulletinUser>();//发给该用户的公告
	private Set<YMessageUser> messageUsers=new HashSet<YMessageUser>();//发给该用户的信息
	private Set<YRemindUser> remindUsers=new HashSet<YRemindUser>();//发给该用户的提醒
	private int isLogin;//登陆状态
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(length = 11, name = "id")
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@OneToMany(mappedBy = "user")
	public Set<YActivityUser> getActivityUsers() {
		return activityUsers;
	}

	public void setActivityUsers(Set<YActivityUser> activityUsers) {
		this.activityUsers = activityUsers;
	}

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY,targetEntity = YComment.class) 
	public Set<YComment> getComments() {
		return comments;
	}

	public void setComments(Set<YComment> comments) {
		this.comments = comments;
	}

	@OneToMany(mappedBy = "user")
	public Set<YBulletinUser> getBulletinUsers() {
		return bulletinUsers;
	}

	public void setBulletinUsers(Set<YBulletinUser> bulletinUsers) {
		this.bulletinUsers = bulletinUsers;
	}

	@OneToMany(mappedBy = "user")
	public Set<YMessageUser> getMessageUsers() {
		return messageUsers;
	}

	public void setMessageUsers(Set<YMessageUser> messageUsers) {
		this.messageUsers = messageUsers;
	}

	@OneToMany(mappedBy = "user")
	public Set<YRemindUser> getRemindUsers() {
		return remindUsers;
	}

	public void setRemindUsers(Set<YRemindUser> remindUsers) {
		this.remindUsers = remindUsers;
	}

	@Column(name = "userName", length = 20, nullable = false)
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	
	@Column(name = "gender", length = 20, nullable = true)
	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}
	@Column(name = "origin", length = 20, nullable = true)
	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	@Column(length = 13, nullable = false)
	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	@Column(length = 100, nullable = false)
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	@Type(type = "text")
	@Column(length = 50,nullable=true)
	public String getFacePath() {
		return facePath;
	}

	public void setFacePath(String facePath) {
		this.facePath = facePath;
	}

	@Column(length = 20)
	public String getIntroduce() {
		return introduce;
	}

	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}

	@Column(length = 50, nullable = false)
	@DateTimeFormat(pattern = "yyyy-mm-dd hh:mm:ss")
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Column(length = 50)
	@DateTimeFormat(pattern = "yyyy-mm-dd hh:mm:ss")
	public Date getLastLoginTimeDate() {
		return lastLoginTimeDate;
	}

	public void setLastLoginTimeDate(Date lastLoginTimeDate) {
		this.lastLoginTimeDate = lastLoginTimeDate;
	}
	
	@Column(name="isLogin",length = 11,nullable=false,columnDefinition="INT default 0")
	public int getIsLogin() {
		return isLogin;
	}

	public void setIsLogin(int isLogin) {
		this.isLogin = isLogin;
	}

	@Column(length = 50)
	public String getLastLoginIp() {
		return lastLoginIp;
	}

	public void setLastLoginIp(String lastLoginIp) {
		this.lastLoginIp = lastLoginIp;
	}

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, targetEntity = YActivity.class)
	@JoinColumn(name = "creatorId")
	public Set<YActivity> getActivities() {
		return activities;
	}

	public void setActivities(Set<YActivity> activities) {
		this.activities = activities;
	}

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
		YUser other = (YUser) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "YUser [id=" + id + ", userName=" + userName + ", phoneNum="
				+ phoneNum + ", password=" + password + ", facePath="
				+ facePath + ", introduce=" + introduce + ", createTime="
				+ createTime + ", lastLoginTimeDate=" + lastLoginTimeDate
				+ ", lastLoginIp=" + lastLoginIp + "]";
	}

	
	
}