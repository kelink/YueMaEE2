package com.gdufs.gd.daoImpl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import com.gdufs.gd.dao.YMessageDao;
import com.gdufs.gd.entity.YActivityUser;
import com.gdufs.gd.entity.YMessage;
import com.gdufs.gd.entity.YMessageUser;

/**
 * 用户消息类的Dao操作
 * 
 * @author Administrator
 *
 */
@Repository(value = "messageDao")
public class YMessageDaoImpl extends BaseDao implements YMessageDao {

	@Override
	public boolean add(YMessage message) {
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
			return true;
		} catch (Exception ex) {
			if (tx != null)
				tx.rollback();
		}
		return false;
	}

	@Override
	public boolean update(YMessage message) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public YMessage getMessageById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<YMessage> getMessageByUserId(int uId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean delete(YMessage message) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteById(int id) {
		// TODO Auto-generated method stub
		return false;
	}

}
