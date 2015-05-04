package com.gdufs.gd.service;

import com.gdufs.gd.entity.YLike;


public interface YLikeService {
	public boolean addLike(YLike like);
	public boolean cancleLike(int activityId, int userId);
}
