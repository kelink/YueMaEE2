package com.gdufs.gd.dao;

import java.util.List;
import com.gdufs.gd.entity.YRemind;

public interface YRemindDao {
	public boolean add(YRemind remind);

	public boolean update(YRemind remind);
	
	public YRemind getRemindById(int id);
	
	public List<YRemind> getRemindByUserId(int uId);

	public boolean delete(YRemind remind);

}
