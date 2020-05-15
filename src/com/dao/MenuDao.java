package com.dao;

import java.util.List;

import com.beans.MenuInfo;
import com.jdbc.DBUtil;

public class MenuDao {
	//根据登录用户权限获取菜单列表
	public List<MenuInfo>getMenuList(int parentId,int roleId){
		List<MenuInfo> menuList=DBUtil.getList("select m.id,menuName,target,url,parentId,icon from menuInfo m join rolemenu on m.id=menuid where parentId=? and roleid = ?", MenuInfo.class, parentId,roleId);
		 
		for(MenuInfo m: menuList) {
			if(m.getParentId()==0) {
				
				m.setSubMenuList( getMenuList( m.getId(),roleId));
			}
		}
		return menuList;
	}
	//获取全部菜单信息
	public List<MenuInfo>getAllMenuList(int parentId){
		List<MenuInfo> menuList=DBUtil.getList("select * from menuInfo where parentId=?",MenuInfo.class,parentId);
		for(MenuInfo m: menuList) {
			if(m.getParentId()==0) {
				m.setSubMenuList( getAllMenuList( m.getId()));
			}
		}
		return menuList;
	}
}
