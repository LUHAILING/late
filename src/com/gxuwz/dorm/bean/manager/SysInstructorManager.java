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
	
	//���
	@SuppressWarnings("null")
	public void addInstructor(SysInstructor sysInstructor)throws Exception{
		Connection conn = dbUtil.getConn();
		PreparedStatement pstmt = null;
		try{
			//����SQL���
			String sql = "insert into sys_instructor(instId,instName,depId,sex,telephone,password)";
			sql += "values(?,?,?,?,?,?)";
			//������̬SQL������
			pstmt = conn.prepareStatement(sql);
			//Ϊ��̬SQL�Ĳ�����ֵ
			int i = 1;
			pstmt.setString(i++, sysInstructor.getInstId());
			pstmt.setString(i++, sysInstructor.getInstName());
			pstmt.setString(i++, sysInstructor.getDepId());
			pstmt.setString(i++, sysInstructor.getSex());
			pstmt.setString(i++, sysInstructor.getTelephone());
			pstmt.setString(i++, sysInstructor.getPassword());
			//ִ��SQL������ִ�н��
			@SuppressWarnings("unused")
			int result = pstmt.executeUpdate();
		}catch(SQLException se){
			se.printStackTrace();
			throw new SQLException("¼������ʧ�ܣ�"+se.getMessage(),se);
			
		}finally{
			dbUtil.close(pstmt, conn);
		}
	}
	
	//ɾ��
    public void delInstructor(SysInstructor sysInstructor)throws Exception{
    	
    	Connection conn = dbUtil.getConn();
    	PreparedStatement pstmt = null;
		try{
			//����SQL���
			String sql = "delete from sys_instructor where instId=?";
		    //������̬SQL���
			pstmt = conn.prepareStatement(sql);
			//Ϊ��̬SQL�Ĳ�����ֵ
			int i = 1;
			pstmt.setString(i, sysInstructor.getInstId());
			//ִ��SQL������ִ�н��
			int result = pstmt.executeUpdate();
		}catch(SQLException se){
			se.printStackTrace();
			throw new SQLException("ɾ������ʧ�ܣ�"+se.getMessage(), se);
		}finally{
			dbUtil.close(pstmt, conn);
		}
	}
    
    
	//�޸�
    public void editInstructor(SysInstructor sysInstructor)throws Exception{
    	Connection conn = dbUtil.getConn();
    	PreparedStatement pstmt = null;
		try{
			//����SQL���
			String sql = "update sys_instructor set instName=?,depId=?,sex=?,telephone=?,password=? where instId=?";
			StringBuffer sqlBuff=new StringBuffer();
			sqlBuff.append(sql);
			sql = sqlBuff.toString();
			//������̬SQL���
			pstmt = conn.prepareStatement(sql);
			//Ϊ��̬SQL�Ĳ�����ֵ
			int i = 1;
			pstmt.setString(i++, sysInstructor.getInstName());
			pstmt.setString(i++, sysInstructor.getDepId());
			pstmt.setString(i++, sysInstructor.getSex());
			pstmt.setString(i++, sysInstructor.getTelephone());
			pstmt.setString(i++, sysInstructor.getPassword());
			pstmt.setString(i++, sysInstructor.getInstId());
			//ִ��SQL������ִ�н��
			@SuppressWarnings("unused")
			int result = pstmt.executeUpdate();
		}catch(SQLException se){
			se.printStackTrace();
			throw new SQLException("��������ʧ�ܣ�"+se.getMessage(), se);
		}finally{
			dbUtil.close(pstmt, conn);
		}
	}
    
    //�༭������Ϣ
    public void infoInstructor(SysInstructor sysInstructor)throws Exception{
    	Connection conn = dbUtil.getConn();
    	PreparedStatement pstmt = null;
		try{
			//����SQL���
			String sql = "update sys_instructor set instName=?,depId=?,sex=?,telephone=? where instId=?";
			StringBuffer sqlBuff=new StringBuffer();
			sqlBuff.append(sql);
			sql = sqlBuff.toString();
			//������̬SQL���
			pstmt = conn.prepareStatement(sql);
			//Ϊ��̬SQL�Ĳ�����ֵ
			int i = 1;
			pstmt.setString(i++, sysInstructor.getInstName());
			pstmt.setString(i++, sysInstructor.getDepId());
			pstmt.setString(i++, sysInstructor.getSex());
			pstmt.setString(i++, sysInstructor.getTelephone());
			
			pstmt.setString(i++, sysInstructor.getInstId());
			//ִ��SQL������ִ�н��
			@SuppressWarnings("unused")
			int result = pstmt.executeUpdate();
		}catch(SQLException se){
			se.printStackTrace();
			throw new SQLException("��������ʧ�ܣ�"+se.getMessage(), se);
		}finally{
			dbUtil.close(pstmt, conn);
		}
	}
    
    //�޸�����
    public void pwdInstructor(SysInstructor sysInstructor)throws Exception{
    	Connection conn = dbUtil.getConn();
    	PreparedStatement pstmt = null;
		try{
			//����SQL���
			String sql = "update sys_instructor set password=? where instId=?";
			StringBuffer sqlBuff=new StringBuffer();
			sqlBuff.append(sql);
			sql = sqlBuff.toString();
			//������̬SQL���
			pstmt = conn.prepareStatement(sql);
			//Ϊ��̬SQL�Ĳ�����ֵ
			int i = 1;
			
			pstmt.setString(i++, sysInstructor.getPassword());
			pstmt.setString(i++, sysInstructor.getInstId());
			//ִ��SQL������ִ�н��
			@SuppressWarnings("unused")
			int result = pstmt.executeUpdate();
		}catch(SQLException se){
			se.printStackTrace();
			throw new SQLException("��������ʧ�ܣ�"+se.getMessage(), se);
		}finally{
			dbUtil.close(pstmt, conn);
		}
	}
    
    //��ѯ
    public List<SysInstructor> searchInstructor(String keyword)throws Exception{
    	
    	List<SysInstructor> inst_list = new ArrayList<SysInstructor>();
    	Connection conn=dbUtil.getConn();
    	//����SQL��䣬Ĭ��ģ����ѯ
    	String sql="select * from sys_instructor where 1=1";
    	//����ؼ��ֲ�Ϊ�գ����ض���SQL���
    	if(keyword != null){
    		sql = "select * from sys_instructor where "
    				+ "instId like '%"+keyword+"%' "
    						+ "or instName like '%"+keyword+"%' "
    								+ "or depId like'%"+keyword+"%' "
    										+ "or sex like'%"+keyword+"%'";
    	}
    	//����ִ�ж�̬SQL������
    	PreparedStatement pstmt = conn.prepareStatement(sql);
    	//ִ��SQL������ResultSet�����
    	ResultSet rs = pstmt.executeQuery(sql);
    	//�������ת��ΪSysInstructorʵ��������List
		 //�������ָ���ƶ�����һ����¼
		 while(rs.next()){
		 //ͨ��ResutlSet �ӿڷ������λ�ȡÿһ�ж�Ӧ��ֵ			 
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
    
    //��ʾ�б�
    public List<SysInstructor> findAll()throws Exception{
    	
    	List<SysInstructor> inst_list = new ArrayList<SysInstructor>();
    	Connection conn=dbUtil.getConn();
    	
			 //�����ѯSQL���
			 String sql="select * from sys_instructor where 1=1";			 
			 //����ִ�в�ѯSQL���Ķ���
			 Statement stmt=conn.createStatement();
			 //ִ�в�ѯ
			 ResultSet rs=stmt.executeQuery(sql);
			 //�������ת��ΪSysInstructorʵ��������List
			 //�������ָ���ƶ�����һ����¼
			 while(rs.next()){
			 //ͨ��ResutlSet �ӿڷ������λ�ȡÿһ�ж�Ӧ��ֵ			 
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
