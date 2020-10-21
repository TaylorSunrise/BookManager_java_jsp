package com.neusoft.book.entity;

import java.util.Date;

public class LenBook {
//	String sql="insert into lenbook(bid,mid,credate,retday,retstatus,creditno) values(?,?,?,?,?,?)";
   private Integer leid;
   private Books books;//图书
   private Member member;//用户
   private Date credate;//借书起始时间
   private Date retdate;//归还图书时间
   private int retday;//借书天数
   private int creditno;//不守信誉次数
   private String remarks;//备注
   public String getRemarks() {
	return remarks;
}
public void setRemarks(String remarks) {
	this.remarks = remarks;
}
public int getCreditno() {
	return creditno;
}
public void setCreditno(int creditno) {
	this.creditno = creditno;
}
private String retstatus;
public String getRetstatus() {
	return retstatus;
}
public void setRetstatus(String retstatus) {
	this.retstatus = retstatus;
}
public int getRetday() {
	return retday;
}
public void setRetday(int retday) {
	this.retday = retday;
}

@Override
public String toString() {
	return "LenBook [leid=" + leid + ", books=" + books + ", member=" + member
			+ ", credate=" + credate + ", retdate=" + retdate + ", retday="
			+ retday + ", retstatus=" + retstatus + "]";
}
public Integer getLeid() {
	return leid;
}
public void setLeid(Integer leid) {
	this.leid = leid;
}
public Books getBooks() {
	return books;
}
public void setBooks(Books books) {
	this.books = books;
}
public Member getMember() {
	return member;
}
public void setMember(Member member) {
	this.member = member;
}
public Date getCredate() {
	return credate;
}
public void setCredate(Date credate) {
	this.credate = credate;
}
public Date getRetdate() {
	return retdate;
}
public void setRetdate(Date retdate) {
	this.retdate = retdate;
}
   
}
