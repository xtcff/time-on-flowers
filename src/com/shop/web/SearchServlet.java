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

import com.shop.bean.Flowers;
import com.shop.service.FlowersService;

/**
 * Servlet implementation class SearchServlet
 */
@WebServlet("/SearchServlet")
public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private FlowersService flowersService = new FlowersService();
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String select = request.getParameter("select");
		String low_price = request.getParameter("low_price");
		String high_price = request.getParameter("high_price");
		String keyword = request.getParameter("keyword");
		Map<String, String> map = new HashMap<String, String>();
		map.put("select", select);
		map.put("low_price", low_price);
		map.put("high_price", high_price);
		map.put("keyword", keyword);
		List<Flowers> flowerList;
		if (request.getSession().getAttribute("username") != null) {
			flowerList = flowersService.getFlowersVIP(select, low_price, high_price, keyword);
		} else {
			flowerList = flowersService.getFlowers(select, low_price, high_price, keyword);
		}
		request.getSession().setAttribute("map", map);
		request.getServletContext().setAttribute("flowersList", flowerList);
		request.getRequestDispatcher("PageFlowersServlet").forward(request, response);
	}

}
