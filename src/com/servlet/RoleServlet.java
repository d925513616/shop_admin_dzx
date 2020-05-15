package com.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.beans.AdminInfo;
import com.beans.MenuInfo;
import com.beans.RoleInfo;
import com.dao.AdminDao;
import com.dao.MenuDao;
import com.dao.RoleDao;

public class RoleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private RoleDao roledao = new RoleDao();
	private AdminDao admindao = new AdminDao();
	private MenuDao menudao = new MenuDao();
    public RoleServlet() {
        super();
       
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String opt = request.getParameter("flag");
		if("listadmin".equals(opt)) {
			listadmin(request, response);
		}
		else if("roleSet".equals(opt)) {
			roleSet(request, response);
		}
		else if("res".equals(opt)) {
			roleRes(request, response);
		}
		else if("add".equals(opt)) {
			roleAdd(request, response);
		}
		else if("cnki".equals(opt)) {
			selectByName(request,response);
		}
		else if("manage".equals(opt)) {
			manage(request,response);
		}
		else if("select".equals(opt)) {
			selectById(request,response);
		}
		else if("roleInfoRes".equals(opt)) {
			roleInfoRes(request,response);
		}
		else if("del".equals(opt)) {
			roleInfoDel(request,response);
		}
		else if("grand".equals(opt)) {
			roleGrand(request,response);
		}
		else if("grandRes".equals(opt)) {
			roleGrandRes(request,response);
		}
	}
	//�޸�Ȩ��
	private void roleGrandRes(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String[] ids = request.getParameterValues("ids");
		int roleid = Integer.valueOf(request.getParameter("id"));
		roledao.delAuthoryFromRolemenu(roleid);
		for(String id:ids) {
			int menuId = Integer.valueOf(id);
			roledao.addAuthoryOnRolemenu(roleid, menuId);
		}
		request.setAttribute("msg", "�޸ĳɹ�");
		roleGrand(request,response);
	}
	//����Ȩ�޷������
	private void roleGrand(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.valueOf(request.getParameter("id"));
		RoleInfo role = roledao.getRoleById(id);
		List<MenuInfo> list = menudao.getAllMenuList(0);
		List<Integer> grandlist = roledao.getMenuIdByRoleId(id);
		request.setAttribute("id", id);
		request.setAttribute("roleName", role.getRoleName());
		request.setAttribute("list", list);
		request.setAttribute("grandlist", grandlist);
		request.getRequestDispatcher("/perm/role_grand.jsp").forward(request, response);
	}
	//ɾ����ɫ��Ϣ
	private void roleInfoDel(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.valueOf(request.getParameter("id"));
		int count = roledao.whetherAdminHaveThisRole(id);
		if(count != 0) {
			request.setAttribute("msg", "���û�����ʹ�øý�ɫ������ɾ��");
			manage(request,response);
		}
		else {
			roledao.delRoleInfoById(id);
			manage(request,response);
		}
		
		
	}
	//�޸Ľ�ɫ��Ϣ
	private void roleInfoRes(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.valueOf(request.getParameter("id"));
		String roleName = request.getParameter("roleName");
		String des = request.getParameter("des");
		RoleInfo role = new RoleInfo();
		role.setRoleName(roleName);
		role.setDes(des);
		int result = roledao.updateRoleInfo(roleName,des,id);
		request.setAttribute("role", role);
		if(result == 1) {
			request.setAttribute("msg", "�޸ĳɹ�");
			request.getRequestDispatcher("/perm/role_res.jsp").forward(request, response);
		}
		else {
			request.setAttribute("msg", "�޸�ʧ��");
			request.getRequestDispatcher("/perm/role_res.jsp").forward(request, response);
		}
	}
	//��ȡ��ɫ��Ϣ
	private void selectById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.valueOf(request.getParameter("id"));
		RoleInfo role = roledao.getRoleById(id);
		AdminInfo now_admin = (AdminInfo)request.getSession().getAttribute("now_admin");
		
		if(role.getId()==now_admin.getRoleId()) {
			System.out.println(now_admin);
			request.setAttribute("msg", "�����޸ĵ�ǰ��¼�û���Ȩ��");
			manage(request,response);
		}
		else {
			request.setAttribute("role",role);
			
			request.getRequestDispatcher("/perm/role_res.jsp").forward(request, response);
		}
	}
	//��ɫ����
	private void manage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<RoleInfo> list = roledao.getAllRoleInfo();
		request.setAttribute("list", list);
		request.getRequestDispatcher("/perm/role_manage.jsp").forward(request, response);
		
	}
	//���ӽ�ɫ
	private void roleAdd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String roleName = request.getParameter("roleName");
		String des = request.getParameter("des");
		RoleInfo role = new RoleInfo();
		role.setRoleName(roleName);
		role.setDes(des);
		int result = roledao.roleAdd(role);
		if(result == 1) {
			request.setAttribute("msg", "��ӳɹ�");
			request.getRequestDispatcher("/perm/role_add.jsp").forward(request, response);
		}
		else {
			request.setAttribute("msg", "���ʧ��");
			request.getRequestDispatcher("/perm/role_add.jsp").forward(request, response);
		}
		
	}
	//�޸��û���ɫ
	private void roleRes(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.valueOf(request.getParameter("id"));
		int roleId = Integer.valueOf(request.getParameter("roleId"));
		int result = 0;
		AdminInfo admin = admindao.getAdminById(id);
		request.setAttribute("admin", admin);
		if(roleId != -1) {
			result = roledao.resAdminRole(roleId, id);
		}
		else {
			request.setAttribute("msg", "��ѡ���û���ɫ");
			roleSet(request, response);
		}
		
		if(result == 1) {
			request.setAttribute("msg", "�޸ĳɹ�");
			roleSet(request, response);
		}
		else {
			request.setAttribute("msg", "�޸�ʧ��");
			roleSet(request, response);
		}
		
	}
	//��ɫ������
	private void selectByName(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String roleName = request.getParameter("roleName");
		String startName = request.getParameter("startName");
		RoleInfo role = roledao.getRoleByName(roleName);
		
		if(role!=null&&!roleName.equals(startName)) {
			response.getWriter().print("�ý�ɫ�Ѵ��ڣ�");
		}
		else {
			String regex="\\S{2,15}"; 
			if(roleName.matches(regex)) {
				response.getWriter().print("��");
			}
			else {
				response.getWriter().print("��ɫ����ʽ�Ƿ���");
			}
		}
	}
	//��ɫ����
	private void roleSet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.valueOf(request.getParameter("id"));
		AdminInfo admin = admindao.getAdminById(id);
		AdminInfo now_admin = (AdminInfo)request.getSession().getAttribute("now_admin");
		
		if(admin.getId()==now_admin.getRoleId()) {
			System.out.println(now_admin);
			request.setAttribute("msg", "���ܸ���ǰ��¼���û�����Ȩ��");
			listadmin(request,response);
		}
		else {
			List<RoleInfo> roles = roledao.getAllRoleInfo();
			request.setAttribute("admin", admin);
			request.setAttribute("roles", roles);
			request.getRequestDispatcher("/perm/role_authority.jsp").forward(request, response);
		
		}
	}
	//�û���ɫ����
	private void listadmin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<RoleInfo> list = roledao.getAllAdminRole();
		request.setAttribute("list", list);
		request.getRequestDispatcher("/perm/role_listadmin.jsp").forward(request, response);
		
	}

}
