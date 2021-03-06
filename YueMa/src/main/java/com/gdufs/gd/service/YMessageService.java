package com.gdufs.gd.service;

import java.util.List;

import com.gdufs.gd.entity.YMessage;
import com.gdufs.gd.entity.YMessageUser;

public interface YMessageService {
	public boolean add(YMessage message);
	
	/**
	 * 查找用户的没发送消息
	 * @return
	 */
	public List<YMessageUser> getUserUnSendtMsgs(int userId);
	
	/**
	 * 插入消息
	 * @param message
	 * @return
	 */
	public YMessageUser addMsg(YMessage message);
	
	/**
	 * 查找所有没法送的消息进行推送
	 * @return
	 */
	public List<YMessageUser> getAllUserUnSendtMsgs();
}
