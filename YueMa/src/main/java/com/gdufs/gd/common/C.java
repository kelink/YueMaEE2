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
		public static final String USER_FACE_PATH = "facePath";
		public static final String USER_INTRODUCE = "introduce";
		public static final String USER_GENDER = "gender";
		public static final String USER_ORIGIN = "origin";
		public static final String REGIST_CODE = "registCode";

		// 更新通讯录
		public static final String CONTACT = "contact";
		public static final String HOST_NUM = "hostNum";
		public static final String FRIEND_NUM = "friendNum";
		public static final String FRIENDID = "friendId";
		// Activity 参数
		public static final String AID = "activityId";
		public static final String TITLE = "title";
		public static final String INTRODUCE = "introduce";
		public static final String BEGINTIME = "beginTime";
		public static final String ENDTIME = "endTime";
		public static final String COST = "cost";
		public static final String ACTIVITY_ADDRESS_CITY = "addressCity";
		public static final String ACTIVITY_ADDRESS_DETIAL = "addressDetial";
		public static final String ACTIVITY_ADDRESS_LATITUDE = "activityAddressLatitude";
		public static final String ACTIVITY_ADDRESS_LONGITUDE = "activityAddressLongitude";
		public static final String COUNT = "count";
		public static final String CATEGORY = "category";
		public static final String BENIFIT = "benifit";
		public static final String CONTACTPHONE = "contactPhoneNum";
		// 分页
		public static final String PAGENUM = "pageNum";
		public static final String PAGESIZE = "pageSize";
		// 标记人脉关系
		public static final String MINE = "mine";
		public static final String FIRST = "first";
		public static final String SECOND = "second";
		// 评论
		public static final String CID = "commentId";// 评论的id
		public static final String COMMENT_CONTENT = "commentContent";// 评论内容
		public static final String COMMENT_FATHER = "fatherCommentId";// 父评论的id
		// 消息
		public static final String MSG_CONTENT = "content";// 消息内容
		public static final String MSG_TITLE = "title";// 消息titile
		public static final String MSG_TYPE = "msg_type";// 消息类型

	}

	// 用于返回的参数
	public class ResponseCode {
		public final static String SUCCESS = "1";
		public final static String ERROR = "2";

	}

	// 用于返回的信息
	public class ResponseMessage {
		public final static String REGIST_CODE_ERROR = "注册码错误";
		public final static String REGIST_USER_EXIST = "用户存在";
		public final static String INVALID_PARMANTS = "无效参数";
		public final static String REQUEST_ERROR = "请求异常";
		public final static String REGIST_CODE_TIME_OUT = "注册码过期，请重新获取";

		public final static String REGIST_DB_ERROR = "数据库错误";
		public final static String NOEXCUTE = "不执行该请求";
		public final static String SUCCESS = "操作成功";

		public final static String NETWORK_ERROR = "网络错误r";
		public final static String UNKNOWN_ERROR = "未知错误";
		public final static String ERROR = "执行错误";
		public final static String NOT_FUND = "404 资源无效";
		// 登陆
		public final static String WRONG_USER_INFO = "账号或密码有误";
	}

	// 默认的上传文件的存储地
	public static final String DEFAULT_UPLOAD_PATH = "D:/images/";
	
	public class DefaultValues{
		public final static String DEFAULT_LOGO= "images/default/logo.PNG";
		public final static String DEFAULT_FACEPATH = "images/default/default_facepath.PNG";
		public final static String DEFAULT_ACTIVITY_PHOTO = "images/default/default_activity.PNG";
		public final static int DEFAULT_IS_READ =0;
		public final static int DEFAULT_IS_DELETE =0;
		public final static int DEFAULT_IS_SEND =0;
		
		public final static int DEFAULT_MSG_TYPE_SYSTEM=0;//系统类型消息
		public final static int DEFAULT_MSG_TYPE_VALIDATE=1;//验证类型信息
		public final static int DEFAULT_MSG_TYPE_COMMENt=2;//评论类型消息
		public final static int DEFAULT_MSG_TYPE_REMIND=3;//提醒类型消息
		public final static int DEFAULT_MSG_TYPE_CHAT=4;//聊天类型消息
	}

}
