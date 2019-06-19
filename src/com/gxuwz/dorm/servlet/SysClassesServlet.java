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

import com.gxuwz.dorm.bean.entity.SysClasses;
import com.gxuwz.dorm.bean.manager.SysClassesManager;

public class SysClassesServlet extends HttpServlet {

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
		SysClasses sysClasses = new SysClasses();
		sysClasses.setClassId(req.getParameter("classId"));
		sysClasses.setClassName(req.getParameter("className"));
		sysClasses.setDepId(req.getParameter("depId"));
		sysClasses.setMajor(req.getParameter("major"));
		sysClasses.setGrade(req.getParameter("grade"));
		SysClassesManager classManager = new SysClassesManager();
		classManager.addClasses(sysClasses);
		list(req, resp);
		
		}catch(Exception e){
			e.printStackTrace();
		}
		//proccess(req, resp, "/page/Classes/class_list.jsp");
	}
	
	//删除
	private void delete(HttpServletRequest req, HttpServletResponse resp)
			throws Exception{
		SysClasses sysClasses = new SysClasses();
		sysClasses.setClassId(req.getParameter("classId"));
		SysClassesManager classManager = new SysClassesManager();
		classManager.delClasses(sysClasses);
		list(req, resp);
		
		//proccess(req, resp, "/page/Classes/class_list.jsp");
	}
	
	//修改
	private void edit(HttpServletRequest req, HttpServletResponse resp)
			throws Exception{
		SysClasses sysClasses = new SysClasses();
		sysClasses.setClassId(req.getParameter("classId"));
		sysClasses.setClassName(req.getParameter("className"));
		sysClasses.setDepId(req.getParameter("depId"));
		sysClasses.setMajor(req.getParameter("major"));
		sysClasses.setGrade(req.getParameter("grade"));
		SysClassesManager classManager = new SysClassesManager();
		classManager.editClasses(sysClasses);
		list(req, resp);
		
		
	}
	
	//查询
	protected void search(HttpServletRequest req, HttpServletResponse resp)
			throws Exception{
		
		String keyword = req.getParameter("keywords");
		List<SysClasses> class_list = new ArrayList<SysClasses>();
		SysClassesManager classManager = new SysClassesManager();
		class_list = classManager.searchClasses(keyword);
		req.setAttribute("class_list", class_list);			
		
		proccess(req, resp, "/page/classes/classes_list.jsp");
		
	}
	
	//显示列表
	protected void list(HttpServletRequest req,HttpServletResponse resp)throws Exception{
		   
			List<SysClasses> class_list = new ArrayList<SysClasses>();
			SysClassesManager classManager = new SysClassesManager();
			class_list = classManager.findAll();
			req.setAttribute("class_list", class_list);			
			
			proccess(req, resp, "/page/classes/classes_list.jsp");
			
	}

	
    private void proccess(HttpServletRequest req,HttpServletResponse resp,String path)throws ServletException,IOException{
		
		RequestDispatcher rd=req.getRequestDispatcher(path);
		rd.forward(req, resp);
		
	}


}
