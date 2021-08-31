package com.shop.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shop.bean.Category;
import com.shop.bean.Flowers;
import com.shop.service.CategoryService;
import com.shop.service.FlowersService;

/**
 * Servlet implementation class IndexServlet
 */
@WebServlet("/IndexServlet")
public class IndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private FlowersService flowersService = new FlowersService();
	private CategoryService categoryService = new CategoryService();
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		List<Flowers> flowersList = flowersService.getAllFlowers();
		List<Category> categoryList = categoryService.getAllCategory();
		request.getSession().setAttribute("categoryList", categoryList);
		request.getServletContext().setAttribute("flowersList", flowersList);
		
		request.getRequestDispatcher("PageFlowersServlet").forward(request, response);
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
