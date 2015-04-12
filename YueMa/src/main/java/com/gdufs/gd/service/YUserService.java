package com.gdufs.gd.service;

import com.gdufs.gd.entity.TransferMessage;
import com.gdufs.gd.entity.YUser;

public interface YUserService {
	public TransferMessage register(YUser user);

	public YUser CheckUser(YUser user);

	public YUser getUserById(int id);
	
	public YUser getUserByPhone(String phoneNum);
}
