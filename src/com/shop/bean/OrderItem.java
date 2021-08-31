package com.shop.bean;

public class OrderItem {
	private Long id;
	private Flowers flowers;
	private int quantity;
	private double price;
	private Long order_id;						//所属订单号
	
	public Long getOrder_id() {
		return order_id;
	}
	public void setOrder_id(Long order_id) {
		this.order_id = order_id;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}

	public Flowers getFlowers() {
		return flowers;
	}
	public void setFlowers(Flowers flowers) {
		this.flowers = flowers;
	}
}
