package com.gdufs.gd;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.rmi.server.UID;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import javassist.expr.NewArray;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.gdufs.gd.common.C;
import com.gdufs.gd.common.CActivity;
import com.gdufs.gd.entity.TransferMessage;
import com.gdufs.gd.entity.YActivity;
import com.gdufs.gd.entity.YActivityUser;
import com.gdufs.gd.entity.YComment;
import com.gdufs.gd.entity.YUser;
import com.gdufs.gd.response.modle.FriendsActivityObj;
import com.gdufs.gd.service.YActivityService;
import com.gdufs.gd.service.YActivityUserService;
import com.gdufs.gd.service.YUserService;
import com.gdufs.gd.util.JacksonUtil;
import com.gdufs.gd.util.UploadUtil;
import com.google.common.base.Joiner;

@Controller
@RequestMapping("/activity")
public class ActivityController {

	private static final Logger logger = LoggerFactory
			.getLogger(ActivityController.class);

	public ActivityController() {
	}

	@Resource(name = "activityService")
	private YActivityService activityService;

	@Resource(name = "userService")
	private YUserService userService;

	@Resource(name = "activityUserService")
	private YActivityUserService activityUserService;

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index(Locale locale, Model model) {
		return "activity";
	}

	/**
	 * 
	 * @param request
	 * @param uId
	 * @param session
	 * @return
	 */
	// 发起活动
	@RequestMapping(value = "/newActivity", method = { RequestMethod.GET,
			RequestMethod.POST })
	@ResponseBody
	public String newActivity(
			final HttpServletRequest request,
			final HttpServletResponse response,
			@RequestParam(value = C.ParamsName.TITLE, required = true, defaultValue = "") String title,
			@RequestParam(value = C.ParamsName.INTRODUCE, required = false, defaultValue = "") String introduce,
			@RequestParam(value = C.ParamsName.BEGINTIME, required = true, defaultValue = "") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm") Date beginTime,
			@RequestParam(value = C.ParamsName.ENDTIME, required = true, defaultValue = "") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm") Date endTime,
			@RequestParam(value = C.ParamsName.COST, required = false, defaultValue = "") double cost,
			@RequestParam(value = C.ParamsName.ACTIVITY_ADDRESS, required = true, defaultValue = "") String activityAddress,
			@RequestParam(value = C.ParamsName.ACTIVITY_ADDRESS_LATITUDE, required = true, defaultValue = "") String activityAddressLatitude,
			@RequestParam(value = C.ParamsName.ACTIVITY_ADDRESS_LONGITUDE, required = true, defaultValue = "") String activityAddressLongitude,
			@RequestParam(value = C.ParamsName.COUNT, required = true, defaultValue = "") int count,
			@RequestParam(value = C.ParamsName.CATEGORY, required = true, defaultValue = "") String category,
			@RequestParam(value = C.ParamsName.PHONE_NUM, required = true, defaultValue = "") String creatorPhoneNum,
			@RequestParam(value = C.ParamsName.CONTACTPHONE, required = true, defaultValue = "") String contactPhone,
			@RequestParam(value = C.ParamsName.UID, required = true, defaultValue = "") int uId) {
		// 上传图片
		HashMap<String, String> pathMap = new UploadUtil().uploadFiles(request,
				response);
		System.out.println(pathMap.toString());
		// 设定只有一张图片
		String picturePath = "";
		for (String path : pathMap.values()) {
			picturePath = path;
		}
		//设定默认的照片
		
		// 新活动
		YActivity activity = new YActivity();
		YUser creator = userService.getUserById(uId);
		System.out.println(creator);
		activity.setTitle(title);
		activity.setIntroduce(introduce);
		activity.setCreateTimeDate(new Date());
		activity.setBeginTime(beginTime);
		activity.setEndTime(endTime);
		activity.setCost(cost);
		activity.setActivityAddress(activityAddress);
		activity.setActivityAddressLatitude(activityAddressLatitude);
		activity.setActivityAddressLongitude(activityAddressLongitude);
		activity.setCount(count);
		activity.setCreator(creator);
		activity.setCreatorPhoneNum(creatorPhoneNum);
		activity.setContactPhone(contactPhone);
		activity.setCategory(category);
		activity.setPicturePath(picturePath);
		// 参与者
		YActivityUser activityUser = new YActivityUser();
		activityUser.setActivity(activity);
		activityUser.setUser(activity.getCreator());
		activityUser.setIsAuth(1);
		activityUser.setIsTickOff(0);
		activityUser.setJoin_time(new Date());
		// 返回信息
		TransferMessage message = new TransferMessage();
		if (activityService.add(activity)) {
			message.setCode(C.ResponseCode.SUCCESS);
			message.setMessage(C.ResponseMessage.SUCCESS);
			message.setResultMap(null);
		} else {
			message.setCode(C.ResponseCode.ERROR);
			message.setMessage(C.ResponseMessage.WRONG_USER_INFO);
			message.setResultMap(null);
		}
		return JacksonUtil.writeEntity2JSON(message);
	}

	
	// 分页获取用户的所有活动
	// 获取圈子的活动(支持分页)
	@RequestMapping(value = "/getFriendsActivityMsg", method = {RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public String getFriendsMsg(
			final HttpServletRequest request,
			final HttpServletResponse response,
			@RequestParam(C.ParamsName.UID) int uId,
			@RequestParam(C.ParamsName.PHONE_NUM) String phoneNum,
			@RequestParam(value = C.ParamsName.PAGENUM, required = false, defaultValue = "0") int pageNum,
			@RequestParam(value = C.ParamsName.PAGESIZE, required = false, defaultValue = "0") int pageSize) {
		// 获得我的
		// 获得一度朋友的
		// 获得二度朋友的
		// 返回数据(可以选择或者不选择分页)
		
		//封装返回参数
		// 返回信息
		TransferMessage message = new TransferMessage();
		if (uId==0||phoneNum.equals("")) {
			message.setCode(C.ResponseCode.ERROR);
			message.setMessage(C.ResponseMessage.ERROR);
			message.setResultMap(null);
		} else {
			List<YActivity> activities=activityService.getYActivitiesbyPhonNum(phoneNum);
			ArrayList<FriendsActivityObj> result=new ArrayList<FriendsActivityObj>();
			for (YActivity activity : activities) {
				FriendsActivityObj friendsActivityObj=new FriendsActivityObj();
				friendsActivityObj.setCreateTimeDate(activity.getCreateTimeDate());
				friendsActivityObj.setFacePath(activity.getCreator().getFacePath());
				friendsActivityObj.setIntroduce(activity.getIntroduce());
				friendsActivityObj.setPhoneNum(activity.getCreatorPhoneNum());
				friendsActivityObj.setPicturePath(activity.getPicturePath());
				friendsActivityObj.setTitle(activity.getTitle());
				friendsActivityObj.setUserId(activity.getCreator().getId());
				friendsActivityObj.setUserName(activity.getCreator().getUserName());
				//创建参与者
				for (YActivityUser item : activity.getActivityUsers()) {
					friendsActivityObj.getJoinerFaceList().add(item.getUser().getFacePath());
					friendsActivityObj.getJoinerIdList().add(item.getUser().getId());
				}
				result.add(friendsActivityObj);
			}
			HashMap<String, ArrayList<FriendsActivityObj>> resultMap=new HashMap<String, ArrayList<FriendsActivityObj>>();
			resultMap.put("result", result);
			message.setCode(C.ResponseCode.SUCCESS);
			message.setMessage(C.ResponseMessage.SUCCESS);
			message.setResultMap(resultMap);
			
			TransferMessage transferMessage=new TransferMessage();
			transferMessage=JacksonUtil.readJson2Entity(JacksonUtil.writeEntity2JSON(resultMap), TransferMessage.class);
			
			System.out.println(transferMessage.getResultMap().get("result"));
			System.out.println(transferMessage);
			
		}	
		return JacksonUtil.writeEntity2JSON(message);

	}
	
	
	
	
	
	
	
	
	
	//////下面的都等待测试
	// 获取创建者所创建的所有活动
	@RequestMapping(value = "/getCreatorActivities", method = RequestMethod.GET)
	@ResponseBody
	public String getCreatorActivities(
			final HttpServletRequest request,
			@RequestParam("uId") int uId,
			final HttpSession session,
			@RequestParam(value = C.ParamsName.PAGENUM, required = false, defaultValue = "0") int pageNum,
			@RequestParam(value = C.ParamsName.PAGESIZE, required = false, defaultValue = "0") int pageSize) {
		HashMap<String, Object> map=new HashMap<String, Object>();
		
		List<YActivity> activitiesList = activityService
				.getActivityByCreatorId(uId);		
		return JacksonUtil.writeEntity2JSON(activitiesList.toString());
	}

	@RequestMapping(value = "/deleteActivity", method = RequestMethod.GET)
	@ResponseBody
	public String deleteActivity(final HttpServletRequest request,
			@RequestParam("activityId") int activityId,
			final HttpSession session) {
		activityService.deleteActivity(activityId);
		return "";
	}


	// 参与活动
	@RequestMapping(value = "/joinActivity", method = RequestMethod.GET)
	@ResponseBody
	public String joinActivity(@RequestParam("uId") int uId,
			@RequestParam("activityId") int activityId) {
		boolean isJoin = activityUserService.joinActivity(uId, activityId);
		return "";
	}

	// 退出活动
	@RequestMapping(value = "/quitActivity", method = RequestMethod.GET)
	@ResponseBody
	public String quitActivity(@RequestParam("uId") int uId,
			@RequestParam("activityId") int activityId) {
		boolean isQuit = activityUserService.quitActivity(uId, activityId);
		return "";
	}
}
