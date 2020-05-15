package com.servlet;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.beans.MemberInfo;
import com.dao.MemberDao;

import com.utils.OtherUtil;
import com.utils.PageInfo;
import com.utils.PageUtil;


public class MemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private MemberDao memberdao = new MemberDao();
    public MemberServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String opt = request.getParameter("flag");
		if("manage".equals(opt)) {
			manage(request, response);
		}
		else if("del".equals(opt)) {
			meberDel(request, response);
		}
		else if("delMore".equals(opt)) {
			meberDelMore(request, response);
		}
		else if("select".equals(opt)) {
			meberSelect(request, response);
		}
	}
	//通过id查询
	private void meberSelect(HttpServletRequest request, HttpServletResponse response) throws NumberFormatException, ServletException, IOException {
		int id = Integer.valueOf(request.getParameter("id"));
		MemberInfo member = memberdao.getMemberById(id);
		request.setAttribute("member", member);
		request.getRequestDispatcher("/member/member_show.jsp").forward(request, response);
		
		
	}
	//删除多个会员
	private void meberDelMore(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String[] ids = request.getParameterValues("ids");
		for(String id:ids) {
			int mid = Integer.valueOf(id);
			memberdao.memberDel(mid);
		}
		response.getWriter().print("删除成功");
	}
	//删除会员
	private void meberDel(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.valueOf(request.getParameter("id"));
		int result = memberdao.memberDel(id);
		if(result == 1) {
			request.setAttribute("msg", "删除成功");
			
		}
		else {
			request.setAttribute("msg", "删除失败");
		}
		manage(request, response);
	}
	//会员维护
	private void manage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException  {
		String findMemberNo = request.getParameter("findMemberNo");
		String startTimeStr = request.getParameter("startTime");
		String endTimeStr = request.getParameter("endTime");
		String startTime = null;
		String endTime = null;
		if(!OtherUtil.StrIsNullOrEmpty(startTimeStr)) {
			startTime = startTimeStr.split("\\.")[0];
			
		}
		if(!OtherUtil.StrIsNullOrEmpty(endTimeStr)) {
			endTime= endTimeStr.split("\\.")[0];
		}
		int pageSize = 5;
		int rowCount = memberdao.getAllMemberCount(findMemberNo, startTime, endTime);
		int pageIndex = 1;
		String indexStr = request.getParameter("pageIndex");
		
		if(indexStr != null) {
			pageIndex = Integer.valueOf(indexStr);
		}
		PageInfo page = PageUtil.getPageInfo(pageSize, rowCount, pageIndex);
	
		List<MemberInfo> list = memberdao.getAllMember(findMemberNo,startTime,endTime,page);

		request.setAttribute("list", list);
		request.setAttribute("page", page);
		request.getRequestDispatcher("/member/member_manage.jsp").forward(request, response);
		
	}

}
