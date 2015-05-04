package com.gdufs.gd.daoImpl;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import com.gdufs.gd.common.C;
import com.gdufs.gd.dao.YMessageUserDao;
import com.gdufs.gd.entity.YMessage;
import com.gdufs.gd.entity.YMessageUser;

@Repository(value = "messageUserDao")
public class YMessageUserDaoImpl extends BaseDao implements YMessageUserDao{

	@Override
	public List<YMessageUser> getMessageUserByUser(int userId) {
		Session session = this.getSession();
		String hql = "from YMessageUser as messageUser where messageUser.user.id=? and messageUser.isSend=?";
		Query query = session.createQuery(hql);
		query.setParameter(0, userId);
		query.setParameter(1, C.DefaultValues.DEFAULT_IS_SEND);		
		return  query.list();
	}

	@Override
	public boolean updateMessageUser(YMessageUser messageUser) {
		Session session = this.getSession();
		Transaction tx = session.beginTransaction();
		try {
			String hql = "update YMessageUser messageUser set messageUser.isRead=?,messageUser.readTime=?,messageUser.isSend=? "
					+ "where messageUser.user.id=?"
					+"and messageUser.message.id=?";
			Query query = session.createQuery(hql);
			query.setParameter(0, messageUser.getIsRead());
			query.setParameter(1, messageUser.getReadTime());
			query.setParameter(2, messageUser.getIsSend());
			query.setParameter(3, messageUser.getUser().getId());
			query.setParameter(4, messageUser.getMessage().getId());
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
			return false;
		}
	}

	@Override
	public YMessageUser getMessageUserById(int userId,int messageId) {
		Session session = this.getSession();
		String hql = "from YMessageUser as messageUser where messageUser.user.id=? and messageUser.message.id=?";
		Query query = session.createQuery(hql);
		query.setParameter(0, userId);
		query.setParameter(0, messageId);
		return  (YMessageUser) query.uniqueResult();
	}

	@Override
	public List<YMessageUser> getUserUnSendtMsgs(int userId) {
		Session session = this.getSession();
		String hql = "from YMessageUser as messageUser where messageUser.user.id=? and messageUser.isSend=?";
		Query query = session.createQuery(hql);
		query.setParameter(0, userId);
		query.setParameter(1, C.DefaultValues.DEFAULT_IS_SEND);		
		return  query.list();
	}
	@Override
	public YMessageUser addMsg(YMessage message) {
		YMessageUser returnMessageUser=null;
		Session session = this.getSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			// 插入消息
			session.save(message);
			// 插入发往的消息
			for (YMessageUser messageUser : message.getMessageUser()) {
				 session.save(messageUser);
			}
			session.flush();
			tx.commit();
			Iterator<YMessageUser> iterator=message.getMessageUser().iterator();
			  while(iterator.hasNext()){//遍历  
				  returnMessageUser=iterator.next(); 
		        }  
			System.out.println("插入的信息的id----->"+returnMessageUser);
		} catch (Exception ex) {
			if (tx != null)
				tx.rollback();
		}
		return returnMessageUser;
	}

	@Override
	public List<YMessageUser> getAllUserUnSendtMsgs() {
		Session session = this.getSession();
		String hql = "from YMessageUser as messageUser where messageUser.isSend=? and messageUser.user.isLogin=?";
		Query query = session.createQuery(hql);
		query.setParameter(0, C.DefaultValues.DEFAULT_IS_SEND);	
		query.setParameter(1, 1);//只查询已经登陆中的发送
		return  query.list();
	}
}
