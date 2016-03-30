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
	//�߳̿���
	/**
	 * jvm��һ��ʹJDBCUtil��ʱ��,��������static�����
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
	 * �������
	 */
	public static Connection getConnection(){
		Connection conn  = null;
		try {
			conn = TDL.get();//��õ�ǰ�߳��е�thread
			if(conn == null){
				conn = DriverManager.getConnection(PROP.getProperty("url"),PROP.getProperty("user"),PROP.getProperty("password"));
				TDL.set(conn);//�������õ�conn���뵱ǰ�߳���ȥ
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}
	/*
	 * �ͷ���Դ
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
