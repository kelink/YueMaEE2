package com.gdufs.gd.daoImpl;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.gdufs.gd.dao.YActivityUserDao;
import com.gdufs.gd.entity.YActivityUser;

@Repository(value = "activityUserDao")
public class YActivityUserDaoImpl extends BaseDao implements YActivityUserDao {
	private static final Logger logger = LoggerFactory
			.getLogger(YActivityDaoImpl.class);

	public YActivityUserDaoImpl() {
	}

	@Override
	public boolean add(YActivityUser activityUser) {
		Session session = this.getSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.save(activityUser);
			session.flush();
			tx.commit();
			return true;
		} catch (Exception ex) {
			if (tx != null) {
				tx.rollback();
			}
			System.out.println(ex);
			logger.error("Add activity error" + ex.getMessage());
			return false;
		}
	}

	@Override
	public YActivityUser query(int uId, int activityId) {
		Session session = this.getSession();
		String hql = "from YActivityUser where activityId=? and userId=?";
		Query query = session.createQuery(hql);
		query.setParameter(0, activityId);
		query.setParameter(1, uId);
		YActivityUser activityUser = (YActivityUser) query.uniqueResult();
		return activityUser;
	}

	@Override
	public boolean delete(YActivityUser activityUser) {
		Session session = this.getSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.delete(activityUser);
			tx.commit();
			return true;
		} catch (Exception ex) {
			if (tx != null) {
				tx.rollback();
			}
			System.out.println(ex);
			logger.error("Add activity error" + ex.getMessage());
			return false;
		}
	}

	@Override
	public boolean deleteByActivityId(int activityId) {
		Session session = this.getSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			String hql = "delete YActivityUser as activity where activity.id=?";
			Query query = session.createQuery(hql);
			query.setInteger(0, activityId);
			query.executeUpdate();
			tx.commit();
			return true;
		} catch (Exception ex) {
			if (tx != null) {
				tx.rollback();
			}
			System.out.println(ex);
			logger.error("Add activity error" + ex.getMessage());
			return false;
		}
	}

}
