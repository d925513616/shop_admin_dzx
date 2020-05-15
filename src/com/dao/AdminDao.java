package com.dao;
import java.util.List;

import com.beans.AdminInfo;
import com.jdbc.DBUtil;

import com.utils.PageInfo;


public class AdminDao {
	//分页查询用户信息
	public List<AdminInfo> getAdminInfos(PageInfo page){
		
		String sql = "select * from admininfo where state != '0' limit ?,?";
		return DBUtil.getList(sql, AdminInfo.class, page.getBeginRow(),page.getPageSize());
		
	}
	//登录
	public AdminInfo login(String adminName, String password){
		String sql = "select * from admininfo where adminName=? and password=?";
		return  (AdminInfo) DBUtil.getSingleObject(sql, AdminInfo.class,adminName,password);
	}
	//用户添加
	public int adminAdd(AdminInfo admin) {
		String sql = "insert into admininfo(adminName,password,note,state) values(?,?,?,?)";
		return  DBUtil.update(sql, admin.getAdminName(),admin.getPassword(),admin.getNote(),admin.getState());
	}
	//获取总用户数
	public int getAdminCount(){
		String sql="select count(*) from admininfo where state != '0' ";
		long count= DBUtil.getScalar(sql) ; 
		return Integer.parseInt(count+"");
		
	}
	//修改用户状态
	public int adminResState(String state, int id) {
		String sql = "update admininfo set state = ? where id = ?";
		return DBUtil.update(sql, state,id);
	}
	//通过用户名查找用户
	public AdminInfo getAdminByName(String adminName) {
		String sql = "select * from admininfo where adminName = ?";
		return DBUtil.getSingleObject(sql, AdminInfo.class, adminName);
	}
	//通过id查找用户
	public AdminInfo getAdminById(int id) {
		String sql = "select * from admininfo where id = ?";
		return DBUtil.getSingleObject(sql, AdminInfo.class, id);
	}
	//修改用户信息
	public int adminRes(AdminInfo admin) {
		String sql = "update admininfo set adminName=? ,password=? ,note=?,roleId=? where id =?";

		return DBUtil.update(sql, admin.getAdminName(),admin.getPassword(),admin.getNote(),admin.getRoleId(),admin.getId());
		
	}
	//修改密码
	public int pswEditById(String password,int id) {
		String sql = "update admininfo set password = ? where id = ?";
		return DBUtil.update(sql, password,id);
	}
	
	
}
	