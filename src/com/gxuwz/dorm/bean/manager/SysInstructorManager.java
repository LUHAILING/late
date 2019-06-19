package com.gxuwz.dorm.bean.manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.gxuwz.dorm.bean.datebase.DbUtil;
import com.gxuwz.dorm.bean.entity.SysInstructor;

public class SysInstructorManager {
private DbUtil dbUtil =new DbUtil();
	
	//添加
	@SuppressWarnings("null")
	public void addInstructor(SysInstructor sysInstructor)throws Exception{
		Connection conn = dbUtil.getConn();
		PreparedStatement pstmt = null;
		try{
			//定义SQL语句
			String sql = "insert into sys_instructor(instId,instName,depId,sex,telephone,password)";
			sql += "values(?,?,?,?,?,?)";
			//创建动态SQL语句对象
			pstmt = conn.prepareStatement(sql);
			//为动态SQL的参数赋值
			int i = 1;
			pstmt.setString(i++, sysInstructor.getInstId());
			pstmt.setString(i++, sysInstructor.getInstName());
			pstmt.setString(i++, sysInstructor.getDepId());
			pstmt.setString(i++, sysInstructor.getSex());
			pstmt.setString(i++, sysInstructor.getTelephone());
			pstmt.setString(i++, sysInstructor.getPassword());
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
    public void delInstructor(SysInstructor sysInstructor)throws Exception{
    	
    	Connection conn = dbUtil.getConn();
    	PreparedStatement pstmt = null;
		try{
			//定义SQL语句
			String sql = "delete from sys_instructor where instId=?";
		    //创建动态SQL语句
			pstmt = conn.prepareStatement(sql);
			//为动态SQL的参数赋值
			int i = 1;
			pstmt.setString(i, sysInstructor.getInstId());
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
    public void editInstructor(SysInstructor sysInstructor)throws Exception{
    	Connection conn = dbUtil.getConn();
    	PreparedStatement pstmt = null;
		try{
			//定义SQL语句
			String sql = "update sys_instructor set instName=?,depId=?,sex=?,telephone=?,password=? where instId=?";
			StringBuffer sqlBuff=new StringBuffer();
			sqlBuff.append(sql);
			sql = sqlBuff.toString();
			//创建动态SQL语句
			pstmt = conn.prepareStatement(sql);
			//为动态SQL的参数赋值
			int i = 1;
			pstmt.setString(i++, sysInstructor.getInstName());
			pstmt.setString(i++, sysInstructor.getDepId());
			pstmt.setString(i++, sysInstructor.getSex());
			pstmt.setString(i++, sysInstructor.getTelephone());
			pstmt.setString(i++, sysInstructor.getPassword());
			pstmt.setString(i++, sysInstructor.getInstId());
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
    
    //编辑个人信息
    public void infoInstructor(SysInstructor sysInstructor)throws Exception{
    	Connection conn = dbUtil.getConn();
    	PreparedStatement pstmt = null;
		try{
			//定义SQL语句
			String sql = "update sys_instructor set instName=?,depId=?,sex=?,telephone=? where instId=?";
			StringBuffer sqlBuff=new StringBuffer();
			sqlBuff.append(sql);
			sql = sqlBuff.toString();
			//创建动态SQL语句
			pstmt = conn.prepareStatement(sql);
			//为动态SQL的参数赋值
			int i = 1;
			pstmt.setString(i++, sysInstructor.getInstName());
			pstmt.setString(i++, sysInstructor.getDepId());
			pstmt.setString(i++, sysInstructor.getSex());
			pstmt.setString(i++, sysInstructor.getTelephone());
			
			pstmt.setString(i++, sysInstructor.getInstId());
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
    
    //修改密码
    public void pwdInstructor(SysInstructor sysInstructor)throws Exception{
    	Connection conn = dbUtil.getConn();
    	PreparedStatement pstmt = null;
		try{
			//定义SQL语句
			String sql = "update sys_instructor set password=? where instId=?";
			StringBuffer sqlBuff=new StringBuffer();
			sqlBuff.append(sql);
			sql = sqlBuff.toString();
			//创建动态SQL语句
			pstmt = conn.prepareStatement(sql);
			//为动态SQL的参数赋值
			int i = 1;
			
			pstmt.setString(i++, sysInstructor.getPassword());
			pstmt.setString(i++, sysInstructor.getInstId());
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
    public List<SysInstructor> searchInstructor(String keyword)throws Exception{
    	
    	List<SysInstructor> inst_list = new ArrayList<SysInstructor>();
    	Connection conn=dbUtil.getConn();
    	//定义SQL语句，默认模糊查询
    	String sql="select * from sys_instructor where 1=1";
    	//如果关键字不为空，则重定义SQL语句
    	if(keyword != null){
    		sql = "select * from sys_instructor where "
    				+ "instId like '%"+keyword+"%' "
    						+ "or instName like '%"+keyword+"%' "
    								+ "or depId like'%"+keyword+"%' "
    										+ "or sex like'%"+keyword+"%'";
    	}
    	//创建执行动态SQL语句对象
    	PreparedStatement pstmt = conn.prepareStatement(sql);
    	//执行SQL并返回ResultSet结果集
    	ResultSet rs = pstmt.executeQuery(sql);
    	//将结果集转换为SysInstructor实例并存入List
		 //将结果集指针移动至第一条记录
		 while(rs.next()){
		 //通过ResutlSet 接口方法依次获取每一列对应的值			 
		 SysInstructor sysInstructor = new SysInstructor();
		 sysInstructor.setInstId(rs.getString("instId"));
		 sysInstructor.setInstName(rs.getString("instName"));
		 sysInstructor.setDepId(rs.getString("depId"));
		 sysInstructor.setSex(rs.getString("sex"));
		 sysInstructor.setTelephone(rs.getString("telephone"));
		 sysInstructor.setPassword(rs.getString("password"));
		 
		 inst_list.add(sysInstructor);
		 }
    	
		return inst_list;
	}
    
    //显示列表
    public List<SysInstructor> findAll()throws Exception{
    	
    	List<SysInstructor> inst_list = new ArrayList<SysInstructor>();
    	Connection conn=dbUtil.getConn();
    	
			 //定义查询SQL语句
			 String sql="select * from sys_instructor where 1=1";			 
			 //创建执行查询SQL语句的对象
			 Statement stmt=conn.createStatement();
			 //执行查询
			 ResultSet rs=stmt.executeQuery(sql);
			 //将结果集转换为SysInstructor实例并存入List
			 //将结果集指针移动至第一条记录
			 while(rs.next()){
			 //通过ResutlSet 接口方法依次获取每一列对应的值			 
			 SysInstructor sysInstructor = new SysInstructor();
			 sysInstructor.setInstId(rs.getString("instId"));
			 sysInstructor.setInstName(rs.getString("instName"));
			 sysInstructor.setDepId(rs.getString("depId"));
			 sysInstructor.setSex(rs.getString("sex"));
			 sysInstructor.setTelephone(rs.getString("telephone"));
			 sysInstructor.setPassword(rs.getString("password"));
			 
			 inst_list.add(sysInstructor);
			 }
			 
			 return inst_list;
    	
    }

	

}
