package com.gdufs.gd.service;

import java.util.List;

import com.gdufs.gd.entity.YContact;

public interface YContactService {
	public boolean addAContact(YContact contactObj);

	public boolean addContactList(List<YContact> contactList);

	public List<YContact> listAllContact();

	public List<YContact> listContactById();

	public boolean deleteAContact(int id);

	public boolean updateContact(YContact contactObj);

	public boolean updateContactByUserId(int userId, List<YContact> contactList);

}
