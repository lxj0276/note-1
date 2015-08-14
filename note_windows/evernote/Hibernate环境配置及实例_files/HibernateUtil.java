package com.itheima.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
	private static SessionFactory sessionFactory;
	static{
		//加载hibernate.cfg.xml中的配置信息
		Configuration cfg = new Configuration().configure();
		
		//创建工厂
		sessionFactory = cfg.buildSessionFactory();
	}
	public static Session getSession(){
		return sessionFactory.openSession();
	}
}
