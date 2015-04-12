package com.gdufs.gd.dao;

import java.util.List;

import com.gdufs.gd.entity.YComment;

 public interface YCommentDao {
	public boolean add(YComment comment);

	public boolean update(YComment comment);
	
	public YComment getCommentById(int id);
	
	public List<YComment> getCommentByUserId(int uId);

	public boolean delete(YComment comment);

	public boolean deleteById(YComment comment);
}
