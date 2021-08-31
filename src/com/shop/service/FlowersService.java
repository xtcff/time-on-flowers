package com.shop.service;

import java.util.List;

import com.shop.bean.Flowers;
import com.shop.dao.FlowersDao;
import com.shop.dao.impljdbc.FlowersDaoJdbcImpl;

public class FlowersService {
	private FlowersDao flowersDao = new FlowersDaoJdbcImpl();
	public List<Flowers> getAllFlowers() {
		// TODO Auto-generated method stub
		return flowersDao.getAllFlowers();
	}
	public List<Flowers> getSpecialFlowers(Long cate, String order) {
		// TODO Auto-generated method stub
		return flowersDao.getSpecialFlowers(cate, order);
	}
	public Flowers getFlowersById(Long flowerId) {
		// TODO Auto-generated method stub
		return flowersDao.getFlowersById(flowerId);
	}
	public List<Flowers> getFlowers(String select, String low_price, String high_price, String keyword) {
		// TODO Auto-generated method stub
		return flowersDao.getFlowers(select, low_price, high_price, keyword);
	}
	public List<Flowers> getFlowersVIP(String select, String low_price, String high_price, String keyword) {
		// TODO Auto-generated method stub
		return flowersDao.getFlowersVIP(select, low_price, high_price, keyword);
	}

}
