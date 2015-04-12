package com.gdufs.gd.daoImpl;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class BaseDao {
	public BaseDao() {
		super();
	}

	@Resource(name = "sessionFactory")
	protected SessionFactory sessionFactory;

	public Session getSession() {
		return this.sessionFactory.getCurrentSession();
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	//////////////////////////////////////////////////////////
	//封装获取Hql
	//////////////////////////////////////////////////////
	public List<?> findByHql(String hql, Map<String, Object> map, int pageSize,
			int pageNo) {
		return this.getQuery(hql, map, pageSize, pageNo).list();
	}

	private Query getQuery(String hql, Map<String, Object> map, int pageSize,
			int pageNo) {
		Query query = this.createQuery(hql);
		query = this.setParameter(query, map);
		query = this.setPageProperty(query, pageSize, pageNo);
		return query;
	}

	private Query createQuery(String hql) {
		return getSession().createQuery(hql);
	}

	private Query setParameter(Query query, Map<String, Object> map) {  
        if (map != null) {  
            Set<String> keySet = map.keySet();  
            for (String string : keySet) {  
                Object obj = map.get(string);  
                //这里考虑传入的参数是什么类型，不同类型使用的方法不同  
                if(obj instanceof Collection<?>){  
                    query.setParameterList(string, (Collection<?>)obj);  
                }else if(obj instanceof Object[]){  
                    query.setParameterList(string, (Object[])obj);  
                }else{  
                    query.setParameter(string, obj);  
                }  
            }  
        }  
        return query;  
    }  
	private Query setPageProperty(Query query, int pageSize, int pageNo) {
		if (pageNo != 0 && pageSize != 0) {
			query.setFirstResult((pageNo - 1) * pageSize);
			query.setMaxResults(pageSize);
		}
		return query;
	}

}
