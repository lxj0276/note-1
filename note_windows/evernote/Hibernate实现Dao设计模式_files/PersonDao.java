package com.itheima.dao;

import java.util.List;

import com.itheima.domain.Person;

public interface PersonDao extends Dao<Person> {
	
	/**
	 * ����һ��Person
	 * @param startIndex
	 * @param pageSize
	 * @return
	 */
	List<Person> find(int startIndex,int pageSize);
}
