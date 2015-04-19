package com.gdufs.gd;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gdufs.gd.common.C;
import com.gdufs.gd.entity.TransferMessage;
import com.gdufs.gd.entity.YUser;
import com.gdufs.gd.service.YBulletinService;
import com.gdufs.gd.service.YUserService;
import com.gdufs.gd.util.JacksonUtil;
import com.gdufs.gd.util.RandomUtils;

@Controller
@RequestMapping("/user")
public class UserController {

	private static final Logger logger = LoggerFactory
			.getLogger(UserController.class);

	@Resource(name = "userService")
	private YUserService userService;

	@Resource(name = "bulletinService")
	private YBulletinService bulletinService;// 系统公告类

	/**
	 * 验证注册码
	 * 
	 * @param phoneNum
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/getRegisterCode", method = { RequestMethod.POST,
			RequestMethod.GET })
	@ResponseBody
	public String getRegisterCode(
			@RequestParam(C.ParamsName.PHONE_NUM) String phoneNum,
			HttpSession session) {
		TransferMessage message = new TransferMessage();
		if (phoneNum == null || phoneNum.equals("")) {
			message.setCode(C.ResponseCode.ERROR);
			message.setMessage(C.ResponseMessage.INVALID_PARMANTS);
			message.setResultMap(null);
		}else {
			// 设置验证码
			String codeString = String.valueOf(RandomUtils.getRandom(0, 999999));
			long time = System.currentTimeMillis();
			session.setAttribute(phoneNum, time + "-" + codeString);
			logger.info("set code as------>"+codeString);	
			// 返回消息
			Map<String, String> result = new HashMap<String, String>();
			result.put(C.ParamsName.REGIST_CODE, codeString);
			message.setCode(C.ResponseCode.SUCCESS);
			message.setMessage(C.ResponseMessage.SUCCESS);
			message.setResultMap(result);
		}
		
		return JacksonUtil.writeEntity2JSON(message);
	}
	
	/**
	 * 注册
	 * 
	 * @param phoneNum
	 * @param pwd
	 * @param registerCode
	 * @param username
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/register", method = { RequestMethod.POST,
			RequestMethod.GET })
	@ResponseBody
	public String register(
			@RequestParam(C.ParamsName.PHONE_NUM) String phoneNum,
			@RequestParam(C.ParamsName.PASSWORD) String pwd,
			@RequestParam(C.ParamsName.REGIST_CODE) String registerCode,
			@RequestParam(C.ParamsName.USERNAME) String username,
			HttpSession httpSession) {
		TransferMessage message = new TransferMessage();
		if (phoneNum == null || phoneNum.equals("") || pwd == null
				|| pwd.equals("") || registerCode == null
				|| registerCode.equals("")) {
			message.setCode(C.ResponseCode.ERROR);
			message.setMessage(C.ResponseMessage.INVALID_PARMANTS);
			message.setResultMap(null);
		} else {
			// 验证注册码
			String seeesionTime = (String) httpSession.getAttribute(phoneNum);
			if (seeesionTime == null) {
				message.setCode(C.ResponseCode.ERROR);
				message.setMessage(C.ResponseMessage.REQUEST_ERROR);
				message.setResultMap(null);
			} else {
				String[] parms = seeesionTime.split("-");
				if (System.currentTimeMillis() - Long.parseLong(parms[0]) > (1000 * 60)) {
					message.setCode(C.ResponseCode.ERROR);
					message.setMessage(C.ResponseMessage.REGIST_CODE_TIME_OUT);
					message.setResultMap(null);
				} else {
					if (!registerCode.equals(parms[1])) {
						message.setCode(C.ResponseCode.ERROR);
						message.setMessage(C.ResponseMessage.REGIST_CODE_ERROR);
						message.setResultMap(null);
					} else {
						if (userService.getUserByPhone(phoneNum) != null) {
							message.setCode(C.ResponseCode.ERROR);
							message.setMessage(C.ResponseMessage.REGIST_USER_EXIST);
							message.setResultMap(null);
						} else {
							// 创建新对象
							YUser user = new YUser();
							user.setPassword(pwd);
							user.setPhoneNum(phoneNum);
							user.setCreateTime(new Date());
							user.setUserName(username);
							// 系统公告
							// 创建提醒
							message = userService.register(user);
						}
					}
				}
			}
		}
		return JacksonUtil.writeEntity2JSON(message);

	}
	
	/**
	 * 用户登陆
	 * @param httpSession
	 * @param phoneNum
	 * @param pwd
	 * @return
	 */
	
	@RequestMapping(value = "/login", method = { RequestMethod.GET,
			RequestMethod.POST })
	@ResponseBody
	public String login(HttpSession httpSession,
			@RequestParam(C.ParamsName.PHONE_NUM) String phoneNum,
			@RequestParam(C.ParamsName.PASSWORD) String pwd) {
		TransferMessage message = new TransferMessage();
		YUser user = new YUser();
		user.setPhoneNum(phoneNum);
		user.setPassword(pwd);
		YUser user2 = userService.CheckUser(user);
		if (user2 != null) {
			HashMap<String, String> result = new HashMap<String, String>();
			result.put(C.ParamsName.UID, String.valueOf(user2.getId()));
			result.put(C.ParamsName.USERNAME, user2.getUserName());
			message.setCode(C.ResponseCode.SUCCESS);
			message.setMessage(C.ResponseMessage.SUCCESS);
			message.setResultMap(result);
		} else {
			message.setCode(C.ResponseCode.ERROR);
			message.setMessage(C.ResponseMessage.WRONG_USER_INFO);
			message.setResultMap(null);
		}
		return JacksonUtil.writeEntity2JSON(message);
	}

	
	





	
	
	
	// /////////////待验证
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	@ResponseBody
	public String logout(HttpSession httpSession) {
		httpSession.getAttribute("manager");
		return (String) httpSession.getAttribute("manager").toString();
	}

	/**
	 * 查找用户的所有相关信息（活动数，个人信息）
	 * 
	 * @return
	 */
	@RequestMapping(value = "/getUserinfo", method = { RequestMethod.POST,
			RequestMethod.GET })
	@ResponseBody
	public String getUserinfo(@RequestParam(C.ParamsName.UID) int uId) {
		YUser user = userService.getUserById(uId);
		TransferMessage message = new TransferMessage();
		if (user == null) {
			message.setCode(C.ResponseCode.ERROR);
			message.setMessage(C.ResponseMessage.ERROR);
			message.setResultMap(null);
		} else {
			message.setCode(C.ResponseCode.SUCCESS);
			message.setMessage(C.ResponseMessage.SUCCESS);
			HashMap<String, YUser> map = new HashMap<String, YUser>();
			map.put(String.valueOf(user.getId()), user);
			message.setResultMap(map);
		}
		return JacksonUtil.writeEntity2JSON(message);

	}
//	/**
//	 * 判断用户是否已经注册
//	 * 
//	 * @param phoneNum
//	 * @return
//	 */
//	@RequestMapping(value = "/isUserExist", method = { RequestMethod.POST,
//			RequestMethod.GET })
//	@ResponseBody
//	public String isUserExist(
//			@RequestParam(C.ParamsName.PHONE_NUM) String phoneNum) {
//		TransferMessage message = new TransferMessage();
//		// 是否已经注册
//		if (userService.getUserByPhone(phoneNum) != null) {
//			message.setCode(C.ResponseCode.ERROR);
//			message.setMessage(C.ResponseMessage.REGIST_USER_EXIST);
//			message.setResultMap(null);
//		} else {
//			message.setCode(C.ResponseCode.SUCCESS);
//			message.setMessage(C.ResponseMessage.SUCCESS);
//			message.setResultMap(null);
	
	
	
//		}
//		return JacksonUtil.writeEntity2JSON(message);
//	}

}
