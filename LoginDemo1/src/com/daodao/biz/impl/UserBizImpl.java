package com.daodao.biz.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.daodao.biz.UserBiz;
import com.daodao.dao.ContactDao;
import com.daodao.dao.UserDao;
import com.daodao.dao.impl.ContactDaoImpl;
import com.daodao.dao.impl.UserDaoImpl;
import com.daodao.entity.Contact;
import com.daodao.entity.User;
import com.daodao.util.DBCPUtil;
import com.daodao.util.DBCPUtil;
import com.daodao.util.MD5Util;

public class UserBizImpl implements UserBiz {
	private UserDao userDao= new UserDaoImpl();
	private ContactDao contactDao = new ContactDaoImpl();
	@Override
	public void regist(String username, String nickname, String pwd, String confirmPwd) {
		Connection conn = null;
		try {
			// 0.��������
			conn = DBCPUtil.getConnection();
			conn.setAutoCommit(false);
			// 1.ҵ������ж�
			if(!pwd.equals(confirmPwd)){
				throw new RuntimeException("�������벻һ��");
			}
			//��pwd����md5���ܴ���
			String md5pwd1 = MD5Util.getStringMD5(pwd);
			String md5username = MD5Util.getStringMD5(username);
			String md5 = MD5Util.getStringMD5(md5pwd1+md5username);
//			//ʹ��commons
//			//DigestUtils.md5Hex(pwd);
//			// 2.����dao��insert����user
			userDao.insert(new User(null, username, nickname, md5, 0));
			// 3.ͨ���쳣�����Ƿ�ע��ɹ�
			// 4.�ύ����
			conn.commit();
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			throw new RuntimeException("ע���쳣",e);
		}finally{
			// 5.�ر�conn
			DBCPUtil.close(null, null, conn);
			}
		
	}
	@Override
	public List<Contact> getAllContacts() {
		List<Contact> list = contactDao.listAll();
		
		return list;
	}
	@Override
	public boolean login(String username, String password) {
		// TODO Auto-generated method stub
		Connection conn = null;
		
		try{
			conn = DBCPUtil.getConnection();
			//conn.setAutoCommit(false);
			//�û����Ƿ����
			if(userDao.queryUserByUsername(username)==null){
				throw new RuntimeException("�û�������");
			}
			
			String md5pwd1 = MD5Util.getStringMD5(password);
			String md5username = MD5Util.getStringMD5(username);
			String md5 = MD5Util.getStringMD5(md5pwd1+md5username);
			//�ж�����Ѵ����û��������Ƿ��������������һ��;
			if(userDao.queryUserByUsername(username).getPassword().equals(md5)){
				
				System.out.println("��¼�ɹ�");
			
			}else{
				throw new RuntimeException("�������");
			}
			
			return true;
		}catch(Exception e){
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			throw new RuntimeException(e);
		}finally{
			DBCPUtil.close(null, null, conn);
		}
		//return true;
	}
	public static void main(String[] args) {
		UserDao userDao = new UserDaoImpl();
		User user = userDao.queryUserByUsername("number1");
		System.out.println(user);
		UserBiz userBiz = new UserBizImpl();
		boolean flag = userBiz.login("TestUser", "test");
		System.out.println(flag);
		 
		
	}
}
