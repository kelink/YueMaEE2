package com.gdufs.gd.serviceImpl;

import java.util.HashMap;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gdufs.gd.dao.YActivityDao;
import com.gdufs.gd.dao.YMessageUserDao;
import com.gdufs.gd.entity.YMessage;
import com.gdufs.gd.entity.YMessageUser;
import com.gdufs.gd.response.modle.PushMessage;
import com.gdufs.gd.service.JPushServie;
import com.gdufs.gd.util.DateUtil;
import com.gdufs.gd.util.JPushUtil;

import cn.jpush.api.push.model.PushPayload;

@Service(value = "jPushServie")
public class JPushServiecImpl implements JPushServie{

	protected static final Logger LOG = LoggerFactory
			.getLogger(JPushServiecImpl.class);

	@Resource(name = "messageUserDao")
	private YMessageUserDao messageUserDao;

	@Override
	@Transactional
	public void sendMessagee(YMessageUser messageUser, String[] alias) {		
		//发送的消息
		String title=messageUser.getMessage().getTitle();
		String msgContent=messageUser.getMessage().getContent();
		String contentType=String.valueOf(messageUser.getMessage().getType());	
		HashMap<String, String> extraMap=new HashMap<String, String>();
		extraMap.put(PushMessage.PUSHMESSAGE_TABLE_ID, String.valueOf(messageUser.getMessage().getId()));
		extraMap.put(PushMessage.PUSHMESSAGE_TABLE_SENDER_ID, String.valueOf(messageUser.getMessage().getCreator().getId()));
		extraMap.put(PushMessage.PUSHMESSAGE_TABLE_SENDER_PHONE_NUM, messageUser.getMessage().getCreator().getPhoneNum());
		extraMap.put(PushMessage.PUSHMESSAGE_TABLE_SENDER_FACE_PATH, messageUser.getMessage().getCreator().getFacePath());
		extraMap.put(PushMessage.PUSHMESSAGE_TABLE_SENDER_NAME, messageUser.getMessage().getCreator().getUserName());
		extraMap.put(PushMessage.PUSHMESSAGE_TABLE_CREATE_TIME, DateUtil.formatDateTime(messageUser.getMessage().getCreateTime()));	
		extraMap.put(PushMessage.PUSHMESSAGE_TABLE_TOUSERID, String.valueOf(messageUser.getUser().getId()));
		PushPayload pushPayload=JPushUtil.buildPushObject_android_ios_audience_More_messageWithExtras(alias,title,msgContent,contentType,extraMap);
		try {
			JPushUtil.sendPush(pushPayload);
			//发送的消息
			System.out.println("id:"+messageUser.getMessage().getId()+"\n");
			System.out.println("title:"+title+"\n");
			System.out.println("msgContent:"+msgContent+"\n");
			System.out.println("contentType:"+contentType+"\n");
			System.out.println("extraMap:"+extraMap+"\n");
			//设置发送成功后的消息
			messageUser.setIsSend(1);
			messageUserDao.updateMessageUser(messageUser);
		} catch (Exception e) {
			//保存数据库
			System.out.println("异常，需要保存数据库");
		}	
		
	}
	

		
}