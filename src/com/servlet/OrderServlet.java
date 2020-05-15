package com.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.beans.OrderInfo;
import com.dao.OrderDao;
import com.utils.OtherUtil;
import com.utils.PageInfo;
import com.utils.PageUtil;

public class OrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private OrderDao orderdao = new OrderDao();
    public OrderServlet() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String opt = request.getParameter("flag");
		if("manage".equals(opt)) {
			manage(request, response);
		}
		else if("select".equals(opt)) {
			select(request, response);
		}
		else if("send".equals(opt)) {
			send(request, response);
		}
	}
	//发货
	private void send(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.valueOf(request.getParameter("id"));
		orderdao.send(id);
		manage(request, response);
	}
	//查看订单
	private void select(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.valueOf(request.getParameter("id"));
		OrderInfo order = orderdao.getOrderById(id);
		List<OrderInfo> orderGoods = orderdao.getOrderGoodsById(id);
		request.setAttribute("order", order);
		request.setAttribute("orderGoods", orderGoods);
		
		request.getRequestDispatcher("/order/order_show.jsp").forward(request, response);
		
	}
	//订单维护
	private void manage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String findOrderNo = request.getParameter("findOrderNo");
		String startTimeStr = request.getParameter("startTime");
		String endTimeStr = request.getParameter("endTime");
		String findOrderState = request.getParameter("findOrderState");
		String startTime = null;
		String endTime = null;
		if(!OtherUtil.StrIsNullOrEmpty(startTimeStr)) {
			startTime = startTimeStr.split("\\.")[0];
			
		}
		if(!OtherUtil.StrIsNullOrEmpty(endTimeStr)) {
			endTime= endTimeStr.split("\\.")[0];
		}
		if("-1".equals(findOrderState)) {
			findOrderState = "";
		}
		int pageSize = 5;
		int rowCount = orderdao.getAllOrderCount(findOrderNo, startTime, endTime, findOrderState);
		int pageIndex = 1;
		String indexStr = request.getParameter("pageIndex");
		
		if(indexStr != null) {
			pageIndex = Integer.valueOf(indexStr);
		}
		PageInfo page = PageUtil.getPageInfo(pageSize, rowCount, pageIndex);
	
		List<OrderInfo> list = orderdao.getAllOrder(findOrderNo,startTime,endTime, findOrderState, page);

		request.setAttribute("list", list);
		
		request.setAttribute("page", page);
		request.getRequestDispatcher("/order/order_manage.jsp").forward(request, response);
		
	}

}
