package com.shop.dao;

import com.shop.bean.User;

public interface UserDao {

	User findUserByName(String name);
	
	void addUser(User user);
	
	
}
