package com.shop.dao.impljdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import com.shop.bean.OrderItem;
import com.shop.bean.Order_Info;
import com.shop.dao.OrderDao;
import com.shop.service.FlowersService;

public class OrderDaoJdbcImpl implements OrderDao {
	private static final String driver = "com.mysql.cj.jdbc.Driver";
	private static final String url = "jdbc:mysql://8.142.121.139:3306/flower_shop?CharSet=utf8&useUnicode=true&characterEncoding=utf-8&autoReconnect=true&useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
	private static final String username = "root";
	private static final String password = "ROOT";
	@Override
	public void saveOrUpdateOrder(Order_Info order_Info, String status) {		//订单ID已随机生成，用status来区分修改订单和生成订单
		// TODO Auto-generated method stub
		try {
			Class.forName(driver);
			Connection connection = DriverManager.getConnection(url,username,password);
			String sql;
			if ("update".equals(status)) {
				sql = "update flower_shop.order_inf set address=?, tosb_name=?, tosb_phone=? where orderid=? ;";
			} else {
				sql = "insert into flower_shop.order_inf(orderid, user_id, user_phone, address, tosb_name, tosb_phone, whento, price) values(?, ?, ?, ?, ?, ?, ?, ?) ;";
			}
			PreparedStatement pStatement = (PreparedStatement)connection.prepareStatement(sql);
			
			if ("update".equals(status)) {
				pStatement.setString(1, order_Info.getAddress());
				pStatement.setString(2, order_Info.getTosb_name());
				pStatement.setString(3, order_Info.getTosb_phone());
				pStatement.setLong(4, order_Info.getOrderid());
			} else {
				order_Info.setOrderid(order_Info.getOrderid());
				pStatement.setLong(1, order_Info.getOrderid());
				pStatement.setLong(2, order_Info.getUserid());
				pStatement.setString(3, order_Info.getUserphone());
				pStatement.setString(4, order_Info.getAddress());
				pStatement.setString(5, order_Info.getTosb_name());
				pStatement.setString(6, order_Info.getTosb_phone());
				pStatement.setString(7, order_Info.getWhento());
				pStatement.setDouble(8, order_Info.getPrice());
			}
			
			pStatement.executeUpdate();
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public void saveOrderItems(List<OrderItem> orderItemsList) {
		// TODO Auto-generated method stub
		try {
			Class.forName(driver);
			Connection connection = DriverManager.getConnection(url,username,password);
			String sql = "insert into flower_shop.orderitem(quantity, price, order_id, flowers_id) values(?, ?, ?, ?) ; ";
			PreparedStatement pStatement = (PreparedStatement)connection.prepareStatement(sql);
			for (OrderItem orderItem : orderItemsList) {
				pStatement.setInt(1, orderItem.getQuantity());
				pStatement.setDouble(2, orderItem.getPrice());
				pStatement.setLong(3, orderItem.getOrder_id());
				pStatement.setLong(4, orderItem.getFlowers().getId());
				pStatement.executeUpdate();
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public List<Order_Info> getOrderListByUserId(Long user_id) {
		// TODO Auto-generated method stub
		List<Order_Info> order_ProList = new ArrayList<Order_Info>();
		try {
			Class.forName(driver);
			Connection connection = DriverManager.getConnection(url,username,password);
			String sql = "select orderid, user_id, user_phone, address, tosb_name, tosb_phone, whento, price, order_status, receive_time from flower_shop.order_inf where user_id=? ;";
			PreparedStatement pStatement = (PreparedStatement)connection.prepareStatement(sql);
			pStatement.setLong(1, user_id);
			ResultSet rSet = pStatement.executeQuery();
			Order_Info order_Info;
			while(rSet.next()) {
				order_Info = new Order_Info();
				order_Info.setOrderid(rSet.getLong("orderid"));
				order_Info.setUserid(rSet.getLong("user_id"));
				order_Info.setUserphone(rSet.getString("user_phone"));
				order_Info.setAddress(rSet.getString("address"));
				order_Info.setTosb_name(rSet.getString("tosb_name"));
				order_Info.setTosb_phone(rSet.getString("tosb_phone"));
				order_Info.setWhento(rSet.getString("whento"));
				order_Info.setPrice(rSet.getDouble("price"));
				order_Info.setOrder_status(rSet.getString("order_status"));
				order_Info.setReceive_time(rSet.getString("receive_time"));
				order_ProList.add(order_Info);
			}
			return order_ProList;
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return order_ProList;
	}
	@Override
	public List<OrderItem> getOrderItemByOrderId(Long orderid) {
		// TODO Auto-generated method stub
		List<OrderItem> orderItemList = new ArrayList<OrderItem>();
		FlowersService flowersService = new FlowersService();
		try {
			Class.forName(driver);
			Connection connection = DriverManager.getConnection(url,username,password);
			String sql = "select id, quantity, price, order_id, flowers_id from flower_shop.orderitem where order_id=? ;";
			PreparedStatement pStatement = (PreparedStatement)connection.prepareStatement(sql);
			pStatement.setLong(1, orderid);
			ResultSet rSet = pStatement.executeQuery();
			OrderItem orderItem;
			while(rSet.next()) {
				orderItem = new OrderItem();
				orderItem.setId(rSet.getLong("id"));
				orderItem.setOrder_id(rSet.getLong("order_id"));
				orderItem.setFlowers(flowersService.getFlowersById(rSet.getLong("flowers_id")));
				orderItem.setQuantity(rSet.getInt("quantity"));
				orderItem.setPrice(rSet.getDouble("price"));
				orderItemList.add(orderItem);
			}
			return orderItemList;
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return orderItemList;
	}
	@Override
	public void deleteOrderByOrderId(Long order_id) {
		// TODO Auto-generated method stub
		try {
			Class.forName(driver);
			Connection connection = DriverManager.getConnection(url,username,password);
			String sql = "delete from flower_shop.order_inf where orderid=? ; ";
			PreparedStatement pStatement = (PreparedStatement)connection.prepareStatement(sql);
			pStatement.setLong(1, order_id);
			pStatement.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

}
