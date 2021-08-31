package com.shop.service;

import java.util.List;

import com.shop.bean.OrderItem;
import com.shop.bean.Order_Info;
import com.shop.dao.OrderDao;
import com.shop.dao.impljdbc.OrderDaoJdbcImpl;

public class OrderService {
	private OrderDao orderDao = new OrderDaoJdbcImpl();

	public void saveOrUpdateOrder(Order_Info order_Info, String status) {
		// TODO Auto-generated method stub
		orderDao.saveOrUpdateOrder(order_Info, status);
	}

	public void saveOrderItems(List<OrderItem> orderItemsList) {
		// TODO Auto-generated method stub
		orderDao.saveOrderItems(orderItemsList);
	}

	public List<Order_Info> getOrderListByUserId(Long user_id) {
		// TODO Auto-generated method stub
		return orderDao.getOrderListByUserId(user_id);
	}
	
	public List<OrderItem> getOrderItemByOrderId(Long orderid) {
		// TODO Auto-generated method stub
		return orderDao.getOrderItemByOrderId(orderid);
	}

	public void deleteOrderByOrderId(Long order_id) {
		// TODO Auto-generated method stub
		orderDao.deleteOrderByOrderId(order_id);
	}

	
}
