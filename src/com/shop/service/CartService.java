package com.shop.service;


import java.util.List;

import com.shop.bean.CartItem;
import com.shop.dao.CartDao;
import com.shop.dao.impljdbc.CartDaoJdbcImpl;

public class CartService {
	private CartDao cartDao = new CartDaoJdbcImpl();
	public void saveOrUpdateCartItem(CartItem item) {
		// TODO Auto-generated method stub
		cartDao.saveOrUpdateCartItem(item);
	}
	public List<CartItem> getCartItemByUserId(Long id) {
		// TODO Auto-generated method stub
		return cartDao.getCartItemByUserId(id);
	}
	public void deleteCartItemByUserId(Long user_id) {
		// TODO Auto-generated method stub
		cartDao.deleteCartItemByUserId(user_id);
	}
	public void deleteCartItemByItemId(String item_id) {
		// TODO Auto-generated method stub
		cartDao.deleteCartItemByItemId(item_id);
	}
}
