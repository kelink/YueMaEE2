package com.gdufs.gd.dao;

import java.util.List;

import com.gdufs.gd.entity.YBulletin;
import com.gdufs.gd.entity.YBulletinUser;
import com.gdufs.gd.entity.YUser;

/**
 * 系统公告类
 * @author Administrator
 *
 */
public interface YBulletinDao {
	public boolean add(YBulletin bulletin);

	public boolean update(YBulletin bulletin);
	
	public YBulletin getBulletinById(int id);

	public List<YBulletinUser> getBulletinByUserId(int id);
	
	public boolean delete(YBulletin bulletin);

	
}
