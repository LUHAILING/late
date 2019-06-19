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

import com.gxuwz.dorm.bean.entity.SysDepartment;
import com.gxuwz.dorm.bean.manager.SysDepartmentManager;

public class SysDepartmentServlet extends HttpServlet {

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
		SysDepartment sysDepartment = new SysDepartment();
		sysDepartment.setDepId(req.getParameter("depId"));
		sysDepartment.setDepName(req.getParameter("depName"));
		
		SysDepartmentManager depManager = new SysDepartmentManager();
		depManager.addDepartment(sysDepartment);
		list(req, resp);
		
		}catch(Exception e){
			e.printStackTrace();
		}
		//proccess(req, resp, "/page/Department/dep_list.jsp");
	}
	
	//删除
	private void delete(HttpServletRequest req, HttpServletResponse resp)
			throws Exception{
		SysDepartment sysDepartment = new SysDepartment();
		sysDepartment.setDepId(req.getParameter("depId"));
		SysDepartmentManager depManager = new SysDepartmentManager();
		depManager.delDepartment(sysDepartment);
		list(req, resp);
		
		//proccess(req, resp, "/page/Department/dep_list.jsp");
	}
	
	//修改
	private void edit(HttpServletRequest req, HttpServletResponse resp)
			throws Exception{
		SysDepartment sysDepartment = new SysDepartment();
		sysDepartment.setDepId(req.getParameter("depId"));
		sysDepartment.setDepName(req.getParameter("depName"));
		
		SysDepartmentManager depManager = new SysDepartmentManager();
		depManager.editDepartment(sysDepartment);
		list(req, resp);
		
		
	}
	
	//查询
	protected void search(HttpServletRequest req, HttpServletResponse resp)
			throws Exception{
		
		String keyword = req.getParameter("keywords");
		List<SysDepartment> dep_list = new ArrayList<SysDepartment>();
		SysDepartmentManager depManager = new SysDepartmentManager();
		dep_list = depManager.searchDepartment(keyword);
		req.setAttribute("dep_list", dep_list);			
		
		proccess(req, resp, "/page/department/department_list.jsp");
		
	}
	
	//显示列表
	protected void list(HttpServletRequest req,HttpServletResponse resp)throws Exception{
		   
			List<SysDepartment> dep_list = new ArrayList<SysDepartment>();
			SysDepartmentManager depManager = new SysDepartmentManager();
			dep_list = depManager.findAll();
			req.setAttribute("dep_list", dep_list);			
			
			proccess(req, resp, "/page/department/department_list.jsp");
			
	}

	
    private void proccess(HttpServletRequest req,HttpServletResponse resp,String path)throws ServletException,IOException{
		
		RequestDispatcher rd=req.getRequestDispatcher(path);
		rd.forward(req, resp);
		
	}



}
