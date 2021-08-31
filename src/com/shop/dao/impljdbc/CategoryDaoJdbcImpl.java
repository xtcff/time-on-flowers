package com.shop.dao.impljdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.shop.bean.Category;
import com.shop.dao.CategoryDao;

public class CategoryDaoJdbcImpl implements CategoryDao {
	private static final String driver = "com.mysql.cj.jdbc.Driver";
	private static final String url = "jdbc:mysql://8.142.121.139:3306/flower_shop?CharSet=utf8&useUnicode=true&characterEncoding=utf-8&autoReconnect=true&useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
	private static final String username = "root";
	private static final String password = "ROOT";
	@Override
	public List<Category> getAllCategory() {
		// TODO Auto-generated method stub
		List<Category> list = new ArrayList<Category>();
		try {
			Class.forName(driver);
			Connection connection = DriverManager.getConnection(url,username,password);
			String sql = "SELECT id, name, description FROM flower_shop.category ;";
			PreparedStatement pStatement = (PreparedStatement)connection.prepareStatement(sql);
			ResultSet rSet = pStatement.executeQuery();
			Category category;
			while (rSet.next()) {
				category = new Category();
				category.setId(rSet.getLong("id"));
				category.setName(rSet.getString("name"));
				category.setDescription(rSet.getString("description"));
				list.add(category);
			}
			return list;
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return list;
	}
	@Override
	public Category getCategoryByName(String select) {
		// TODO Auto-generated method stub
		try {
			Class.forName(driver);
			Connection connection = DriverManager.getConnection(url,username,password);
			String sql = "SELECT id, name, description FROM flower_shop.category where name=? ;";
			PreparedStatement pStatement = (PreparedStatement)connection.prepareStatement(sql);
			pStatement.setString(1, select);
			ResultSet rSet = pStatement.executeQuery();
			Category category;
			while (rSet.next()) {
				category = new Category();
				category.setId(rSet.getLong("id"));
				category.setName(rSet.getString("name"));
				category.setDescription(rSet.getString("description"));
				return category;
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}
}
