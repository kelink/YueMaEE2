package com.gdufs.gd.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.log4j.chainsaw.Main;

public class Test {
	public static String getMD532(String key) {
		key = String.valueOf(System.currentTimeMillis()) + key;
		StringBuffer buf = null;
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(key.getBytes());
			byte b[] = md.digest();
			int i;
			buf = new StringBuffer("");
			for (int offset = 0; offset < b.length; offset++) {
				i = b[offset];
				if (i < 0)
					i += 256;
				if (i < 16)
					buf.append("0");
				buf.append(Integer.toHexString(i));
			}
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return buf.toString();// // 32位的加密
	}
	public static void main(String[] args) {
		System.out.println(getMD532("http://www.oschina.net/question/185225_125475"));
	}
}
