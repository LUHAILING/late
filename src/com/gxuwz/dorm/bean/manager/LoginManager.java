package com.gxuwz.dorm.bean.manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


import com.gxuwz.dorm.bean.datebase.DbUtil;

public class LoginManager {
	private DbUtil dbUtil = new DbUtil();
	//HttpServletRequest request = null;
	//HttpSession session = request.getSession();
	
	//�޹�Ա��¼
	public boolean managerLogin(String userid, String password)throws Exception{
		//�����¼�жϵı�־
		boolean flag = false;
		//����SQL���
		String sql = "select * from sys_manager where managerId = '"+userid+"' and password = '"+password+"'";
		
    	//ִ��SQL������ResultSet�����
    	ResultSet rs = dbUtil.executeQuery(sql);
    			
    	//��¼�ж�
    	if(rs.next()){
    		//��¼�ɹ�
    		flag = true;
    		
    	}
		return flag;
	}
	
	//����Ա��¼
    public boolean instructorLogin(String userid, String password)throws Exception{
    	//�����¼�жϵı�־
    	boolean flag = false;
    	//����SQL���
    	String sql = "select * from sys_instructor where instId = '"+userid+"' and password = '"+password+"' ";
    	
    	//ִ��SQL������ResultSet�����
    	ResultSet rs = dbUtil.executeQuery(sql);
    	//��¼�ж�
    	if(rs.next()){
    		//��¼�ɹ�
    	    flag = true;
    	    
    	}
		return flag;
	}
	
	//ϵͳ����Ա��¼
	
    public boolean adminLogin(String userid, String password)throws Exception{
    	//�����¼�жϵı�־
    	boolean flag = false;
    	//����SQL���
    	String sql = "select * from sys_admin where userid = '"+userid+"' and password = '"+password+"' ";
    	//ִ��SQL������ResultSet�����
    	ResultSet rs = dbUtil.executeQuery(sql);
    	//��¼�ж�
    	if(rs.next()){
    		//��¼�ɹ�
    	    flag = true;
     	}
		
		return flag;
	}
	

}
