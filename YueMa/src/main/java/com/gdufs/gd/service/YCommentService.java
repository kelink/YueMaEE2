package com.gdufs.gd.service;

import java.util.List;

import com.gdufs.gd.entity.YComment;

public interface YCommentService {
	 /**
	  * 添加活动的评论
	  * @param comment
	  * @return
	  */
	public boolean add(YComment comment);
	/**
	 * 删除活动的评论
	 * @param comment
	 * @return
	 */
	public boolean delete(int commentId);
	
	
	public List<YComment> getCommentsByActivityId(int activityId);
}
