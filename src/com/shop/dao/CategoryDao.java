package com.shop.dao;

import java.util.List;

import com.shop.bean.Category;

public interface CategoryDao {
	List<Category> getAllCategory();

	Category getCategoryByName(String select);
}
