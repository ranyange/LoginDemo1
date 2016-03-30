package com.daodao.dao.impl;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javassist.compiler.ast.Stmnt;

import com.daodao.dao.ContactDao;
import com.daodao.entity.Contact;
import com.daodao.util.DBCPUtil;
import com.daodao.util.DBCPUtil;

public class ContactDaoImpl implements ContactDao{
	public List<Contact> listAll() {
		Connection conn = null;
		Statement stm = null;
		ResultSet rs = null;
		List<Contact> list = new ArrayList<Contact>();
		
		try {
			conn = DBCPUtil.getConnection();
			stm = conn.createStatement();
			rs = stm.executeQuery("select * from table_contact");
			while(rs.next()){
				Integer id = rs.getInt(1);
				String name = rs.getString(2);
				Date birth = rs.getDate(3);
				Integer age = rs.getInt(4);
				String phone = rs.getString(5);
				String zipCode = rs.getString(6);
				//将查询到的数据全部封装起来,并封到一个集合当中.
				Contact p = new Contact(id, name, birth, age, phone, zipCode);
				list.add(p);
				
			}
		} catch (SQLException e) {
			throw new RuntimeException("查询所有联系人异常",e);
		}finally{
			DBCPUtil.close(rs,stm,null);
		}
			
				
		return list;
	}
	
	//根据联系人id更新一条记录
	@Override
	public void alterContact(Contact contact) {
		
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement pstm = null;
		//获得conn对象
		/* 联系人sql表 
		conn = DBCPUtil.getConnection();
		id integer primary key,
		name varchar2(50),
		birthday date,
		age number(3),
		phone varchar2(12),
		zipcode varchar2(6)
		*/
		
		// 前端页面从后台获取一个Contact 对象, 并显示联系人细心;
		String sql = "update table_contact set name=?,birthday=?,age=?,phone=?,zipcode=? where id=?";
		
		try {
			conn = DBCPUtil.getConnection();
			pstm = conn.prepareStatement(sql);
			pstm.setString(1,contact.getName());
			//需要将获取到的
			long bir = contact.getBirth().getTime();
			java.sql.Date birth = new java.sql.Date(bir);
			pstm.setDate(2, birth);
			pstm.setInt(3,contact.getAge());
			pstm.setString(4, contact.getPhone());
			pstm.setString(5, contact.getZipCode());
			pstm.setInt(6, contact.getId());
			pstm.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBCPUtil.close(null, pstm, null);
		}
	}

	@Override
	public void addContact(Contact contact) {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement pstm = null;
		conn = DBCPUtil.getConnection();
		String sql = "insert into table_contact values(sequence_contact.nextval,?,?,?,?,?)";
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, contact.getName());
			long bir = contact.getBirth().getTime();
			java.sql.Date birth = new java.sql.Date(bir);
			pstm.setDate(2, birth);
			pstm.setInt(3,contact.getAge());
			pstm.setString(4, contact.getPhone());
			pstm.setString(5, contact.getZipCode());
			pstm.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			DBCPUtil.close(null, pstm, null);
		}
	}

	@Override
	public void deleteContactById(Integer id) {
		// TODO Auto-generated method stub
		Connection conn = null;
		//Statement stm = null;
		PreparedStatement pstm = null;
		conn = DBCPUtil.getConnection();
		String sql = "delete from table_contact where id = ?";
		
		//stm = conn.createStatement();
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, id);
			pstm.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBCPUtil.close(null,pstm,null);
		}
		
		
	}
	
}
