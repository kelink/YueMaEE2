package com.gdufs.gd.dao;

import java.util.List;

import com.gdufs.gd.entity.YRelationSecond;

public interface YRelationSecondDao {
	public List<YRelationSecond> getSecondFriends(String phoneNum);
}
