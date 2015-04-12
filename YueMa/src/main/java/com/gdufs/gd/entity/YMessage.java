package com.gdufs.gd.entity;

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

/**
 * 用户之间的信息类
 * 
 * @author Administrator
 *
 */
@Entity
@Table(name = "YMessage")
public class YMessage extends BaseEntity {
	private static final long serialVersionUID = 1L;
	private int id;
	private YUser creator;//创建者
	private String content;
	private Date createTime;
	private int type;
	private Set<YMessageUser> messageUser;//表示发送的

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(length = 11, name = "id")
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	//单向多对一
	@ManyToOne
	@JoinColumn(name="creatorId")
	public YUser getCreator() {
		return creator;
	}

	public void setCreator(YUser creator) {
		this.creator = creator;
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

	@Column(name = "type", nullable = false)
	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	@OneToMany(mappedBy = "message")
	public Set<YMessageUser> getMessageUser() {
		return messageUser;
	}

	public void setMessageUser(Set<YMessageUser> messageUser) {
		this.messageUser = messageUser;
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
		YMessage other = (YMessage) obj;
		if (id != other.id)
			return false;
		return true;
	}

}
