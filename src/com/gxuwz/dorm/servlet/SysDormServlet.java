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

import com.gxuwz.dorm.bean.entity.SysDorm;
import com.gxuwz.dorm.bean.manager.SysDormManager;

public class SysDormServlet extends HttpServlet {

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
		SysDorm sysDorm = new SysDorm();
		sysDorm.setDormId(req.getParameter("dormId"));
		sysDorm.setDormsID(req.getParameter("dormsID"));
		sysDorm.setCheckIn(Integer.parseInt(req.getParameter("checkIn")));
		sysDorm.setRest(Integer.parseInt(req.getParameter("rest")));
		
		SysDormManager dormManager = new SysDormManager();
		dormManager.addDorm(sysDorm);
		list(req, resp);
		
		}catch(Exception e){
			e.printStackTrace();
		}
		//proccess(req, resp, "/page/Dorm/dorm_list.jsp");
	}
	
	//删除
	private void delete(HttpServletRequest req, HttpServletResponse resp)
			throws Exception{
		SysDorm sysDorm = new SysDorm();
		sysDorm.setDormId(req.getParameter("dormId"));
		SysDormManager dormManager = new SysDormManager();
		dormManager.delDorm(sysDorm);
		list(req, resp);
		
		//proccess(req, resp, "/page/Dorm/dorm_list.jsp");
	}
	
	//修改
	private void edit(HttpServletRequest req, HttpServletResponse resp)
			throws Exception{
		SysDorm sysDorm = new SysDorm();
		sysDorm.setDormId(req.getParameter("dormId"));
		sysDorm.setDormsID(req.getParameter("dormsID"));
		sysDorm.setCheckIn(Integer.parseInt(req.getParameter("checkIn")));
		sysDorm.setRest(Integer.parseInt(req.getParameter("rest")));
		
		SysDormManager dormManager = new SysDormManager();
		dormManager.editDorm(sysDorm);
		list(req, resp);
		
		
	}
	
	//查询
	protected void search(HttpServletRequest req, HttpServletResponse resp)
			throws Exception{
		
		String keyword = req.getParameter("keywords");
		List<SysDorm> dorm_list = new ArrayList<SysDorm>();
		SysDormManager dormManager = new SysDormManager();
		dorm_list = dormManager.searchDorm(keyword);
		req.setAttribute("dorm_list", dorm_list);			
		
		proccess(req, resp, "/page/dorm/dorm_list.jsp");
		
	}
	
	//显示列表
	protected void list(HttpServletRequest req,HttpServletResponse resp)throws Exception{
		   
			List<SysDorm> dorm_list = new ArrayList<SysDorm>();
			SysDormManager dormManager = new SysDormManager();
			dorm_list = dormManager.findAll();
			req.setAttribute("dorm_list", dorm_list);			
			
			proccess(req, resp, "/page/dorm/dorm_list.jsp");
			
	}

	
    private void proccess(HttpServletRequest req,HttpServletResponse resp,String path)throws ServletException,IOException{
		
		RequestDispatcher rd=req.getRequestDispatcher(path);
		rd.forward(req, resp);
		
	}



}
