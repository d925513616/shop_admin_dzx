package com.beans;

import java.util.Date;

public class RoleInfo {

	private int id;
	private String roleName;//角色名
	private String des;//描述
	
	private String adminName;//角色对应的用户名
	private String state;//状态，来自admininfo
	private Date editDate;//修改时间，来自admininfo
	public String getAdminName() {
		return adminName;
	}
	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public Date getEditDate() {
		return editDate;
	}
	public void setEditDate(Date editDate) {
		this.editDate = editDate;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getDes() {
		return des;
	}
	public void setDes(String des) {
		this.des = des;
	}
	@Override
	public String toString() {
		return "RoleInfo [id=" + id + ", roleName=" + roleName + ", des=" + des + ", adminName=" + adminName
				+ ", state=" + state + ", editDate=" + editDate + "]";
	}
	
}
