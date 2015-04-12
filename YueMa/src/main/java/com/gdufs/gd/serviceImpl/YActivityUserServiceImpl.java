package com.gdufs.gd.serviceImpl;

import java.util.Date;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.gdufs.gd.dao.YActivityDao;
import com.gdufs.gd.dao.YActivityUserDao;
import com.gdufs.gd.dao.YUserDao;
import com.gdufs.gd.entity.YActivityUser;
import com.gdufs.gd.service.YActivityUserService;

/**
 * 用户参与活动记录的表操作
 * 
 * @author Administrator
 *
 */
@Service(value = "activityUserService")
public class YActivityUserServiceImpl implements YActivityUserService {
	private static final Logger logger = LoggerFactory
			.getLogger(YActivityServiceImpl.class);

	public YActivityUserServiceImpl() {
		// TODO Auto-generated constructor stub
	}

	@Resource(name = "activityUserDao")
	private YActivityUserDao activityUserDao;

	@Resource(name = "activityDao")
	private YActivityDao activityDao;

	@Resource(name = "userDao")
	private YUserDao userDao;

	@Override
	public boolean joinActivity(int activityId, int uId) {
		YActivityUser activityUser = new YActivityUser();
		activityUser.setActivity(activityDao
				.getActivityByActivityId(activityId));
		activityUser.setUser(userDao.getUserById(uId));
		activityUser.setIsAuth(1);// 默认为授权
		activityUser.setIsTickOff(0);// 默认没有剔除
		activityUser.setJoin_time(new Date());
		return activityUserDao.add(activityUser);
	}

	@Override
	public boolean quitActivity(int activityId, int uId) {
		return activityUserDao.delete(activityUserDao.query(uId, activityId));
	}

}
