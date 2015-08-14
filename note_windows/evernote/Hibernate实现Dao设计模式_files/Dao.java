package com.itheima.dao;

import java.io.Serializable;

public interface Dao<T> {
	
	/**
	 * 添加
	 * @param t
	 */
	void add(T t);
	
	/**
	 * 更新
	 * @param t
	 */
	void update(T t);
	
	/**
	 * 根据主键删除
	 * @param pk
	 */
	//要传入的类型都实现了Serializable接口
	void del(Serializable pk);
	
	/**
	 * 根据主键查找数据
	 * @param pk
	 * @return
	 */
	T findOne(Serializable pk);
}
