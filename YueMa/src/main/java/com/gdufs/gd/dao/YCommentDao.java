package com.gdufs.gd.dao;

import java.util.List;

import com.gdufs.gd.entity.YComment;

 public interface YCommentDao {
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
	public boolean delete(YComment comment);
	
	
	public List<YComment> getCommentsByActivityId(int activityId);

}
