package com.dao;

import java.util.List;

import com.beans.MemberInfo;
import com.jdbc.DBUtil;
import com.utils.OtherUtil;
import com.utils.PageInfo;

public class MemberDao {
	//��������ѯ�û���Ϣ
	public List<MemberInfo> getAllMember(String findMemberNo,String startTime,String endTime, PageInfo page){
		String sql = "select * from MemberInfo where 1=1 ";
		if(!OtherUtil.StrIsNullOrEmpty(findMemberNo)) {
			sql += " and MemberNo like '%" + findMemberNo + "%' ";
		}
		if(!OtherUtil.StrIsNullOrEmpty(startTime)) {
			sql += " and registerDate > '" + startTime + "' ";
		}
		if(!OtherUtil.StrIsNullOrEmpty(endTime)) {
			sql += " and registerDate < '" + endTime + "' ";
		}
		sql += " limit ?,?";
		return DBUtil.getList(sql, MemberInfo.class,page.getBeginRow(),page.getPageSize());
		
	}
	//��ȡ�ض��������û�����
	public int getAllMemberCount(String findMemberNo,String startTime,String endTime){
		String sql = "select count(*) from MemberInfo where 1=1 ";
		if(!OtherUtil.StrIsNullOrEmpty(findMemberNo)) {
			sql += "and MemberNo like '%" + findMemberNo + "%' ";
		}
		if(!OtherUtil.StrIsNullOrEmpty(startTime)) {
			sql += "and registerDate > '" + startTime + "' ";
		}
		if(!OtherUtil.StrIsNullOrEmpty(endTime)) {
			sql += "and registerDate < '" + endTime + "' "; 
		}
		long count = DBUtil.getScalar(sql);
		return Integer.valueOf(count+"");
	}
	//ɾ���û�
	public int memberDel(int id) {
		String sql = "delete from MemberInfo where id = ?";
		return DBUtil.update(sql, id);
	}
	//ͨ��id��ȡ�û���Ϣ
	public MemberInfo getMemberById(int id) {
		String sql = "select * from MemberInfo where id = ?";
		return DBUtil.getSingleObject(sql, MemberInfo.class, id);
	}
	
	
}
