package com.gdufs.gd.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
	
	public  static String formatDateTime(Date date) {
		 SimpleDateFormat mDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		if (date==null) {			
			return "";
		}
		if (0 == date.getTime()) {
			return "";
		}
		return mDateFormat.format(date);
	}
	
}
