package com.gdufs.gd.dao;

import java.util.HashMap;
import java.util.List;

import com.gdufs.gd.entity.YActivity;

public interface YActivityDao {
	public boolean add(YActivity activity);

	public boolean update(YActivity activity);

	public YActivity getActivityByActivityId(int aId);

	public List<YActivity> getActivityByCreatorId(int uId);
	

	/**
	 *  查找我发布的活动
	 * @param phoneNUm
	 * @return
	 */	
	public List<YActivity> getMyActivitiesbyPhonNnum(String phoneNum);
	
	/**
	 *  查找一度人脉发布的活动
	 * @param phoneNUm
	 * @return
	 */
	public List<YActivity> getFirstActivitiesbyPhonNnum(String phoneNum);
	
	/**
	 *  查找二度人脉发布的活动
	 * @param phoneNUm
	 * @return
	 */
	public List<YActivity> getSecondActivitiesbyPhonNnum(String phoneNum);
	
	/**
	 * 查找全部的朋友圈的活动
	 * @param activityId
	 * @return
	 */
	public List<YActivity> getAllActivity(String phoneNum);
	
	/**
	 * 分页获取朋友圈的活动
	 * @param pageNum
	 * @param pageSize
	 * @param phoneNum
	 * @return
	 */
	public List<YActivity> getActivityOnPage(int pageNum, int pageSize,
			String phoneNum);
	/**
	 * 获取朋友圈的活动的总个数
	 * @return
	 */
	public int countFriendsActivity(String phoneNum);
	
	public boolean delete(int activityId);
	
	public boolean updateActivity(YActivity activity);
}
