package com.gdufs.gd.dao;

import java.util.List;

import com.gdufs.gd.entity.YRelationSecond;
import com.gdufs.gd.entity.YUser;

public interface YRelationSecondDao {
	/**
	 * 获取二度人脉的号码
	 * @param phoneNum
	 * @return
	 */
	public List<YRelationSecond> getSecondFriends(String phoneNum);
	/**
	 * 获取二度人脉的用户信息
	 * @param phoneNum
	 * @return
	 */
	public List<YUser> getSecondPeople(String phoneNum);
	
	/**
	 * 判断是否为二度人脉
	 * @param hostNum
	 * @param friendNum
	 * @return
	 */
	public boolean isSecondFrined(String hostNum,String friendNum);
}
