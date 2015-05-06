package com.gdufs.gd.dao;

import java.util.List;

import com.gdufs.gd.entity.YUser;

public interface YUserDao {
	public boolean addUser(YUser user);

	public boolean updateUser(YUser user);

	public YUser getUserByNameAndPwd(String name,String pwd);
	
	public YUser getUserById(int id);
	
	public YUser getUserByPhone(String phoneNum);

	public boolean delete(YUser user);

	/**
	 * 查询二度人脉
	 * @param userName
	 * @return
	 */
	public List<YUser> searchSecondUser(String userName,String phoneNum);

	
}