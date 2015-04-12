package com.gdufs.gd.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.gdufs.gd.dao.YContactDao;
import com.gdufs.gd.entity.YContact;
import com.gdufs.gd.service.YContactService;

@Service(value = "contactService")
public class YContactServiceImpl implements YContactService {

	@Resource(name = "contactDao")
	private YContactDao contactDao;

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

}
