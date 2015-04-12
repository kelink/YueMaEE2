package com.gdufs.gd.service;

import java.util.List;

import com.gdufs.gd.entity.YBulletin;
import com.gdufs.gd.entity.YBulletinUser;

public interface YBulletinService {
	public boolean add(YBulletin bulletin);

	public boolean update(YBulletin bulletin);
	
	public YBulletin getBulletinById(int id);

	public List<YBulletinUser> getBulletinByUserId(int id);
	
	public boolean delete(YBulletin bulletin);
}
