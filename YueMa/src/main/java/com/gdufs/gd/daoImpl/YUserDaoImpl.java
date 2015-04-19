 package com.gdufs.gd.daoImpl;

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
		//需要重写
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
	public YUser getUserByNameAndPwd(String phoneNum,String pwd) {
		Session session = this.getSession();
		String hql = "from YUser where phoneNum=? and password=?";
		Query query = session.createQuery(hql);
		query.setParameter(0, phoneNum);
		query.setParameter(1, pwd);
		return  (YUser) query.uniqueResult();		
	}
}