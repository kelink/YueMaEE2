//package com.gdufs.gd.entity;
//
//import java.util.Date;
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.Table;
//import javax.persistence.Temporal;
//import javax.persistence.TemporalType;
//
//import org.springframework.format.annotation.DateTimeFormat;
//
///**
// * 用户参与或者发起活动的历史记录
// * 
// * @author Administrator
// *
// */
//
//@Entity
//@Table(name = "YHistory")
//public class YHistory extends BaseEntity {
//
//	private static final long serialVersionUID = 1L;
//	private int id;
//	private int userId;
//	private int activityId;
//	private Date createTime=new Date();//历史创建的消息
//	private int type;//0表示新建活动，1表示参与活动
//	
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	@Column(name = "id", length = 11)
//	public int getId() {
//		return id;
//	}
//
//	public void setId(int id) {
//		this.id = id;
//	}
//
//	@Column(name = "userId", length = 11 ,nullable = false)
//	public int getUserId() {
//		return userId;
//	}
//	public void setUserId(int userId) {
//		this.userId = userId;
//	}
//	@Column(name = "activityId", length = 11 ,nullable = false)
//	public int getActivityId() {
//		return activityId;
//	}
//	public void setActivityId(int activityId) {
//		this.activityId = activityId;
//	}
//	
//	@Temporal(TemporalType.TIMESTAMP)
//	@Column(name = "createTime", length = 50, nullable = true)
//	@DateTimeFormat(pattern = "yyyy-mm-dd hh:mm:ss")
//	public Date getCreateTime() {
//		return createTime;
//	}
//	public void setCreateTime(Date createTime) {
//		this.createTime = createTime;
//	}
//	@Column(name = "type", length = 11 ,nullable = false)
//	public int getType() {
//		return type;
//	}
//	public void setType(int type) {
//		this.type = type;
//	}
//	@Override
//	public String toString() {
//		return "YHistory [userId=" + userId + ", activityId=" + activityId
//				+ ", createTime=" + createTime + ", type=" + type + "]";
//	}
//	
//	
//	
//}
