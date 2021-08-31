package com.shop.dao.impljdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import com.shop.bean.User;
import com.shop.dao.UserDao;

public class UserDaoJdbcImpl implements UserDao {
	private static final String driver = "com.mysql.cj.jdbc.Driver";
	private static final String url = "jdbc:mysql://8.142.121.139:3306/flower_shop?CharSet=utf8&useUnicode=true&characterEncoding=utf-8&autoReconnect=true&useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
	private static final String username = "root";
	private static final String password = "ROOT";
	@Override
	public User findUserByName(String name) {
		// TODO Auto-generated method stub
		User user = new User();
		try {
			Class.forName(driver);
			Connection connection = DriverManager.getConnection(url,username,password);
			String sql = "SELECT id, username, password, phone, email, address FROM flower_shop.user where username=? ";
			PreparedStatement pStatement = (PreparedStatement)connection.prepareStatement(sql);
			pStatement.setString(1, name);
			ResultSet rSet = pStatement.executeQuery();
			
			while(rSet.next()) {
				user = new User();
				user.setId(rSet.getLong("id"));
				user.setUsername(rSet.getString("username"));
				user.setPassword(rSet.getString("password"));
				user.setPhone(rSet.getString("phone"));
				user.setEmail(rSet.getString("email"));
				user.setAddress(rSet.getString("address"));
				return user;
			}
			connection.close();
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;
	}
	@Override
	public void addUser(User user) {				//注册用户
		// TODO Auto-generated method stub
		try {
			Class.forName(driver);
			Connection connection = DriverManager.getConnection(url,username,password);
			String sql = "insert into flower_shop.user(username, password, phone, email, address) values(?, ?, ?, ?, ?); ";
			PreparedStatement pStatement = (PreparedStatement)connection.prepareStatement(sql);
			pStatement.setString(1, user.getUsername());
			pStatement.setString(2, user.getPassword());
			pStatement.setString(3, user.getPhone());
			pStatement.setString(4, user.getEmail());
			pStatement.setString(5, user.getAddress());
			pStatement.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
