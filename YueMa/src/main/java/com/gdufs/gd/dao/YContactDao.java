package com.gdufs.gd.dao;

import java.util.List;

import com.gdufs.gd.entity.YContact;

public interface YContactDao {
	/**
	 * 添加通讯录对象
	 * @param contactObj
	 * @return
	 */
	public boolean addContacts(List<YContact> contactObj);	
	
	/**
	 * 注册时候更新contact表，修改人脉信息
	 * @param friendNum
	 * @return
	 */
	public boolean updateIsSysUserByFriendsNum(String friendNum);
	
	/**
	 * 获取一度人脉通讯表
	 * @param phoneNum
	 * @return
	 */
	public List<YContact> getFirstContact(String phoneNum);
}
