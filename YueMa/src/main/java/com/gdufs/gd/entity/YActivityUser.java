package com.gdufs.gd.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * 表示用户-活动之间的参与关系
 * @author Administrator
 *
 */
@Entity
@Table(name = "YActivityUser")
@IdClass(YActivityUserPK.class)
public class YActivityUser implements Serializable {

	private static final long serialVersionUID = 1L;
	private YActivity activity;//活动
	private YUser user;//对应的用户
	private Date join_time=new Date();
	private int isTickOff;//是否剔除，默认值为0
	private int isAuth;//是否验证，默认为0
	private int type;//0表示新创建，1表示参与

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

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "join_time", length = 50, nullable = true)
	@DateTimeFormat(pattern = "yyyy-mm-dd hh:mm:ss")
	public Date getJoin_time() {
		return join_time;
	}

	public void setJoin_time(Date join_time) {
		this.join_time = join_time;
	}

	@Column(name = "isTickOff", length = 11, nullable = false)
	public int getIsTickOff() {
		return isTickOff;
	}

	public void setIsTickOff(int isTickOff) {
		this.isTickOff = isTickOff;
	}
	
	@Column(name = "isAuth", length = 50, nullable = true,columnDefinition="INT default 0")
	public int getIsAuth() {
		return isAuth;
	}

	public void setIsAuth(int isAuth) {
		this.isAuth = isAuth;
	}

	@Column(name = "type", length = 50, nullable = false)
	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "YActivityUser [activityId=" + activity.getId()+ ", userId=" + user.getId()
				+ ", join_time=" + join_time + ", isTickOff=" + isTickOff
				+ ", isAuth=" + isAuth + ", type=" + type + "]";
	}

	/**
	 * Jackson 序列化和toString 方法有关，记得覆写
	 */
	

	
	
	

}