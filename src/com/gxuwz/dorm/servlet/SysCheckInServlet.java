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

import com.gxuwz.dorm.bean.entity.SysCheckIn;
import com.gxuwz.dorm.bean.manager.SysCheckInManager;

public class SysCheckInServlet extends HttpServlet {

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
		SysCheckIn sysCheckIn = new SysCheckIn();
		sysCheckIn.setCheckInId(req.getParameter("checkInId"));
		sysCheckIn.setStuId(req.getParameter("stuId"));
		sysCheckIn.setStuName(req.getParameter("stuName"));
		sysCheckIn.setClassName(req.getParameter("className"));
		sysCheckIn.setDormId(req.getParameter("dormId"));
		sysCheckIn.setTime(req.getParameter("time"));
		SysCheckInManager checkInManager = new SysCheckInManager();
		checkInManager.addCheckIn(sysCheckIn);
		list(req, resp);
		
		}catch(Exception e){
			e.printStackTrace();
		}
		//proccess(req, resp, "/page/CheckIn/CheckIn_list.jsp");
	}
	
	//删除
	private void delete(HttpServletRequest req, HttpServletResponse resp)
			throws Exception{
		SysCheckIn sysCheckIn = new SysCheckIn();
		sysCheckIn.setCheckInId(req.getParameter("checkInId"));
		SysCheckInManager checkInManager = new SysCheckInManager();
		checkInManager.delCheckIn(sysCheckIn);
		list(req, resp);
		

		//proccess(req, resp, "/page/CheckIn/CheckIn_list.jsp");
	}
	
	//修改
	private void edit(HttpServletRequest req, HttpServletResponse resp)
			throws Exception{
		SysCheckIn sysCheckIn = new SysCheckIn();
		sysCheckIn.setCheckInId(req.getParameter("checkInId"));
		sysCheckIn.setStuId(req.getParameter("stuId"));
		sysCheckIn.setStuName(req.getParameter("stuName"));
		sysCheckIn.setClassName(req.getParameter("className"));
		sysCheckIn.setDormId(req.getParameter("dormId"));
		sysCheckIn.setTime(req.getParameter("time"));
		SysCheckInManager checkInManager = new SysCheckInManager();
		checkInManager.editCheckIn(sysCheckIn);
		list(req, resp);
		
	}
	
	//查询
	protected void search(HttpServletRequest req, HttpServletResponse resp)
			throws Exception{
		
		String keyword = req.getParameter("keywords");
		List<SysCheckIn> checkIn_list = new ArrayList<SysCheckIn>();
		SysCheckInManager checkInManager = new SysCheckInManager();
		checkIn_list = checkInManager.searchCheckIn(keyword);
		req.setAttribute("checkIn_list", checkIn_list);			
		
		proccess(req, resp, "/page/checkIn/checkIn_list.jsp");
		
	}
	
	//显示列表
	protected void list(HttpServletRequest req,HttpServletResponse resp)throws Exception{
		   
			List<SysCheckIn> checkIn_list = new ArrayList<SysCheckIn>();
			SysCheckInManager checkInManager = new SysCheckInManager();
			checkIn_list = checkInManager.findAll();
			req.setAttribute("checkIn_list", checkIn_list);			
			
			proccess(req, resp, "/page/checkIn/checkIn_list.jsp");
			
	}

	
    private void proccess(HttpServletRequest req,HttpServletResponse resp,String path)throws ServletException,IOException{
		
		RequestDispatcher rd=req.getRequestDispatcher(path);
		rd.forward(req, resp);
		
	}


}
