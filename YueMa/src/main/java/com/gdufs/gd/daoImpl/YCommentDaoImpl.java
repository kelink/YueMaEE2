package com.gdufs.gd.daoImpl;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.gdufs.gd.dao.YCommentDao;
import com.gdufs.gd.entity.YComment;

@Repository(value = "commentDao")
public class YCommentDaoImpl extends BaseDao implements YCommentDao{
	private static final Logger logger = LoggerFactory.getLogger(YCommentDaoImpl.class);
	@Override
	public boolean add(YComment comment) {
		Session session = this.getSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.save(comment);
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
	public boolean delete(YComment comment) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<YComment> getCommentsByActivityId(int activityId) {
		Session session = this.getSession();
		String hql = "from YComment comment where comment.activity.id=? and comment.isDelete=?";
		Query query = session.createQuery(hql);
		query.setParameter(0, activityId);
		query.setParameter(1, 0);
		return  query.list();
	}

	
}
