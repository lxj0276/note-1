package com.itheima.hibernate.utils;

import org.hibernate.Session;

public interface Countable {
	/**
	 * 
	 * @param  org.hibernate.session
	 * @return  һ�������е�ʵ��ĸ�����
	 */
	public void getCount(Session session);

}
