package com.gdufs.gd.serviceImpl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.gdufs.gd.dao.YActivityDao;
import com.gdufs.gd.dao.YMessageDao;
import com.gdufs.gd.dao.YMessageUserDao;
import com.gdufs.gd.entity.YMessage;
import com.gdufs.gd.entity.YMessageUser;
import com.gdufs.gd.service.YMessageService;
@Service(value = "messageService")
public class YMessageServiceImpl  implements YMessageService{

	
	@Resource(name = "messageDao")
	private YMessageDao messageDao;
	
	@Resource(name = "messageUserDao")
	private YMessageUserDao messageUserDao;
	
	@Override
	public boolean add(YMessage message) {
		return messageDao.add(message);
		
	}
	/**
	 * 查找用户没发送的消息
	 */
	@Override
	public List<YMessageUser> getUserUnSendtMsgs(int userId) {
		return messageUserDao.getUserUnSendtMsgs(userId);
	}
	/**
	 * 添加验证消息
	 */
	@Override
	public YMessageUser addMsg(YMessage message) {
		return messageUserDao.addMsg(message);
	}
	/**
	 * 获取所有没发送的消息
	 */
	@Override
	public List<YMessageUser> getAllUserUnSendtMsgs() {		
		return messageUserDao.getAllUserUnSendtMsgs();
	}

}
