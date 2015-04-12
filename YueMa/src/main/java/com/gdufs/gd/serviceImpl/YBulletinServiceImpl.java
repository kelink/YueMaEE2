package com.gdufs.gd.serviceImpl;

import java.util.List;



import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.gdufs.gd.dao.YBulletinDao;
import com.gdufs.gd.entity.YBulletin;
import com.gdufs.gd.entity.YBulletinUser;
import com.gdufs.gd.service.YBulletinService;
@Service(value = "bulletinService")
public class YBulletinServiceImpl implements YBulletinService {

	@Resource(name = "bulletinDao")
	private YBulletinDao bulletinDao;
	@Override
	public boolean add(YBulletin bulletin) {
		return bulletinDao.add(bulletin);
	}

	@Override
	public boolean update(YBulletin bulletin) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public YBulletin getBulletinById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<YBulletinUser> getBulletinByUserId(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean delete(YBulletin bulletin) {
		// TODO Auto-generated method stub
		return false;
	}

}
