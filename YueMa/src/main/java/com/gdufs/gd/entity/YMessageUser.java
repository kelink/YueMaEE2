package com.gdufs.gd.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
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
	private YUser user;
	private YMessage message;
	private int isRead;
	private Date readTime;
	private int isSend;//判断是否已经发送出去


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
	
	@Column(name = "isSend", length = 11, nullable = true,columnDefinition="INT default 0")
	public int getIsSend() {
		return isSend;
	}

	public void setIsSend(int isSend) {
		this.isSend = isSend;
	}

	@Column(name = "readTime", length = 11, nullable = true)
	@DateTimeFormat(pattern = "yyyy-mm-dd hh:mm:ss")
	public Date getReadTime() {
		return readTime;
	}

	public void setReadTime(Date readTime) {
		this.readTime = readTime;
	}

	@Override
	public String toString() {
		return "YMessageUser [userId=" + user.getId() + ", messageId=" + message.getId()
				+ ", isRead=" + isRead + ", readTime=" + readTime + ", isSend="
				+ isSend + "]";
	}




}
