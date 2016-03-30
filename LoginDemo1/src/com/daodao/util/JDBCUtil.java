package com.daodao.util;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
public class JDBCUtil {
	private static final Properties PROP = new Properties();
	private static final ThreadLocal<Connection> TDL = new ThreadLocal<Connection>();
	//线程控制
	/**
	 * jvm第一次使JDBCUtil的时候,会限制性static代码块
	 */
	
	static {
		InputStream is = null;
		try {
			is = JDBCUtil.class
					.getResourceAsStream("/com/daodao/conf/jdbc.properties");
			PROP.load(is);
			Class.forName(PROP.getProperty("driverClassName"));
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	/*
	 * 获得连接
	 */
	public static Connection getConnection(){
		Connection conn  = null;
		try {
			conn = TDL.get();//获得当前线程中的thread
			if(conn == null){
				conn = DriverManager.getConnection(PROP.getProperty("url"),PROP.getProperty("user"),PROP.getProperty("password"));
				TDL.set(conn);//将创建好的conn放入当前线程中去
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}
	/*
	 * 释放资源
	 */
	public static void close(ResultSet rs, Statement stm,
			Connection conn) {
		if (rs != null)
			try {
				rs.close();
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		if (stm != null)
			try {
				stm.close();
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		if (conn != null)
			try {
				TDL.remove();
				conn.close();
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
	}
	
}
