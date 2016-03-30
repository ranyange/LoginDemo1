package com.daodao.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.daodao.dao.UserDao;
import com.daodao.entity.User;
import com.daodao.util.DBCPUtil;
import com.daodao.util.DBCPUtil;

public class UserDaoImpl implements UserDao {
	@Override
	public void insert(User user) {
		Connection conn = null;
		PreparedStatement pstm = null;
		try{
			//1.获得连接
			conn = DBCPUtil.getConnection();
			//2.获得pstm
			pstm = conn.prepareStatement("insert into table_user values(seq_user.nextval,?,?,?,?)");
			//3.讲user绑定到sql中
			pstm.setString(1, user.getUsername());
			pstm.setString(2, user.getNickname());
			pstm.setString(3, user.getPassword());
			pstm.setInt(4, user.getLocked());
			//4.执行sql
			pstm.executeUpdate();
			
			
		}catch(Exception e){
			throw new RuntimeException("insert User数据异常",e);
		}finally{
			//5.释放资源  pstm
			DBCPUtil.close(null, pstm, null);
		}
		
	}

	@Override
	public User queryUserByUsername(String username) {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		User user = null;
		
		conn = DBCPUtil.getConnection();
		 
		String sql = "select id,username,nickname,password,locked from table_user where username = ?";
		
		try{
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, username);
			rs = pstm.executeQuery();
			while(rs.next()){
				user = new User(rs.getInt("id"),rs.getString("username"),rs.getString("nickname"),rs.getString("password"),rs.getInt("locked"));
			}
			
			return user;
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			DBCPUtil.close(rs, pstm, null);
			
		}
		return user;
	}
}
