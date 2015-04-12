//package com.gdufs.gd.entity;
//
//import java.io.Serializable;
//
//import javax.activity.ActivityCompletedException;
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.JoinColumn;
//import javax.persistence.ManyToOne;
//import javax.persistence.Table;
//
///**
// * 标签表
// * 
// * @author Administrator
// *
// */
//@Entity
//@Table(name = "YLabel")
//public class YLabel implements Serializable {
//
//	private static final long serialVersionUID = 1L;
//	private int id;
//	private String content;
//	
//
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	@Column(name = "id", length = 11, nullable = false)
//	public int getId() {
//		return id;
//	}
//
//	public void setId(int id) {
//		this.id = id;
//	}
//
//	@Column(name = "content", length = 50, nullable = false)
//	public String getContent() {
//		return content;
//	}
//
//	public void setContent(String content) {
//		this.content = content;
//	}
//	
//
//	@Override
//	public String toString() {
//		return "YLabel [id=" + id + ", content=" + content + "]";
//	}
//
//
//}
