package com.gxuwz.dorm.bean.manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.gxuwz.dorm.bean.datebase.DbUtil;
import com.gxuwz.dorm.bean.entity.SysDepartment;

public class SysDepartmentManager {

private DbUtil dbUtil =new DbUtil();
	
	//添加
	@SuppressWarnings("null")
	public void addDepartment(SysDepartment sysDepartment)throws Exception{
		Connection conn = dbUtil.getConn();
		PreparedStatement pstmt = null;
		try{
			//定义SQL语句
			String sql = "insert into sys_department(depId,depName)";
			sql += "values(?,?)";
			//创建动态SQL语句对象
			pstmt = conn.prepareStatement(sql);
			//为动态SQL的参数赋值
			int i = 1;
			
			pstmt.setString(i++, sysDepartment.getDepId());
			pstmt.setString(i++, sysDepartment.getDepName());
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
    public void delDepartment(SysDepartment sysDepartment)throws Exception{
    	
    	Connection conn = dbUtil.getConn();
    	PreparedStatement pstmt = null;
		try{
			//定义SQL语句
			String sql = "delete from sys_department where depId=?";
		    //创建动态SQL语句
			pstmt = conn.prepareStatement(sql);
			//为动态SQL的参数赋值
			int i = 1;
			pstmt.setString(i, sysDepartment.getDepId());
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
    public void editDepartment(SysDepartment sysDepartment)throws Exception{
    	Connection conn = dbUtil.getConn();
    	PreparedStatement pstmt = null;
		try{
			//定义SQL语句
			String sql = "update sys_department set depName=? where depId=?";
			StringBuffer sqlBuff=new StringBuffer();
			sqlBuff.append(sql);
			sql = sqlBuff.toString();
			//创建动态SQL语句
			pstmt = conn.prepareStatement(sql);
			//为动态SQL的参数赋值
			int i = 1;
			pstmt.setString(i++, sysDepartment.getDepName());
			pstmt.setString(i++, sysDepartment.getDepId());
			
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
    public List<SysDepartment> searchDepartment(String keyword)throws Exception{
    	
    	List<SysDepartment> dep_list = new ArrayList<SysDepartment>();
    	Connection conn=dbUtil.getConn();
    	//定义SQL语句，默认模糊查询
    	String sql="select * from sys_department where 1=1";
    	//如果关键字不为空，则重定义SQL语句
    	if(keyword != null){
    		sql = "select * from sys_department where "
    				+ "depId like '%"+keyword+"%' "
    						+ "or depName like '%"+keyword+"%'";
    	}
    	//创建执行动态SQL语句对象
    	PreparedStatement pstmt = conn.prepareStatement(sql);
    	//执行SQL并返回ResultSet结果集
    	ResultSet rs = pstmt.executeQuery(sql);
    	//将结果集转换为SysDepartment实例并存入List
		 //将结果集指针移动至第一条记录
		 while(rs.next()){
		 //通过ResutlSet 接口方法依次获取每一列对应的值			 
		 SysDepartment sysDepartment = new SysDepartment();
		 sysDepartment.setDepId(rs.getString("depId"));
		 sysDepartment.setDepName(rs.getString("depName"));
		 
		 dep_list.add(sysDepartment);
		 }
    	
		return dep_list;
	}
    
    //显示列表
    public List<SysDepartment> findAll()throws Exception{
    	
    	List<SysDepartment> dep_list = new ArrayList<SysDepartment>();
    	Connection conn=dbUtil.getConn();
    	
			 //定义查询SQL语句
			 String sql="select * from sys_department where 1=1";			 
			 //创建执行查询SQL语句的对象
			 Statement stmt=conn.createStatement();
			 //执行查询
			 ResultSet rs=stmt.executeQuery(sql);
			 //将结果集转换为SysDepartment实例并存入List
			 //将结果集指针移动至第一条记录
			 while(rs.next()){
			 //通过ResutlSet 接口方法依次获取每一列对应的值			 
			 SysDepartment sysDepartment = new SysDepartment();
			 sysDepartment.setDepId(rs.getString("depId"));
			 sysDepartment.setDepName(rs.getString("depName"));
			 
			 dep_list.add(sysDepartment);
			 }
			 
			 return dep_list;
    	
    }

	
	
}
