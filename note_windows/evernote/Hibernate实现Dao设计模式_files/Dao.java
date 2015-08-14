package com.itheima.dao;

import java.io.Serializable;

public interface Dao<T> {
	
	/**
	 * ���
	 * @param t
	 */
	void add(T t);
	
	/**
	 * ����
	 * @param t
	 */
	void update(T t);
	
	/**
	 * ��������ɾ��
	 * @param pk
	 */
	//Ҫ��������Ͷ�ʵ����Serializable�ӿ�
	void del(Serializable pk);
	
	/**
	 * ����������������
	 * @param pk
	 * @return
	 */
	T findOne(Serializable pk);
}
