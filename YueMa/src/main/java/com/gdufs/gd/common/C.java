package com.gdufs.gd.common;

public class C {
	// 请求参数的同一名称
	public class ParamsName {
		// 登陆注册
		public static final String USER = "user";
		public static final String USERNAME = "userName";
		public static final String PASSWORD = "pwd";
		public static final String PHONE_NUM = "phoneNum";
		public static final String UID = "uID";
		public static final String REGIST_CODE = "registCode";
		// 更新通讯录
		public static final String CONTACT = "contact";
		public static final String HOST_NUM = "hostNum";
		public static final String FRIEND_NUM = "friendNum";
		// Activity 参数
		public static final String TITLE = "title";
		public static final String INTRODUCE = "introduce";
		public static final String BEGINTIME = "beginTime";
		public static final String ENDTIME = "endTime";
		public static final String COST = "cost";
		public static final String ACTIVITY_ADDRESS = "activityAddress";
		public static final String ACTIVITY_ADDRESS_LATITUDE = "activityAddressLatitude";
		public static final String ACTIVITY_ADDRESS_LONGITUDE = "activityAddressLongitude";
		public static final String COUNT = "count";
		public static final String CATEGORY = "category";
		public static final String BENIFIT = "benifit";
		public static final String CONTACTPHONE = "contactPhone";
		//分页
		public static final String PAGENUM = "pageNum";
		public static final String PAGESIZE = "pageSize";
		//标记人脉关系
		public static final String MINE="mine";
		public static final String FIRST="first";
		public static final String SECOND="second";
	}

	// 用于返回的参数
	public class ResponseCode {
		public final static String NOEXCUTE = "-1";
		public final static String SUCCESS = "1";
		public final static String ERROR = "2";

	}

	// 用于返回的信息
	public class ResponseMessage {
		public final static String REGIST_ERROR_PARMANTS = "error parmant";
		public final static String REGIST_CODE_TIME_OUT = "code over time, please get regist code again";
		public final static String REGIST_DB_ERROR = "regist in database error";
		public final static String NOEXCUTE = "no process current request";
		public final static String SUCCESS = "Execute successfully";
		public final static String REQUEST_ERROR = "Request occured error";
		public final static String NETWORK_ERROR = "NetWork occured error";
		public final static String UNKNOWN_ERROR = "Unknown error";
		public final static String ERROR = "execute error";
		public final static String NOT_FUND = "404 NOT Fund";
		// 登陆
		public final static String WRONG_USER_INFO = "wrong username or password";
	}

	// 默认的上传文件的存储地
	public static final String DEFAULT_UPLOAD_PATH = "D:/images/";

}
