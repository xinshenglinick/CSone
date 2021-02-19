package com.xsl.utils;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DbConUtil {
	public static Connection getcon(){
		String driverClassName = "com.mysql.jdbc.Driver";
		String username = "root";
		String password = "123";
		String url = "jdbc:mysql://127.0.0.1:3306/pinyougoudb?characterEncoding=utf-8";
		Connection conn = null;
		try {
			Class.forName(driverClassName);
			conn = DriverManager.getConnection(url, username, password);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
	public static void closeCone(Connection con){
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	// 测试
	public static void main(String[] args) throws Exception {
		Connection con = getcon();
		if (con != null) {
			System.out.println("测试成功！");
		}
	}
}
