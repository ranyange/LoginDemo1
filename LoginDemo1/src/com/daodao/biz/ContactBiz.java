package com.daodao.biz;

import com.daodao.entity.Contact;

public interface ContactBiz {
	public void updateContact(Contact contact);
	public void insertContact(Contact contact);
	public void delContact(Integer id);
}

