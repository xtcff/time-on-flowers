package com.shop.dao.impljdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.shop.bean.CartItem;
import com.shop.dao.CartDao;
import com.shop.service.FlowersService;

public class CartDaoJdbcImpl implements CartDao {
	private static final String driver = "com.mysql.cj.jdbc.Driver";
	private static final String url = "jdbc:mysql://8.142.121.139:3306/flower_shop?CharSet=utf8&useUnicode=true&characterEncoding=utf-8&autoReconnect=true&useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
	private static final String username = "root";
	private static final String password = "ROOT";
	@Override
	public void saveOrUpdateCartItem(CartItem item) {
		// TODO Auto-generated method stub
		try {
			Class.forName(driver);
			Connection connection = DriverManager.getConnection(url,username,password);
			String sql = "insert into flower_shop.cartitem(flower_id, user_id, quantity, price) values(?, ?, ?, ?) ;";
			PreparedStatement pStatement = (PreparedStatement)connection.prepareStatement(sql);
			pStatement.setLong(1, item.getFlowers().getId());
			pStatement.setLong(2, item.getUser_id());
			pStatement.setInt(3, item.getQuantity());
			pStatement.setDouble(4, item.getVip_price());
			pStatement.executeUpdate();
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public List<CartItem> getCartItemByUserId(Long id) {
		// TODO Auto-generated method stub
		List<CartItem> list = new ArrayList<CartItem>();
		try {
			Class.forName(driver);
			Connection connection = DriverManager.getConnection(url,username,password);
			String sql = "select item_id, flower_id, user_id, quantity, price from flower_shop.cartitem where user_id=? ;";
			PreparedStatement pStatement = (PreparedStatement)connection.prepareStatement(sql);
			pStatement.setLong(1, id);
			ResultSet rSet = pStatement.executeQuery();
			CartItem cartItem;
			FlowersService flowersService = new FlowersService();
			while (rSet.next()) {
				cartItem = new CartItem();
				cartItem.setItem_id(rSet.getLong("item_id"));
				cartItem.setFlowers(flowersService.getFlowersById(rSet.getLong("flower_id")));
				cartItem.setUser_id(rSet.getLong("user_id"));
				cartItem.setQuantity(rSet.getInt("quantity"));
				cartItem.setVip_price(rSet.getDouble("price"));
				list.add(cartItem);
			}
			return list;
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	@Override
	public void deleteCartItemByUserId(Long user_id) {
		// TODO Auto-generated method stub
		try {
			Class.forName(driver);
			Connection connection = DriverManager.getConnection(url,username,password);
			String sql = "delete from flower_shop.cartitem where user_id=? ;";
			PreparedStatement pStatement = (PreparedStatement)connection.prepareStatement(sql);
			pStatement.setLong(1, user_id);
			pStatement.executeUpdate();
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public void deleteCartItemByItemId(String item_id) {
		// TODO Auto-generated method stub
		try {
			Class.forName(driver);
			Connection connection = DriverManager.getConnection(url,username,password);
			String sql = "delete from flower_shop.cartitem where item_id=? ;";
			PreparedStatement pStatement = (PreparedStatement)connection.prepareStatement(sql);
			pStatement.setLong(1, Long.parseLong(item_id));
			pStatement.executeUpdate();
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
