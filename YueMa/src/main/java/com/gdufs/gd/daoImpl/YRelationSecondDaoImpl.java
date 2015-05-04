package com.gdufs.gd.daoImpl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.gdufs.gd.dao.YRelationSecondDao;
import com.gdufs.gd.entity.YRelationSecond;
import com.gdufs.gd.entity.YUser;
@Repository(value = "relationSecondDao")
public class YRelationSecondDaoImpl extends BaseDao implements YRelationSecondDao  {
	protected static final Logger logger = LoggerFactory
			.getLogger(YRelationSecondDaoImpl.class);
	@Override
	public List<YRelationSecond> getSecondFriends(String phoneNum) {
		Session session = this.getSession();
		String hql = "from YRelationSecond where hostNum=?";
		Query query = session.createQuery(hql);
		query.setParameter(0, phoneNum);
		return  query.list();
	}

	/**
	 * 获取二度人脉信息
	 */
	@Override
	public List<YUser> getSecondPeople(String phoneNum) {
		Session session=this.getSession();
//		String hql = "from YUser user where user.phoneNum in(select friendNum from YRelationSecond relationsecond where relationsecond.hostNum=?)"
//				+ " or user.phoneNum in (select hostNum from YRelationSecond relationsecond where relationsecond.friendNum=?)";
		
		String hql = "from YUser user where user.phoneNum in(select friendNum from YRelationSecond relationsecond where relationsecond.hostNum=?)";
		Query query = session.createQuery(hql);
		query.setParameter(0, phoneNum);
		//query.setParameter(1, phoneNum);
		return query.list();
	}

	@Override
	public boolean isSecondFrined(String hostNum, String friendNum) {
		Session session = this.getSession();
		String hql = "Select count(*) from YRelationSecond second where (second.hostNum=? and second.friendNum=?) or(second.friendNum=? and second.hostNum=?)";
		Query query = session.createQuery(hql);
		query.setParameter(0, hostNum);
		query.setParameter(1, friendNum);
		query.setParameter(2, hostNum);
		query.setParameter(3, friendNum);
		
		Object object=query.uniqueResult();
		int num=Integer.parseInt(object.toString());
		if (num>0) {
			return true;
		}
		return false;
	}

}
