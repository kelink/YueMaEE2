package com.gdufs.gd.daoImpl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.gdufs.gd.dao.YCommentDao;
import com.gdufs.gd.entity.YComment;

@Repository(value = "commentDao")
public class YCommentDaoImpl extends BaseDao implements YCommentDao{

	@Override
	public boolean add(YComment comment) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(YComment comment) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public YComment getCommentById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<YComment> getCommentByUserId(int uId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean delete(YComment comment) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteById(YComment comment) {
		// TODO Auto-generated method stub
		return false;
	}

}
