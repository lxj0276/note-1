package com.itheima.dao;

import java.util.List;

import com.itheima.domain.Customer;

public interface CustomerDao extends Dao<Customer>{
	
	/**
	 * ²éÕÒËùÓĞ
	 * @return
	 */
	List<Customer> findAll();
}
