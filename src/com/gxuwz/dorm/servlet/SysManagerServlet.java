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

import com.gxuwz.dorm.bean.entity.SysManager;
import com.gxuwz.dorm.bean.manager.SysMManager;

public class SysManagerServlet extends HttpServlet {

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
		SysManager sysManager = new SysManager();
		sysManager.setManagerId(req.getParameter("managerId"));
		sysManager.setManagerName(req.getParameter("managerName"));
		sysManager.setSex(req.getParameter("sex"));
		sysManager.setTelephone(req.getParameter("telephone"));
		sysManager.setPassword(req.getParameter("password"));
		SysMManager manager = new SysMManager();
		manager.addManager(sysManager);
		list(req, resp);
		
		}catch(Exception e){
			e.printStackTrace();
		}
		//proccess(req, resp, "/page/Manager/Manager_list.jsp");
	}
	
	//删除
	private void delete(HttpServletRequest req, HttpServletResponse resp)
			throws Exception{
		SysManager sysManager = new SysManager();
		sysManager.setManagerId(req.getParameter("managerId"));
		SysMManager manager = new SysMManager();
		manager.delManager(sysManager);
		list(req, resp);
		
		//proccess(req, resp, "/page/Manager/Manager_list.jsp");
	}
	
	//修改
	private void edit(HttpServletRequest req, HttpServletResponse resp)
			throws Exception{
		SysManager sysManager = new SysManager();
		sysManager.setManagerId(req.getParameter("managerId"));
		sysManager.setManagerName(req.getParameter("managerName"));
		sysManager.setSex(req.getParameter("sex"));
		sysManager.setTelephone(req.getParameter("telephone"));
		sysManager.setPassword(req.getParameter("password"));
		SysMManager manager = new SysMManager();
		manager.editManager(sysManager);
		list(req, resp);
		
	}
	
	//编辑个人信息
	private void info(HttpServletRequest req, HttpServletResponse resp)
			throws Exception{
		SysManager sysManager = new SysManager();
		sysManager.setManagerId(req.getParameter("managerId"));
		sysManager.setManagerName(req.getParameter("managerName"));
		sysManager.setSex(req.getParameter("sex"));
		sysManager.setTelephone(req.getParameter("telephone"));
		
		SysMManager manager = new SysMManager();
		manager.infoManager(sysManager);
		proccess(req, resp, "/manager_index.jsp");
	}
	
	//查询
	protected void search(HttpServletRequest req, HttpServletResponse resp)
			throws Exception{
		
		String keyword = req.getParameter("keywords");
		List<SysManager> manager_list = new ArrayList<SysManager>();
		SysMManager manager = new SysMManager();
		manager_list = manager.searchManager(keyword);
		req.setAttribute("manager_list", manager_list);			
		
		proccess(req, resp, "/page/manager/manager_list.jsp");
		
	}
	
	//显示列表
	protected void list(HttpServletRequest req,HttpServletResponse resp)throws Exception{
		   
			List<SysManager> manager_list = new ArrayList<SysManager>();
			SysMManager manager = new SysMManager();
			manager_list = manager.findAll();
			req.setAttribute("manager_list", manager_list);			
			
			proccess(req, resp, "/page/manager/manager_list.jsp");
			
	}

	
    private void proccess(HttpServletRequest req,HttpServletResponse resp,String path)throws ServletException,IOException{
		
		RequestDispatcher rd=req.getRequestDispatcher(path);
		rd.forward(req, resp);
		
	}


}
