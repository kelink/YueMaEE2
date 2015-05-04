package com.gdufs.gd;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.dialect.FirebirdDialect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gdufs.gd.common.C;
import com.gdufs.gd.entity.TransferMessage;
import com.gdufs.gd.entity.YActivityUser;
import com.gdufs.gd.entity.YContact;
import com.gdufs.gd.entity.YUser;
import com.gdufs.gd.response.modle.User;
import com.gdufs.gd.service.YActivityService;
import com.gdufs.gd.service.YActivityUserService;
import com.gdufs.gd.service.YBulletinService;
import com.gdufs.gd.service.YContactService;
import com.gdufs.gd.service.YUserService;
import com.gdufs.gd.util.JacksonUtil;
import com.gdufs.gd.util.RandomUtils;
import com.gdufs.gd.util.UploadUtil;

@Controller
@RequestMapping("/user")
public class UserController {

	private static final Logger logger = LoggerFactory
			.getLogger(UserController.class);

	@Resource(name = "userService")
	private YUserService userService;

	@Resource(name = "activityUserService")
	private YActivityUserService activityUserService;

	@Resource(name = "contactService")
	private YContactService contactService;

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
		} else {
			// 设置验证码
			String codeString = String
					.valueOf(RandomUtils.getRandom(0, 999999));
			long time = System.currentTimeMillis();
			session.setAttribute(phoneNum, time + "-" + codeString);
			logger.info("set code as------>" + codeString);
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
							user.setFacePath(C.DefaultValues.DEFAULT_FACEPATH);
							user.setUserName(username);
							user.setGender(null);
							user.setOrigin(null);
							message = userService.register(user);
							// 系统公告
							// 创建提醒
						}
					}
				}
			}
		}
		return JacksonUtil.writeEntity2JSON(message);

	}

	/**
	 * 用户登陆
	 * 
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
			//更新登陆状态
			user2.setIsLogin(1);
			userService.updateUser(user2);
			HashMap<String, String> result = new HashMap<String, String>();
			result.put(C.ParamsName.UID, String.valueOf(user2.getId()));
			result.put(C.ParamsName.USERNAME, user2.getUserName());
			result.put(C.ParamsName.USER_FACE_PATH, user2.getFacePath());
			result.put(C.ParamsName.USER_INTRODUCE, user2.getIntroduce());
			result.put(C.ParamsName.USER_GENDER, user2.getGender());
			result.put(C.ParamsName.USER_ORIGIN, user2.getOrigin());
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
	
	@RequestMapping(value = "/logout", method = {RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public String logout(
			HttpSession httpSession,
			@RequestParam(value = C.ParamsName.UID, defaultValue = "0") int userId) {
		TransferMessage message = new TransferMessage();
		
		if (userId == 0) {
			message.setCode(C.ResponseCode.ERROR);
			message.setMessage(C.ResponseMessage.ERROR);
			message.setResultMap(null);
		} else {
			//更新登陆状态为0
			YUser user=userService.getUserById(userId);
			user.setIsLogin(0);
			userService.updateUser(user);
			
			message.setCode(C.ResponseCode.SUCCESS);
			message.setMessage(C.ResponseMessage.SUCCESS);
			message.setResultMap(null);
		}
		return JacksonUtil.writeEntity2JSON(message);

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

	@RequestMapping(value = "/updateUser", method = { RequestMethod.POST,
			RequestMethod.GET })
	@ResponseBody
	public String updateUser(
			final HttpServletRequest request,
			final HttpServletResponse response,
			@RequestParam(value = C.ParamsName.UID) int uId,
			@RequestParam(value = C.ParamsName.USERNAME, defaultValue = "") String userName,
			@RequestParam(value = C.ParamsName.USER_GENDER, defaultValue = "") String gender,
			@RequestParam(value = C.ParamsName.USER_INTRODUCE, defaultValue = "") String introduce,
			@RequestParam(value = C.ParamsName.USER_ORIGIN, defaultValue = "") String origin) {
		// 判断上传的头像
		// 上传图片
		HashMap<String, String> pathMap = UploadUtil.uploadFiles(request);
		System.out.println(pathMap.toString());
		// 设定只有一张图片
		String facePath = "";
		for (String path : pathMap.values()) {
			facePath = path;
		}
		YUser user = userService.getUserById(uId);
		TransferMessage message = new TransferMessage();
		if (user == null) {
			message.setCode(C.ResponseCode.ERROR);
			message.setMessage(C.ResponseMessage.ERROR);
			message.setResultMap(null);
		} else {
			if (!facePath.equals("")) {
				user.setFacePath(facePath);
			}
			if (!userName.equals("")) {
				user.setUserName(userName);
			}
			if (!gender.equals("")) {
				user.setGender(gender);
			}
			if (!introduce.equals("")) {
				user.setIntroduce(introduce);
			}
			if (!origin.equals("")) {
				user.setOrigin(origin);
			}
			if (!userService.updateUser(user)) {
				message.setCode(C.ResponseCode.ERROR);
				message.setMessage(C.ResponseMessage.ERROR);
				message.setResultMap(null);
			} else {
				message.setCode(C.ResponseCode.SUCCESS);
				message.setMessage(C.ResponseMessage.SUCCESS);
				message.setResultMap(null);
			}
		}
		return JacksonUtil.writeEntity2JSON(message);
	}

	// 更新用户头像
	@RequestMapping(value = "/updateUserface", method = { RequestMethod.POST,
			RequestMethod.GET })
	@ResponseBody
	public String updateUserface(final HttpServletRequest request,
			@RequestParam(value = C.ParamsName.UID) int uId) {
		// 判断上传的头像
		// 上传图片
		HashMap<String, String> pathMap = UploadUtil.uploadFiles(request);
		System.out.println(pathMap.toString());
		// 设定只有一张图片
		String facePath = "";
		for (String path : pathMap.values()) {
			facePath = path;
		}
		TransferMessage message = new TransferMessage();
		YUser user = userService.getUserById(uId);
		if (facePath.equals("")) {
			message.setCode(C.ResponseCode.ERROR);
			message.setMessage(C.ResponseMessage.ERROR);
			message.setResultMap(null);
		} else {
			user.setFacePath(facePath);
			if (!userService.updateUser(user)) {
				message.setCode(C.ResponseCode.ERROR);
				message.setMessage(C.ResponseMessage.ERROR);
				message.setResultMap(null);
			} else {
				HashMap<String, String> resultMap = new HashMap<String, String>();
				resultMap.put(C.ParamsName.USER_FACE_PATH, facePath);
				message.setCode(C.ResponseCode.SUCCESS);
				message.setMessage(C.ResponseMessage.SUCCESS);
				message.setResultMap(resultMap);
			}
		}
		return JacksonUtil.writeEntity2JSON(message);
	}

	// 获取用户个人中心
	@RequestMapping(value = "/getUserCenter", method = { RequestMethod.POST,
			RequestMethod.GET })
	@ResponseBody
	public String getUserCenter(
			final HttpServletRequest request,
			@RequestParam(value = C.ParamsName.UID, defaultValue = "0") int userId,
			@RequestParam(value = C.ParamsName.FRIENDID, defaultValue = "0") int friendId,
			@RequestParam(value = C.ParamsName.PHONE_NUM, defaultValue = "") String hostNum,
			@RequestParam(value = C.ParamsName.FRIEND_NUM, defaultValue = "") String friendNum) {
		TransferMessage message = new TransferMessage();
		if (userId == 0 || friendId == 0 || hostNum.equals("")
				|| friendNum.equals("")) {
			message.setCode(C.ResponseCode.ERROR);
			message.setMessage(C.ResponseMessage.ERROR);
			message.setResultMap(null);
		} else {
			// 参与活动个数
			int count = activityUserService.countJoinOrCreate(friendId);

			// 判断两个用户的关系
			boolean isFirstfriend = contactService.isFirstFriend(hostNum,
					friendNum);
			System.out.println("isFirstFriend----------->" + isFirstfriend);
			boolean isSecondfriend = contactService.isSecondFriend(hostNum,
					friendNum);
			System.out.println("isSecondFriend----------->" + isSecondfriend);
			// 获取用户信息
			YUser user = userService.getUserById(friendId);
			User returnUser = new User();
			returnUser.setId(user.getId());
			returnUser.setFacePath(user.getFacePath());
			returnUser.setGender(user.getGender());
			returnUser.setIntroduce(user.getIntroduce());
			returnUser.setOrigin(user.getOrigin());
			returnUser.setPhoneNum(user.getPhoneNum());
			returnUser.setUsername(user.getUserName());

			HashMap<String, User> resultMap = new HashMap<String, User>();
			resultMap.put("result", returnUser);
			String extra = "";
			if (isFirstfriend) {
				extra = count + "," + "1";
			} else if (isSecondfriend) {
				extra = count + "," + "2";
			} else {
				extra = count + "," + "0";
			}
			message.setCode(C.ResponseCode.SUCCESS);
			message.setMessage(C.ResponseMessage.SUCCESS);
			message.setResultMap(resultMap);
			message.setExtra(extra);
		}
		return JacksonUtil.writeEntity2JSON(message);
	}
	
	// 获取用户个人中心
		@RequestMapping(value = "/getUserCenterByPhone", method = { RequestMethod.POST,
				RequestMethod.GET })
		@ResponseBody
		public String getUserCenterByPhone(
				final HttpServletRequest request,
				@RequestParam(value = C.ParamsName.PHONE_NUM, defaultValue = "") String hostNum,
				@RequestParam(value = C.ParamsName.FRIEND_NUM, defaultValue = "") String friendNum) {
			TransferMessage message = new TransferMessage();
			if ( hostNum.equals("")
					|| friendNum.equals("")) {
				message.setCode(C.ResponseCode.ERROR);
				message.setMessage(C.ResponseMessage.ERROR);
				message.setResultMap(null);
			} else {
				// 参与活动个数
				int count = activityUserService.countJoinOrCreateByPhone(friendNum);

				// 判断两个用户的关系
				boolean isFirstfriend = contactService.isFirstFriend(hostNum,
						friendNum);
				System.out.println("isFirstFriend----------->" + isFirstfriend);
				boolean isSecondfriend = contactService.isSecondFriend(hostNum,
						friendNum);
				System.out.println("isSecondFriend----------->" + isSecondfriend);
				// 获取用户信息
				YUser user = userService.getUserByPhone(friendNum);
				User returnUser = new User();
				returnUser.setId(user.getId());
				returnUser.setFacePath(user.getFacePath());
				returnUser.setGender(user.getGender());
				returnUser.setIntroduce(user.getIntroduce());
				returnUser.setOrigin(user.getOrigin());
				returnUser.setPhoneNum(user.getPhoneNum());
				returnUser.setUsername(user.getUserName());

				HashMap<String, User> resultMap = new HashMap<String, User>();
				resultMap.put("result", returnUser);
				String extra = "";
				if (isFirstfriend) {
					extra = count + "," + "1";
				} else if (isSecondfriend) {
					extra = count + "," + "2";
				} else {
					extra = count + "," + "0";
				}
				message.setCode(C.ResponseCode.SUCCESS);
				message.setMessage(C.ResponseMessage.SUCCESS);
				message.setResultMap(resultMap);
				message.setExtra(extra);
			}
			return JacksonUtil.writeEntity2JSON(message);
		}


}
