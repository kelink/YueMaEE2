package com.gdufs.gd.service;

import java.util.HashMap;
import java.util.List;

import com.gdufs.gd.entity.YActivity;
import com.gdufs.gd.entity.YActivityUser;

public interface YActivityService {
	/**
	 * 发布新的
	 * 
	 * @param activity
	 * @return
	 */
	public boolean add(YActivity activity);

	/**
	 * 通过活动id获取某一个活动
	 * 
	 * @param aId
	 * @return
	 */
	public YActivity getActivityByActivityId(int aId);

	/**
	 * 通过创建者的Id获取创建者所创建的所有活动信息
	 * 
	 * @param uId
	 * @return
	 */
	public List<YActivity> getActivityByCreatorId(int uId);

	/**
	 * 通过活动id号进行删除活动
	 * 
	 * @param activityId
	 * @return
	 */
	public boolean deleteActivity(int activityId);
	
	
	/**
	 * 通过用户的手机号码，获取每个activity的情况
	 * 包括自己，一度朋友，二度朋友
	 * @return
	 */
	public List<YActivity> getYActivitiesbyPhonNum(String phoneNum);
}
