package com.beans;

import java.util.Date;

public class OrderInfo {
	private int id;
	private String orderNo;//������
	private int memberId;//�û�id
	private float postage;//�ʷ�
	private String postMethod;//�ʵݷ�ʽ
	private String payMethod;//֧����ʽ
	private Date orderDate;//������������
	private String address;//�ʼĵ�ַ
	private String orderState;//����״̬
	private float amount;//���
	private Date sendDate;
	private Date editDate;
	
	private String memberName;
	private String goodsName;
	private String unit;
	private float price;
	private int saleCount;
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public int getSaleCount() {
		return saleCount;
	}
	public void setSaleCount(int saleCount) {
		this.saleCount = saleCount;
	}
	
	@Override
	public String toString() {
		return "OrderInfo [id=" + id + ", orderNo=" + orderNo + ", memberId=" + memberId + ", postage=" + postage
				+ ", postMethod=" + postMethod + ", payMethod=" + payMethod + ", orderDate=" + orderDate + ", address="
				+ address + ", orderState=" + orderState + ", amount=" + amount + ", sendDate=" + sendDate
				+ ", editDate=" + editDate + ", memberName=" + memberName + ", goodsName=" + goodsName + ", unit="
				+ unit + ", price=" + price + ", saleCount=" + saleCount + "]";
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public int getMemberId() {
		return memberId;
	}
	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}
	public float getPostage() {
		return postage;
	}
	public void setPostage(float postage) {
		this.postage = postage;
	}
	public String getPostMethod() {
		return postMethod;
	}
	public void setPostMethod(String postMethod) {
		this.postMethod = postMethod;
	}
	public String getPayMethod() {
		return payMethod;
	}
	public void setPayMethod(String payMethod) {
		this.payMethod = payMethod;
	}
	public Date getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getOrderState() {
		return orderState;
	}
	public void setOrderState(String orderState) {
		this.orderState = orderState;
	}
	public float getAmount() {
		return amount;
	}
	public void setAmount(float amount) {
		this.amount = amount;
	}
	public Date getSendDate() {
		return sendDate;
	}
	public void setSendDate(Date sendDate) {
		this.sendDate = sendDate;
	}
	public Date getEditDate() {
		return editDate;
	}
	public void setEditDate(Date editDate) {
		this.editDate = editDate;
	}
	
}
