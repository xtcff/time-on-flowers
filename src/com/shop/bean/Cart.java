package com.shop.bean;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Cart {
	private Map<Long, CartItem> map = new HashMap<Long, CartItem>();			//键：Flower_id    值：购物项
	private double price;														//总价
	
	
	public Collection<CartItem> getMapValues() {
		return map.values();
	}
	public Map<Long, CartItem> getMap() {
		return map;
	}
	public void setMap(Map<Long, CartItem> map) {
		this.map = map;
	}
	
	public double getPrice() {
		double totalPrice = 0;
		for(Map.Entry<Long, CartItem> me : map.entrySet() ) {
			CartItem item = me.getValue();
			totalPrice += item.getVip_price();
		}
		this.price = totalPrice;
		return price;
	}
	
	public void setPrice(double price) {
		this.price = price;
	}

}
