package com.gdufs.gd.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.gdufs.gd.dao.YContactDao;
import com.gdufs.gd.entity.YContact;
import com.gdufs.gd.entity.YUser;

@Repository(value = "contactDao")
public class YContactDaoImpl extends BaseDao implements YContactDao {

	protected static final Logger logger = LoggerFactory
			.getLogger(YContactDaoImpl.class);

	public YContactDaoImpl() {
		super();
	}

	/**
	 * 批量插入contact
	 */
	@Override
	public boolean addContacts(List<YContact> contactObj) {
		Session session = this.getSession();
		Transaction tx = null;
		try {
			Connection conn=getConnection();
			String sql="INSERT ignore INTO ycontact (friendNum,hostNum,isSysUser,version) VALUES (?, ?,?,?)";
			PreparedStatement pst=conn.prepareStatement(sql);			
			tx = session.beginTransaction();
			for (YContact contact : contactObj) {
				pst.setString(1, contact.getFriendNum());
				pst.setString(2, contact.getHostNum());
				pst.setInt(3,contact.getIsSysUser());
				pst.setInt(4, contact.getVersion());
				pst.addBatch();
			}
			pst.executeBatch();
			session.flush();
			tx.commit();
			return true;
		} catch (Exception ex) {
			if (tx != null) {
				tx.rollback();
			}
			logger.error("Add contact object error" + ex.getMessage());
			return false;
		}

	}

	/**
	 * 更新属性isSysUser
	 */
	@Override
	public boolean updateIsSysUserByFriendsNum(String friendNum) {
		Session session = this.getSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			String hql="update YContact contact set contact.isSysUser=1 where contact.friendNum=?";
			Query query=session.createQuery(hql);
			query.setString(0, friendNum);
			query.executeUpdate();
			session.flush();
			tx.commit();
			return true;
		} catch (Exception ex) {
			if (tx != null) {
				tx.rollback();
			}
			logger.error("Add contact object error" + ex.getMessage());
			return false;
		}
	}

	@Override
	public List<YContact> getFirstContact(String phoneNum) {
		Session session = this.getSession();
		String hql = "from YContact where hostNum=?";
		Query query = session.createQuery(hql);
		query.setParameter(0, phoneNum);
		return  query.list();
	}


}
