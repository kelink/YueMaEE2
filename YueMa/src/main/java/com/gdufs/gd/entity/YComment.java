package com.gdufs.gd.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.gdufs.gd.entity.BaseEntity;

/**
 * 活动和用户之间的评价关系表
 * 活动和用户都是多对多
 * 使用YActivityUserPK作为主键标记唯一
 * @author Administrator
 *
 */
@Entity
@Table(name = "YComment")
@IdClass(YActivityUserPK.class)
public class YComment extends BaseEntity {
	private static final long serialVersionUID = 1L;	
	private String content;	
	private Date createTime=new Date();	
	private int isDelete;	
	private int fatherCommentId;
	private String commentPath;
	
	private YActivity activity;//活动
	private YUser user;//对应的用户

	@Id
	public YActivity getActivity() {
		return activity;
	}

	public void setActivity(YActivity activity) {
		this.activity = activity;
	}

	@Id
	public YUser getUser() {
		return user;
	}
	
	public void setUser(YUser user) {
		this.user = user;
	}
	
	@Column(name = "content", length = 200, nullable = false)
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	@Column(name = "createTime", length = 11, nullable = false)
	@DateTimeFormat(pattern = "yyyy-mm-dd hh:mm:ss")
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	@Column(name = "isDelete", length = 11, nullable = false)
	public int getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(int isDelete) {
		this.isDelete = isDelete;
	}
	@Column(name = "fatherCommentId", length = 11, nullable = false)
	public int getFatherCommentId() {
		return fatherCommentId;
	}

	public void setFatherCommentId(int fatherCommentId) {
		this.fatherCommentId = fatherCommentId;
	}
	
	@Column(name = "commentPath", length = 200, nullable = false)
	public String getCommentPath() {
		return commentPath;
	}

	public void setCommentPath(String commentPath) {
		this.commentPath = commentPath;
	}

	@Override
	public String toString() {
		return "YComment [content=" + content + ", createTime=" + createTime
				+ ", isDelete=" + isDelete + ", fatherCommentId="
				+ fatherCommentId + ", commentPath=" + commentPath
				+ ", activityId=" + activity.getId() + ", userId=" + user.getId()+ "]";
	}




	
}
