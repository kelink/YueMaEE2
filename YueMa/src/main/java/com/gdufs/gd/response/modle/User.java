package com.gdufs.gd.response.modle;

import java.util.HashMap;
import java.util.Map;

public class User {
	private int id;
	private String username;
	private String phoneNum;
	private String password;
	private String facePath;
	private String introduce;
	private String gender;
	private String origin;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPhoneNum() {
		return phoneNum;
	}
	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFacePath() {
		return facePath;
	}
	public void setFacePath(String facePath) {
		this.facePath = facePath;
	}
	public String getIntroduce() {
		return introduce;
	}
	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getOrigin() {
		return origin;
	}
	public void setOrigin(String origin) {
		this.origin = origin;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", phoneNum="
				+ phoneNum + ", password=" + password + ", facePath="
				+ facePath + ", introduce=" + introduce + ", gender=" + gender
				+ ", origin=" + origin + "]";
	}
	
	public static Map<String, String> UserToMap(User user){
		HashMap<String, String> map=new HashMap<String, String>();
		map.put("facePath", user.getFacePath());
		map.put("gender", user.getGender());
		map.put("introduce", user.getIntroduce());
		map.put("origin", user.getOrigin());
		map.put("password", user.getPassword());
		map.put("phoneNum", user.getPhoneNum());
		map.put("id", String.valueOf(user.getId()));
		map.put("username", user.getUsername());	
		return map;
		
	}

}
