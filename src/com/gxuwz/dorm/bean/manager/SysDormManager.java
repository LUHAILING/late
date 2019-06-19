package com.gxuwz.dorm.bean.manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.gxuwz.dorm.bean.datebase.DbUtil;
import com.gxuwz.dorm.bean.entity.SysDorm;

public class SysDormManager {
	
private DbUtil dbUtil =new DbUtil();
	
	//添加
	@SuppressWarnings("null")
	public void addDorm(SysDorm sysDorm)throws Exception{
		Connection conn = dbUtil.getConn();
		PreparedStatement pstmt = null;
		try{
			//定义SQL语句
			String sql = "insert into sys_dorm(dormId,dormsID,checkIn,rest)";
			sql += "values(?,?,?,?)";
			//创建动态SQL语句对象
			pstmt = conn.prepareStatement(sql);
			//为动态SQL的参数赋值
			int i = 1;
			pstmt.setString(i++, sysDorm.getDormId());
			pstmt.setString(i++, sysDorm.getDormsID());
			pstmt.setInt(i++, sysDorm.getCheckIn());
			pstmt.setInt(i++, sysDorm.getRest());
			
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
    public void delDorm(SysDorm sysDorm)throws Exception{
    	
    	Connection conn = dbUtil.getConn();
    	PreparedStatement pstmt = null;
		try{
			//定义SQL语句
			String sql = "delete from sys_dorm where dormId=?";
		    //创建动态SQL语句
			pstmt = conn.prepareStatement(sql);
			//为动态SQL的参数赋值
			int i = 1;
			pstmt.setString(i, sysDorm.getDormId());
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
    public void editDorm(SysDorm sysDorm)throws Exception{
    	Connection conn = dbUtil.getConn();
    	PreparedStatement pstmt = null;
		try{
			//定义SQL语句
			String sql = "update sys_dorm set dormsID=?,checkIn=?,rest=? where dormId=?";
			StringBuffer sqlBuff=new StringBuffer();
			sqlBuff.append(sql);
			sql = sqlBuff.toString();
			//创建动态SQL语句
			pstmt = conn.prepareStatement(sql);
			//为动态SQL的参数赋值
			int i = 1;
			pstmt.setString(i++, sysDorm.getDormsID());
			pstmt.setInt(i++, sysDorm.getCheckIn());
			pstmt.setInt(i++, sysDorm.getRest());
			pstmt.setString(i++, sysDorm.getDormId());
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
    public List<SysDorm> searchDorm(String keyword)throws Exception{
    	
    	List<SysDorm> dorm_list = new ArrayList<SysDorm>();
    	Connection conn=dbUtil.getConn();
    	//定义SQL语句，默认模糊查询
    	String sql="select * from sys_dorm where 1=1";
    	//如果关键字不为空，则重定义SQL语句
    	if(keyword != null){
    		sql = "select * from sys_dorm where "
    				+ "dormId like '%"+keyword+"%' "
    						+ "or dormsID like '%"+keyword+"%' "
    								+ "or checkIn like'%"+keyword+"%' "    										
    										+ "or rest like'%"+keyword+"%' ";
    	}
    	//创建执行动态SQL语句对象
    	PreparedStatement pstmt = conn.prepareStatement(sql);
    	//执行SQL并返回ResultSet结果集
    	ResultSet rs = pstmt.executeQuery(sql);
    	//将结果集转换为SysDorm实例并存入List
		 //将结果集指针移动至第一条记录
		 while(rs.next()){
		 //通过ResutlSet 接口方法依次获取每一列对应的值			 
		 SysDorm sysDorm = new SysDorm();
		 sysDorm.setDormId(rs.getString("dormId"));
		 sysDorm.setDormsID(rs.getString("dormsID"));
		 sysDorm.setCheckIn(rs.getInt("checkIn"));
		 sysDorm.setRest(rs.getInt("rest"));
		
		 dorm_list.add(sysDorm);
		 }
    	
		return dorm_list;
	}
    
    //显示列表
    public List<SysDorm> findAll()throws Exception{
    	
    	List<SysDorm> dorm_list = new ArrayList<SysDorm>();
    	Connection conn=dbUtil.getConn();
    	
			 //定义查询SQL语句
			 String sql="select * from sys_dorm where 1=1";			 
			 //创建执行查询SQL语句的对象
			 Statement stmt=conn.createStatement();
			 //执行查询
			 ResultSet rs=stmt.executeQuery(sql);
			 //将结果集转换为SysDorm实例并存入List
			 //将结果集指针移动至第一条记录
			 while(rs.next()){
			 //通过ResutlSet 接口方法依次获取每一列对应的值			 
			 SysDorm sysDorm = new SysDorm();
			 sysDorm.setDormId(rs.getString("dormId"));
			 sysDorm.setDormsID(rs.getString("dormsID"));
			 sysDorm.setCheckIn(rs.getInt("checkIn"));
			 sysDorm.setRest(rs.getInt("rest"));
			
			 dorm_list.add(sysDorm);
			 }
			 
			 return dorm_list;
    	
    }

	
	

}
