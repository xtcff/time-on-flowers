package com.shop.service;

import com.shop.bean.User;
import com.shop.dao.UserDao;
import com.shop.dao.impljdbc.UserDaoJdbcImpl;

public class UserService {
	private UserDao userDao = new UserDaoJdbcImpl();
	public User findUserByName(String name) {
		// TODO Auto-generated method stub
		return userDao.findUserByName(name);
	}
	
	public void addUser(User user) {
		userDao.addUser(user);
	}

}
