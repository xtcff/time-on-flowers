package com.shop.web;

import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shop.bean.Cart;
import com.shop.bean.CartItem;
import com.shop.bean.OrderItem;
import com.shop.bean.Order_Info;
import com.shop.bean.User;
import com.shop.service.CartService;
import com.shop.service.OrderService;
import com.shop.service.UserService;

/**
 * Servlet implementation class OrderServlet
 */
@WebServlet("/OrderServlet")
public class OrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserService userService = new UserService();
	private OrderService orderService = new OrderService();
	private CartService cartService = new CartService();
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String username = (String) request.getSession().getAttribute("username");
		User user = userService.findUserByName(username);
		List<Order_Info> orderInfoList = orderService.getOrderListByUserId(user.getId());
		for (Order_Info order_Info : orderInfoList) {						//遍历订单列表，根据订单号获取订单项，插入到每个订单
			List<OrderItem> orderItemList = orderService.getOrderItemByOrderId(order_Info.getOrderid());
			order_Info.setOrderItems(orderItemList);
		}
		request.getSession().setAttribute("orderInfoList", orderInfoList);
		response.sendRedirect("myorder.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String receive_name = request.getParameter("receive_name");
		String receive_phone = request.getParameter("receive_phone");
		String receive_address = request.getParameter("receive_address");
		String order_id = request.getParameter("order_id");
		Order_Info order_Info = new Order_Info();
		order_Info.setTosb_name(receive_name);
		order_Info.setTosb_phone(receive_phone);
		order_Info.setAddress(receive_address);
		
		if (order_id != null && !"".equals(order_id)) {
			order_Info.setOrderid(Long.parseLong(order_id));
			orderService.saveOrUpdateOrder(order_Info, "update");				//更新订单
			response.sendRedirect("OrderServlet");
		} else {
			String username = (String) request.getSession().getAttribute("username");
			User user = userService.findUserByName(username);
			Cart cart = (Cart) request.getSession().getAttribute("cart");
			order_Info.setUserid(user.getId());
			order_Info.setUserphone(user.getPhone());
			order_Info.setPrice(cart.getPrice());
			order_Info.setWhento(this.getTime());
			order_id = UUID.randomUUID().toString().replaceAll("[^(0-9)]", "").substring(0, 9);
			order_Info.setOrderid(Long.parseLong(order_id));
			orderService.saveOrUpdateOrder(order_Info, "insert");				//新增订单
			
			for(CartItem item:cart.getMapValues()) {
				OrderItem orderItem = new OrderItem();
				orderItem.setFlowers(item.getFlowers());
				orderItem.setQuantity(item.getQuantity());
				orderItem.setPrice(item.getVip_price());
				orderItem.setOrder_id(Long.parseLong(order_id));
				order_Info.getOrderItems().add(orderItem);
			}
			List<OrderItem> orderItemsList = order_Info.getOrderItems();
			orderService.saveOrderItems(orderItemsList);
			request.getSession().removeAttribute("cart");
			cartService.deleteCartItemByUserId(user.getId());
			response.sendRedirect("OrderServlet");
		}
	}
	public String getTime() {													//获取当前时间并格式化
		SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
		Date date = new Date(System.currentTimeMillis());
		return formatter.format(date);
	}
}
