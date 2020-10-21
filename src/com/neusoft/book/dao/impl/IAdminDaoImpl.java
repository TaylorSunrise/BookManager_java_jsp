package com.neusoft.book.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import com.neusoft.book.dao.IAdminDAO;
import com.neusoft.book.entity.Admin;
import com.neusoft.book.entity.Item;
import com.neusoft.util.AbstractDAOImpl;

public class IAdminDaoImpl extends AbstractDAOImpl implements IAdminDAO  {

	public IAdminDaoImpl(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}
   
   /**
    *   成功返回true，并且返回最后一次登录的日期
    */
	public boolean findLogin(Admin vo) throws SQLException {
		// TODO Auto-generated method stub
		    boolean flag = false;
	        String sql = "SELECT aid,lastdate,flag FROM admin WHERE username=? AND password=? AND status=1";
	        super.pstmt = super.conn.prepareStatement(sql);
	        super.pstmt.setString(1,vo.getUsername());
	        super.pstmt.setString(2,vo.getPassword());
	        ResultSet rs = super.pstmt.executeQuery();
	        if (rs.next()){
	            flag = true;
	            vo.setAid(rs.getInt(1));
	            vo.setLastdate(rs.getTimestamp(2));
	            vo.setFlag(rs.getInt(3)); 
	        }
	        return flag;
	}

	public boolean doCreate(Admin vo) throws SQLException {
		// TODO Auto-generated method stub
		    String sql = "INSERT INTO admin(aid,username,password,sex,phone,email,flag,status,lastdate,remarks)"
		    		+ " VALUES(null,?,?,?,?,?,?,?,?,?)";
	        super.pstmt = super.conn.prepareStatement(sql);
	        super.pstmt.setString(1,vo.getUsername());
	        super.pstmt.setString(2,vo.getPassword());
	        super.pstmt.setString(3,vo.getSex());
	        super.pstmt.setString(4,vo.getPhone());
	        super.pstmt.setString(5,vo.getEmail());
	        super.pstmt.setInt(6,vo.getFlag());
	        super.pstmt.setInt(7,vo.getStatus());
	        String current = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format( new Date());  
	        Timestamp time = Timestamp.valueOf(current); 
	        super.pstmt.setTimestamp(8, time);
	        super.pstmt.setString(9,vo.getRemarks());
	        return super.pstmt.executeUpdate() > 0;
	}
    
	public boolean doRemove(Set<?> ids) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean doUpdateInfo(Admin vo) throws SQLException {
		String sql = "UPDATE admin SET sex=?,phone=?,email=? WHERE aid=?";
        super.pstmt = super.conn.prepareStatement(sql);
        super.pstmt.setString(1, vo.getSex());
        super.pstmt.setString(2, vo.getPhone());
        super.pstmt.setString(3, vo.getEmail());
        super.pstmt.setInt(4, vo.getAid());
        return super.pstmt.executeUpdate() > 0;
	}
	public boolean doUpdateStatus(Admin vo) throws SQLException {
		String sql = "UPDATE admin SET status=?  WHERE aid=?";
        super.pstmt = super.conn.prepareStatement(sql);
        super.pstmt.setInt(1, vo.getStatus());
        super.pstmt.setInt(2, vo.getAid());
        return super.pstmt.executeUpdate() > 0;
	}

	public List<Admin> findAll() throws SQLException {
		// TODO Auto-generated method stub
		List<Admin> all=new ArrayList<Admin>();
		String sql="select aid,username,password,sex,phone,email,flag,status,lastdate,remarks from admin";
		pstmt=conn.prepareStatement(sql);
		ResultSet rs = super.pstmt.executeQuery();
        while (rs.next()){
           Admin vo = new Admin();
           vo.setAid(rs.getInt(1));
           vo.setUsername(rs.getString(2));
           vo.setPassword(rs.getString(3));
           vo.setSex(rs.getString(4));
           vo.setPhone(rs.getString(5));
           vo.setEmail(rs.getString(6));
           vo.setFlag((rs.getInt(7)));
           vo.setStatus((rs.getInt(8)));
           vo.setLastdate(rs.getTimestamp(9));
           vo.setRemarks(rs.getString(10));
           all.add(vo);
        }
        return all;
	}


	public List<Admin> findBySplit(String column, String KeyWord,
			Integer currentPage, Integer lineSize) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	public Integer getAllCount(String column, String keyWord)
			throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean updatetime(int aid) throws SQLException {
		 String sql = "UPDATE admin SET lastdate=? WHERE aid=?";
	        super.pstmt = super.conn.prepareStatement(sql);
	        // 登录成功后直接使用当前日期为最后一次登录日期
	        String current = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format( new Date());  
	        Timestamp time = Timestamp.valueOf(current); 
//	        super.pstmt.setTimestamp(1,new Timestamp(new Date().getYear(), new Date().getMonth(),  
//	        		new Date().getDay(), new Date().getHours(),  
//	        		new Date().getMinutes(), new Date().getSeconds(), 0));
	        //pstmt.setTimestamp(1, new Timestamp(System.currentTimeMillis()));
	        pstmt.setTimestamp(1, time);
	        super.pstmt.setInt(2, aid);
	        return super.pstmt.executeUpdate() > 0;
	}



	@Override
	public boolean doRemove(Integer id) throws SQLException {
		// TODO Auto-generated method stub
		String sql = "delete FROM admin where aid=?";
		pstmt=conn.prepareStatement(sql);
		pstmt.setInt(1, id);
		return pstmt.executeUpdate()>0;
	}

	@Override
	public Admin findById(Integer id) throws SQLException {
		// TODO Auto-generated method stub
				Admin vo = null;
		        String sql = "SELECT aid,username,password,sex,phone,email,flag,status,lastdate,remarks  FROM admin WHERE aid=?";
		        super.pstmt = super.conn.prepareStatement(sql);
		        super.pstmt.setInt(1,id);
		        ResultSet rs = super.pstmt.executeQuery();
		        if (rs.next()){
		            vo = new Admin();
		            vo.setAid(rs.getInt(1));
		            vo.setUsername(rs.getString(2));
		            vo.setPassword(rs.getString(3));
		            vo.setSex(rs.getString(4));
		            vo.setPhone(rs.getString(5));
		            vo.setEmail(rs.getString(6));
		            vo.setFlag((rs.getInt(7)));
		            vo.setStatus((rs.getInt(8)));
		            vo.setLastdate(rs.getTimestamp(9));
		            vo.setRemarks(rs.getString(10));
		        }
		        return vo;
	}

	@Override
	public boolean doUpdate(Admin vo) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

}
