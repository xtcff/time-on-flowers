package com.shop.dao;

import java.util.List;

import com.shop.bean.Flowers;

public interface FlowersDao {

	List<Flowers> getAllFlowers();

	List<Flowers> getSpecialFlowers(Long cate, String order);

	Flowers getFlowersById(Long flowerId);

	List<Flowers> getFlowers(String select, String low_price, String high_price, String keyword);

	List<Flowers> getFlowersVIP(String select, String low_price, String high_price, String keyword);

}
