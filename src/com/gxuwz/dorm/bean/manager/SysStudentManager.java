package com.gxuwz.dorm.bean.manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.gxuwz.dorm.bean.datebase.*;
import com.gxuwz.dorm.bean.entity.SysStudent;

public class SysStudentManager {

	private DbUtil dbUtil =new DbUtil();
	
	//添加
	@SuppressWarnings("null")
	public void addStudent(SysStudent sysStudent)throws Exception{
		Connection conn = dbUtil.getConn();
		PreparedStatement pstmt = null;
		try{
			//定义SQL语句
			String sql = "insert into sys_student(stuId,stuName,classId,sex,telephone)";
			sql += "values(?,?,?,?,?)";
			//创建动态SQL语句对象
			pstmt = conn.prepareStatement(sql);
			//为动态SQL的参数赋值
			int i = 1;
			pstmt.setString(i++, sysStudent.getStuId());
			pstmt.setString(i++, sysStudent.getStuName());
			pstmt.setString(i++, sysStudent.getClassId());
			pstmt.setString(i++, sysStudent.getSex());
			pstmt.setString(i++, sysStudent.getTelephone());
			//执行SQL并返回执行结果
			@SuppressWarnings("unused")
			int result = pstmt.executeUpdate();
		}catch(SQLException se){
			se.printStackTrace();
			throw new SQLException("录入数据失败！"+se.getMessage(),se);
			
		}finally{
			dbUtil.close(pstmt, conn);
		}
	}
	
	//删除
    public void delStudent(SysStudent sysStudent)throws Exception{
    	
    	Connection conn = dbUtil.getConn();
    	PreparedStatement pstmt = null;
		try{
			//定义SQL语句
			String sql = "delete from sys_student where stuId=?";
		    //创建动态SQL语句
			pstmt = conn.prepareStatement(sql);
			//为动态SQL的参数赋值
			int i = 1;
			pstmt.setString(i, sysStudent.getStuId());
			//执行SQL并返回执行结果
			int result = pstmt.executeUpdate();
		}catch(SQLException se){
			se.printStackTrace();
			throw new SQLException("删除数据失败！"+se.getMessage(), se);
		}finally{
			dbUtil.close(pstmt, conn);
		}
	}
    
    
	//修改
    public void editStudent(SysStudent sysStudent)throws Exception{
    	Connection conn = dbUtil.getConn();
    	PreparedStatement pstmt = null;
		try{
			//定义SQL语句
			String sql = "update sys_student set stuName=?,classId=?,sex=?,telephone=? where stuId=?";
			StringBuffer sqlBuff=new StringBuffer();
			sqlBuff.append(sql);
			sql = sqlBuff.toString();
			//创建动态SQL语句
			pstmt = conn.prepareStatement(sql);
			//为动态SQL的参数赋值
			int i = 1;
			pstmt.setString(i++, sysStudent.getStuName());
			pstmt.setString(i++, sysStudent.getClassId());
			pstmt.setString(i++, sysStudent.getSex());
			pstmt.setString(i++, sysStudent.getTelephone());
			pstmt.setString(i++, sysStudent.getStuId());
			//执行SQL并返回执行结果
			@SuppressWarnings("unused")
			int result = pstmt.executeUpdate();
		}catch(SQLException se){
			se.printStackTrace();
			throw new SQLException("更新数据失败！"+se.getMessage(), se);
		}finally{
			dbUtil.close(pstmt, conn);
		}
	}
    
    //查询
    public List<SysStudent> searchStudent(String keyword)throws Exception{
    	
    	List<SysStudent> student_list = new ArrayList<SysStudent>();
    	Connection conn=dbUtil.getConn();
    	//定义SQL语句，默认模糊查询
    	String sql="select * from sys_student where 1=1";
    	//如果关键字不为空，则重定义SQL语句
    	if(keyword != null){
    		sql = "select * from sys_student where stuId like '%"+keyword+"%' or stuName like '%"+keyword+"%' or classId like'%"+keyword+"%'";
    	}
    	//创建执行动态SQL语句对象
    	PreparedStatement pstmt = conn.prepareStatement(sql);
    	//执行SQL并返回ResultSet结果集
    	ResultSet rs = pstmt.executeQuery(sql);
    	//将结果集转换为SysStudent实例并存入List
		 //将结果集指针移动至第一条记录
		 while(rs.next()){
		 //通过ResutlSet 接口方法依次获取每一列对应的值			 
		 SysStudent sysStudent = new SysStudent();
		 sysStudent.setStuId(rs.getString("stuId"));
		 sysStudent.setStuName(rs.getString("stuName"));
		 sysStudent.setClassId(rs.getString("classId"));
		 sysStudent.setSex(rs.getString("sex"));
		 sysStudent.setTelephone(rs.getString("telephone"));
		 
		 student_list.add(sysStudent);
		 }
    	
		return student_list;
	}
    
    //显示列表
    public List<SysStudent> findAll()throws Exception{
    	
    	List<SysStudent> student_list = new ArrayList<SysStudent>();
    	Connection conn=dbUtil.getConn();
    	
			 //定义查询SQL语句
			 String sql="select * from sys_student where 1=1";			 
			 //创建执行查询SQL语句的对象
			 Statement stmt=conn.createStatement();
			 //执行查询
			 ResultSet rs=stmt.executeQuery(sql);
			 //将结果集转换为SysStudent实例并存入List
			 //将结果集指针移动至第一条记录
			 while(rs.next()){
			 //通过ResutlSet 接口方法依次获取每一列对应的值			 
			 SysStudent sysStudent = new SysStudent();
			 sysStudent.setStuId(rs.getString("stuId"));
			 sysStudent.setStuName(rs.getString("stuName"));
			 sysStudent.setClassId(rs.getString("classId"));
			 sysStudent.setSex(rs.getString("sex"));
			 sysStudent.setTelephone(rs.getString("telephone"));
			 
			 student_list.add(sysStudent);
			 }
			 
			 return student_list;
    	
    }

	
	
}
