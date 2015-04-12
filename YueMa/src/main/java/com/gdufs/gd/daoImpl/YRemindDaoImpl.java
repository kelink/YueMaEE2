package com.gdufs.gd.daoImpl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.gdufs.gd.dao.YRemindDao;
import com.gdufs.gd.entity.YRemind;

@Repository(value = "remindDao")
public class YRemindDaoImpl extends BaseDao implements YRemindDao {

	@Override
	public boolean add(YRemind remind) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(YRemind remind) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public YRemind getRemindById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<YRemind> getRemindByUserId(int uId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean delete(YRemind remind) {
		// TODO Auto-generated method stub
		return false;
	}

}
