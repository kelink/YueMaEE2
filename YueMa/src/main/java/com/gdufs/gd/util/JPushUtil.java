package com.gdufs.gd.util;

import java.io.IOException;
import java.io.InputStream;
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
import cn.jpush.api.report.ReceivedsResult;

import com.gdufs.gd.serviceImpl.JPushServiecImpl;

/**
 * 单例模式实现JPush 工具类
 * 
 * @author Administrator
 *
 */
public class JPushUtil {

	protected static final Logger LOG = LoggerFactory
			.getLogger(JPushServiecImpl.class);
	private String appKey;
	private String masterSecret;
	private int maxReTryTimes;
	private JPushClient client;
	// 参数
	private String TITLE;
	private String ALERT;
	private String MSG_CONTENT;
	private String REGISTRATION_ID;
	private String TAG;

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

	public static void main(String[] args) {
		JPushUtil.getInstant().sendPush(
				JPushUtil.buildPushObject_all_all_alert("test"));
	}

	/**
	 * 发送通知
	 */
	public void sendPush(PushPayload payload) {
		try {
			PushResult result = client.sendPush(payload);
		} catch (APIConnectionException e) {
			LOG.error("Connection error. Should retry later. ", e);

		} catch (APIRequestException e) {
			LOG.error(
					"Error response from JPush server. Should review and fix it. ",
					e);
			LOG.info("HTTP Status: " + e.getStatus());
			LOG.info("Error Code: " + e.getErrorCode());
			LOG.info("Error Message: " + e.getErrorMessage());
			LOG.info("Msg ID: " + e.getMsgId());
		}
	}

	/**
	 * 推送通知到所有平台，所有用户
	 * 
	 * @param alertMsg
	 * @return
	 */
	public static PushPayload buildPushObject_all_all_alert(String alertMsg) {
		return PushPayload.alertAll(alertMsg);
	}

	/**
	 * 推送通知给所有平台，别称为指定的用户
	 * 
	 * @return
	 */
	public static PushPayload buildPushObject_all_alias_alert(String alert,
			String... alias) {
		return PushPayload.newBuilder().setPlatform(Platform.all())
				.setAudience(Audience.alias(alias))
				.setNotification(Notification.alert(alert)).build();
	}

	/**
	 * 推送通知给android平台，别称为指定的用户
	 * 
	 * @return
	 */
	public static PushPayload buildPushObject_android_alias_alert(String alert,
			String... alias) {
		return PushPayload.newBuilder().setPlatform(Platform.android())
				.setAudience(Audience.alias(alias))
				.setNotification(Notification.alert(alert)).build();
	}

	/**
	 * 推送通知给android 平台，指定tag和指定通知的title，
	 * 
	 * @return
	 */

	public static PushPayload buildPushObject_android_tag_alertWithTitle(
			String alert, String title, Map<String, String> extras,
			String... tagValue) {
		return PushPayload.newBuilder().setPlatform(Platform.android())
				.setAudience(Audience.tag(tagValue))
				.setNotification(Notification.android(alert, title, extras))
				.build();
	}

	// /**
	// * 指定android 和ios平台
	// *
	// * @return
	// */
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
