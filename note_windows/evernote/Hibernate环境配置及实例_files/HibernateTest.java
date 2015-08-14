package com.itheima.test;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import com.itheima.domain.Person;
import com.itheima.util.HibernateUtil;

public class HibernateTest {
	@Test
	public void add(){
		Person p = new Person();
		p.setName("张三");
		p.setBirthday(new Date());
		
		Session s = HibernateUtil.getSession();
		//开始事务
		Transaction tx = s.beginTransaction();
		s.save(p);
		tx.commit();
		s.close();
	}
	@Test
	public void findOne(){
		Session s = HibernateUtil.getSession();
		Person p = (Person) s.get(Person.class, 1);
		System.out.println(p);
		s.close();
	}
	@Test
	public void update(){
		Session s = HibernateUtil.getSession();
		Transaction tx = s.beginTransaction();
		Person p = (Person) s.get(Person.class, 1);
		p.setName("刘小晨");
		s.update(p);
		tx.commit();
		s.close();
	}
	@Test
	public void delete(){
		Session s = HibernateUtil.getSession();
		Transaction tx = s.beginTransaction();
		Person p = (Person) s.get(Person.class, 1);
		s.delete(p);
		tx.commit();
		s.close();
	}
}
