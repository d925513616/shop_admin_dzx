package com.dao;

import java.util.List;

import com.beans.AdminInfo;
import com.beans.RoleInfo;
import com.jdbc.DBUtil;


public class RoleDao {
	//增加角色
	public int roleAdd(RoleInfo role) {
		String sql = "insert into roleinfo(roleName,des) values(?,?)";
		return  DBUtil.update(sql, role.getRoleName(),role.getDes());
	}
	
	//获取全部用户角色
	public List<RoleInfo> getAllAdminRole(){
		String sql = "select a.id,adminName,state,roleName,editDate from admininfo a left join roleinfo r on r.id=a.roleid;";
		return DBUtil.getList(sql,RoleInfo.class);
	}
	//获取全部角色信息
	public List<RoleInfo> getAllRoleInfo(){
		String sql = "select * from roleinfo";
		
		return DBUtil.getList(sql, RoleInfo.class);
	}
	//修改用户角色
	public int resAdminRole(int roleid, int id){
		String sql = "update admininfo set roleId=? where id=?";
		
		return DBUtil.update(sql, roleid, id);
	}
	//通过角色名获取角色信息
	public RoleInfo getRoleByName(String roleName) {
		String sql = "select * from roleinfo where roleName=?";
		
		return DBUtil.getSingleObject(sql, RoleInfo.class, roleName);
	}
	//是否有用户正在使用此权限
	public int whetherAdminHaveThisRole(int roleId) {
		String sql="select count(*) from admininfo where roleId=?";
		return Integer.parseInt(DBUtil.getScalar(sql,roleId)+"");
	}
	//通过id获取角色信息
	public RoleInfo getRoleById(int id) {
		String sql = "select * from roleinfo where id=?";
		
		return DBUtil.getSingleObject(sql, RoleInfo.class, id);
	}
	//更新角色信息
	public int updateRoleInfo(String roleName, String des, int id) {
			String sql = "update roleinfo set roleName=? ,des=? where id=?";
		
		return DBUtil.update(sql, roleName,des,id);
	}
	//通过id删除角色
	public int delRoleInfoById(int id) {
		String sql = "delete from roleinfo where id=?";
		return DBUtil.update(sql,id);
	}
	//通过角色id删除角色菜单权限
	public int delAuthoryFromRolemenu(int roleid) {
		String sql = "delete from rolemenu where roleid=?";
		return DBUtil.update(sql,roleid);
	}
	//添加角色菜单权限
	public int addAuthoryOnRolemenu(int roleid,int menuId) {
		String sql = "insert into rolemenu(roleId,menuId) values(?,?)";
		return DBUtil.update(sql,roleid,menuId);
	}
	//通过角色id查询菜单权限
	public List<Integer> getMenuIdByRoleId(int roleid) {
		String sql = "select menuId from rolemenu where roleid=?";
		
		return DBUtil.getScalarList(sql, roleid);
	}
	
}
