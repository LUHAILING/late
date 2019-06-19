package com.gxuwz.dorm.bean.manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.gxuwz.dorm.bean.datebase.DbUtil;
import com.gxuwz.dorm.bean.entity.SysLate;

public class SysLateManager {
private DbUtil dbUtil =new DbUtil();
	
	//添加
	@SuppressWarnings("null")
	public void addLate(SysLate sysLate)throws Exception{
		Connection conn = dbUtil.getConn();
		PreparedStatement pstmt = null;
		try{
			//定义SQL语句
			String sql = "insert into sys_late(lateId,stuId,stuName,className,dormId,latetime,reason)";
			sql += "values(?,?,?,?,?,?,?)";
			//创建动态SQL语句对象
			pstmt = conn.prepareStatement(sql);
			//为动态SQL的参数赋值
			int i = 1;
			pstmt.setString(i++, sysLate.getLateId());
			pstmt.setString(i++, sysLate.getStuId());
			pstmt.setString(i++, sysLate.getStuName());
			pstmt.setString(i++, sysLate.getClassName());
			pstmt.setString(i++, sysLate.getDormId());
			pstmt.setString(i++, sysLate.getLatetime());
			pstmt.setString(i++, sysLate.getReason());
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
    public void delLate(SysLate sysLate)throws Exception{
    	
    	Connection conn = dbUtil.getConn();
    	PreparedStatement pstmt = null;
		try{
			//定义SQL语句
			String sql = "delete from sys_late where lateId=?";
		    //创建动态SQL语句
			pstmt = conn.prepareStatement(sql);
			//为动态SQL的参数赋值
			int i = 1;
			pstmt.setString(i, sysLate.getLateId());
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
    public void editLate(SysLate sysLate)throws Exception{
    	Connection conn = dbUtil.getConn();
    	PreparedStatement pstmt = null;
		try{
			//定义SQL语句
			String sql = "update sys_late set stuId=?,stuName=?,className=?,dormId=?,latetime=?,reason=? where lateId=?";
			StringBuffer sqlBuff=new StringBuffer();
			sqlBuff.append(sql);
			sql = sqlBuff.toString();
			//创建动态SQL语句
			pstmt = conn.prepareStatement(sql);
			//为动态SQL的参数赋值
			int i = 1;
			pstmt.setString(i++, sysLate.getLateId());
			pstmt.setString(i++, sysLate.getStuId());
			pstmt.setString(i++, sysLate.getStuName());
			pstmt.setString(i++, sysLate.getClassName());
			pstmt.setString(i++, sysLate.getDormId());
			pstmt.setString(i++, sysLate.getLatetime());
			pstmt.setString(i++, sysLate.getReason());
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
    public List<SysLate> searchLate(String keyword)throws Exception{
    	
    	List<SysLate> late_list = new ArrayList<SysLate>();
    	Connection conn=dbUtil.getConn();
    	//定义SQL语句，默认模糊查询
    	String sql="select * from sys_late where 1=1";
    	//如果关键字不为空，则重定义SQL语句
    	if(keyword != null){
    		sql = "select * from sys_late where "
    				+ "lateId like '%"+keyword+"%' "
    						+ "or stuId like '%"+keyword+"%' "
    								+ "or stuName like '%"+keyword+"%' "
    										+ "or className like'%"+keyword+"%' "
    												+ "or dormId like'%"+keyword+"%'";
    	}
    	//创建执行动态SQL语句对象
    	PreparedStatement pstmt = conn.prepareStatement(sql);
    	//执行SQL并返回ResultSet结果集
    	ResultSet rs = pstmt.executeQuery(sql);
    	//将结果集转换为SysLate实例并存入List
		 //将结果集指针移动至第一条记录
		 while(rs.next()){
		 //通过ResutlSet 接口方法依次获取每一列对应的值			 
		 SysLate sysLate = new SysLate();
		 sysLate.setLateId(rs.getString("lateId"));
		 sysLate.setStuId(rs.getString("stuId"));
		 sysLate.setStuName(rs.getString("stuName"));
		 sysLate.setClassName(rs.getString("className"));
		 sysLate.setDormId(rs.getString("dormId"));
		 sysLate.setLatetime(rs.getString("latetime"));
		 sysLate.setReason(rs.getString("reason"));
		 
		 late_list.add(sysLate);
		 }
    	
		return late_list;
	}
    
    //显示列表
    public List<SysLate> findAll()throws Exception{
    	
    	List<SysLate> late_list = new ArrayList<SysLate>();
    	Connection conn=dbUtil.getConn();
    	
			 //定义查询SQL语句
			 String sql="select * from sys_late where 1=1";			 
			 //创建执行查询SQL语句的对象
			 Statement stmt=conn.createStatement();
			 //执行查询
			 ResultSet rs=stmt.executeQuery(sql);
			 //将结果集转换为SysLate实例并存入List
			 //将结果集指针移动至第一条记录
			 while(rs.next()){
			 //通过ResutlSet 接口方法依次获取每一列对应的值			 
			 SysLate sysLate = new SysLate();
			 sysLate.setLateId(rs.getString("lateId"));
			 sysLate.setStuId(rs.getString("stuId"));
			 sysLate.setStuName(rs.getString("stuName"));
			 sysLate.setClassName(rs.getString("className"));
			 sysLate.setDormId(rs.getString("dormId"));
			 sysLate.setLatetime(rs.getString("latetime"));
			 sysLate.setReason(rs.getString("reason"));
			 
			 late_list.add(sysLate);
			 }
			 
			 return late_list;
    	
    }

	

}
