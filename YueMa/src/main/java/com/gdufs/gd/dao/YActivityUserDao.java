package com.gdufs.gd.dao;

import com.gdufs.gd.entity.YActivityUser;

/**
 * 活动参与关系的数据表操作
 * 
 * @author Administrator
 *
 */
public interface YActivityUserDao {
	/**
	 * 添加join关系
	 * 
	 * @param uId
	 * @param activityId
	 * @return
	 */
	public boolean add(YActivityUser activityUser);

	/**
	 * 查询join关系
	 * 
	 * @param uId
	 * @param activityId
	 * @return
	 */
	public YActivityUser query(int uId, int activityId);

	/**
	 * 删除join关系
	 * 
	 * @param uId
	 * @param activityId
	 * @return
	 */
	public boolean delete(YActivityUser activityUser);

	/**
	 * 删除活动时候删除所有joiner
	 * 
	 * @param activityUser
	 * @return
	 */
	public boolean deleteByActivityId(int activityId);

}
