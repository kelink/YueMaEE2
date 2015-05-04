package com.gdufs.gd.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.jpush.api.JPushClient;
import cn.jpush.api.common.APIConnectionException;
import cn.jpush.api.common.APIRequestException;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Message;
import cn.jpush.api.push.model.Options;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.audience.AudienceTarget;
import cn.jpush.api.push.model.notification.AndroidNotification;
import cn.jpush.api.push.model.notification.IosNotification;
import cn.jpush.api.push.model.notification.Notification;

/**
 * 推送服务的工具包
 * @author Administrator
 *
 */
public class JPushUtil {
	protected static final Logger LOG = LoggerFactory
			.getLogger(JPushUtil.class);

	private String appKey;
	private String masterSecret;
	private int maxReTryTimes;
	private JPushClient client;
	private static JPushUtil instant;
	private static Properties p;

	static {
		InputStream in = JPushUtil.class
				.getResourceAsStream("jpush.properties");
		p = new Properties();
		try {
			p.load(in);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private JPushUtil() {
		appKey = p.getProperty("appKey").trim();
		masterSecret = p.getProperty("masterSecret").trim();
		maxReTryTimes = Integer.parseInt(p.getProperty("maxReTryTimes").trim());
		client = new JPushClient(masterSecret, appKey, maxReTryTimes);
	}

	public static JPushUtil getInstant() {

		if (instant == null) {
			instant = new JPushUtil();
		}
		return instant;
	}

	
	public static PushPayload buildPushObject_android_ios_audience_More_messageWithExtras(String[] alias,String title, 
			String msgContent,
			String contentType,Map<String,String> extraMap) {
		
		return PushPayload
				.newBuilder()
				.setPlatform(Platform.android_ios())
				.setOptions(Options.newBuilder().setTimeToLive(864000).build())//设置为把保存10天
				.setAudience(
						Audience.newBuilder()
								.addAudienceTarget(AudienceTarget.alias(alias))
								.build())				
				.setMessage(
						Message.newBuilder()
						.setTitle(title)
						.setMsgContent(msgContent)
						.setContentType(contentType)						
						.addExtras(extraMap).build())
				.build();
		
	}

	/**
	 * 发送推送消息
	 * @param payload
	 * @throws APIRequestException 
	 * @throws APIConnectionException 
	 */
	public static PushResult sendPush(PushPayload payload) throws APIConnectionException, APIRequestException {
		PushResult result=null;
		result = JPushUtil.getInstant().client.sendPush(payload);	
		return result;
	}

	// // 閫氱煡
	// public static PushPayload buildPushObject_all_alias_alert() {
	// return PushPayload.newBuilder().setPlatform(Platform.all())
	// .setAudience(Audience.alias("alias1"))
	// .setNotification(Notification.alert(ALERT)).build();
	// }
	//
	// public static PushPayload buildPushObject_android_tag_alertWithTitle() {
	// return PushPayload.newBuilder().setPlatform(Platform.android())
	// .setAudience(Audience.tag("tag1"))
	// .setNotification(Notification.android(ALERT, TITLE, null))
	// .build();
	// }
	//
	// // 閫氱煡
	// public static PushPayload buildPushObject_android_and_ios() {
	// return PushPayload
	// .newBuilder()
	// .setPlatform(Platform.android_ios())
	// .setAudience(Audience.tag("tag1"))
	// .setNotification(
	// Notification
	// .newBuilder()
	// .setAlert("alert content")
	// .addPlatformNotification(
	// AndroidNotification.newBuilder()
	// .setTitle("Android Title")
	// .build())
	// .addPlatformNotification(
	// IosNotification
	// .newBuilder()
	// .incrBadge(1)
	// .addExtra("extra_key",
	// "extra_value").build())
	// .build()).build();
	// }
	//
	// // 鍙戦�娑堟伅
	// public static PushPayload
	// buildPushObject_ios_tagAnd_alertWithExtrasAndMessage() {
	// return PushPayload
	// .newBuilder()
	// .setPlatform(Platform.ios())
	// .setAudience(Audience.tag_and("tag1", "tag_all"))
	// .setNotification(
	// Notification
	// .newBuilder()
	// .addPlatformNotification(
	// IosNotification.newBuilder()
	// .setAlert(ALERT).setBadge(5)
	// .setSound("happy")
	// .addExtra("from", "JPush")
	// .build()).build())
	// .setMessage(Message.content(MSG_CONTENT))
	// .setOptions(
	// Options.newBuilder().setApnsProduction(true).build())
	// .build();
	// }
	//
	// /**
	// * ios
	// *
	// * @return
	// */
	// public static PushPayload
	// buildPushObject_ios_audienceMore_messageWithExtras() {
	// return PushPayload
	// .newBuilder()
	// .setPlatform(Platform.android_ios())
	// .setAudience(
	// Audience.newBuilder()
	// .addAudienceTarget(
	// AudienceTarget.tag("tag1", "tag2"))
	// .addAudienceTarget(
	// AudienceTarget
	// .alias("alias1", "alias2"))
	// .build())
	// .setMessage(
	// Message.newBuilder().setMsgContent(MSG_CONTENT)
	// .addExtra("from", "JPush").build()).build();
	// }
}