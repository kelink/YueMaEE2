package com.gdufs.gd;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gdufs.gd.common.C;
import com.gdufs.gd.dao.YMessageUserDao;
import com.gdufs.gd.entity.TransferMessage;
import com.gdufs.gd.entity.YMessage;
import com.gdufs.gd.entity.YMessageUser;
import com.gdufs.gd.entity.YUser;
import com.gdufs.gd.response.modle.PushMessage;
import com.gdufs.gd.response.modle.User;
import com.gdufs.gd.service.JPushServie;
import com.gdufs.gd.service.YContactService;
import com.gdufs.gd.service.YMessageService;
import com.gdufs.gd.service.YUserService;
import com.gdufs.gd.util.DateUtil;
import com.gdufs.gd.util.JacksonUtil;

@Controller
@RequestMapping("/message")
public class MessageController {
	private static final Logger logger = LoggerFactory
			.getLogger(MessageController.class);

	@Resource(name = "userService")
	private YUserService userService;

	@Resource(name = "contactService")
	private YContactService contactService;

	@Resource(name = "messageService")
	private YMessageService messageService;

	@Resource(name = "jPushServie")
	private JPushServie jPushServie;


	@RequestMapping(value = "/newValidateMessage", method = { RequestMethod.GET,
			RequestMethod.POST })
	@ResponseBody
	public String newValidateMessage(
			final HttpServletRequest request,
			final HttpServletResponse response,
			@RequestParam(value = C.ParamsName.FRIEND_NUM, required = true, defaultValue = "") String friendNum,
			@RequestParam(value = C.ParamsName.FRIENDID, required = true, defaultValue = "0") int frinedId,
			@RequestParam(value = C.ParamsName.UID, required = true, defaultValue = "0") int userId,
			@RequestParam(value = C.ParamsName.PHONE_NUM, required = true, defaultValue = "") String hostNum,
			@RequestParam(value = C.ParamsName.MSG_CONTENT, required = true, defaultValue = "") String content,
			@RequestParam(value = C.ParamsName.MSG_TYPE, required = true, defaultValue = "") int type) {
		// 返回信息
		TransferMessage message = new TransferMessage();
		if (frinedId == 0 || userId == 0 || friendNum.equals("")
				|| hostNum.equals("") || content.equals("")) {
			message.setCode(C.ResponseCode.ERROR);
			message.setMessage(C.ResponseMessage.WRONG_USER_INFO);
			message.setResultMap(null);
		} else {
			// 消息实体
			YMessage newMessage = new YMessage();
			newMessage.setTitle("验证消息");//等待添加
			newMessage.setContent(content);
			newMessage.setCreateTime(new Date());
			newMessage.setCreator(userService.getUserById(userId));
			newMessage.setType(type);// 1表示验证信息
			// 消息发往者
			YMessageUser messageUser = new YMessageUser();
			YUser toUser = userService.getUserById(frinedId);
			messageUser.setIsRead(C.DefaultValues.DEFAULT_IS_READ);// 0表示没读
			messageUser.setMessage(newMessage);
			messageUser.setReadTime(null);
			messageUser.setUser(toUser);
			messageUser.setIsSend(C.DefaultValues.DEFAULT_IS_SEND);//0表示没有发送成功
			newMessage.getMessageUser().add(messageUser);
			YMessageUser returnMessageUser=messageService.addMsg(newMessage);
			if (returnMessageUser!=null) {
				message.setCode(C.ResponseCode.SUCCESS);
				message.setMessage(C.ResponseMessage.SUCCESS);
				message.setResultMap(null);	
				jPushServie.sendMessagee(returnMessageUser, new String[]{toUser.getPhoneNum()});//马上发送，有异常再说
			} else {
				message.setCode(C.ResponseCode.ERROR);
				message.setMessage(C.ResponseMessage.ERROR);
				message.setResultMap(null);
			}
		}
		return JacksonUtil.writeEntity2JSON(message);
	}
	
	
	
	

	// 在用户登陆后进行查询消息，并且发送
	//废弃不用
	@RequestMapping(value = "/getMyMessage", method = { RequestMethod.GET,
			RequestMethod.POST })
	@ResponseBody
	public String getMyMessage(
			final HttpServletRequest request,
			final HttpServletResponse response,
			@RequestParam(value = C.ParamsName.UID, required = true, defaultValue = "0") int userId,
			@RequestParam(value = C.ParamsName.PHONE_NUM, required = true, defaultValue = "") String phoneNum) {
		// 返回信息
		TransferMessage message = new TransferMessage();
		if (userId == 0 || phoneNum.equals("")) {
			message.setCode(C.ResponseCode.ERROR);
			message.setMessage(C.ResponseMessage.ERROR);
			message.setResultMap(null);
		} else {
			List<YMessageUser> messageUserList=messageService.getUserUnSendtMsgs(userId);
			System.out.println("unsend msg:"+messageUserList.toString());
			for (YMessageUser messageUser : messageUserList) {
				jPushServie.sendMessagee(messageUser, new String[]{messageUser.getUser().getPhoneNum()});
			}
			message.setCode(C.ResponseCode.SUCCESS);
			message.setMessage(C.ResponseMessage.SUCCESS);
			message.setResultMap(null);
		}
		return JacksonUtil.writeEntity2JSON(message);
	}
	
	//新发送的消息
	@RequestMapping(value = "/newMessage", method = { RequestMethod.GET,
			RequestMethod.POST })
	@ResponseBody
	public String newMessage(
			final HttpServletRequest request,
			@RequestParam(value = C.ParamsName.FRIENDID, required = true, defaultValue = "0") int frinedId,
			@RequestParam(value = C.ParamsName.UID, required = true, defaultValue = "0") int userId,
			@RequestParam(value = C.ParamsName.PHONE_NUM, required = true, defaultValue = "") String hostNum,
			@RequestParam(value = C.ParamsName.MSG_CONTENT, required = true, defaultValue = "") String content,
			@RequestParam(value = C.ParamsName.MSG_TYPE, required = true, defaultValue = "0") int type,
			@RequestParam(value = C.ParamsName.TITLE, required = true, defaultValue = "") String title) {
		// 返回信息
		TransferMessage message = new TransferMessage();
		if (frinedId == 0 || userId == 0 || hostNum.equals("") || content.equals("")) {
			message.setCode(C.ResponseCode.ERROR);
			message.setMessage(C.ResponseMessage.WRONG_USER_INFO);
			message.setResultMap(null);
		} else {
			// 消息实体
			YMessage newMessage = new YMessage();
			newMessage.setTitle(title);//等待添加
			newMessage.setContent(content);
			newMessage.setCreateTime(new Date());
			newMessage.setCreator(userService.getUserById(userId));
			newMessage.setType(type);// 1表示验证信息
			// 消息发往者
			YMessageUser messageUser = new YMessageUser();
			YUser toUser = userService.getUserById(frinedId);
			messageUser.setIsRead(C.DefaultValues.DEFAULT_IS_READ);// 0表示没读
			messageUser.setMessage(newMessage);
			messageUser.setReadTime(null);
			messageUser.setUser(toUser);
			messageUser.setIsSend(C.DefaultValues.DEFAULT_IS_SEND);//0表示没有发送成功
			newMessage.getMessageUser().add(messageUser);
			YMessageUser returnMessageUser=messageService.addMsg(newMessage);
			if (returnMessageUser!=null) {
				//返回发送者发送的消息给发送者
				PushMessage pushMessage = new PushMessage();
				pushMessage.setCreateTime(DateUtil.formatDateTime(returnMessageUser.getMessage().getCreateTime()));
				pushMessage.setContent_type(String
						.valueOf(C.DefaultValues.DEFAULT_MSG_TYPE_CHAT));

				pushMessage.setSenderId(String
						.valueOf(returnMessageUser.getMessage().getCreator().getId()));
				pushMessage.setSenderName(returnMessageUser.getMessage().getCreator().getUserName());
				pushMessage.setSenderPhoneNum(returnMessageUser.getMessage().getCreator().getPhoneNum());
				pushMessage.setTitle(returnMessageUser.getMessage().getTitle());
				pushMessage.setMsgContent(returnMessageUser.getMessage().getContent().trim());
				pushMessage.setToUserId(String
						.valueOf(returnMessageUser.getUser().getId()));// 发送者的id
				pushMessage.setId(String.valueOf(returnMessageUser.getMessage().getId()));
				HashMap<String, PushMessage> returnData=new HashMap<String, PushMessage>();
				returnData.put("result", pushMessage);
				
				message.setCode(C.ResponseCode.SUCCESS);
				message.setMessage(C.ResponseMessage.SUCCESS);
				message.setResultMap(returnData);	
				jPushServie.sendMessagee(returnMessageUser, new String[]{toUser.getPhoneNum()});//马上发送，有异常再说
				
			} else {
				message.setCode(C.ResponseCode.ERROR);
				message.setMessage(C.ResponseMessage.ERROR);
				message.setResultMap(null);
			}
		}
		return JacksonUtil.writeEntity2JSON(message);
	}
}

	
