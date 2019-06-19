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

import com.gxuwz.dorm.bean.entity.SysStudent;
import com.gxuwz.dorm.bean.manager.SysStudentManager;

public class SysStudentServlet extends HttpServlet {
	
	 

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
		SysStudent sysStudent = new SysStudent();
		sysStudent.setStuId(req.getParameter("stuId"));
		sysStudent.setStuName(req.getParameter("stuName"));
		sysStudent.setClassId(req.getParameter("classId"));
		sysStudent.setSex(req.getParameter("sex"));
		sysStudent.setTelephone(req.getParameter("telephone"));
		SysStudentManager stuManager = new SysStudentManager();
		stuManager.addStudent(sysStudent);
		list(req, resp);
		
		}catch(Exception e){
			e.printStackTrace();
		}
		//proccess(req, resp, "/page/student/student_list.jsp");
	}
	
	//删除
	private void delete(HttpServletRequest req, HttpServletResponse resp)
			throws Exception{
		SysStudent sysStudent = new SysStudent();
		sysStudent.setStuId(req.getParameter("stuId"));
		SysStudentManager stuManager = new SysStudentManager();
		stuManager.delStudent(sysStudent);
		list(req, resp);
		System.out.println(req.getParameter("stuId"));

		//proccess(req, resp, "/page/student/student_list.jsp");
	}
	
	//修改
	private void edit(HttpServletRequest req, HttpServletResponse resp)
			throws Exception{
		SysStudent sysStudent = new SysStudent();
		sysStudent.setStuId(req.getParameter("stuId"));
		sysStudent.setStuName(req.getParameter("stuName"));
		sysStudent.setClassId(req.getParameter("classId"));
		sysStudent.setSex(req.getParameter("sex"));
		sysStudent.setTelephone(req.getParameter("telephone"));
		SysStudentManager stuManager = new SysStudentManager();
		stuManager.editStudent(sysStudent);
		list(req, resp);
		
	}
	
	//查询
	protected void search(HttpServletRequest req, HttpServletResponse resp)
			throws Exception{
		
		String keyword = req.getParameter("keywords");
		List<SysStudent> student_list = new ArrayList<SysStudent>();
		SysStudentManager stuManager = new SysStudentManager();
		student_list = stuManager.searchStudent(keyword);
		req.setAttribute("student_list", student_list);			
		
		proccess(req, resp, "/page/student/student_list.jsp");
		
	}
	
	//显示列表
	protected void list(HttpServletRequest req,HttpServletResponse resp)throws Exception{
		   
			List<SysStudent> student_list = new ArrayList<SysStudent>();
			SysStudentManager stuManager = new SysStudentManager();
			student_list = stuManager.findAll();
			req.setAttribute("student_list", student_list);			
			
			proccess(req, resp, "/page/student/student_list.jsp");
			
	}

	
    private void proccess(HttpServletRequest req,HttpServletResponse resp,String path)throws ServletException,IOException{
		
		RequestDispatcher rd=req.getRequestDispatcher(path);
		rd.forward(req, resp);
		
	}

}
