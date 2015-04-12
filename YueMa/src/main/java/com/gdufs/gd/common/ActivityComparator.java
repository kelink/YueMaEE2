package com.gdufs.gd.common;

import java.util.Comparator;

import com.gdufs.gd.entity.YActivity;

/**
 * 活动类的排序方法，按照创建时间排序
 * @author Administrator
 *
 */
public class ActivityComparator  implements Comparator<Object>{

	@Override
	public int compare(Object o1, Object o2) {
		YActivity a1 = (YActivity)o1;
		YActivity a2 = (YActivity)o2;
		return a1.getCreateTimeDate().compareTo(a2.getCreateTimeDate());
	}

}
