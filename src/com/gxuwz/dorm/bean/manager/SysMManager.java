package com.gxuwz.dorm.bean.manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.gxuwz.dorm.bean.datebase.DbUtil;
import com.gxuwz.dorm.bean.entity.SysManager;

public class SysMManager {
private DbUtil dbUtil =new DbUtil();
	
	//添加
	@SuppressWarnings("null")
	public void addManager(SysManager sysManager)throws Exception{
		Connection conn = dbUtil.getConn();
		PreparedStatement pstmt = null;
		try{
			//定义SQL语句
			String sql = "insert into sys_manager(managerId,managerName,sex,telephone,password)";
			sql += "values(?,?,?,?,?)";
			//创建动态SQL语句对象
			pstmt = conn.prepareStatement(sql);
			//为动态SQL的参数赋值
			int i = 1;
			pstmt.setString(i++, sysManager.getManagerId());
			pstmt.setString(i++, sysManager.getManagerName());
			pstmt.setString(i++, sysManager.getSex());
			pstmt.setString(i++, sysManager.getTelephone());
			pstmt.setString(i++, sysManager.getPassword());
			
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
    public void delManager(SysManager sysManager)throws Exception{
    	
    	Connection conn = dbUtil.getConn();
    	PreparedStatement pstmt = null;
		try{
			//定义SQL语句
			String sql = "delete from sys_manager where managerId=?";
		    //创建动态SQL语句
			pstmt = conn.prepareStatement(sql);
			//为动态SQL的参数赋值
			int i = 1;
			pstmt.setString(i, sysManager.getManagerId());
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
    public void editManager(SysManager sysManager)throws Exception{
    	Connection conn = dbUtil.getConn();
    	PreparedStatement pstmt = null;
		try{
			//定义SQL语句
			String sql = "update sys_manager set managerName=?,sex=?,telephone=?,password=? where managerId=?";
			StringBuffer sqlBuff=new StringBuffer();
			sqlBuff.append(sql);
			sql = sqlBuff.toString();
			//创建动态SQL语句
			pstmt = conn.prepareStatement(sql);
			//为动态SQL的参数赋值
			int i = 1;
			pstmt.setString(i++, sysManager.getManagerName());
			pstmt.setString(i++, sysManager.getSex());
			pstmt.setString(i++, sysManager.getTelephone());
			pstmt.setString(i++, sysManager.getPassword());			
			pstmt.setString(i++, sysManager.getManagerId());
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
    public void infoManager(SysManager sysManager)throws Exception{
    	Connection conn = dbUtil.getConn();
    	PreparedStatement pstmt = null;
		try{
			//定义SQL语句
			String sql = "update sys_manager set managerName=?,sex=?,telephone=? where managerId=?";
			StringBuffer sqlBuff=new StringBuffer();
			sqlBuff.append(sql);
			sql = sqlBuff.toString();
			//创建动态SQL语句
			pstmt = conn.prepareStatement(sql);
			//为动态SQL的参数赋值
			int i = 1;
			pstmt.setString(i++, sysManager.getManagerName());
			pstmt.setString(i++, sysManager.getSex());
			pstmt.setString(i++, sysManager.getTelephone());				
			pstmt.setString(i++, sysManager.getManagerId());
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
    public List<SysManager> searchManager(String keyword)throws Exception{
    	
    	List<SysManager> manager_list = new ArrayList<SysManager>();
    	Connection conn=dbUtil.getConn();
    	//定义SQL语句，默认模糊查询
    	String sql="select * from sys_manager where 1=1";
    	//如果关键字不为空，则重定义SQL语句
    	if(keyword != null){
    		sql = "select * from sys_manager where "
    				+ "managerId like '%"+keyword+"%' "
    						+ "or managerName like '%"+keyword+"%' "
    								+ "or sex like'%"+keyword+"%'";
    	}
    	//创建执行动态SQL语句对象
    	PreparedStatement pstmt = conn.prepareStatement(sql);
    	//执行SQL并返回ResultSet结果集
    	ResultSet rs = pstmt.executeQuery(sql);
    	//将结果集转换为SysManager实例并存入List
		 //将结果集指针移动至第一条记录
		 while(rs.next()){
		 //通过ResutlSet 接口方法依次获取每一列对应的值			 
		 SysManager sysManager = new SysManager();
		 sysManager.setManagerId(rs.getString("managerId"));
		 sysManager.setManagerName(rs.getString("managerName"));
		 sysManager.setSex(rs.getString("sex"));
		 sysManager.setTelephone(rs.getString("telephone"));
		 sysManager.setPassword(rs.getString("password"));
		 
		 manager_list.add(sysManager);
		 }
    	
		return manager_list;
	}
    
    //显示列表
    public List<SysManager> findAll()throws Exception{
    	
    	List<SysManager> manager_list = new ArrayList<SysManager>();
    	Connection conn=dbUtil.getConn();
    	
			 //定义查询SQL语句
			 String sql="select * from sys_manager where 1=1";			 
			 //创建执行查询SQL语句的对象
			 Statement stmt=conn.createStatement();
			 //执行查询
			 ResultSet rs=stmt.executeQuery(sql);
			 //将结果集转换为SysManager实例并存入List
			 //将结果集指针移动至第一条记录
			 while(rs.next()){
			 //通过ResutlSet 接口方法依次获取每一列对应的值			 
			 SysManager sysManager = new SysManager();
			 sysManager.setManagerId(rs.getString("managerId"));
			 sysManager.setManagerName(rs.getString("managerName"));
			 sysManager.setSex(rs.getString("sex"));
			 sysManager.setTelephone(rs.getString("telephone"));
			 sysManager.setPassword(rs.getString("password"));
			 
			 manager_list.add(sysManager);
			 }
			 
			 return manager_list;
    	
    }

	

}
