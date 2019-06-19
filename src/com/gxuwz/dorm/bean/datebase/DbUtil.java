package com.gxuwz.dorm.bean.datebase;

import java.sql.*;

 public class DbUtil {
	
	//声明连接数据库的信息，数据库URL，驱动名称，用户名，密码
	private String jdbc_url;
	private String jdbc_driver;
	private String jdbc_user;
	private String jdbc_pwd;
	
	public DbUtil(){
		
		this.jdbc_driver="com.mysql.jdbc.Driver";
		this.jdbc_url="jdbc:mysql://127.0.0.1:3306/dormitorydb";
		this.jdbc_user="root";
		this.jdbc_pwd="123456";
		}
	
	//声明JDBC的相关对象
	protected Statement stmt = null;
	protected ResultSet rs = null;
	protected Connection conn = null;
	
	//创建数据库连接
	public Connection getConn()throws Exception{
		
		try{
			Class.forName(this.jdbc_driver);   //加载JDBC驱动程序
			conn = DriverManager.getConnection(jdbc_url, jdbc_user, jdbc_pwd);    //创建数据库连接实例
			return conn;
		}catch(Exception e){
			e.printStackTrace();
			throw e;
		}
	}
	
	//执行insert，update，delete语句，返回int类型
	public int executeUpdate(String sql) throws Exception{
		int result = 0;
		try{
			stmt = getConn().prepareStatement(sql);
			result = stmt.executeUpdate(sql);
		}catch(SQLException se){
			se.printStackTrace();
		}
		return result;
	}
	
	//执行select语句，返回ResultSet结果集
	
	public ResultSet executeQuery(String sql) throws Exception{
		
		try{
			stmt = getConn().prepareStatement(sql);
			rs = stmt.executeQuery(sql);
		}catch(SQLException se){
			se.printStackTrace();
		}
		return rs;
	}
	
	//执行动态SQL语句，返回PreparedStatement对象
	public PreparedStatement executePreparedStatement(String sql) throws Exception{
		PreparedStatement pstmt = null;
		try{
			pstmt = getConn().prepareStatement(sql);
		}catch(Exception e){
			e.printStackTrace();
		}
		return pstmt;
	}

 
	//关闭数据库连接对象
	public void close(ResultSet rs, Statement stmt,Connection conn)throws SQLException{
		try{
			if(rs != null){
				rs.close();
			}
			if(stmt != null){
				stmt.close();
			}
			if(conn != null){
				conn.close();
			}
		}catch(SQLException se){
			se.printStackTrace();
			throw se;
		}
	}
	public void close(PreparedStatement pstmt,Connection conn)throws SQLException{
		close(null, pstmt, conn);
	}

	public void closeStmt(PreparedStatement pstmt)throws SQLException {
		close(null,pstmt,null);
		
	}
	
	
	
}
