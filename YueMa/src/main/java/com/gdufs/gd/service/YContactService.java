package com.gdufs.gd.service;

import java.util.List;

import com.gdufs.gd.entity.YContact;
import com.gdufs.gd.entity.YUser;

public interface YContactService {
	public boolean addAContact(YContact contactObj);

	public boolean addContactList(List<YContact> contactList);

	public List<YContact> listAllContact();

	public List<YContact> listContactById();

	public boolean deleteAContact(int id);

	public boolean updateContact(YContact contactObj);

	public boolean updateContactByUserId(int userId, List<YContact> contactList);
	
	/**
	 * 获取一度人脉
	 * @param phoneNum
	 * @return
	 */
	public List<YContact> getFirstPeople(String phoneNum);
	
	/**
	 * 获取二度人脉
	 * @param phoneNum
	 * @return
	 */
	public List<YUser> getSecondPeople(String phoneNum);
	/**
	 * 通过hostNum和friendNum获取contact
	 * @param hostNum
	 * @param friendNum
	 * @return
	 */
	public YContact getContactByPhoneNum(String hostNum,String friendNum);
	
	/**
	 * 判断是否为一度好友
	 * @param hostNum
	 * @param friendNum
	 * @return
	 */
	public boolean isFirstFriend(String hostNum, String friendNum);
	
	/**
	 * 判断是否为二度人脉
	 * @param hostNum
	 * @param friendNum
	 * @return
	 */
	public boolean isSecondFriend(String hostNum, String friendNum);
	
	public boolean addFriends(YContact friend1,YContact friend2);
	
	/**
	 * 获取一度人脉
	 * @return
	 */
	public List<YUser> getFirstUser(String hostNum);


}
