package com.gdufs.gd.daoImpl;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import com.gdufs.gd.dao.YLikeDao;
import com.gdufs.gd.entity.YLike;
import com.gdufs.gd.entity.YMessage;
import com.gdufs.gd.entity.YMessageUser;

/**
 * 用户点赞模块
 * @author Administrator
 *
 */
@Repository(value = "likeDao")
public class YLikeDaoImpl extends BaseDao implements YLikeDao{

	@Override
	public boolean add(YLike like) {
		Session session = this.getSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.save(like);
			session.flush();
			tx.commit();
			return true;
		} catch (Exception ex) {
			if (tx != null)
				tx.rollback();
		}
		return false;
	}

	@Override
	public boolean delete(int activityId,int userId) {
		
		Session session = this.getSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			String hql="delete from YLike where activityId=? and userId=? ";
			Query query = session.createSQLQuery(hql);
			query.setInteger(0, activityId);
			query.setInteger(1, userId);
			query.executeUpdate();
			tx.commit();
			return true;

		} catch (Exception ex) {
			if (tx != null) {
				tx.rollback();
			}
			return false;
		}
	}

}
