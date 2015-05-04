package com.gdufs.gd.dao;

import java.util.List;

import com.gdufs.gd.entity.YMessage;
import com.gdufs.gd.entity.YMessageUser;

public interface YMessageUserDao {
	public  List<YMessageUser> getMessageUserByUser(int userId);
	/**
	 * 更新对象
	 * @param messageUser
	 * @return
	 */
	public boolean updateMessageUser(YMessageUser messageUser);
	
	public YMessageUser getMessageUserById(int userId,int messageId);
	
	public List<YMessageUser> getUserUnSendtMsgs(int userId);
	
	/**
	 * 添加消息
	 * @param message
	 * @return
	 */
	public YMessageUser addMsg(YMessage message);
	
	/**
	 * 获取所有没有发送的消息
	 * @return
	 */
	public List<YMessageUser> getAllUserUnSendtMsgs();
}
