package com.daodao.dao;

import java.util.List;

import com.daodao.entity.Contact;

public interface ContactDao {
	public List<Contact> listAll();
	public void alterContact(Contact contact);
	public void addContact(Contact contact);
	public void deleteContactById(Integer id);
	
}
