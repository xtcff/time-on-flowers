package com.shop.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shop.bean.Cart;
import com.shop.bean.CartItem;
import com.shop.bean.Flowers;
import com.shop.bean.User;
import com.shop.service.CartService;
import com.shop.service.FlowersService;
import com.shop.service.UserService;

/**
 * Servlet implementation class CartServlet
 */
@WebServlet("/CartServlet")
public class CartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private FlowersService flowersService = new FlowersService();
	private CartService cartService = new CartService();
	private UserService userService = new UserService();
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		if (request.getParameter("flowerId") == null) {							//flower_id为空，从我的购物车传来
			String name = (String)request.getSession().getAttribute("username");
			User user = userService.findUserByName(name);
			List<CartItem> list = cartService.getCartItemByUserId(user.getId());
			Cart cart = null;
			if(list.size() != 0) {
				cart = new Cart();
				Map<Long, CartItem> map = new HashMap<Long, CartItem>();
				for (CartItem cartItem : list) {
					map.put(cartItem.getFlowers().getId(), cartItem);
				}
				cart.setMap(map);
			}
			request.getSession().setAttribute("cart", cart);
			response.sendRedirect("cart.jsp");
		} else {																//不为空，从加入购物车传来
			Long flowerId = Long.parseLong(request.getParameter("flowerId"));
			Flowers flower = flowersService.getFlowersById(flowerId);
			Cart cart = (Cart) request.getSession().getAttribute("cart");
			if(cart == null) {
				cart = new Cart();
			}
			String name = (String)request.getSession().getAttribute("username");
			User user = userService.findUserByName(name);
			CartItem item = cart.getMap().get(flower.getId());
			if (item == null) {
				item = new CartItem();
				item.setFlowers(flower);
				item.setQuantity(1);
				item.setVip_price(flower.getVip_price());
				item.setUser_id(user.getId());
				cart.getMap().put(flower.getId(), item);
				cartService.saveOrUpdateCartItem(item);
			} else {
				item.setQuantity(item.getQuantity() + 1);
				item.setVip_price(item.getVip_price() + flower.getVip_price());
				cartService.saveOrUpdateCartItem(item);
			}
			
			List<CartItem> list = cartService.getCartItemByUserId(user.getId());	//保存的购物项没有item_id，从数据库重新获取
			if(list.size() != 0) {
				cart = new Cart();
				Map<Long, CartItem> map = new HashMap<Long, CartItem>();
				for (CartItem cartItem : list) {
					map.put(cartItem.getFlowers().getId(), cartItem);
				}
				cart.setMap(map);
			}
			request.getSession().setAttribute("cart", cart);
			response.sendRedirect("cart.jsp");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
