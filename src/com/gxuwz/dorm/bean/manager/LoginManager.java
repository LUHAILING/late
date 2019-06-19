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
	
	//宿管员登录
	public boolean managerLogin(String userid, String password)throws Exception{
		//定义登录判断的标志
		boolean flag = false;
		//定义SQL语句
		String sql = "select * from sys_manager where managerId = '"+userid+"' and password = '"+password+"'";
		
    	//执行SQL并返回ResultSet结果集
    	ResultSet rs = dbUtil.executeQuery(sql);
    			
    	//登录判断
    	if(rs.next()){
    		//登录成功
    		flag = true;
    		
    	}
		return flag;
	}
	
	//辅导员登录
    public boolean instructorLogin(String userid, String password)throws Exception{
    	//定义登录判断的标志
    	boolean flag = false;
    	//定义SQL语句
    	String sql = "select * from sys_instructor where instId = '"+userid+"' and password = '"+password+"' ";
    	
    	//执行SQL并返回ResultSet结果集
    	ResultSet rs = dbUtil.executeQuery(sql);
    	//登录判断
    	if(rs.next()){
    		//登录成功
    	    flag = true;
    	    
    	}
		return flag;
	}
	
	//系统管理员登录
	
    public boolean adminLogin(String userid, String password)throws Exception{
    	//定义登录判断的标志
    	boolean flag = false;
    	//定义SQL语句
    	String sql = "select * from sys_admin where userid = '"+userid+"' and password = '"+password+"' ";
    	//执行SQL并返回ResultSet结果集
    	ResultSet rs = dbUtil.executeQuery(sql);
    	//登录判断
    	if(rs.next()){
    		//登录成功
    	    flag = true;
     	}
		
		return flag;
	}
	

}
