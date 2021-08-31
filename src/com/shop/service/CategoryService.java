package com.shop.service;

import java.util.List;

import com.shop.bean.Category;
import com.shop.dao.CategoryDao;
import com.shop.dao.impljdbc.CategoryDaoJdbcImpl;

public class CategoryService {
	private CategoryDao categoryDao = new CategoryDaoJdbcImpl();

	public List<Category> getAllCategory() {
		// TODO Auto-generated method stub
		return categoryDao.getAllCategory();
	}

	public Category getCategoryByName(String select) {
		// TODO Auto-generated method stub
		return categoryDao.getCategoryByName(select);
	}
	
}
