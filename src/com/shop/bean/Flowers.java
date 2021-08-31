package com.shop.bean;

public class Flowers{

	private Long id;
	private String flowername;
	private double price;
	private String image;							//图片名字
	private String description;
	private Long category_id;						//所属分类
	private double vip_price;
	private Long sales;								//销量

	
	public void setId(Long id) {
		this.id = id;
	}
	public Long getId() {
		return this.id;
	}

	public void setFlowerName(String flowername) {
		this.flowername = flowername;
	}
	public String getFlowerName() {
		return this.flowername;
	}
	
	public void setPrice(Double price) {
		this.price = price;
	}
	public Double getPrice() {
		return this.price;
	}
	
	public void setImage(String image) {
		this.image = image;
	}
	public String getImage() {
		return this.image;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	public String getDescription() {
		return this.description;
	}
	
	public void setCategory_id(Long category_id) {
		this.category_id = category_id;
	}
	public Long getCategory_id() {
		return this.category_id;
	}
	
	public void setVip_price(Double vip_price) {
		this.vip_price = vip_price;
	}
	public Double getVip_price() {
		return this.vip_price;
	}

	public Long getSales() {
		return sales;
	}
	public void setSales(Long sales) {
		this.sales = sales;
	}
}
