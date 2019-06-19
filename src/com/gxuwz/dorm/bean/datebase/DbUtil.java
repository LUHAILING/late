package com.gxuwz.dorm.bean.datebase;

import java.sql.*;

 public class DbUtil {
	
	//�����������ݿ����Ϣ�����ݿ�URL���������ƣ��û���������
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
	
	//����JDBC����ض���
	protected Statement stmt = null;
	protected ResultSet rs = null;
	protected Connection conn = null;
	
	//�������ݿ�����
	public Connection getConn()throws Exception{
		
		try{
			Class.forName(this.jdbc_driver);   //����JDBC��������
			conn = DriverManager.getConnection(jdbc_url, jdbc_user, jdbc_pwd);    //�������ݿ�����ʵ��
			return conn;
		}catch(Exception e){
			e.printStackTrace();
			throw e;
		}
	}
	
	//ִ��insert��update��delete��䣬����int����
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
	
	//ִ��select��䣬����ResultSet�����
	
	public ResultSet executeQuery(String sql) throws Exception{
		
		try{
			stmt = getConn().prepareStatement(sql);
			rs = stmt.executeQuery(sql);
		}catch(SQLException se){
			se.printStackTrace();
		}
		return rs;
	}
	
	//ִ�ж�̬SQL��䣬����PreparedStatement����
	public PreparedStatement executePreparedStatement(String sql) throws Exception{
		PreparedStatement pstmt = null;
		try{
			pstmt = getConn().prepareStatement(sql);
		}catch(Exception e){
			e.printStackTrace();
		}
		return pstmt;
	}

 
	//�ر����ݿ����Ӷ���
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
