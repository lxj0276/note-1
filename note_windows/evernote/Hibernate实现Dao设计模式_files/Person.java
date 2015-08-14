package com.itheima.domain;

import java.util.Date;
/*
create database day22;
use day22;
create table person(
	id int primary key auto_increment,
	name varchar(100),
	birthday date
);
 */
public class Person {
	private int id;
	private String name;
	private Date birthday;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	@Override
	public String toString() {
		return "Person [id=" + id + ", name=" + name + ", birthday=" + birthday
				+ "]";
	}
	
}
