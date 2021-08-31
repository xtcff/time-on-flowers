package com.shop.web;

import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shop.bean.Flowers;
import com.shop.service.FlowersService;


/**
 * Servlet implementation class FilterServlet
 */
@WebServlet("/FilterServlet")
public class FilterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	FlowersService flowersService = new FlowersService();
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		if (request.getParameter("cate") != null) {									//有分类，说明是从分类列表传来，根据排序过滤分类
			Long cate = Long.parseLong(request.getParameter("cate"));
			String order = request.getParameter("order");
			List<Flowers> flowersList = flowersService.getSpecialFlowers(cate,order);
			request.getServletContext().setAttribute("flowersList", flowersList);
			request.getRequestDispatcher("PageFlowersServlet").forward(request, response);
		} else {																	//没有分类，从排序传来，执行doOrder排序
			doOrder(request, response);
		}
	}

	@SuppressWarnings("unchecked")
	private void doOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		List<Flowers> flowersList;
		if (request.getServletContext().getAttribute("flowersList") != null) {
			flowersList = (List<Flowers>) request.getServletContext().getAttribute("flowersList");
		}else {
			flowersList = flowersService.getAllFlowers();
		}
		
		if ("salesdesc".equals(request.getParameter("order"))) {
			sortListBySales(flowersList);
			Collections.reverse(flowersList);
		}else if ("salesasc".equals(request.getParameter("order"))) {
			sortListBySales(flowersList);
		}else if ("priceasc".equals(request.getParameter("order"))) {
			sortListByPrice(flowersList);
		}else if ("pricedesc".equals(request.getParameter("order"))) {
			sortListByPrice(flowersList);
			Collections.reverse(flowersList);
		}
		request.getServletContext().setAttribute("flowersList", flowersList);
		response.sendRedirect("PageFlowersServlet");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

	protected void sortListBySales(List<Flowers> list) {							//根据销量将列表排序
		Collections.sort(list, new Comparator<Flowers>() {
			@Override
			public int compare(Flowers f1,Flowers f2) {
				if (f1.getSales() > f2.getSales()) {
					return 1;
				} else if (f1.getSales() < f2.getSales()) {
					return -1;
				} else {
					return 0;
				}
			}
		});
	}
	
	protected void sortListByPrice(List<Flowers> list) {							//根据原价将列表排序
		Collections.sort(list, new Comparator<Flowers>() {
			@Override
			public int compare(Flowers f1,Flowers f2) {
				if (f1.getPrice() > f2.getPrice()) {
					return 1;
				} else if (f1.getPrice() < f2.getPrice()) {
					return -1;
				} else {
					return 0;
				}
			}
		});
	}
	
	protected void sortListByVip_price(List<Flowers> list) {						//根据会员价将列表排序
		Collections.sort(list, new Comparator<Flowers>() {
			@Override
			public int compare(Flowers f1,Flowers f2) {
				if (f1.getVip_price() > f2.getVip_price()) {
					return 1;
				} else if (f1.getVip_price() < f2.getVip_price()) {
					return -1;
				} else {
					return 0;
				}
			}
		});
	}
}
