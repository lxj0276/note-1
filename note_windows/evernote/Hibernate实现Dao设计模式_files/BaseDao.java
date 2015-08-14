package com.itheima.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.itheima.dao.Dao;
import com.itheima.util.HibernateUtil;

public abstract class BaseDao<T> implements Dao<T> {

	private Class<T> clazz;//具体的实体类
	public BaseDao(){//泛型的反射
		//必须知道具体操作的是哪个类。给clazz赋值
		//System.out.println(this);//com.itheima.dao.impl.PersonDaoImpl@3f57fb52
		Type type = this.getClass().getGenericSuperclass();//得到当前对象的带有泛型信息的父类型  BaseDao<Person>
		ParameterizedType pType = (ParameterizedType)type;
		clazz = (Class<T>)pType.getActualTypeArguments()[0];//得到实际的参数泛型类型  Person
	}
	/*
	 * 比较麻烦的一种得到clazz的做法
	 * private Class clazz;//具体的实体类
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
