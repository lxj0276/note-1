package com.itheima.hibernate.utils;

import org.hibernate.Session;

public interface Countable {
	/**
	 * 
	 * @param  org.hibernate.session
	 * @return  一级缓冲中的实体的个数。
	 */
	public void getCount(Session session);

}
