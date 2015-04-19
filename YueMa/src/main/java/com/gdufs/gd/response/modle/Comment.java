package com.gdufs.gd.response.modle;

import com.gdufs.gd.entity.BaseEntity;
public class Comment extends BaseEntity {

	private static final long serialVersionUID = 1L;
	private String ID;
	private String content;
	private String createTime;
	private String isDelete;
	private String fatherCommentId;
	private String commentPath;

	private String userId;
	private String userFace;

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(String isDelete) {
		this.isDelete = isDelete;
	}

	public String getFatherCommentId() {
		return fatherCommentId;
	}

	public void setFatherCommentId(String fatherCommentId) {
		this.fatherCommentId = fatherCommentId;
	}

	public String getCommentPath() {
		return commentPath;
	}

	public void setCommentPath(String commentPath) {
		this.commentPath = commentPath;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserFace() {
		return userFace;
	}

	public void setUserFace(String userFace) {
		this.userFace = userFace;
	}

	@Override
	public String toString() {
		return "Comment [ID=" + ID + ", content=" + content + ", createTime="
				+ createTime + ", isDelete=" + isDelete + ", fatherCommentId="
				+ fatherCommentId + ", commentPath=" + commentPath
				+ ", userId=" + userId + ", userFace=" + userFace + "]";
	}

}
