package com.daodao.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSourceFactory;

public class DBCPUtil {
	private static Properties prop = new Properties();
	private static ThreadLocal<Connection> tcl = new ThreadLocal<Connection>();
	private static DataSource ds = null;
	static {
		try {
			//∂¡»°dbcp≈‰÷√–≈œ¢
			prop.load(DBCPUtil.class
					.getResourceAsStream("/com/daodao/conf/dbcp.properties"));
			
			ds = BasicDataSourceFactory.createDataSource(prop);
			

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static Connection getConnection() {
		Connection conn = tcl.get();
		if (conn == null) {
			try {
				/*conn = DriverManager.getConnection(prop.getProperty("url"),
						prop.getProperty("user"), prop.getProperty("password"));*/
				conn = ds.getConnection();
				tcl.set(conn);
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}
		return conn;
	}

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
				tcl.remove();
				conn.close();
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
	}
	
}