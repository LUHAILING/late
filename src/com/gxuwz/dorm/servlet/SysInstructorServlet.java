package com.gxuwz.dorm.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gxuwz.dorm.bean.entity.SysInstructor;
import com.gxuwz.dorm.bean.manager.SysInstructorManager;

public class SysInstructorServlet extends HttpServlet {

	@Override	
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		this.doPost(req, resp);
	}

	
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String m = req.getParameter("m");
		
		if("add".equals(m)){
            try{
            	add(req, resp);
			}catch(Exception e){
				e.printStackTrace();
			}
						
		}else if("delete".equals(m)){
			try{
				delete(req, resp);
			}catch(Exception e){
				e.printStackTrace();
			}
			
		}else if("edit".equals(m)){
            try{
            	edit(req, resp);
			}catch(Exception e){
				e.printStackTrace();
			}
			
		}else if("info".equals(m)){
            try{
            	info(req, resp);
			}catch(Exception e){
				e.printStackTrace();
			}
			
		}else if("pwd".equals(m)){
            try{
            	pwd(req, resp);
			}catch(Exception e){
				e.printStackTrace();
			}
			
		}else if("search".equals(m)){
            try{
            	search(req, resp);
			}catch(Exception e){
				e.printStackTrace();
			}
			
		}else if("list".equals(m)){
            try{
				list(req, resp);
			}catch(Exception e){
				e.printStackTrace();
			}
								
		}
			
	}
	
	//添加
	private void add(HttpServletRequest req, HttpServletResponse resp)
			throws Exception{
		try{
		SysInstructor sysInstructor = new SysInstructor();
		sysInstructor.setInstId(req.getParameter("instId"));
		sysInstructor.setInstName(req.getParameter("instName"));
		sysInstructor.setDepId(req.getParameter("depId"));
		sysInstructor.setSex(req.getParameter("sex"));
		sysInstructor.setTelephone(req.getParameter("telephone"));
		sysInstructor.setPassword(req.getParameter("password"));
		SysInstructorManager instManager = new SysInstructorManager();
		instManager.addInstructor(sysInstructor);
		list(req, resp);
		
		}catch(Exception e){
			e.printStackTrace();
		}
		//proccess(req, resp, "/page/Instructor/Instructor_list.jsp");
	}
	
	//删除
	private void delete(HttpServletRequest req, HttpServletResponse resp)
			throws Exception{
		SysInstructor sysInstructor = new SysInstructor();
		sysInstructor.setInstId(req.getParameter("instId"));
		SysInstructorManager instManager = new SysInstructorManager();
		instManager.delInstructor(sysInstructor);
		list(req, resp);
		
		//proccess(req, resp, "/page/Instructor/Instructor_list.jsp");
	}
	
	//修改
	private void edit(HttpServletRequest req, HttpServletResponse resp)
			throws Exception{
		SysInstructor sysInstructor = new SysInstructor();
		sysInstructor.setInstId(req.getParameter("instId"));
		sysInstructor.setInstName(req.getParameter("instName"));
		sysInstructor.setDepId(req.getParameter("depId"));
		sysInstructor.setSex(req.getParameter("sex"));
		sysInstructor.setTelephone(req.getParameter("telephone"));
		sysInstructor.setPassword(req.getParameter("password"));
		SysInstructorManager instManager = new SysInstructorManager();
		instManager.editInstructor(sysInstructor);
		list(req, resp);
		
	}
	
	//编辑个人信息
	private void info(HttpServletRequest req, HttpServletResponse resp)
			throws Exception{
		SysInstructor sysInstructor = new SysInstructor();
		sysInstructor.setInstId(req.getParameter("instId"));
		sysInstructor.setInstName(req.getParameter("instName"));
		sysInstructor.setDepId(req.getParameter("depId"));
		sysInstructor.setSex(req.getParameter("sex"));
		sysInstructor.setTelephone(req.getParameter("telephone"));
		
		SysInstructorManager instManager = new SysInstructorManager();
		instManager.infoInstructor(sysInstructor);
		
		proccess(req, resp, "/inst_index.jsp");
		
	}
	
	//修改密码
	private void pwd(HttpServletRequest req, HttpServletResponse resp)
			throws Exception{
		
		String mpass = req.getParameter("mpass");
		String newpass = req.getParameter("newpass");
		String renewpass = req.getParameter("renewpass");
		
		if(newpass.equalsIgnoreCase(renewpass)){
			
			SysInstructor sysInstructor = new SysInstructor();
			sysInstructor.setPassword(req.getParameter("newpass"));
			
			SysInstructorManager instManager = new SysInstructorManager();
			instManager.pwdInstructor(sysInstructor);
			
			proccess(req, resp, "/login.jsp");
		}
	
		
	}
	
	//查询
	protected void search(HttpServletRequest req, HttpServletResponse resp)
			throws Exception{
		
		String keyword = req.getParameter("keywords");
		List<SysInstructor> inst_list = new ArrayList<SysInstructor>();
		SysInstructorManager instManager = new SysInstructorManager();
		inst_list = instManager.searchInstructor(keyword);
		req.setAttribute("inst_list", inst_list);			
		
		proccess(req, resp, "/page/instructor/instructor_list.jsp");
		
	}
	
	//显示列表
	protected void list(HttpServletRequest req,HttpServletResponse resp)throws Exception{
		   
			List<SysInstructor> inst_list = new ArrayList<SysInstructor>();
			SysInstructorManager instManager = new SysInstructorManager();
			inst_list = instManager.findAll();
			req.setAttribute("inst_list", inst_list);			
			
			proccess(req, resp, "/page/instructor/instructor_list.jsp");
			
	}

	
    private void proccess(HttpServletRequest req,HttpServletResponse resp,String path)throws ServletException,IOException{
		
		RequestDispatcher rd=req.getRequestDispatcher(path);
		rd.forward(req, resp);
		
	}

}
