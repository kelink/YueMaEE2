package com.gdufs.gd.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.databind.deser.Deserializers.Base;

/**
 * 消息提醒实体表
 * 
 * @author Administrator
 *
 */
@Entity
@Table(name = "YRemind")
public class YRemind extends BaseEntity {

	private static final long serialVersionUID = 1L;
	private int id;
	private int type;
	private YUser user;
	private String content;
	private Date createTime;
	private Set<YRemindUser> remindUsers;// 提醒的人

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", length = 11, nullable = false)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name = "type", length = 11, nullable = false)
	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	// 单向多对一
	@ManyToOne
	@JoinColumn(name = "userId")
	public YUser getUser() {
		return user;
	}

	public void setUser(YUser user) {
		this.user = user;
	}

	@Type(type = "text")
	@Column(name = "content", nullable = false)
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "createTime", length = 50, nullable = false)
	@DateTimeFormat(pattern = "yyyy-mm-dd hh:mm:ss")
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@OneToMany(mappedBy = "remind")
	public Set<YRemindUser> getRemindUsers() {
		return remindUsers;
	}

	public void setRemindUsers(Set<YRemindUser> remindUsers) {
		this.remindUsers = remindUsers;
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
		YRemind other = (YRemind) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "YRemind [id=" + id + ", type=" + type + ", userId="
				+ user.getId() + ", content=" + content + ", createTime="
				+ createTime + "]";
	}

}
