package com.itheima.dao.impl;

import java.util.List;

import com.itheima.dao.PersonDao;
import com.itheima.domain.Person;

public class PersonDaoImpl extends BaseDao<Person> implements PersonDao {
	
	/*public PersonDaoImpl(){
		super(Person.class);
	}*/
	
	public List<Person> find(int startIndex, int pageSize) {
		return null;
	}

}
