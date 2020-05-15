package com.beans;

import java.util.List;

public class CateInfo {
	private int id;
	private String cateName;//分类名
	private String des;//分类描述
	private int parentId;//父级id
	
	private List<CateInfo> subCateList; //一级分类下的二级分类
	@Override
	public String toString() {
		return "CateInfo [id=" + id + ", cateName=" + cateName + ", des=" + des + ", parentId=" + parentId + ", subCateList="
				+ subCateList + "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCateName() {
		return cateName;
	}

	public void setCateName(String cateName) {
		this.cateName = cateName;
	}

	public String getDes() {
		return des;
	}

	public void setDes(String des) {
		this.des = des;
	}

	public int getParentId() {
		return parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

	public List<CateInfo> getSubCateList() {
		return subCateList;
	}

	public void setSubCateList(List<CateInfo> subCateList) {
		this.subCateList = subCateList;
	}

	
}
