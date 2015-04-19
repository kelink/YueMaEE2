package com.gdufs.gd.service;

import java.util.List;

import com.gdufs.gd.entity.YActivityUser;


public interface YActivityUserService {
	/**
	 * 加入活动
	 * 
	 * @param activityUser
	 * @return
	 */
	public boolean joinActivity(int activityId, int uId);

	/**
	 * 退出活动
	 * 
	 * @param activityUser
	 * @return
	 */
	public boolean quitActivity(int activityId, int uId);
	
	/**
	 *  获取用户参加或者创建的活动信息 
	 * @param uId
	 * @return
	 */
	public List<YActivityUser> getUserJoinOrCreate(int uId);
}
