package com.gdufs.gd.dao;

import java.util.HashMap;
import java.util.List;

import com.gdufs.gd.entity.YActivity;

public interface YActivityDao {
	public boolean add(YActivity activity);

	public boolean update(YActivity activity);

	public YActivity getActivityByActivityId(int aId);

	public List<YActivity> getActivityByCreatorId(int uId);
	
	public List<YActivity> getActivityByPage(int pageNum, int pageSize);
	

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
	
	public boolean delete(int activityId);
}
