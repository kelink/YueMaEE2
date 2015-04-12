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
 * 消息提醒用户实体表
 * 
 * @author Administrator
 *
 */
@Entity
@Table(name = "YRemindUser")
@IdClass(YRemindUserPK.class)
public class YRemindUser extends BaseEntity {

	private static final long serialVersionUID = 1L;
	private int id;
	private YUser user;
	private YRemind remind;
	private int isRead;
	private Date readTime;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", length = 11, nullable = false)
	public int getId() {
		return id;
	}

	@Id
	public YUser getUser() {
		return user;
	}

	public void setUser(YUser user) {
		this.user = user;
	}

	@Id
	public YRemind getRemind() {
		return remind;
	}

	public void setRemind(YRemind remind) {
		this.remind = remind;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name = "isRead", length = 11, nullable = false)
	public int getIsRead() {
		return isRead;
	}

	public void setIsRead(int isRead) {
		this.isRead = isRead;
	}

	@Column(name = "readTime", length = 50, nullable = false)
	@DateTimeFormat(pattern = "yyyy-mm-dd hh:mm:ss")
	public Date getReadTime() {
		return readTime;
	}

	public void setReadTime(Date readTime) {
		this.readTime = readTime;
	}

	@Override
	public String toString() {
		return "YRemindUser [id=" + id + ", userId=" + user.getId() + ", remindId="
				+ remind.getId() + ", isRead=" + isRead + ", readTime=" + readTime
				+ "]";
	}

}
