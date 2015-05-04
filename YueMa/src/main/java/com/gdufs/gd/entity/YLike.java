package com.gdufs.gd.entity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * 点赞表
 * @author Administrator
 *
 */

@Entity
@Table(name = "YLike",uniqueConstraints = {@UniqueConstraint(columnNames={"activityId", "userId"})})
public class YLike extends BaseEntity {
	
	private static final long serialVersionUID = 1L;
	private int id;
	private int activityId;//活动
	private int userId;//对应的用户
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(length = 11, name = "id")
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public int getActivityId() {
		return activityId;
	}
	public void setActivityId(int activityId) {
		this.activityId = activityId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	@Override
	public String toString() {
		return "YLike [activityId=" + activityId + ", userId=" + userId + "]";
	}
	
	
	
}
