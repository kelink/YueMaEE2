package com.gdufs.gd;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;




import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.rossillo.spring.web.mvc.CacheControl;
import net.rossillo.spring.web.mvc.CachePolicy;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gdufs.gd.common.C;
import com.gdufs.gd.entity.TransferMessage;
import com.gdufs.gd.entity.YActivity;
import com.gdufs.gd.entity.YActivityUser;
import com.gdufs.gd.entity.YComment;
import com.gdufs.gd.entity.YUser;
import com.gdufs.gd.response.modle.Activity;
import com.gdufs.gd.response.modle.Comment;
import com.gdufs.gd.response.modle.FriendsActivityObj;
import com.gdufs.gd.response.modle.Joiner;
import com.gdufs.gd.response.modle.MyActivityObj;
import com.gdufs.gd.service.YActivityService;
import com.gdufs.gd.service.YActivityUserService;
import com.gdufs.gd.service.YUserService;
import com.gdufs.gd.util.DateUtil;
import com.gdufs.gd.util.JacksonUtil;
import com.gdufs.gd.util.UploadUtil;


@Controller
@RequestMapping("/activity")
public class ActivityController {

	@Resource(name = "activityService")
	private YActivityService activityService;

	@Resource(name = "userService")
	private YUserService userService;

	@Resource(name = "activityUserService")
	private YActivityUserService activityUserService;

	/**
	 * 创建新活动
	 * @param request
	 * @param uId
	 * @param session
	 * @return
	 */
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
		HashMap<String, String> pathMap = UploadUtil.uploadFiles(request);
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
		activityUser.setType(0);//0表示新创建，1表示加入
		activity.getActivityUsers().add(activityUser);
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

	
	// 获取圈子的活动支持分页，全部获
	@RequestMapping(value = "/getFriendsActivityMsg", method = {RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public String getFriendsMsg(
			final HttpServletRequest request,
			final HttpServletResponse response,
			@RequestParam(C.ParamsName.UID) int uId,
			@RequestParam(C.ParamsName.PHONE_NUM) String phoneNum,
			@RequestParam(value = C.ParamsName.PAGENUM, required = false, defaultValue = "0") int pageNum,
			@RequestParam(value = C.ParamsName.PAGESIZE, required = false, defaultValue = "5") int pageSize) {
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
			int count=activityService.countFriendActivity(phoneNum);//总共的活动个数
			
			List<YActivity> activities=activityService.getActivitiesOnPage(pageNum, pageSize, phoneNum);
			ArrayList<FriendsActivityObj> result=new ArrayList<FriendsActivityObj>();
			
			for (YActivity activity : activities) {
				FriendsActivityObj friendsActivityObj=new FriendsActivityObj();
				friendsActivityObj.setActivityId(String.valueOf(activity.getId()));
				friendsActivityObj.setCreatorId(String.valueOf(activity.getCreator().getId()));
				friendsActivityObj.setCreator_userName(activity.getCreator().getUserName());
				friendsActivityObj.setCreator_phoneNum(activity.getCreator().getPhoneNum());
				friendsActivityObj.setCreator_facePath(activity.getCreator().getFacePath());
				friendsActivityObj.setCreateTimeDate(DateUtil.formatDateTime(activity.getCreateTimeDate()));
				friendsActivityObj.setPicturePath(activity.getPicturePath());
				friendsActivityObj.setTitle(activity.getTitle());
				friendsActivityObj.setIntroduce(activity.getIntroduce());
				friendsActivityObj.setCommentNum(String.valueOf(activity.getComments().size()));
				friendsActivityObj.setLikeNum(String.valueOf(activity.getLikes().size()));//需要修改修改，先测试
				friendsActivityObj.setCategory(activity.getCategory());
				friendsActivityObj.setActivityAddress(activity.getActivityAddress());
				friendsActivityObj.setActivityAddressLatitude(activity.getActivityAddressLatitude());
				friendsActivityObj.setActivityAddressLongitude(activity.getActivityAddressLongitude());
				friendsActivityObj.setCost(String.valueOf(activity.getCost()));
				friendsActivityObj.setCount(String.valueOf(activity.getCount()));
				//设置Joiner
				ArrayList<Joiner> joinerList=new ArrayList<Joiner>();
				for (YActivityUser joiner : activity.getActivityUsers()) {
					Joiner	joiner2=new Joiner();
					joiner2.setActivityId(String.valueOf(activity.getId()));
					joiner2.setJoineFacePath(joiner.getUser().getFacePath());
					joiner2.setuId(String.valueOf(joiner.getUser().getId()));
					joiner2.setJoinerUserName(joiner.getUser().getUserName());
					joiner2.setIntroduce(joiner.getUser().getIntroduce());
					joiner2.setJoinerPhoneNum(joiner.getUser().getPhoneNum());
					joiner2.setJoinTime(DateUtil.formatDateTime(joiner.getJoin_time()));
					joinerList.add(joiner2);
				}
				friendsActivityObj.setJoinerList(joinerList);
				//设置Comments(需要接着写)
				ArrayList<Comment> commentList=new ArrayList<Comment>();
				for (YComment comment : activity.getComments()) {
					if (comment.getIsDelete()==0) {
						Comment itemComment=new Comment();
						itemComment.setCommentPath(comment.getCommentPath());
						itemComment.setContent(comment.getContent());
						itemComment.setCreateTime(DateUtil.formatDateTime(comment.getCreateTime()));
						itemComment.setFatherCommentId(String.valueOf(comment.getFatherCommentId()));
						itemComment.setID(String.valueOf(comment.getId()));
						commentList.add(itemComment);
					}
					
				}							
				friendsActivityObj.setCommentsList(commentList);
				result.add(friendsActivityObj);
			}
			HashMap<String,ArrayList<FriendsActivityObj>>  resultMap=new HashMap<String,ArrayList<FriendsActivityObj>>();
			resultMap.put("result", result);
			message.setCode(C.ResponseCode.SUCCESS);
			message.setMessage(C.ResponseMessage.SUCCESS);
			message.setResultMap(resultMap);
			message.setExtra(String.valueOf(count));//设置返回的数目
		}	
		return JacksonUtil.writeEntity2JSON(message);

	}

	//获取我参与的或者发起的活动s
	@RequestMapping(value = "/getMyMsg",  method ={ RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public String getMyMsg(final HttpServletRequest request,
			final HttpServletResponse response,
			@RequestParam(C.ParamsName.UID) int uId,
			@RequestParam(C.ParamsName.PHONE_NUM) String phoneNum,
			@RequestParam(value = C.ParamsName.PAGENUM, required = false, defaultValue = "0") int pageNum,
			@RequestParam(value = C.ParamsName.PAGESIZE, required = false, defaultValue = "5") int pageSize){
		
		TransferMessage message=new TransferMessage();
		
		if (uId==0) {
			message.setCode(C.ResponseCode.ERROR);
			message.setMessage(C.ResponseMessage.ERROR);
			message.setResultMap(null);
		}else {
			List<YActivityUser> activityUsers=activityUserService.getUserJoinOrCreate(uId);
			ArrayList<MyActivityObj> result=new ArrayList<MyActivityObj>();
			for (YActivityUser activityUser : activityUsers) {
				MyActivityObj myActivityObj=new MyActivityObj();
				myActivityObj.setActivityId(String.valueOf(activityUser.getActivity().getId()));
				myActivityObj.setCreatorId(String.valueOf(activityUser.getActivity().getCreator().getId()));
				myActivityObj.setActivityPicture(activityUser.getActivity().getPicturePath());
				myActivityObj.setCreateTime(DateUtil.formatDateTime(activityUser.getActivity().getCreateTimeDate()));
				myActivityObj.setTitle(activityUser.getActivity().getTitle());
				myActivityObj.setIntroduce(activityUser.getActivity().getIntroduce());
				myActivityObj.setCategory(activityUser.getActivity().getCategory());
				if (activityUser.getType()==0) {
					myActivityObj.setTypeText("我发布了新活动");
				}else if(activityUser.getType()==1) {
					myActivityObj.setTypeText("我参与了该活动");
				}			
				result.add(myActivityObj);
			}
			HashMap<String,ArrayList<MyActivityObj>>  resultMap=new HashMap<String,ArrayList<MyActivityObj>>();
			resultMap.put("result", result);
			message.setCode(C.ResponseCode.SUCCESS);
			message.setMessage(C.ResponseMessage.SUCCESS);
			message.setResultMap(resultMap);
		}
		return JacksonUtil.writeEntity2JSON(message);
		
	}
	
	/**
	 * 获取一个活动的全部信息
	 * @param request
	 * @param activityId
	 * @return
	 */
	@RequestMapping(value = "/getAActivity",  method ={ RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public String getAActivity(final HttpServletRequest request,@RequestParam(value = C.ParamsName.AID) int activityId){
		TransferMessage message = new TransferMessage();
		if (activityId==0) {
			message.setCode(C.ResponseCode.ERROR);
			message.setMessage(C.ResponseMessage.ERROR);
			message.setResultMap(null);
		}else {
			YActivity yactivity=activityService.getActivityByActivityId(activityId);
			System.out.println(yactivity.getActivityUsers().toString());
			Activity tempActivity=new Activity();
			tempActivity.setId(String.valueOf(yactivity.getId()));
			tempActivity.setActivityId(String.valueOf(yactivity.getId()));
			tempActivity.setCreatorId(String.valueOf(yactivity.getCreator().getId()));
			tempActivity.setCreator_userName(yactivity.getCreator().getUserName());
			tempActivity.setCreator_facePath(yactivity.getCreator().getFacePath());
			tempActivity.setCreator_phoneNum(yactivity.getCreator().getPhoneNum());
			tempActivity.setCreateTimeDate(DateUtil.formatDateTime(yactivity.getCreateTimeDate()));
			tempActivity.setPicturePath(yactivity.getPicturePath());
			tempActivity.setTitle(yactivity.getTitle());
			tempActivity.setIntroduce(yactivity.getIntroduce());
			tempActivity.setCategory(yactivity.getCategory());
			tempActivity.setCost(String.valueOf(yactivity.getCost()));
			tempActivity.setCount(String.valueOf(yactivity.getCount()));
			tempActivity.setActivityAddress(yactivity.getActivityAddress());
			tempActivity.setActivityAddressLatitude(yactivity.getActivityAddressLatitude());
			tempActivity.setActivityAddressLongitude(yactivity.getActivityAddressLongitude());
			tempActivity.setCommentNum(String.valueOf(yactivity.getComments().size()));
			tempActivity.setBeginTime(DateUtil.formatDateTime(yactivity.getBeginTime()));
			tempActivity.setEndTime(DateUtil.formatDateTime(yactivity.getEndTime()));
			ArrayList<Comment> commentsList=new ArrayList<Comment>();
			for (YComment comment : yactivity.getComments()) {
				if (comment.getIsDelete()!=1) {
					Comment tempComment=new Comment();
					tempComment.setCommentPath(comment.getCommentPath());
					tempComment.setContent(comment.getContent());
					tempComment.setFatherCommentId(String.valueOf(comment.getFatherCommentId()));
					tempComment.setID(String.valueOf(comment.getId()));
					tempComment.setIsDelete(String.valueOf(comment.getIsDelete()));
					commentsList.add(tempComment);
				}		
				
			}
			tempActivity.setCommentsList(commentsList);
			//设置Joiner
			ArrayList<Joiner> joinerList=new ArrayList<Joiner>();
			for (YActivityUser joiner : yactivity.getActivityUsers()) {
				Joiner	joiner2=new Joiner();
				joiner2.setActivityId(String.valueOf(yactivity.getId()));
				joiner2.setJoineFacePath(joiner.getUser().getFacePath());
				joiner2.setuId(String.valueOf(joiner.getUser().getId()));
				joiner2.setIntroduce(joiner.getUser().getIntroduce());
				joiner2.setJoinerPhoneNum(joiner.getUser().getPhoneNum());
				joiner2.setJoinTime(DateUtil.formatDateTime(joiner.getJoin_time()));
				joinerList.add(joiner2);
			}
			tempActivity.setJoinerList(joinerList);			
			HashMap<String,Activity>  resultMap=new HashMap<String,Activity>();
			resultMap.put("result", tempActivity);
			message.setCode(C.ResponseCode.SUCCESS);
			message.setMessage(C.ResponseMessage.SUCCESS);
			message.setResultMap(resultMap);
		}
		return JacksonUtil.writeEntity2JSON(message);
	}
	
	//////下面的都等待测试
	// 获取创建者所创建的所有活动
	@RequestMapping(value = "/getCreatorActivities",  method ={ RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public String getCreatorActivities(
			final HttpServletRequest request,
			@RequestParam(value = C.ParamsName.UID) int uId,
			final HttpSession session,
			@RequestParam(value = C.ParamsName.PAGENUM, required = false, defaultValue = "0") int pageNum,
			@RequestParam(value = C.ParamsName.PAGESIZE, required = false, defaultValue = "5") int pageSize) {
		
		List<YActivity> activitiesList = activityService
				.getActivityByCreatorId(uId);		
		return JacksonUtil.writeEntity2JSON(activitiesList.toString());
	}

	/**
	 * 通过id删除活动
	 * @param request
	 * @param activityId
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/deleteActivity", method ={ RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public String deleteActivity(final HttpServletRequest request,
			@RequestParam(value=C.ParamsName.AID, defaultValue = "0") int activityId,
			final HttpSession session) {
		TransferMessage message = new TransferMessage();
		if (activityId==0) {
			message.setCode(C.ResponseCode.ERROR);
			message.setMessage(C.ResponseMessage.ERROR);
			message.setResultMap(null);
		}else {
			if (activityService.deleteActivity(activityId)) {
				message.setCode(C.ResponseCode.SUCCESS);
				message.setMessage(C.ResponseMessage.SUCCESS);
				message.setResultMap(null);
			}else {
				message.setCode(C.ResponseCode.ERROR);
				message.setMessage(C.ResponseMessage.ERROR);
				message.setResultMap(null);
			}
		}
		return JacksonUtil.writeEntity2JSON(message);
	}


	// 参与活动
	@RequestMapping(value = "/joinActivity",  method ={ RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public String joinActivity(@RequestParam(value=C.ParamsName.UID, required = true, defaultValue = "0") int userId,
			@RequestParam(value=C.ParamsName.AID, required = true, defaultValue = "0") int activityId) {
		TransferMessage message = new TransferMessage();
		System.out.println("userId---->"+userId);
		System.out.println("activityId---->"+activityId);
		if (activityId==0||userId==0) {
			message.setCode(C.ResponseCode.ERROR);
			message.setMessage(C.ResponseMessage.ERROR);
			message.setResultMap(null);
		}else {
			if (activityUserService.joinActivity(activityId,userId)) {			
				message.setCode(C.ResponseCode.SUCCESS);
				message.setMessage(C.ResponseMessage.SUCCESS);
				message.setResultMap(null);
			}else {
				message.setCode(C.ResponseCode.ERROR);
				message.setMessage(C.ResponseMessage.ERROR);
				message.setResultMap(null);
			}
		}
		return JacksonUtil.writeEntity2JSON(message);
	}

	// 退出活动
	@RequestMapping(value = "/quitActivity", method ={ RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public String quitActivity(@RequestParam(value=C.ParamsName.UID, required = true, defaultValue = "0") int userId,
			@RequestParam(value=C.ParamsName.AID, required = true, defaultValue = "0") int activityId) {
		System.out.println("userId---->"+userId);
		System.out.println("activityId---->"+activityId);
		TransferMessage message = new TransferMessage();
		if (activityId==0||userId==0) {
			message.setCode(C.ResponseCode.ERROR);
			message.setMessage(C.ResponseMessage.ERROR);
			message.setResultMap(null);
		}else {
			if (activityUserService.quitActivity(activityId,userId)) {			
				message.setCode(C.ResponseCode.SUCCESS);
				message.setMessage(C.ResponseMessage.SUCCESS);
				message.setResultMap(null);
			}else {
				message.setCode(C.ResponseCode.ERROR);
				message.setMessage(C.ResponseMessage.ERROR);
				message.setResultMap(null);
			}
		}
		return JacksonUtil.writeEntity2JSON(message);
	}
}
