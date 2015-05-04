package com.gdufs.gd.task;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.gdufs.gd.daoImpl.YMessageDaoImpl;
import com.gdufs.gd.entity.YMessageUser;
import com.gdufs.gd.relation.CalMain;
import com.gdufs.gd.service.JPushServie;
import com.gdufs.gd.service.YMessageService;
import com.gdufs.gd.serviceImpl.JPushServiecImpl;


//关系计算模块
@Component
public class ScheduleTask {  
	@Resource(name = "messageService")
	private YMessageService messageService;
	
	@Resource(name = "jPushServie")
	private JPushServie jPushServie;
	
    @Scheduled(cron="0/5 * * * * ? ") //间隔5秒执行  
    public void taskCalRelation(){  
    	try {
			CalMain.main(null);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("启动关系计算模块失败...");
		} 
    }
    //定时推送消息

    @Scheduled(cron="0/5 * * * * ? ") //间隔5秒执行  
    public void taskPushMsg(){  
    	System.out.println("推送消息");
    	List<YMessageUser> messageUserList=messageService.getAllUserUnSendtMsgs();
		for (YMessageUser messageUser : messageUserList) {
			jPushServie.sendMessagee(messageUser, new String[]{messageUser.getUser().getPhoneNum()});
		}
    }
    
} 