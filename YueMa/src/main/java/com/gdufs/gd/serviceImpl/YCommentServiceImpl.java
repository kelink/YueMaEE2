package com.gdufs.gd.serviceImpl;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.gdufs.gd.ActivityController;
import com.gdufs.gd.dao.YCommentDao;
import com.gdufs.gd.dao.YUserDao;
import com.gdufs.gd.entity.YComment;
import com.gdufs.gd.service.YCommentService;

@Service(value = "commentService")
public class YCommentServiceImpl implements YCommentService{
	
	private static final Logger logger = LoggerFactory
			.getLogger(YCommentServiceImpl.class);
	
	@Resource(name = "commentDao")
	private YCommentDao commentDao;
	
	@Override
	public boolean add(YComment comment) {
		return commentDao.add(comment);
	}

	@Override
	public boolean delete(YComment comment) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<YComment> getCommentsByActivityId(int activityId) {
		return commentDao.getCommentsByActivityId(activityId);
	}

}
