package com.beans;

import java.util.List;

public class MenuInfo {
	private int id;
	private String menuName;//菜单名
	private String url;//地址
	private String target;//位置
	private String icon;//图标
	private int parentId;//父级id
	
	private List<MenuInfo> subMenuList;//所属子菜单
	private int roleId;

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public int getParentId() {
		return parentId;
	}

	public void setParentId(int parentid) {
		this.parentId = parentid;
	}

	public List<MenuInfo> getSubMenuList() {
		return subMenuList;
	}

	@Override
	public String toString() {
		return "MenuInfo [id=" + id + ", menuName=" + menuName + ", url=" + url + ", target=" + target + ", icon="
				+ icon + ", parentId=" + parentId + ", subMenuList=" + subMenuList + "]";
	}

	public void setSubMenuList(List<MenuInfo> submenu) {
		this.subMenuList = submenu;
	}
	
	
}

