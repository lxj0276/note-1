package com.itheima.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.itheima.dao.Dao;
import com.itheima.util.HibernateUtil;

public abstract class BaseDao<T> implements Dao<T> {

	private Class<T> clazz;//�����ʵ����
	public BaseDao(){//���͵ķ���
		//����֪��������������ĸ��ࡣ��clazz��ֵ
		//System.out.println(this);//com.itheima.dao.impl.PersonDaoImpl@3f57fb52
		Type type = this.getClass().getGenericSuperclass();//�õ���ǰ����Ĵ��з�����Ϣ�ĸ�����  BaseDao<Person>
		ParameterizedType pType = (ParameterizedType)type;
		clazz = (Class<T>)pType.getActualTypeArguments()[0];//�õ�ʵ�ʵĲ�����������  Person
	}
	/*
	 * �Ƚ��鷳��һ�ֵõ�clazz������
	 * private Class clazz;//�����ʵ����
	public BaseDao(Class clazz){
		this.clazz = clazz;
	}*/
	
	public void add(T t) {
		Session s = HibernateUtil.getSession();
		Transaction tx = s.beginTransaction();
		s.save(t);
		tx.commit();
		s.close();
	}
	public T findOne(Serializable pk) {
		Session s = HibernateUtil.getSession();
		T bean = (T)s.get(clazz, pk);
		s.close();
		return bean;
	}
	public void update(T t) {
		Session s = HibernateUtil.getSession();
		Transaction tx = s.beginTransaction();
		s.update(t);
		tx.commit();
		s.close();
	}

	public void del(Serializable pk) {
		Session s = HibernateUtil.getSession();
		Transaction tx = s.beginTransaction();
		T obj = (T)s.get(clazz, pk);
		s.delete(obj);
		tx.commit();
		s.close();
	}

	

}
