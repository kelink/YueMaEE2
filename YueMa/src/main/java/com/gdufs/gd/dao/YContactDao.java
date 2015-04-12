package com.gdufs.gd.dao;

import java.util.List;

import com.gdufs.gd.entity.YContact;

public interface YContactDao {
	public boolean addContacts(List<YContact> contactObj);	
	public boolean updateIsSysUserByFriendsNum(String friendNum);
}
