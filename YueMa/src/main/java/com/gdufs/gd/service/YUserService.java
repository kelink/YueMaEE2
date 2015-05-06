package com.gdufs.gd.service;

import java.util.List;

import com.gdufs.gd.entity.TransferMessage;
import com.gdufs.gd.entity.YUser;

public interface YUserService {
	
	public TransferMessage register(YUser user);

	public YUser CheckUser(YUser user);

	public YUser getUserById(int id);
	
	public YUser getUserByPhone(String phoneNum);
	
	public boolean updateUser(YUser user);
	
	/**
	 * 查询二度人脉
	 * @param userName
	 * @return
	 */
	public List<YUser> searchSecondUser(String userName,String phoneNum);
}
