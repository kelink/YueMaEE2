package com.gdufs.gd.daoImpl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.gdufs.gd.dao.YRelationSecondDao;
import com.gdufs.gd.entity.YRelationSecond;

public class YRelationSecondDaoImpl extends BaseDao implements YRelationSecondDao  {

	@Override
	public List<YRelationSecond> getSecondFriends(String phoneNum) {
		Session session = this.getSession();
		String hql = "from YRelationSecond where hostNum=?";
		Query query = session.createQuery(hql);
		query.setParameter(0, phoneNum);
		return  query.list();
	}

}
