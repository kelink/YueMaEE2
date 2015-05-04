package com.gdufs.gd.response.modle;

import java.util.Date;

import com.gdufs.gd.entity.BaseEntity;
import com.gdufs.gd.util.DateUtil;



/**
 * 用户接收到的推动的消息类
 * 
 * @author Administrator
 * 
 */
public class PushMessage extends BaseEntity {
	private static final long serialVersionUID = 1L;
	// PushMessage 表
	public static final String PUSHMESSAGE_TABLE_NAME = "PushMessage";
	public static final String PUSHMESSAGE_TABLE_ID = "id";
	public static final String PUSHMESSAGE_TABLE_TITLE = "title";
	public static final String PUSHMESSAGE_TABLE_MSGCONTENT = "msgContent";
	public static final String PUSHMESSAGE_TABLE_EXTRA = "extra";
	public static final String PUSHMESSAGE_TABLE_CONTENT_TYPE = "content_type";
	public static final String PUSHMESSAGE_TABLE_ISREAD = "isRead";
	public static final String PUSHMESSAGE_TABLE_SENDER_ID = "senderId";
	public static final String PUSHMESSAGE_TABLE_SENDER_NAME = "senderName";

	public static final String PUSHMESSAGE_TABLE_SENDER_FACE_PATH = "senderFacePath";
	public static final String PUSHMESSAGE_TABLE_SENDER_PHONE_NUM = "senderPhoneNum";
	public static final String PUSHMESSAGE_TABLE_CREATE_TIME = "createTime";
	public static final String PUSHMESSAGE_TABLE_TOUSERID = "toUserId";

	private String id;
	private String title;
	private String msgContent;
	private String extra;
	private String content_type;
	private String isRead;
	private String senderId;// 发送者的id
	private String senderName;// 发送者的名字
	
	private String senderFacePath;// 发送者头像
	private String senderPhoneNum;// 发送者手机号
	private String createTime;// 创建 的日期
	private String toUserId;// 代表发给谁
	


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getMsgContent() {
		return msgContent;
	}

	public void setMsgContent(String msgContent) {
		this.msgContent = msgContent;
	}

	public String getExtra() {
		return extra;
	}

	public void setExtra(String extra) {
		this.extra = extra;
	}

	public String getIsRead() {
		return isRead;
	}

	public void setIsRead(String isRead) {
		this.isRead = isRead;
	}

	public String getContent_type() {
		return content_type;
	}

	public void setContent_type(String content_type) {
		this.content_type = content_type;
	}

	public String getSenderId() {
		return senderId;
	}

	public void setSenderId(String senderId) {
		this.senderId = senderId;
	}

	public String getSenderName() {
		return senderName;
	}

	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}

	public String getSenderFacePath() {
		return senderFacePath;
	}

	public void setSenderFacePath(String senderFacePath) {
		this.senderFacePath = senderFacePath;
	}

	public String getSenderPhoneNum() {
		return senderPhoneNum;
	}

	public void setSenderPhoneNum(String senderPhoneNum) {
		this.senderPhoneNum = senderPhoneNum;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getToUserId() {
		return toUserId;
	}

	public void setToUserId(String toUserId) {
		this.toUserId = toUserId;
	}

	@Override
	public String toString() {
		return "PushMessage [id=" + id + ", title=" + title + ", msgContent="
				+ msgContent + ", extra=" + extra + ", content_type="
				+ content_type + ", isRead=" + isRead + ", senderId="
				+ senderId + ", senderName=" + senderName + ", senderFacePath="
				+ senderFacePath + ", senderPhoneNum=" + senderPhoneNum
				+ ", createTime=" + createTime + ", toUserId=" + toUserId + "]";
	}
}
