package com.dao;

import java.util.List;

import com.beans.CateInfo;
import com.jdbc.DBUtil;

public class CateDao {
	//增加商品分类
	public int cateAdd(CateInfo cate) {
		String sql="insert into cateinfo(cateName,des,parentId) values(?,?,?)";
		return DBUtil.update(sql, cate.getCateName(), cate.getDes(),cate.getParentId());
	}
	//通过分类名查找分类信息
	public CateInfo getCateByName(String cateName) {
		String sql="select * from cateinfo where cateName=?";
		return DBUtil.getSingleObject(sql, CateInfo.class, cateName);
	}
	
	//通过id查询分类信息
	public CateInfo getCateById(int id) {
		String sql="select * from cateinfo where id=?";
		return DBUtil.getSingleObject(sql, CateInfo.class, id);
	}
	//通过父级id查询分类信息
	public List<CateInfo> getCateInfoByParentId(int parentId){
		List<CateInfo> cateList=DBUtil.getList("select * from cateinfo where parentId=?", CateInfo.class, parentId);
		for(CateInfo c: cateList) {
			if(c.getParentId()==0){
				c.setSubCateList(getCateInfoByParentId(c.getId()));
			}
		}
		return cateList;
	}
	//删除分类信息
	public int cateDel(int id) {
		String sql="delete from cateinfo where id = ?";
		return DBUtil.update(sql,id);
	}
	//修改分类信息
	public int cateRes(CateInfo cate) {
		String sql="update cateinfo set cateName=?, des=?,parentId=? where id=?";
		return DBUtil.update(sql,cate.getCateName(),cate.getDes(),cate.getParentId(),cate.getId());
	}
	
}
