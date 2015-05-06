package com.gdufs.gd.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.gdufs.gd.dao.YContactDao;
import com.gdufs.gd.dao.YRelationSecondDao;
import com.gdufs.gd.entity.YContact;
import com.gdufs.gd.entity.YRelationSecond;
import com.gdufs.gd.entity.YUser;
import com.gdufs.gd.service.YContactService;

@Service(value = "contactService")
public class YContactServiceImpl implements YContactService {

	@Resource(name = "contactDao")
	private YContactDao contactDao;
	
	@Resource(name = "relationSecondDao")
	private YRelationSecondDao relationSecondDao;

	@Override
	public boolean addAContact(YContact contactObj) {
		ArrayList<YContact> list = new ArrayList<YContact>();
		list.add(contactObj);
		return contactDao.addContacts(list);
	}

	@Override
	public boolean addContactList(List<YContact> contactList) {
		return contactDao.addContacts(contactList);
	}

	@Override
	public List<YContact> listAllContact() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<YContact> listContactById() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deleteAContact(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateContact(YContact contactObj) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * 更新用户关系表
	 */
	@Override
	public boolean updateContactByUserId(int userId, List<YContact> contactList) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * 更新获取一度人脉关系
	 */
	@Override
	public List<YContact> getFirstPeople(String phoneNum) {
		return contactDao.getFirstContact(phoneNum);
	}
	/**
	 * 获取二度人脉关系
	 */
	@Override
	public List<YUser> getSecondPeople(String phoneNum) {
		return relationSecondDao.getSecondPeople(phoneNum);
	}

	@Override
	public YContact getContactByPhoneNum(String hostNum, String friendNum) {
		return contactDao.getContactByPhoneNum(hostNum,friendNum);
	}

	@Override
	public boolean isFirstFriend(String hostNum, String friendNum) {
		return contactDao.isFirstFriend(hostNum, friendNum);
	}

	@Override
	public boolean isSecondFriend(String hostNum, String friendNum) {
		return relationSecondDao.isSecondFrined(hostNum, friendNum);
	}

	@Override
	public boolean addFriends(YContact friend1, YContact friend2) {
		ArrayList<YContact> list = new ArrayList<YContact>();
		list.add(friend1);
		list.add(friend2);
		return contactDao.addContacts(list);
	}

	@Override
	public List<YUser> getFirstUser(String hostNum) {
		return contactDao.getFirstUser(hostNum);
	}

	
}
