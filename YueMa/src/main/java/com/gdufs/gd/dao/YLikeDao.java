package com.gdufs.gd.dao;

import com.gdufs.gd.entity.YLike;

public interface YLikeDao {

	public boolean add(YLike like);
	
	public boolean delete(int activityId,int userId);
}
