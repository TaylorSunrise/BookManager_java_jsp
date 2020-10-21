package com.neusoft.book.entity;

import java.io.Serializable;
import java.util.Date;

public class Admin implements Serializable {
   private int aid;
   private String username;
   private String password;
   private String sex;
   private String phone;
   private String email;
   private String remarks;
   private Date lastdate;
   public int getAid() {
	return aid;
}
public void setAid(int aid) {
	this.aid = aid;
}
public String getUsername() {
	return username;
}
public void setUsername(String username) {
	this.username = username;
}
public String getSex() {
	return sex;
}
public void setSex(String sex) {
	this.sex = sex;
}
public String getPhone() {
	return phone;
}
public void setPhone(String phone) {
	this.phone = phone;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public String getRemarks() {
	return remarks;
}
public void setRemarks(String remarks) {
	this.remarks = remarks;
}
private Integer flag;
   private Integer status;// 激活状态

public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
public Date getLastdate() {
	return lastdate;
}
public void setLastdate(Date i) {
	this.lastdate = i;
}
public Integer getFlag() {
	return flag;
}
public void setFlag(Integer flag) {
	this.flag = flag;
}
public Integer getStatus() {
	return status;
}
public void setStatus(Integer status) {
	this.status = status;
}
public Admin() {
	super();
}
}
