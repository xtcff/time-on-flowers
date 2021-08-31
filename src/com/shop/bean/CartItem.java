package com.shop.bean;

public class CartItem {
	private Long item_id;
	private Flowers flowers;
	private int quantity;
	private double vip_price;									//总价
	private Long user_id;
	
	public Long getItem_id() {
		return item_id;
	}
	public void setItem_id(Long item_id) {
		this.item_id = item_id;
	}
	public Long getUser_id() {
		return user_id;
	}
	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}
	public void setFlowers(Flowers flowers) {
		this.flowers = flowers;
	}
	public Flowers getFlowers() {
		return this.flowers;
	}
	
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public Integer getQuantity() {
		return this.quantity;
	}
	
	public void setVip_price(double vip_price) {
		this.vip_price = vip_price;
	}
	public Double getVip_price() {
		return this.vip_price;
	}

}
