package com.dao;

import java.util.List;

import com.beans.CateInfo;
import com.jdbc.DBUtil;

public class CateDao {
	//������Ʒ����
	public int cateAdd(CateInfo cate) {
		String sql="insert into cateinfo(cateName,des,parentId) values(?,?,?)";
		return DBUtil.update(sql, cate.getCateName(), cate.getDes(),cate.getParentId());
	}
	//ͨ�����������ҷ�����Ϣ
	public CateInfo getCateByName(String cateName) {
		String sql="select * from cateinfo where cateName=?";
		return DBUtil.getSingleObject(sql, CateInfo.class, cateName);
	}
	
	//ͨ��id��ѯ������Ϣ
	public CateInfo getCateById(int id) {
		String sql="select * from cateinfo where id=?";
		return DBUtil.getSingleObject(sql, CateInfo.class, id);
	}
	//ͨ������id��ѯ������Ϣ
	public List<CateInfo> getCateInfoByParentId(int parentId){
		List<CateInfo> cateList=DBUtil.getList("select * from cateinfo where parentId=?", CateInfo.class, parentId);
		for(CateInfo c: cateList) {
			if(c.getParentId()==0){
				c.setSubCateList(getCateInfoByParentId(c.getId()));
			}
		}
		return cateList;
	}
	//ɾ��������Ϣ
	public int cateDel(int id) {
		String sql="delete from cateinfo where id = ?";
		return DBUtil.update(sql,id);
	}
	//�޸ķ�����Ϣ
	public int cateRes(CateInfo cate) {
		String sql="update cateinfo set cateName=?, des=?,parentId=? where id=?";
		return DBUtil.update(sql,cate.getCateName(),cate.getDes(),cate.getParentId(),cate.getId());
	}
	
}
