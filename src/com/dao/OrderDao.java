package com.dao;

import java.util.List;

import com.beans.OrderInfo;
import com.jdbc.DBUtil;
import com.utils.OtherUtil;
import com.utils.PageInfo;

public class OrderDao {
	//��������ѯ������Ϣ
	public List<OrderInfo> getAllOrder(String orderNo,String startTime,String endTime, String findOrderState, PageInfo page){
		String sql = "select * from OrderInfo where 1=1 ";
		if(!OtherUtil.StrIsNullOrEmpty(orderNo)) {
			sql += " and orderNo like '%" + orderNo + "%' ";
		}
		if(!OtherUtil.StrIsNullOrEmpty(startTime)) {
			sql += " and orderDate > '" + startTime + "' ";
		}
		if(!OtherUtil.StrIsNullOrEmpty(endTime)) {
			sql += " and orderDate < '" + endTime + "' ";
		}
		if(!OtherUtil.StrIsNullOrEmpty(findOrderState)) {
			sql += " and OrderState = '" + findOrderState + "' ";
		}
		
		sql += " limit ?,?";
		return DBUtil.getList(sql, OrderInfo.class,page.getBeginRow(),page.getPageSize());
		
	}
	//��ȡ�ض������¶�������
	public int getAllOrderCount(String orderNo,String startTime,String endTime,String findOrderState){
		String sql = "select count(*) from OrderInfo where 1=1 ";
		if(!OtherUtil.StrIsNullOrEmpty(orderNo)) {
			sql += " and orderNo like '%" + orderNo + "%' ";
		}
		if(!OtherUtil.StrIsNullOrEmpty(startTime)) {
			sql += " and orderDate > '" + startTime + "' ";
		}
		if(!OtherUtil.StrIsNullOrEmpty(endTime)) {
			sql += " and orderDate < '" + endTime + "' ";
		}
		if(!OtherUtil.StrIsNullOrEmpty(findOrderState)) {
			sql += " and OrderState = '" + findOrderState + "' ";
		}
		
		long count = DBUtil.getScalar(sql);
		return Integer.valueOf(count+"");
		
	}
	//ͨ��id��ȡ������Ϣ
	public OrderInfo getOrderById(int id) {
		String sql = "select orderNo,postage,payMethod,postMethod,orderDate, " + 
				"	o.address,orderState,editDate,amount,memberName " + 
				"	from orderinfo o " +  
				"	left join memberinfo m on memberId=m.id " + 
				"	where o.id = ?;";
		return DBUtil.getSingleObject(sql, OrderInfo.class, id);
	}
	//���ݶ���id��ȡȫ������������Ϣ
	public List<OrderInfo> getOrderGoodsById(int id){
		String sql = "select goodsName,unit,price,saleCount from ordergoods where orderId=?";
		return DBUtil.getList(sql, OrderInfo.class, id);
	}
	//��״̬��Ϊ����
	public int send(int id) {
		String sql = "update orderinfo set orderState=\"�ѷ���\" where id=?";
		return DBUtil.update(sql, id);
	}
	
}
