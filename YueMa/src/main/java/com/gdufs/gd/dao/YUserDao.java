package com.gdufs.gd.dao;

import com.gdufs.gd.entity.YUser;

public interface YUserDao {
	public boolean addUser(YUser user);

	public boolean updateUser(YUser user);

	public YUser getUserByNameAndPwd(String name,String pwd);
	
	public YUser getUserById(int id);
	
	public YUser getUserByPhone(String phoneNum);

	public boolean delete(YUser user);

	
}