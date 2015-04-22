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

	/**
	 * 新增加活动
	 */
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

	/**
	 *通过活动id获取活动
	 */
	@Override
	public YActivity getActivityByActivityId(int aid) {
		Session session = this.getSession();
		String hql = "from YActivity where id=?";
		Query query = session.createQuery(hql);
		query.setParameter(0, aid);
		YActivity activity = (YActivity) query.uniqueResult();
		return activity;
	}

	/**
	 * 通过用户id查找创建的
	 */
	@Override
	public List<YActivity> getActivityByCreatorId(int uId) {
		Session session = this.getSession();
		String hql = "from YActivity where creator.id=?";
		Query query = session.createQuery(hql);
		query.setParameter(0, uId);
		List<YActivity> user = query.list();
		return user;
	}

	/**
	 * 删除活动和其关系
	 */
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
	/**
	 *  查找我发布的
	 */
	@Override
	public List<YActivity> getMyActivitiesbyPhonNnum(String phoneNum) {
		
		Session session = this.getSession();
		String hql = "from YActivity activity where activity.creatorPhoneNum=? order by activity.createTimeDate desc";
		Query query = session.createQuery(hql);
		query.setParameter(0, phoneNum);
		return query.list();
	}
	/**
	 *  查找一度人脉发布的
	 */
	@Override
	public List<YActivity> getFirstActivitiesbyPhonNnum(String phoneNum) {
		
		Session session = this.getSession();
		String hql = "from YActivity activity where activity.creatorPhoneNum in(select friendNum from YContact contact where contact.hostNum=? and contact.isSysUser=1) order by activity.createTimeDate desc";
		Query query = session.createQuery(hql);
		query.setParameter(0, phoneNum);
		return query.list();
	}

	/**
	 *  查找二度人脉发布的
	 */
	@Override
	public List<YActivity> getSecondActivitiesbyPhonNnum(String phoneNum) {
		
		Session session = this.getSession();
		String hql = "from YActivity activity where activity.creatorPhoneNum in(select friendNum from YRelationSecond relationsecond where relationsecond.hostNum=?) order by activity.createTimeDate desc";
		Query query = session.createQuery(hql);
		query.setParameter(0, phoneNum);
		return query.list();
	}

	/**
	 * 查找用户所有的活动
	 */
	@Override
	public List<YActivity> getAllActivity(String phoneNum) {
		Session session = this.getSession();
		String hql = "from YActivity activity where activity.creatorPhoneNum=? or activity.creatorPhoneNum in(select friendNum from YRelationSecond relationsecond where relationsecond.hostNum=?) or activity.creatorPhoneNum in(select friendNum from YRelationSecond relationsecond where relationsecond.hostNum=?) order by activity.createTimeDate desc";
		Query query = session.createQuery(hql);
		query.setParameter(0, phoneNum);
		query.setParameter(1, phoneNum);
		query.setParameter(2, phoneNum);
		return query.list();
	}

	
	/**
	 * 分页查找用户的活动
	 */
	@Override
	public List<YActivity> getActivityOnPage(int pageNum, int pageSize,
			String phoneNum) {
		Session session = this.getSession();
		String hql = "from YActivity activity where activity.creatorPhoneNum=? "
				+"or activity.creatorPhoneNum in(select friendNum from YContact contact where contact.hostNum=? and contact.isSysUser=?)"
				+ "or activity.creatorPhoneNum in(select friendNum from YRelationSecond relationsecond where relationsecond.hostNum=?) "
				+"or activity.creatorPhoneNum in(select hostNum from YRelationSecond relationsecond where relationsecond.friendNum=?)"
				+ "order by activity.createTimeDate desc";
		Query query = session.createQuery(hql);
		int offSet = (pageNum  * pageSize)>= 0 ? (pageNum  * pageSize): 0;
		query.setParameter(0, phoneNum);
		query.setParameter(1, phoneNum);
		query.setParameter(2, 1);
		query.setParameter(3, phoneNum);
		query.setParameter(4, phoneNum);
		query.setFirstResult(offSet);
		query.setMaxResults(pageSize);
		return query.list();
	}

	/**
	 * 计算用户朋友圈的信息的个数
	 */
	@Override
	public int countFriendsActivity(String phoneNum) {
		Session session = this.getSession();
		String hql = "select count(*) from YActivity activity where activity.creatorPhoneNum=? "
				+"or activity.creatorPhoneNum in(select friendNum from YContact contact where contact.hostNum=? and contact.isSysUser=?)"
				+ "or activity.creatorPhoneNum in(select friendNum from YRelationSecond relationsecond where relationsecond.hostNum=?) "
				+ "or activity.creatorPhoneNum in(select hostNum from YRelationSecond relationsecond where relationsecond.friendNum=?)";
		Query query = session.createQuery(hql);
		query.setParameter(0, phoneNum);
		query.setParameter(1, phoneNum);
		query.setParameter(2, 1);
		query.setParameter(3, phoneNum);
		query.setParameter(4, phoneNum);
		Object object=query.uniqueResult();
		int num=Integer.parseInt(object.toString());
		return num;
	}

}
