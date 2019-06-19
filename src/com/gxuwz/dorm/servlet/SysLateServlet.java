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

import com.gxuwz.dorm.bean.entity.SysLate;
import com.gxuwz.dorm.bean.manager.SysLateManager;

public class SysLateServlet extends HttpServlet {

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
		SysLate sysLate = new SysLate();
		sysLate.setLateId(req.getParameter("lateId"));
		sysLate.setStuId(req.getParameter("stuId"));
		sysLate.setStuName(req.getParameter("stuName"));
		sysLate.setClassName(req.getParameter("className"));
		sysLate.setDormId(req.getParameter("dormId"));
		sysLate.setLatetime(req.getParameter("latetime"));
		sysLate.setReason(req.getParameter("reason"));
		SysLateManager lateManager = new SysLateManager();
		lateManager.addLate(sysLate);
		list(req, resp);
		
		}catch(Exception e){
			e.printStackTrace();
		}
		//proccess(req, resp, "/page/Late/Late_list.jsp");
	}
	
	//删除
	private void delete(HttpServletRequest req, HttpServletResponse resp)
			throws Exception{
		SysLate sysLate = new SysLate();
		sysLate.setLateId(req.getParameter("lateId"));
		SysLateManager lateManager = new SysLateManager();
		lateManager.delLate(sysLate);
		list(req, resp);
		
		//proccess(req, resp, "/page/Late/Late_list.jsp");
	}
	
	//修改
	private void edit(HttpServletRequest req, HttpServletResponse resp)
			throws Exception{
		SysLate sysLate = new SysLate();
		sysLate.setLateId(req.getParameter("lateId"));
		sysLate.setStuId(req.getParameter("stuId"));
		sysLate.setStuName(req.getParameter("stuName"));
		sysLate.setClassName(req.getParameter("className"));
		sysLate.setDormId(req.getParameter("dormId"));
		sysLate.setLatetime(req.getParameter("latetime"));
		sysLate.setReason(req.getParameter("reason"));
		SysLateManager lateManager = new SysLateManager();
		lateManager.editLate(sysLate);
		list(req, resp);
		
	}
	
	//查询
	protected void search(HttpServletRequest req, HttpServletResponse resp)
			throws Exception{
		
		String keyword = req.getParameter("keywords");
		List<SysLate> late_list = new ArrayList<SysLate>();
		SysLateManager lateManager = new SysLateManager();
		late_list = lateManager.searchLate(keyword);
		req.setAttribute("late_list", late_list);			
		
		proccess(req, resp, "/page/late/late_list.jsp");
		
	}
	
	//显示列表
	protected void list(HttpServletRequest req,HttpServletResponse resp)throws Exception{
		   
			List<SysLate> late_list = new ArrayList<SysLate>();
			SysLateManager lateManager = new SysLateManager();
			late_list = lateManager.findAll();
			req.setAttribute("late_list", late_list);			
			
			proccess(req, resp, "/page/late/late_list.jsp");
			
	}

	
    private void proccess(HttpServletRequest req,HttpServletResponse resp,String path)throws ServletException,IOException{
		
		RequestDispatcher rd=req.getRequestDispatcher(path);
		rd.forward(req, resp);
		
	}

}
