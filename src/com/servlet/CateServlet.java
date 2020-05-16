package com.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.beans.CateInfo;
import com.dao.CateDao;

import net.sf.json.JSONArray;

public class CateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CateDao catedao = new CateDao();
    public CateServlet() {
        super();
      
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String opt = request.getParameter("flag");
		if("bigAdd".equals(opt)) {
			cateBigAdd(request, response);
		}
		else if("cnki".equals(opt)) {
			selectByName(request,response);
		}
		else if("smallAdd".equals(opt)) {
			cateSmallAdd(request,response);
		}
		else if("manage".equals(opt)) {
			manage(request,response);
		}
		else if("select".equals(opt)) {
			selectById(request,response);
		}
		else if("del".equals(opt)) {
			delById(request,response);
		}
		else if("res".equals(opt)) {
			res(request,response);
		}
		else if("selectSmallCate".equals(opt)) {
			selectSmallCate(request,response);
		}
		
	}
	//��ѯ��������
	private void selectSmallCate(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int pid = Integer.valueOf(request.getParameter("pid"));
		List<CateInfo> list = catedao.getCateInfoByParentId(pid);
		
		JSONArray json = JSONArray.fromObject(list);
		response.getWriter().print(json);
	}
	//�޸�
	private void res(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.valueOf(request.getParameter("id"));
		String cateName = request.getParameter("cateName");
		String des = request.getParameter("des");
		String strParentId =  request.getParameter("parentId");
		int parentId = 0;
		if(strParentId!=null) {
			parentId = Integer.valueOf(strParentId);
		}
		CateInfo cate = new CateInfo();
		cate.setCateName(cateName);
		cate.setDes(des);
		cate.setParentId(parentId);
		cate.setId(id);
		int result = catedao.cateRes(cate);
		request.setAttribute("cate", cate);
		if(cate.getParentId()==0) {
			request.setAttribute("level", "һ����Ʒ");
		}
		else {
			List<CateInfo> list = catedao.getCateInfoByParentId(0);
			request.setAttribute("list", list);
			request.setAttribute("level", "������Ʒ");
		}

		if(result == 1) {
			request.setAttribute("msg", "�޸ĳɹ�");
			request.getRequestDispatcher("/goods/cate_res.jsp").forward(request, response);
		}
		else {
			request.setAttribute("msg", "�޸�ʧ��");
			request.getRequestDispatcher("/goods/cate_res.jsp").forward(request, response);
		}
	}

	//ͨ��idɾ��
	private void delById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.valueOf(request.getParameter("id"));
		List<CateInfo> list = catedao.getCateInfoByParentId(id);
		for(CateInfo cate:list) {
			catedao.cateDel(cate.getId());
		}
		catedao.cateDel(id);
		manage(request,response);
		
	}

	//ͨ��id��ѯ
	private void selectById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.valueOf(request.getParameter("id"));
		CateInfo cate = catedao.getCateById(id);
		if(cate.getParentId()==0) {
			request.setAttribute("level", "һ����Ʒ");
		}
		else {
			List<CateInfo> list = catedao.getCateInfoByParentId(0);
			request.setAttribute("list", list);
			request.setAttribute("level", "������Ʒ");
		}
		request.setAttribute("cate", cate);
		request.getRequestDispatcher("/goods/cate_res.jsp").forward(request, response);
	}

	//����ά��
	private void manage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<CateInfo> list = catedao.getCateInfoByParentId(0);
		
		request.setAttribute("list", list);
		request.getRequestDispatcher("/goods/cate_manage.jsp").forward(request, response);
		
	}

	//��Ӷ�������
	private void cateSmallAdd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cateName = request.getParameter("cateName");
		String des = request.getParameter("des");
		int pid = Integer.valueOf(request.getParameter("pid"));
		CateInfo cate = new CateInfo();
		cate.setCateName(cateName);
		cate.setDes(des);
		cate.setParentId(pid);
		int result = catedao.cateAdd(cate);
		request.setAttribute("cate", cate);
		if(result == 1) {
			request.setAttribute("msg", "��ӳɹ�");
			request.getRequestDispatcher("/goods/smallcate_add.jsp").forward(request, response);
		}
		else {
			request.setAttribute("msg", "���ʧ��");
			request.getRequestDispatcher("/goods/smallcate_add.jsp").forward(request, response);
		}

		
	}

	//ͨ����������ѯ
	private void selectByName(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String cateName = request.getParameter("cateName");
		String startName = request.getParameter("startName");
		CateInfo cates = catedao.getCateByName(cateName);
		if(cates!=null&&!cateName.equals(startName)) {
			response.getWriter().print("�������Ѵ��ڣ�");
		}
		else {
			String regex="\\S{2,20}"; 
			if(cateName.matches(regex)) {
				response.getWriter().print("��");
				}
			else {
				response.getWriter().print("��������ʽ�Ƿ���");
			}
		}
	}
	//���һ������
	private void cateBigAdd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cateName = request.getParameter("cateName");
		String des = request.getParameter("des");
		CateInfo cate = new CateInfo();
		cate.setCateName(cateName);
		cate.setDes(des);
		cate.setParentId(0);
		int result = catedao.cateAdd(cate);
		if(result == 1) {
			request.setAttribute("msg", "��ӳɹ�");
			request.getRequestDispatcher("/goods/bigcate_add.jsp").forward(request, response);
		}
		else {
			request.setAttribute("msg", "���ʧ��");
			request.getRequestDispatcher("/goods/bigcate_add.jsp").forward(request, response);
		}

		
	}

}
