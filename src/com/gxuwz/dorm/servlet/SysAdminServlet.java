package com.gxuwz.dorm.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gxuwz.dorm.bean.entity.SysAdmin;
import com.gxuwz.dorm.bean.manager.SysAdminManager;;

public class SysAdminServlet extends HttpServlet {

	@Override	
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		this.doPost(req, resp);
	}

	
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		try{
			SysAdmin sysAdmin = new SysAdmin();
			sysAdmin.setUserid(req.getParameter("userid"));
			sysAdmin.setFullname(req.getParameter("fullname"));
			sysAdmin.setTelephone(req.getParameter("telephone"));
			
			SysAdminManager adminManager = new SysAdminManager();
			adminManager.infoAdmin(sysAdmin);
			proccess(req, resp, "/admin_index.jsp");
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}

private void proccess(HttpServletRequest req,HttpServletResponse resp,String path)throws ServletException,IOException{
		
		RequestDispatcher rd=req.getRequestDispatcher(path);
		rd.forward(req, resp);
		
	}

}
