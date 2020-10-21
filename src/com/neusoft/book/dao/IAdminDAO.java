package com.neusoft.book.dao;

import java.sql.SQLException;

import com.neusoft.book.entity.Admin;

public interface IAdminDAO extends IDAO<Integer, Admin> {
    
	public boolean findLogin(Admin vo) throws SQLException;
	public boolean updatetime(int aid) throws SQLException;
	public boolean doUpdateInfo(Admin vo) throws SQLException;
}
