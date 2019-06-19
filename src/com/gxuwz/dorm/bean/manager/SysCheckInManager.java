package com.gxuwz.dorm.bean.manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.gxuwz.dorm.bean.datebase.DbUtil;
import com.gxuwz.dorm.bean.entity.SysCheckIn;

public class SysCheckInManager {
private DbUtil dbUtil =new DbUtil();
	
	//���
	@SuppressWarnings("null")
	public void addCheckIn(SysCheckIn sysCheckIn)throws Exception{
		Connection conn = dbUtil.getConn();
		PreparedStatement pstmt = null;
		try{
			//����SQL���
			String sql = "insert into sys_checkIn(checkInId,stuId,stuName,className,dormId,time)";
			sql += "values(?,?,?,?,?,?)";
			//������̬SQL������
			pstmt = conn.prepareStatement(sql);
			//Ϊ��̬SQL�Ĳ�����ֵ
			int i = 1;
			pstmt.setString(i++, sysCheckIn.getCheckInId());
			pstmt.setString(i++, sysCheckIn.getStuId());
			pstmt.setString(i++, sysCheckIn.getStuName());
			pstmt.setString(i++, sysCheckIn.getClassName());
			pstmt.setString(i++, sysCheckIn.getDormId());
			pstmt.setString(i++, sysCheckIn.getTime());
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
    public void delCheckIn(SysCheckIn sysCheckIn)throws Exception{
    	
    	Connection conn = dbUtil.getConn();
    	PreparedStatement pstmt = null;
		try{
			//����SQL���
			String sql = "delete from sys_checkIn where checkInId=?";
		    //������̬SQL���
			pstmt = conn.prepareStatement(sql);
			//Ϊ��̬SQL�Ĳ�����ֵ
			int i = 1;
			pstmt.setString(i, sysCheckIn.getCheckInId());
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
    public void editCheckIn(SysCheckIn sysCheckIn)throws Exception{
    	Connection conn = dbUtil.getConn();
    	PreparedStatement pstmt = null;
		try{
			//����SQL���
			String sql = "update sys_checkIn set stuId=?,stuName=?,className=?,dormId=?,time=? where checkInId=?";
			StringBuffer sqlBuff=new StringBuffer();
			sqlBuff.append(sql);
			sql = sqlBuff.toString();
			//������̬SQL���
			pstmt = conn.prepareStatement(sql);
			//Ϊ��̬SQL�Ĳ�����ֵ
			int i = 1;
			pstmt.setString(i++, sysCheckIn.getStuId());
			pstmt.setString(i++, sysCheckIn.getStuName());
			pstmt.setString(i++, sysCheckIn.getClassName());
			pstmt.setString(i++, sysCheckIn.getDormId());
			pstmt.setString(i++, sysCheckIn.getTime());
			pstmt.setString(i++, sysCheckIn.getCheckInId());
			
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
    public List<SysCheckIn> searchCheckIn(String keyword)throws Exception{
    	
    	List<SysCheckIn> checkIn_list = new ArrayList<SysCheckIn>();
    	Connection conn=dbUtil.getConn();
    	//����SQL��䣬Ĭ��ģ����ѯ
    	String sql="select * from sys_checkIn where 1=1";
    	//����ؼ��ֲ�Ϊ�գ����ض���SQL���
    	if(keyword != null){
    		sql = "select * from sys_checkIn where "
    				+ "checkInId like '%"+keyword+"%' "
    						+ "or stuId like '%"+keyword+"%' "
    								+ "or stuName like '%"+keyword+"%' "
    										+ "or className like '%"+keyword+"%' "
    												+ "or dormId like '%"+keyword+"%' ";
    	}
    	//����ִ�ж�̬SQL������
    	PreparedStatement pstmt = conn.prepareStatement(sql);
    	//ִ��SQL������ResultSet�����
    	ResultSet rs = pstmt.executeQuery(sql);
    	//�������ת��ΪSysCheckInʵ��������List
		 //�������ָ���ƶ�����һ����¼
		 while(rs.next()){
		 //ͨ��ResutlSet �ӿڷ������λ�ȡÿһ�ж�Ӧ��ֵ			 
		 SysCheckIn sysCheckIn = new SysCheckIn();
		 sysCheckIn.setCheckInId(rs.getString("checkInId"));
		 sysCheckIn.setStuId(rs.getString("stuId"));
		 sysCheckIn.setStuName(rs.getString("stuName"));
		 sysCheckIn.setClassName(rs.getString("className"));
		 sysCheckIn.setDormId(rs.getString("dormId"));
		 sysCheckIn.setTime(rs.getString("time"));
		 
		 checkIn_list.add(sysCheckIn);
		 }
    	
		return checkIn_list;
	}
    
    //��ʾ�б�
    public List<SysCheckIn> findAll()throws Exception{
    	
    	List<SysCheckIn> checkIn_list = new ArrayList<SysCheckIn>();
    	Connection conn=dbUtil.getConn();
    	
			 //�����ѯSQL���
			 String sql="select * from sys_checkIn where 1=1";			 
			 //����ִ�в�ѯSQL���Ķ���
			 Statement stmt=conn.createStatement();
			 //ִ�в�ѯ
			 ResultSet rs=stmt.executeQuery(sql);
			 //�������ת��ΪSysCheckInʵ��������List
			 //�������ָ���ƶ�����һ����¼
			 while(rs.next()){
			 //ͨ��ResutlSet �ӿڷ������λ�ȡÿһ�ж�Ӧ��ֵ			 
			 SysCheckIn sysCheckIn = new SysCheckIn();
			 sysCheckIn.setCheckInId(rs.getString("checkInId"));
			 sysCheckIn.setStuId(rs.getString("stuId"));
			 sysCheckIn.setStuName(rs.getString("stuName"));
			 sysCheckIn.setClassName(rs.getString("className"));
			 sysCheckIn.setDormId(rs.getString("dormId"));
			 sysCheckIn.setTime(rs.getString("time"));
			 
			 checkIn_list.add(sysCheckIn);
			 }
			 
			 return checkIn_list;
    	
    }

	

}
