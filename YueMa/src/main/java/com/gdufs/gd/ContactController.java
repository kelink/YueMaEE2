package com.gdufs.gd;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gdufs.gd.common.C;
import com.gdufs.gd.entity.TransferMessage;
import com.gdufs.gd.entity.YContact;
import com.gdufs.gd.service.YContactService;
import com.gdufs.gd.util.JacksonUtil;

@Controller
@RequestMapping("/contact")
public class ContactController {
	private static final Logger logger = LoggerFactory
			.getLogger(ContactController.class);

	@Resource(name = "contactService")
	private YContactService contactService;

	/**
	 * 添加通讯录 （发生更改时候需要更新一度和二度人脉关系表）
	 * 
	 * @param contactList
	 * @return
	 */
	 @RequestMapping(value = "/addContact",method = {RequestMethod.GET,RequestMethod.POST})  
	 @ResponseBody
	public String addContact(
			@RequestParam(value =C.ParamsName.UID, defaultValue = "") int uId,
			@RequestParam(value =C.ParamsName.PHONE_NUM, defaultValue = "") String phoneNum,
			@RequestParam(value =C.ParamsName.CONTACT, defaultValue = "") String contactStr) {
		 System.out.println("phoneNum--------->"+phoneNum);
		 System.out.println("contactStr------>"+contactStr);
		TransferMessage messageObj = new TransferMessage();
		if (phoneNum.equals("")||contactStr.equals("")) {
			messageObj.setCode(C.ResponseCode.ERROR);
			messageObj.setMessage(C.ResponseMessage.REQUEST_ERROR);
			messageObj.setResultMap(null);
		}else {
			String[] list=contactStr.split("-");
			List<YContact> contactList=new ArrayList<YContact>();
			for (String item : list) {
				YContact contact=new YContact();
				contact.setId(uId);
				contact.setHostNum(phoneNum);
				contact.setFriendNum(item);
				contact.setVersion(0);
				contactList.add(contact);
			}
			if (contactService.addContactList(contactList)) {
				messageObj.setCode(C.ResponseCode.SUCCESS);
				messageObj.setMessage(C.ResponseMessage.SUCCESS);
				messageObj.setResultMap(null);
				logger.info("add contact success");
			} else {
				messageObj.setCode(C.ResponseCode.ERROR);
				messageObj.setMessage(C.ResponseMessage.ERROR);
				messageObj.setResultMap(null);
				logger.error("add contact fail");
			}
		}			
		return JacksonUtil.writeEntity2JSON(messageObj);
	}

	/**
	 * 获取表的所有通讯录
	 */


	/**
	 * 更新通信录
	 */
//	 @RequestMapping(value = "/updateContact",method = {RequestMethod.GET,RequestMethod.POST})  
//	 @ResponseBody
//	public String updateContact(
//			@RequestParam(value = "contacts", defaultValue = "") List<YContact> contactList) {
//		TransferMessage messageObj = new TransferMessage();
//		if (contactService.addContactList(contactList) == true) {
//			messageObj.setCode(C.ResponseCode.SUCCESS);
//			messageObj.setMessage(C.ResponseMessage.SUCCESS);
//			messageObj.setResultMap(null);
//			logger.info("update contact success");
//		} else {
//			messageObj.setCode(C.ResponseCode.ERROR);
//			messageObj.setMessage(C.ResponseMessage.SUCCESS);
//			messageObj.setResultMap(null);
//			logger.error("update contact fail");
//		}
//		return JacksonUtil.writeEntity2JSON(messageObj);
//	}

}
