package com.gdufs.gd;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gdufs.gd.common.C;
import com.gdufs.gd.entity.TransferMessage;
import com.gdufs.gd.entity.YContact;
import com.gdufs.gd.entity.YMessage;
import com.gdufs.gd.entity.YMessageUser;
import com.gdufs.gd.entity.YUser;
import com.gdufs.gd.response.modle.AppContact;
import com.gdufs.gd.response.modle.FriendsActivityObj;
import com.gdufs.gd.response.modle.User;
import com.gdufs.gd.service.JPushServie;
import com.gdufs.gd.service.YContactService;
import com.gdufs.gd.service.YMessageService;
import com.gdufs.gd.service.YUserService;
import com.gdufs.gd.util.JacksonUtil;

@Controller
@RequestMapping("/contact")
public class ContactController {
	private static final Logger logger = LoggerFactory
			.getLogger(ContactController.class);

	@Resource(name = "contactService")
	private YContactService contactService;

	@Resource(name = "userService")
	private YUserService userService;

	@Resource(name = "messageService")
	private YMessageService messageService;

	@Resource(name = "jPushServie")
	private JPushServie jPushServie;

	/**
	 * 添加通讯录 （发生更改时候需要更新一度和二度人脉关系表）
	 * 
	 * @param contactList
	 * @return
	 */
	@RequestMapping(value = "/addContact", method = { RequestMethod.GET,
			RequestMethod.POST })
	@ResponseBody
	public String addContact(
			@RequestParam(value = C.ParamsName.UID, defaultValue = "") int uId,
			@RequestParam(value = C.ParamsName.PHONE_NUM, defaultValue = "") String phoneNum,
			@RequestParam(value = C.ParamsName.CONTACT, defaultValue = "") String contactStr) {
		System.out.println("phoneNum--------->" + phoneNum);
		System.out.println("contactStr------>" + contactStr);
		TransferMessage messageObj = new TransferMessage();
		if (phoneNum.equals("") || contactStr.equals("")) {
			messageObj.setCode(C.ResponseCode.ERROR);
			messageObj.setMessage(C.ResponseMessage.REQUEST_ERROR);
			messageObj.setResultMap(null);
		} else {
			String[] list = contactStr.split("-");
			List<YContact> contactList = new ArrayList<YContact>();
			for (String item : list) {
				YContact contact = new YContact();
				contact.setId(uId);
				contact.setHostNum(phoneNum);
				contact.setFriendNum(item);
				contact.setVersion(0);
				contactList.add(contact);
			}
			if (contactService.addContactList(contactList)) {
				// List<YContact> contacts = contactService
				// .getFirstPeople(phoneNum);
				//
				// HashMap<String, List<YContact>> resultMap = new
				// HashMap<String, List<YContact>>();

				// 修改为回复的是用户对象而不是联系表对象
				List<YUser> contacts = contactService.getFirstUser(phoneNum);
				HashMap<String, ArrayList<AppContact>> resultMap = new HashMap<String, ArrayList<AppContact>>();
				ArrayList<AppContact> result = new ArrayList<AppContact>();
				for (YUser user : contacts) {
					AppContact appContact = new AppContact();
					appContact.setFriendName(user.getUserName());
					appContact.setIsSysUser("1");// 1表示为系统用户
					appContact.setFriendNum(user.getPhoneNum());
					appContact.setUserId(String.valueOf(user.getId()));
					result.add(appContact);
				}
				resultMap.put("result", result);
				messageObj.setCode(C.ResponseCode.SUCCESS);
				messageObj.setMessage(C.ResponseMessage.SUCCESS);
				messageObj.setResultMap(resultMap);

			} else {
				messageObj.setCode(C.ResponseCode.ERROR);
				messageObj.setMessage(C.ResponseMessage.ERROR);
				messageObj.setResultMap(null);
				logger.error("add contact fail");
			}
		}
		return JacksonUtil.writeEntity2JSON(messageObj);
	}

	/**
	 * 获取一度人脉 （通讯录）
	 */
	@RequestMapping(value = "/getFirstPeople", method = { RequestMethod.GET,
			RequestMethod.POST })
	@ResponseBody
	public String getFirstPeople(
			@RequestParam(value = C.ParamsName.PHONE_NUM) String phoneNum) {
		TransferMessage messageObj = new TransferMessage();
		if (phoneNum.equals("")) {
			messageObj.setCode(C.ResponseCode.ERROR);
			messageObj.setMessage(C.ResponseMessage.REQUEST_ERROR);
			messageObj.setResultMap(null);
		} else {
			// List<YContact> contacts=contactService.getFirstPeople(phoneNum);
			// HashMap<String,List<YContact>> resultMap=new
			// HashMap<String,List<YContact>>();
			// resultMap.put("result", contacts);
			// messageObj.setCode(C.ResponseCode.SUCCESS);
			// messageObj.setMessage(C.ResponseMessage.SUCCESS);
			// messageObj.setResultMap(resultMap);
			List<YUser> contacts = contactService.getFirstUser(phoneNum);
			HashMap<String, ArrayList<AppContact>> resultMap = new HashMap<String, ArrayList<AppContact>>();
			ArrayList<AppContact> result = new ArrayList<AppContact>();
			for (YUser user : contacts) {
				AppContact appContact = new AppContact();
				appContact.setFriendName(user.getUserName());
				appContact.setIsSysUser("1");// 1表示为系统用户
				appContact.setFriendNum(user.getPhoneNum());
				appContact.setUserId(String.valueOf(user.getId()));
				result.add(appContact);
			}
			resultMap.put("result", result);
			messageObj.setCode(C.ResponseCode.SUCCESS);
			messageObj.setMessage(C.ResponseMessage.SUCCESS);
			messageObj.setResultMap(resultMap);
		}
		return JacksonUtil.writeEntity2JSON(messageObj);
	}

	/**
	 * 获取二度人脉
	 */
	@RequestMapping(value = "/getSecondPeople", method = { RequestMethod.GET,
			RequestMethod.POST })
	@ResponseBody
	public String getSecondPeople(
			@RequestParam(value = C.ParamsName.PHONE_NUM) String phoneNum) {
		TransferMessage messageObj = new TransferMessage();
		if (phoneNum.equals("")) {
			messageObj.setCode(C.ResponseCode.ERROR);
			messageObj.setMessage(C.ResponseMessage.REQUEST_ERROR);
			messageObj.setResultMap(null);
		} else {
			List<YUser> contacts = contactService.getSecondPeople(phoneNum);
			HashMap<String, List<User>> resultMap = new HashMap<String, List<User>>();
			ArrayList<User> returnList = new ArrayList<User>();
			for (YUser yUser : contacts) {
				User user = new User();
				user.setFacePath(yUser.getFacePath());
				user.setGender(yUser.getGender());
				user.setId(yUser.getId());
				user.setIntroduce(yUser.getIntroduce());
				user.setOrigin(yUser.getOrigin());
				user.setPhoneNum(yUser.getPhoneNum());
				user.setUsername(yUser.getUserName());
				returnList.add(user);
			}

			resultMap.put("result", returnList);
			messageObj.setCode(C.ResponseCode.SUCCESS);
			messageObj.setMessage(C.ResponseMessage.SUCCESS);
			messageObj.setResultMap(resultMap);
		}
		return JacksonUtil.writeEntity2JSON(messageObj);
	}

	/**
	 * 新添加好友
	 * 
	 * @param phoneNum
	 * @param friendNum
	 * @return
	 */
	@RequestMapping(value = "/addUser", method = { RequestMethod.GET,
			RequestMethod.POST })
	@ResponseBody
	private String addUser(
			@RequestParam(value = C.ParamsName.HOST_NUM, defaultValue = "") String hostNum,
			@RequestParam(value = C.ParamsName.FRIEND_NUM, defaultValue = "") String friendNum) {

		TransferMessage messageObj = new TransferMessage();
		if (hostNum.equals("") || friendNum.equals("")) {
			messageObj.setCode(C.ResponseCode.ERROR);
			messageObj.setMessage(C.ResponseMessage.ERROR);
			messageObj.setResultMap(null);
		} else {
			YContact friend1 = new YContact();
			friend1.setHostNum(hostNum);
			friend1.setFriendNum(friendNum);
			friend1.setVersion(0);
			friend1.setIsSysUser(1);

			YContact friend2 = new YContact();
			friend2.setHostNum(friendNum);
			friend2.setFriendNum(hostNum);
			friend2.setVersion(0);
			friend2.setIsSysUser(1);

			if (contactService.addFriends(friend1, friend2)) {
				messageObj.setCode(C.ResponseCode.SUCCESS);
				messageObj.setMessage(C.ResponseMessage.SUCCESS);
				messageObj.setResultMap(null);

				// 插入回复消息
				// 消息实体
				YMessage newMessage = new YMessage();
				newMessage.setTitle("通过验证");// 等待添加
				newMessage.setContent("我通过你的好友验证，我们可以开始对话了");
				newMessage.setCreateTime(new Date());
				newMessage.setCreator(userService.getUserByPhone(hostNum));
				newMessage.setType(C.DefaultValues.DEFAULT_MSG_TYPE_CHAT);// 4表示通过验证消息
				// 消息发往者
				YMessageUser messageUser = new YMessageUser();
				YUser toUser = userService.getUserByPhone(friendNum);
				messageUser.setIsRead(C.DefaultValues.DEFAULT_IS_READ);// 0表示没读
				messageUser.setMessage(newMessage);
				messageUser.setReadTime(null);
				messageUser.setUser(toUser);
				messageUser.setIsSend(C.DefaultValues.DEFAULT_IS_SEND);// 0表示没有发送成功
				newMessage.getMessageUser().add(messageUser);
				YMessageUser returnMessageUser = messageService
						.addMsg(newMessage);
				// 发送给请求验证方
				jPushServie.sendMessagee(returnMessageUser,
						new String[] { toUser.getPhoneNum() });// 马上发送，有异常再说

			} else {
				messageObj.setCode(C.ResponseCode.ERROR);
				messageObj.setMessage(C.ResponseMessage.ERROR);
				messageObj.setResultMap(null);
			}
		}
		return JacksonUtil.writeEntity2JSON(messageObj);
	}

	/**
	 * 获取一度人脉 （通讯录）
	 */
	@RequestMapping(value = "/getFirstRelation", method = { RequestMethod.GET,
			RequestMethod.POST })
	@ResponseBody
	public String getFirstRelation(
			@RequestParam(value = C.ParamsName.PHONE_NUM) String phoneNum) {
		TransferMessage messageObj = new TransferMessage();
		if (phoneNum.equals("")) {
			messageObj.setCode(C.ResponseCode.ERROR);
			messageObj.setMessage(C.ResponseMessage.REQUEST_ERROR);
			messageObj.setResultMap(null);
		} else {
			List<YUser> contacts = contactService.getFirstUser(phoneNum);
			HashMap<String, ArrayList<AppContact>> resultMap = new HashMap<String, ArrayList<AppContact>>();
			ArrayList<AppContact> result = new ArrayList<AppContact>();
			for (YUser user : contacts) {
				AppContact appContact = new AppContact();
				appContact.setFriendName(user.getUserName());
				appContact.setIsSysUser("1");// 1表示为系统用户
				appContact.setFriendNum(user.getPhoneNum());
				appContact.setUserId(String.valueOf(user.getId()));
				result.add(appContact);				
			}
			resultMap.put("result", result);
			messageObj.setCode(C.ResponseCode.SUCCESS);
			messageObj.setMessage(C.ResponseMessage.SUCCESS);
			messageObj.setResultMap(resultMap);
		}
		return JacksonUtil.writeEntity2JSON(messageObj);
	}
	/**
	 * 搜索二度人脉
	 */
	@RequestMapping(value = "/searchSecond", method = { RequestMethod.GET,
			RequestMethod.POST })
	@ResponseBody
	public String searchSecond(
			@RequestParam(value = C.ParamsName.USERNAME, defaultValue = "") String userName,
			@RequestParam(value = C.ParamsName.PHONE_NUM) String phoneNum) {
		TransferMessage messageObj = new TransferMessage();
		if (userName.equals("")) {
			messageObj.setCode(C.ResponseCode.ERROR);
			messageObj.setMessage(C.ResponseMessage.ERROR);
			messageObj.setResultMap(null);
		} else {
			List<YUser> contacts = userService.searchSecondUser(userName,phoneNum);
			HashMap<String, List<User>> resultMap = new HashMap<String, List<User>>();
			ArrayList<User> returnList = new ArrayList<User>();
			for (YUser yUser : contacts) {
				User user = new User();
				user.setFacePath(yUser.getFacePath());
				user.setGender(yUser.getGender());
				user.setId(yUser.getId());
				user.setIntroduce(yUser.getIntroduce());
				user.setOrigin(yUser.getOrigin());
				user.setPhoneNum(yUser.getPhoneNum());
				user.setUsername(yUser.getUserName());
				returnList.add(user);
			}

			resultMap.put("result", returnList);
			messageObj.setCode(C.ResponseCode.SUCCESS);
			messageObj.setMessage(C.ResponseMessage.SUCCESS);
			messageObj.setResultMap(resultMap);
		}
		return JacksonUtil.writeEntity2JSON(messageObj);
	}


}
