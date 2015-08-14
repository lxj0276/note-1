package com.itheima.util;

import java.sql.Connection;
import java.sql.SQLException;
//所有与事务有关的管理器
public class TransactionManager {
	private static ThreadLocal<Connection> tl = new ThreadLocal<Connection>();
	public static Connection getConnection(){
		Connection conn = tl.get();
		if(conn==null){
			conn = DBCPUtil.getConnection();
			tl.set(conn);
		}
		return conn;
	}
	public static void startTransaction(){
		Connection conn = getConnection();
		try {
			conn.setAutoCommit(false);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static void rollback(){
		Connection conn = getConnection();
		try {
			conn.rollback();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static void commit(){
		Connection conn = getConnection();
		try {
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static void release(){
		Connection conn = getConnection();
		try {
			conn.close();
			tl.remove();//从当前线程上解绑。    服务器用了线程池。
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
