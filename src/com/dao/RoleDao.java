package com.dao;

import java.util.List;

import com.beans.AdminInfo;
import com.beans.RoleInfo;
import com.jdbc.DBUtil;


public class RoleDao {
	//���ӽ�ɫ
	public int roleAdd(RoleInfo role) {
		String sql = "insert into roleinfo(roleName,des) values(?,?)";
		return  DBUtil.update(sql, role.getRoleName(),role.getDes());
	}
	
	//��ȡȫ���û���ɫ
	public List<RoleInfo> getAllAdminRole(){
		String sql = "select a.id,adminName,state,roleName,editDate from admininfo a left join roleinfo r on r.id=a.roleid;";
		return DBUtil.getList(sql,RoleInfo.class);
	}
	//��ȡȫ����ɫ��Ϣ
	public List<RoleInfo> getAllRoleInfo(){
		String sql = "select * from roleinfo";
		
		return DBUtil.getList(sql, RoleInfo.class);
	}
	//�޸��û���ɫ
	public int resAdminRole(int roleid, int id){
		String sql = "update admininfo set roleId=? where id=?";
		
		return DBUtil.update(sql, roleid, id);
	}
	//ͨ����ɫ����ȡ��ɫ��Ϣ
	public RoleInfo getRoleByName(String roleName) {
		String sql = "select * from roleinfo where roleName=?";
		
		return DBUtil.getSingleObject(sql, RoleInfo.class, roleName);
	}
	//�Ƿ����û�����ʹ�ô�Ȩ��
	public int whetherAdminHaveThisRole(int roleId) {
		String sql="select count(*) from admininfo where roleId=?";
		return Integer.parseInt(DBUtil.getScalar(sql,roleId)+"");
	}
	//ͨ��id��ȡ��ɫ��Ϣ
	public RoleInfo getRoleById(int id) {
		String sql = "select * from roleinfo where id=?";
		
		return DBUtil.getSingleObject(sql, RoleInfo.class, id);
	}
	//���½�ɫ��Ϣ
	public int updateRoleInfo(String roleName, String des, int id) {
			String sql = "update roleinfo set roleName=? ,des=? where id=?";
		
		return DBUtil.update(sql, roleName,des,id);
	}
	//ͨ��idɾ����ɫ
	public int delRoleInfoById(int id) {
		String sql = "delete from roleinfo where id=?";
		return DBUtil.update(sql,id);
	}
	//ͨ����ɫidɾ����ɫ�˵�Ȩ��
	public int delAuthoryFromRolemenu(int roleid) {
		String sql = "delete from rolemenu where roleid=?";
		return DBUtil.update(sql,roleid);
	}
	//��ӽ�ɫ�˵�Ȩ��
	public int addAuthoryOnRolemenu(int roleid,int menuId) {
		String sql = "insert into rolemenu(roleId,menuId) values(?,?)";
		return DBUtil.update(sql,roleid,menuId);
	}
	//ͨ����ɫid��ѯ�˵�Ȩ��
	public List<Integer> getMenuIdByRoleId(int roleid) {
		String sql = "select menuId from rolemenu where roleid=?";
		
		return DBUtil.getScalarList(sql, roleid);
	}
	
}
