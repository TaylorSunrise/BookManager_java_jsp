package com.neusoft.book.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;







import com.neusoft.book.dao.IMemberDAO;
import com.neusoft.book.entity.Item;
import com.neusoft.book.entity.Member;
import com.neusoft.util.AbstractDAOImpl;

public class IMemberDaoImpl extends AbstractDAOImpl implements IMemberDAO{

	public IMemberDaoImpl(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}

	 
	public boolean doCreate(Member vo) throws SQLException {
		// TODO Auto-generated method stub
		String sql="insert into member(mid,name,age,sex,phone,creditno,num) values(null,?,?,?,?,?,?)";
		pstmt=conn.prepareStatement(sql);
		pstmt.setString(1, vo.getName());
		pstmt.setInt(2, vo.getAge());;
		pstmt.setString(3, vo.getSex());
		pstmt.setString(4, vo.getPhone());
		pstmt.setInt(5, vo.getCreditno());
		pstmt.setInt(6, vo.getNum());
		return pstmt.executeUpdate()>0;
	}

	 
	public boolean doUpdate(Member vo) throws SQLException {
		// TODO Auto-generated method stub
		String sql = "UPDATE member SET name=?,age=?,sex=?,phone=?,creditno=?,num=? WHERE mid=?";
        super.pstmt = super.conn.prepareStatement(sql);
        super.pstmt.setString(1, vo.getName());
        super.pstmt.setInt(2, vo.getAge());
        super.pstmt.setString(3, vo.getSex());
        super.pstmt.setString(4, vo.getPhone());
        super.pstmt.setInt(5, vo.getCreditno());
        super.pstmt.setInt(6, vo.getNum());
        super.pstmt.setInt(7, vo.getMid());
        return super.pstmt.executeUpdate() > 0;
	}

	 
	public boolean doRemove(Set<?> ids) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	 
	public List<Member>  FindById(int id) throws SQLException {
		// TODO Auto-generated method stub
		List<Member> all = new ArrayList<Member>();
		Member vo=null;
		String sql="select mid,name,age,sex,phone,creditno,num from member where mid=?";
		pstmt=conn.prepareStatement(sql);
		pstmt.setInt(1, id);
		ResultSet rs=pstmt.executeQuery();
		if(rs.next())
		 {
			vo=new Member();
			vo.setMid(rs.getInt(1));
			vo.setName(rs.getString(2));
			vo.setAge(rs.getInt(3));
			vo.setSex(rs.getString(4));
			vo.setPhone(rs.getString(5));
			vo.setCreditno(rs.getInt(6));
			vo.setNum(rs.getInt(7));
			all.add(vo);
		 }
		return all;
	}

	 
	public List<Member> findAll() throws SQLException {
		List<Member> all = new ArrayList<Member>();
        String sql = "SELECT mid,name,age,sex,phone,creditno,num FROM member";
        super.pstmt = super.conn.prepareStatement(sql);
        ResultSet rs = super.pstmt.executeQuery();
        while (rs.next()){
            Member vo=new Member();
            vo.setMid(rs.getInt(1));
            vo.setName(rs.getString(2));
            vo.setAge(rs.getInt(3));
            vo.setSex(rs.getString(4));
            vo.setPhone(rs.getString(5));
            vo.setCreditno(rs.getInt(6));
            vo.setNum(rs.getInt(7));
            all.add(vo);
        }
        return all;
	}

	 
	public List<Member> findBySplit(String column, String KeyWord,
			Integer currentPage, Integer lineSize) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	 
	public Integer getAllCount(String column, String keyWord)
			throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	 
	public boolean doRemove(Integer id) throws SQLException {
		// TODO Auto-generated method stub
		String sql = "delete FROM member where mid=?";
		pstmt=conn.prepareStatement(sql);
		pstmt.setInt(1, id);
		return pstmt.executeUpdate()>0;
	}

	 
	public Member findById(Integer id) throws SQLException {
		// TODO Auto-generated method stub
		Member vo=null;
		String sql="select mid,name,age,sex,phone,creditno,num from member where mid=?";
		pstmt=conn.prepareStatement(sql);
		pstmt.setInt(1, id);
		ResultSet rs=pstmt.executeQuery();
        while (rs.next()){
            vo=new Member();
            vo.setMid(rs.getInt(1));
            vo.setName(rs.getString(2));
            vo.setAge(rs.getInt(3));
            vo.setSex(rs.getString(4));
            vo.setPhone(rs.getString(5));
            vo.setCreditno(rs.getInt(6));
            vo.setNum(rs.getInt(7));
            return vo;
        }
		return null;
	}



}
