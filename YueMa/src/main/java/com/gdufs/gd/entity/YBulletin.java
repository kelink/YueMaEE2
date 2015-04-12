package com.gdufs.gd.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Type;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * 系统公告表
 * 
 * @author Administrator
 *
 */
@Entity
@Table(name = "YBulletin")
public class YBulletin extends BaseEntity{
	private static final long serialVersionUID = 1L;
	private int id;
	private int userType;//发送公告的用户类型
	private String title;//公告标题
	private String content;//公告内容
	private Date createTime=new Date();//公告创建日期
	private int isCancle;//是否取消公告
	private Date cancelTime=new Date();//取消公告的日期
	private int isDelete;//是否删除，默认值为0,删除表示为1
	private Date deleteTime=new Date();//删除时间
	private int priority;//公告的优先级别，默认为0
	
	private Set<YBulletinUser> YBulletinUser;//代表公告和user之间的对应关系
	
	@OneToMany(mappedBy = "bulletin")
	public Set<YBulletinUser> getYBulletinUser() {
		return YBulletinUser;
	}

	public void setYBulletinUser(Set<YBulletinUser> yBulletinUser) {
		YBulletinUser = yBulletinUser;
	}

	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", length = 11, nullable = false)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name = "userType", length = 11, nullable = false)
	public int getUserType() {
		return userType;
	}

	public void setUserType(int userType) {
		this.userType = userType;
	}

	@Column(name = "title", length = 50, nullable = false)
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Type(type = "text")
	@Column(name = "content", nullable = false)
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "createTime", length = 30, nullable = true)
	@DateTimeFormat(pattern = "yyyy-mm-dd hh:mm:ss")
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Column(name = "isCancle", length = 11, nullable = false,columnDefinition="INT default 0")
	public int getIsCancle() {
		return isCancle;
	}

	public void setIsCancle(int isCancle) {
		this.isCancle = isCancle;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "cancelTime", nullable = true)
	@DateTimeFormat(pattern = "yyyy-mm-dd hh:mm:ss")
	public Date getCancelTime() {
		return cancelTime;
	}

	public void setCancelTime(Date cancelTime) {
		this.cancelTime = cancelTime;
	}

	@Column(name = "isDelete", length = 11, nullable = false,columnDefinition="INT default 0")
	public int getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(int isDelete) {
		this.isDelete = isDelete;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "deleteTime", nullable = true)
	@DateTimeFormat(pattern = "yyyy-mm-dd hh:mm:ss")
	public Date getDeleteTime() {
		return deleteTime;
	}

	public void setDeleteTime(Date deleteTime) {
		this.deleteTime = deleteTime;
	}

	@Column(name = "priority", length = 11, nullable = false,columnDefinition="INT default 0")
	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
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
		YBulletin other = (YBulletin) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "YBulletin [id=" + id + ", userType=" + userType + ", title="
				+ title + ", content=" + content + ", createTime=" + createTime
				+ ", isCancle=" + isCancle + ", cancelTime=" + cancelTime
				+ ", isDelete=" + isDelete + ", deleteTime=" + deleteTime
				+ ", priority=" + priority + "]";
	}

}
