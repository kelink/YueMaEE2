package com.gdufs.gd.daoImpl;

import java.util.Date;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import antlr.collections.List;

import com.gdufs.gd.dao.YUserDao;
import com.gdufs.gd.entity.YUser;

@Repository(value = "userDao")
public class YUserDaoImpl extends BaseDao implements YUserDao {
	private static final Logger logger = LoggerFactory
			.getLogger(YUserDaoImpl.class);

	@Override
	public boolean addUser(YUser user) {
		Session session = this.getSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.save(user);
			session.flush();
			tx.commit();
			return true;
		} catch (Exception ex) {
			if (tx != null) {
				tx.rollback();
			}
			System.out.println(ex);
			logger.error("Add YUser error" + ex.getMessage());
			return false;
		}
	}

	@Override
	public boolean updateUser(YUser user) {
		Session session = this.getSession();
		Transaction tx = session.beginTransaction();
		try {
			String hql = "update YUser user set "
					+ "user.userName=?,phoneNum=?,user.password=?,"
					+ "user.facePath=?,user.introduce=?,user.lastLoginTimeDate=?,"
					+ "user.lastLoginIp=?,user.gender=?,user.origin=?,user.isLogin=? "
					+ "where user.id=?";
			Query query = session.createQuery(hql);
			query.setParameter(0, user.getUserName());
			query.setParameter(1, user.getPhoneNum());
			query.setParameter(2, user.getPassword());
			query.setParameter(3, user.getFacePath());
			query.setParameter(4, user.getIntroduce());
			query.setParameter(5, user.getLastLoginTimeDate());
			query.setParameter(6, user.getLastLoginIp());
			query.setParameter(7, user.getGender());
			query.setParameter(8, user.getOrigin());
			query.setParameter(9, user.getIsLogin());
			query.setParameter(10, user.getId());
			int ret = query.executeUpdate();
			tx.commit();
			if (ret > 0) {
				return true;
			} else {
				return false;
			}		
		} catch (Exception ex) {
			if (tx != null) {
				tx.rollback();
			}
			System.out.println(ex);
			logger.error("Add YUser error" + ex.getMessage());
			return false;
		}
	}

	@Override
	public boolean delete(YUser user) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public YUser getUserById(int id) {
		Session session = this.getSession();
		String hql = "from YUser where id=?";
		Query query = session.createQuery(hql);
		query.setParameter(0, id);
		YUser user = (YUser) query.uniqueResult();
		return user;
	}

	@Override
	public YUser getUserByPhone(String phoneNum) {
		Session session = this.getSession();
		String hql = "from YUser where phoneNum=?";
		Query query = session.createQuery(hql);
		query.setParameter(0, phoneNum);
		YUser user = (YUser) query.uniqueResult();
		return user;
	}

	@Override
	public YUser getUserByNameAndPwd(String phoneNum, String pwd) {
		Session session = this.getSession();
		String hql = "from YUser where phoneNum=? and password=?";
		Query query = session.createQuery(hql);
		query.setParameter(0, phoneNum);
		query.setParameter(1, pwd);
		return (YUser) query.uniqueResult();
	}

	@Override
	public java.util.List<YUser> searchSecondUser(String userName,String phoneNum) {
		System.out.println("searchSecondUser------------------>"+userName);
		Session session = this.getSession();
		String hql = "from YUser user where user.userName like '%"+userName+"%' "
				+ "where user.phoneNum in(select friendNum from YRelationSecond relationsecond where relationsecond.hostNum=?)";
		Query query = session.createQuery(hql);
		return  query.list();
	}
}