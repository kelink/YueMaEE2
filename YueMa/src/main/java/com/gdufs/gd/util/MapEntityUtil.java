package com.gdufs.gd.util;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import com.gdufs.gd.entity.BaseEntity;
import com.gdufs.gd.response.modle.User;

public class MapEntityUtil {
	
	/* Model 转化成 Map */
	static public Map<String, ?> dataToMap(BaseEntity data, String[] fields) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		try {
			for (String fieldName : fields) {
				Field field = data.getClass().getDeclaredField(fieldName);
				field.setAccessible(true); // have private to be accessable
				map.put(fieldName, field.get(data));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	

}
