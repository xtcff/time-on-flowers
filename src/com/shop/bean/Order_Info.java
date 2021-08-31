package com.shop.bean;

import java.util.ArrayList;
import java.util.List;

public class Order_Info {

	private Long orderid;
	private Long userid;
	private String userphone;
	private String address;							//收货人地址
	private String tosb_name;						//收货人姓名
	private String tosb_phone;						//收货人联系电话
	private String whento;							//订单生成时间
	private double price;
	private String order_status;					//订单状态：未处理、配货、发货、签收
	private String receive_time;
	
	List<OrderItem> orderItems = new ArrayList<OrderItem>();
	
	
	public List<OrderItem> getOrderItems() {
		return orderItems;
	}
	public void setOrderItems(List<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public void setOrderid(Long orderid) {
		this.orderid = orderid;
	}
	public Long getOrderid() {
		return this.orderid;
	}
	
	public void setUserid(Long userid) {
		this.userid = userid;
	}
	public Long getUserid() {
		return this.userid;
	}
	
	public void setUserphone(String userphone) {
		this.userphone = userphone;
	}
	public String getUserphone() {
		return this.userphone;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	public String getAddress() {
		return this.address;
	}
	
	public void setTosb_name(String tosb_name) {
		this.tosb_name = tosb_name;
	}
	public String getTosb_name() {
		return this.tosb_name;
	}
	
	public void setTosb_phone(String tosb_phone) {
		this.tosb_phone = tosb_phone;
	}
	public String getTosb_phone() {
		return this.tosb_phone;
	}
	
	public void setWhento(String whento) {
		this.whento = whento;
	}
	public String getWhento() {
		return this.whento;
	}

	public String getOrder_status() {
		return order_status;
	}
	public void setOrder_status(String order_status) {
		this.order_status = order_status;
	}
	public String getReceive_time() {
		return receive_time;
	}
	public void setReceive_time(String receive_time) {
		this.receive_time = receive_time;
	}
}
