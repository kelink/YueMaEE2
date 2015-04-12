package com.gdufs.gd.common;

public class CResponse {
	public class Code {
		public final static int NOEXCUTE = -1;
		public final static int SUCCESS = 0;
		public final static int REQUEST_ERROR = 1;
		public final static int NETWORK_ERROR = 2;
		public final static int UNKNOWN_ERROR = 3;
		public final static int ERROR = 4;
		public final static int NOT_FUND = 5;
	}

	public class Message {
		// regist
		public final static String REGIST_ERROR_PARMANTS = "error parmants";
		public final static String REGIST_CODE_TIME_OUT = "please get regist code again";
		public final static String REGIST_USER_EXIST = "User exist";
		public final static String REGIST_CODE_ERROR = "Error regist code";
		public final static String REGIST_DB_ERROR = "regist in database error";
		public final static String NOEXCUTE = "no process current request";
		public final static String SUCCESS = "Execute successfully";
		public final static String REQUEST_ERROR = "Request occured error";
		public final static String NETWORK_ERROR = "NetWork occured error";
		public final static String UNKNOWN_ERROR = "Unknown error";
		public final static String ERROR = "执行错误";
		public final static String NOT_FUND = "404 NOT Fund";

	}
}
