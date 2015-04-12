package com.gdufs.gd.daoImpl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.gdufs.gd.dao.YBulletinDao;
import com.gdufs.gd.entity.YBulletin;
import com.gdufs.gd.entity.YBulletinUser;
import com.gdufs.gd.entity.YUser;

@Repository(value = "bulletinDao")
public class YBulletinDaoImpl extends BaseDao implements YBulletinDao{
	
	private static final Logger logger = LoggerFactory
			.getLogger(YBulletinDaoImpl.class);

	/**
	 * 发布公告
	 */
	@Override
	public boolean add(YBulletin bulletin) {
		Session session = this.getSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.save(bulletin);
			session.flush();
			tx.commit();
			return true;
		} catch (Exception ex) {
			if (tx != null) {
				tx.rollback();
			}
			logger.error("Add bulletin error" + ex.getMessage());
			return false;
		}
	}

	@Override
	public boolean update(YBulletin bulletin) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public boolean delete(YBulletin bulletin) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public YBulletin getBulletinById(int id) {
		Session session = this.getSession();
		String hql = "from YBulletin where id=?";
		Query query = session.createQuery(hql);
		query.setParameter(0, id);
		return  (YBulletin) query.uniqueResult();
	}

	//根据当前用户
	@Override
	public List<YBulletinUser> getBulletinByUserId(int id) {
		Session session = this.getSession();
		String hql = "from YBulletinUser bulletinUser where bulletinUser.user.id=? and bulletinUser.isRead=1 ";
		Query query = session.createQuery(hql);
		query.setParameter(0, id);
		return  query.list();
	}
}