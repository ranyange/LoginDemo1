package com.daodao.dao;

import com.daodao.entity.User;

public interface UserDao {
	public void insert(User user);
	public User queryUserByUsername(String username);
}
