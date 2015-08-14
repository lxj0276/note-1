package com.itheima.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
	private static SessionFactory sessionFactory;
	static{
		//����hibernate.cfg.xml�е�������Ϣ
		Configuration cfg = new Configuration().configure();
		
		//��������
		sessionFactory = cfg.buildSessionFactory();
	}
	public static Session getSession(){
		return sessionFactory.openSession();
	}
}
