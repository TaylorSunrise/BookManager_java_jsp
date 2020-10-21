package com.neusoft.book.service.impl;

import java.util.List;

import com.neusoft.book.dbc.DatabaseConnection;
import com.neusoft.book.entity.Admin;
import com.neusoft.book.entity.Member;
import com.neusoft.book.factory.DAOFactory;
import com.neusoft.book.service.IMemberService;

public class IMemberServiceImpl implements IMemberService {
	 DatabaseConnection dbc=new DatabaseConnection();
	 
	public boolean insert(Member vo) throws Exception {
		// TODO Auto-generated method stub
		try {
			if(new DAOFactory().getMemberDAOInstance(dbc.getconn()).findById(vo.getMid())==null)
			{    	
				return new DAOFactory().getMemberDAOInstance(dbc.getconn()).doCreate(vo);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}
		finally{
			dbc.close();
	    }
		return false;
	
	}

	public List<Member> list() throws Exception {
		// TODO Auto-generated method stub
		try {
			return new DAOFactory().getMemberDAOInstance(dbc.getconn()).findAll();
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}finally{
			
		}
	}
	public Member findMemberById(int id) throws Exception {
		try{   
	        return DAOFactory.getMemberDAOInstance(this.dbc.getconn()).findById(id);
	    }catch (Exception e){
	        throw e;
	    }finally {
	        this.dbc.close();
	    }
	}

	public List<Member> findById(int mid) throws Exception {
		// TODO Auto-generated method stub
		try {
			return new DAOFactory().getMemberDAOInstance(dbc.getconn()).FindById(mid);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	@Override
	public boolean delete(Integer id) throws Exception {
		// TODO Auto-generated method stub
		try {
			 return new DAOFactory().getMemberDAOInstance(dbc.getconn()).doRemove(id);
		} catch (Exception e) {
			// TODO: handle exception
			 throw e;
		}
		finally{
			 dbc.close();
		}
	}

	@Override
	public boolean doUpdateInfo(Member vo) throws Exception {
		// TODO Auto-generated method stub
		try {
			 return new DAOFactory().getMemberDAOInstance(dbc.getconn()).doUpdate(vo);
		} catch (Exception e) {
			// TODO: handle exception
			 throw e;
		}
		finally{
			 dbc.close();
		}
	}

}
