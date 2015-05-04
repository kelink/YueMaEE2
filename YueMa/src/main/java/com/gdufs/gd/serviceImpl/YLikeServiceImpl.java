package com.gdufs.gd.serviceImpl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import com.gdufs.gd.dao.YLikeDao;
import com.gdufs.gd.entity.YLike;
import com.gdufs.gd.service.YLikeService;

@Service(value = "likeService")
public class YLikeServiceImpl implements YLikeService{

	@Resource(name = "likeDao")
	private YLikeDao likeDao;
	
	@Override
	public boolean addLike(YLike like) {
		return likeDao.add(like);
	}

	@Override
	public boolean cancleLike(int activityId, int userId) {
		return likeDao.delete(activityId, userId);
	}

}
