package com.beans;

import java.io.Serializable;
import java.util.Date;

public class AdminInfo implements Serializable{
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String adminName;
	private String password;
	private String note; 
	private String state; //状态 0暂停 1正常 2禁用
	private Date editDate; //更新时间
	private Integer roleId; //角色ID，关联角色表RoleInfo
	
	private String roleName; //角色名称，关联查询角色信息

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAdminName() {
		return adminName;
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
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

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	@Override
	public String toString() {
		return "AdminInfo [id=" + id + ", adminName=" + adminName + ", password=" + password + ", note=" + note
				+ ", state=" + state + ", editDate=" + editDate + ", roleId=" + roleId + ", roleName=" + roleName + "]";
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	
	
}
