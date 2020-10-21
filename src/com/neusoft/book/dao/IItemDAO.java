package com.neusoft.book.dao;

import java.sql.SQLException;

import com.neusoft.book.entity.Item;

public interface IItemDAO extends IDAO<Integer, Item> {
	public boolean isExist(String categoryName) throws SQLException;
  
}
