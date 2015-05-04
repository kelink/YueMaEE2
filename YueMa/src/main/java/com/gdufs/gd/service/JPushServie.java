package com.gdufs.gd.service;

import com.gdufs.gd.entity.YMessageUser;
import com.gdufs.gd.entity.YUser;

public interface JPushServie {
	/**
	 * 发送消息
	 * @param message
	 */
	public void sendMessagee(YMessageUser messageUser,String[] alias);

}
