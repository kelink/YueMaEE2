package com.gdufs.gd.serviceImpl;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.gdufs.gd.common.C;
import com.gdufs.gd.dao.YContactDao;
import com.gdufs.gd.dao.YUserDao;
import com.gdufs.gd.entity.TransferMessage;
import com.gdufs.gd.entity.YMessage;
import com.gdufs.gd.entity.YMessageUser;
import com.gdufs.gd.entity.YUser;
import com.gdufs.gd.response.modle.PushMessage;
import com.gdufs.gd.service.YMessageService;
import com.gdufs.gd.service.YUserService;

@Service(value = "userService")
public class YUserServiceImpl implements YUserService {

	@Resource(name = "userDao")
	private YUserDao userDao;
	
	@Resource(name = "contactDao")
	private YContactDao contactDao;
	
	@Resource(name = "messageService")
	private YMessageService messageService;
	
	@Override
	public TransferMessage register(YUser user) {
		TransferMessage message = new TransferMessage();
		
		//2.创建提醒
		
		if (userDao.addUser(user)) {
			//更新关系contact表
			if (contactDao.updateIsSysUserByFriendsNum(user.getPhoneNum())) {
				message.setCode(C.ResponseCode.SUCCESS);
				message.setMessage(C.ResponseMessage.SUCCESS);
				message.setResultMap(null);
			}else {
				message.setCode(C.ResponseCode.ERROR);
				message.setMessage(C.ResponseMessage.REGIST_DB_ERROR);
				message.setResultMap(null);
			}
			
			//1.创建系统发给用户的公告，欢迎用户
			YMessage welcomeMsg=new YMessage();
			welcomeMsg.setContent("欢迎使用悦吗手机应用，一起和朋友约起来！");
			welcomeMsg.setCreateTime(new Date());
			welcomeMsg.setCreator(userDao.getUserById(1));//默认第一个是官方的用户
			welcomeMsg.setTitle("欢迎");
			welcomeMsg.setType(C.DefaultValues.DEFAULT_MSG_TYPE_SYSTEM);//0表示系统消息
			YMessageUser messageUser=new YMessageUser();
			messageUser.setIsRead(0);
			messageUser.setIsSend(0);
			messageUser.setMessage(welcomeMsg);
			messageUser.setUser(user);
			welcomeMsg.getMessageUser().add(messageUser);
			messageService.add(welcomeMsg);//添加欢迎消息
			
		} else {
			message.setCode(C.ResponseCode.ERROR);
			message.setMessage(C.ResponseMessage.REGIST_DB_ERROR);
			message.setResultMap(null);
		}		
		return message;
	}

	@Override
	public YUser CheckUser(YUser user) {	
		return userDao.getUserByNameAndPwd(user.getPhoneNum(), user.getPassword());
	}

	@Override
	public YUser getUserById(int id) {
		return userDao.getUserById(id);
	}

	@Override
	public YUser getUserByPhone(String phoneNum) {
		return userDao.getUserByPhone(phoneNum);
	}

	@Override
	public boolean updateUser(YUser user) {
		return userDao.updateUser(user);
	}



}
