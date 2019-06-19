package com.gxuwz.dorm.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.annotation.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.gxuwz.dorm.bean.manager.LoginManager;

public class LoginServlet extends HttpServlet {

	
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		this.doPost(req, resp);
	}

	
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String m = req.getParameter("m");
		
		if("do".equals(m)){
			try{
				dologin(req, resp);
			}catch(Exception e){
				e.printStackTrace();
			}
		}else if("out".equals(m)){
			try{
				logout(req, resp);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
	}
	
	//登录
	private void dologin(HttpServletRequest req, HttpServletResponse resp) throws Exception{
		//接受页面传过来的参数
		String userid = req.getParameter("userid");
		String password = req.getParameter("password");
		String identype = req.getParameter("identype");
		// 将userid存入session对象
		HttpSession session= req.getSession();
		session.setAttribute("userid",userid);
		
		
		LoginManager loginManager = new  LoginManager();
		try{
			switch(Integer.parseInt(identype)){
			case 0:   //宿管员
				if(loginManager.managerLogin(userid, password)){
					proccess(req, resp, "/manager_index.jsp");
				}else{
					proccess(req, resp, "/login.jsp");
				}
			case 1:  //辅导员
				if(loginManager.instructorLogin(userid, password)){
					proccess(req, resp, "/inst_index.jsp");
				}else{
					proccess(req, resp, "/login.jsp");
				}
			case 2:  //系统管理员
				if(loginManager.adminLogin(userid, password)){
					proccess(req, resp, "/admin_index.jsp");
				}else{
					proccess(req, resp, "/login.jsp");
				}
			}
			
		}catch(Exception e){
			
		}
	}
	
	//退出登录
	private void  logout(HttpServletRequest req, HttpServletResponse resp) throws Exception{
		
		HttpSession session=req.getSession();
	    session.invalidate();
	    proccess(req, resp, "/login.jsp");
			
		
	}
	

	//页面跳转
	
    private void proccess(HttpServletRequest req,HttpServletResponse resp,String path)throws ServletException,IOException{
		
		RequestDispatcher rd=req.getRequestDispatcher(path);
		rd.forward(req, resp);
		
	}

}
