package com.shop.dao;

import java.util.List;

import com.shop.bean.OrderItem;
import com.shop.bean.Order_Info;

public interface OrderDao {

	void saveOrUpdateOrder(Order_Info order_Info, String status);

	void saveOrderItems(List<OrderItem> orderItemsList);

	List<Order_Info> getOrderListByUserId(Long user_id);

	List<OrderItem> getOrderItemByOrderId(Long orderid);

	void deleteOrderByOrderId(Long order_id);

}
