package com.gdufs.gd.service;

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
}
