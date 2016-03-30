package com.daodao.biz;

import java.util.List;

import com.daodao.entity.Contact;

public interface UserBiz {
	public void regist(String username, String nickname, String pwd, String confirmPwd);
	public List<Contact> getAllContacts();
	public boolean login(String username,String password);
}
