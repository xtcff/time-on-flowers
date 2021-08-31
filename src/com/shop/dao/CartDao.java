package com.shop.dao;

import java.util.List;

import com.shop.bean.CartItem;

public interface CartDao {

	void saveOrUpdateCartItem(CartItem item);

	List<CartItem> getCartItemByUserId(Long id);

	void deleteCartItemByUserId(Long user_id);

	void deleteCartItemByItemId(String item_id);


}
