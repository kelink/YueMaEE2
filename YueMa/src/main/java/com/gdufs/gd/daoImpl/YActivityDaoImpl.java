package com.gdufs.gd.daoImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.gdufs.gd.common.C;
import com.gdufs.gd.dao.YActivityDao;
import com.gdufs.gd.entity.YActivity;
import com.gdufs.gd.entity.YActivityUser;

@Repository(value = "activityDao")
public class YActivityDaoImpl extends BaseDao implements YActivityDao {
	private static final Logger logger = LoggerFactory
			.getLogger(YActivityDaoImpl.class);

	public YActivityDaoImpl() {
	}

	@Override
	public boolean add(YActivity activity) {
		Session session = this.getSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			// //插入图片
			// for (YPicture picture : activity.getPictures()) {
			// session.save(picture);
			// }
			// //插入标签
			// for (YLabel label : activity.getLabels()) {
			// session.save(label);
			// }
			// 插入活动
			session.save(activity);
			// 插入活动参与者
			for (YActivityUser activityUser : activity.getActivityUsers()) {
				session.save(activityUser);
			}
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
	public boolean update(YActivity activity) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public YActivity getActivityByActivityId(int aid) {
		Session session = this.getSession();
		String hql = "from YActivity where id=?";
		Query query = session.createQuery(hql);
		query.setParameter(0, aid);
		YActivity activity = (YActivity) query.uniqueResult();
		return activity;
	}

	@Override
	public List<YActivity> getActivityByCreatorId(int uId) {
		Session session = this.getSession();
		String hql = "from YActivity where creator.id=?";
		Query query = session.createQuery(hql);
		query.setParameter(0, uId);
		List<YActivity> user = query.list();
		return user;
	}

	@Override
	public boolean delete(int activityId) {
		Session session = this.getSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			// 删除所有参加关系
			String sql = "delete from YActivityUser where activityId=?";
			Query query2 = session.createSQLQuery(sql);
			query2.setInteger(0, activityId);
			query2.executeUpdate();
			// 删除活动
			String hql = "delete YActivity as activity where activity.id=?";
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

	@Override
	public List<YActivity> getActivityByPage(int pageNum, int pageSize) {
		// 需要修改
		Session session = this.getSession();
		String hql = "from YActivity activity";
		Query query = session.createQuery(hql);
		int offSet = (pageNum - 1) * pageSize >= 0 ? (pageNum - 1) * pageSize
				: 0;
		query.setFirstResult(offSet);
		query.setMaxResults(pageSize);
		return query.list();
	}

	@Override
	public List<YActivity> getMyActivitiesbyPhonNnum(String phoneNum) {
		// 查找我发布的
		Session session = this.getSession();
		String hql = "from YActivity activity where activity.creatorPhoneNum=? order by activity.createTimeDate desc";
		Query query = session.createQuery(hql);
		query.setParameter(0, phoneNum);
		return query.list();
	}

	@Override
	public List<YActivity> getFirstActivitiesbyPhonNnum(String phoneNum) {
		// 查找一度人脉发布的
		Session session = this.getSession();
		String hql = "from YActivity activity where activity.creatorPhoneNum in(select friendNum from YContact contact where contact.hostNum=? and contact.isSysUser=1) order by activity.createTimeDate desc";
		Query query = session.createQuery(hql);
		query.setParameter(0, phoneNum);
		return query.list();
	}

	@Override
	public List<YActivity> getSecondActivitiesbyPhonNnum(String phoneNum) {
		// 查找一度人脉发布的
		Session session = this.getSession();
		String hql = "from YActivity activity where activity.creatorPhoneNum in(select friendNum from YRelationSecond relationsecond where relationsecond.hostNum=?) order by activity.createTimeDate desc";
		Query query = session.createQuery(hql);
		query.setParameter(0, phoneNum);
		return query.list();
	}

}
