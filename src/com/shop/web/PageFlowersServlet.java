package com.shop.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shop.bean.Flowers;
import com.shop.bean.Page;
import com.shop.service.FlowersService;

/**
 * Servlet implementation class pageFlowersServlet
 */
@WebServlet("/PageFlowersServlet")
public class PageFlowersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private FlowersService flowersService = new FlowersService();
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String pageNum = request.getParameter("pageNum");
		List<Flowers> flowersList = (List<Flowers>)request.getServletContext().getAttribute("flowersList");
		if (flowersList == null) {
			flowersList = flowersService.getAllFlowers();
		}
		Page page = null;
		if ("".equals(pageNum) || pageNum == null) {
			page = new Page(1, flowersList.size());
			List<Flowers> pageList = flowersList.subList(0, 3);
			page.setList(pageList);
			
		} else {
			if (Integer.parseInt(pageNum) <= 0) {
				page = new Page(1, flowersList.size());
				List<Flowers> pageList = flowersList.subList(0, 3);
				page.setList(pageList);
			} else if(Integer.parseInt(pageNum) < ((flowersList.size() + 2) / 3)){
				page = new Page(Integer.parseInt(pageNum), flowersList.size());
				List<Flowers> pageList = flowersList.subList((Integer.parseInt(pageNum) - 1) * 3, (Integer.parseInt(pageNum) - 1) * 3 + 3);
				page.setList(pageList);
			}else {
				page = new Page((flowersList.size() + 2) / 3, flowersList.size());
				List<Flowers> pageList = flowersList.subList(((flowersList.size() + 2) / 3 - 1) * 3, flowersList.size());
				page.setList(pageList);
			}
		}
		request.setAttribute("page", page);
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
