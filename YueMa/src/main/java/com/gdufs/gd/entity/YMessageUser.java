package com.gdufs.gd.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * 用户--用户消息 之间的匹配关系
 * (多对多)
 * 该activity表示信息
 * 该user表示需要发送提醒的人
 * @author Administrator
 *
 */
@Entity
@Table(name = "YMessageUser")
@IdClass(YMessageUserPK.class)
public class YMessageUser extends BaseEntity {

	private static final long serialVersionUID = 1L;
	private int id;
	private YUser user;
	private YMessage message;
	private int isRead;
	private Date readTime;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(length = 11, name = "id")
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Id
	public YUser getUser() {
		return user;
	}

	public void setUser(YUser user) {
		this.user = user;
	}

	@Id
	public YMessage getMessage() {
		return message;
	}

	public void setMessage(YMessage message) {
		this.message = message;
	}

	@Column(name = "isRead", length = 11, nullable = false)
	public int getIsRead() {
		return isRead;
	}

	public void setIsRead(int isRead) {
		this.isRead = isRead;
	}

	@Column(name = "readTime", length = 11, nullable = false)
	@DateTimeFormat(pattern = "yyyy-mm-dd hh:mm:ss")
	public Date getReadTime() {
		return readTime;
	}

	public void setReadTime(Date readTime) {
		this.readTime = readTime;
	}

	@Override
	public String toString() {
		return "YMessageUser [id=" + id + ", userId=" + user.getId() + ", messageId="
				+ message.getId() + ", isRead=" + isRead + ", readTime=" + readTime
				+ "]";
	}

}
