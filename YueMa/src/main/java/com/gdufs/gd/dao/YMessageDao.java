package com.gdufs.gd.dao;

import java.util.List;

import com.gdufs.gd.entity.YMessage;
import com.gdufs.gd.entity.YMessageUser;

public interface YMessageDao {
	public boolean add(YMessage message);

	public boolean update(YMessage message);
	
	public YMessage getMessageById(int id);
	
	public List<YMessage> getMessageByUserId(int uId);

	public boolean delete(YMessage message);

	public boolean deleteById(int id);
}
