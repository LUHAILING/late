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
	
	//���
	@SuppressWarnings("null")
	public void addLate(SysLate sysLate)throws Exception{
		Connection conn = dbUtil.getConn();
		PreparedStatement pstmt = null;
		try{
			//����SQL���
			String sql = "insert into sys_late(lateId,stuId,stuName,className,dormId,latetime,reason)";
			sql += "values(?,?,?,?,?,?,?)";
			//������̬SQL������
			pstmt = conn.prepareStatement(sql);
			//Ϊ��̬SQL�Ĳ�����ֵ
			int i = 1;
			pstmt.setString(i++, sysLate.getLateId());
			pstmt.setString(i++, sysLate.getStuId());
			pstmt.setString(i++, sysLate.getStuName());
			pstmt.setString(i++, sysLate.getClassName());
			pstmt.setString(i++, sysLate.getDormId());
			pstmt.setString(i++, sysLate.getLatetime());
			pstmt.setString(i++, sysLate.getReason());
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
    public void delLate(SysLate sysLate)throws Exception{
    	
    	Connection conn = dbUtil.getConn();
    	PreparedStatement pstmt = null;
		try{
			//����SQL���
			String sql = "delete from sys_late where lateId=?";
		    //������̬SQL���
			pstmt = conn.prepareStatement(sql);
			//Ϊ��̬SQL�Ĳ�����ֵ
			int i = 1;
			pstmt.setString(i, sysLate.getLateId());
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
    public void editLate(SysLate sysLate)throws Exception{
    	Connection conn = dbUtil.getConn();
    	PreparedStatement pstmt = null;
		try{
			//����SQL���
			String sql = "update sys_late set stuId=?,stuName=?,className=?,dormId=?,latetime=?,reason=? where lateId=?";
			StringBuffer sqlBuff=new StringBuffer();
			sqlBuff.append(sql);
			sql = sqlBuff.toString();
			//������̬SQL���
			pstmt = conn.prepareStatement(sql);
			//Ϊ��̬SQL�Ĳ�����ֵ
			int i = 1;
			pstmt.setString(i++, sysLate.getLateId());
			pstmt.setString(i++, sysLate.getStuId());
			pstmt.setString(i++, sysLate.getStuName());
			pstmt.setString(i++, sysLate.getClassName());
			pstmt.setString(i++, sysLate.getDormId());
			pstmt.setString(i++, sysLate.getLatetime());
			pstmt.setString(i++, sysLate.getReason());
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
    public List<SysLate> searchLate(String keyword)throws Exception{
    	
    	List<SysLate> late_list = new ArrayList<SysLate>();
    	Connection conn=dbUtil.getConn();
    	//����SQL��䣬Ĭ��ģ����ѯ
    	String sql="select * from sys_late where 1=1";
    	//����ؼ��ֲ�Ϊ�գ����ض���SQL���
    	if(keyword != null){
    		sql = "select * from sys_late where "
    				+ "lateId like '%"+keyword+"%' "
    						+ "or stuId like '%"+keyword+"%' "
    								+ "or stuName like '%"+keyword+"%' "
    										+ "or className like'%"+keyword+"%' "
    												+ "or dormId like'%"+keyword+"%'";
    	}
    	//����ִ�ж�̬SQL������
    	PreparedStatement pstmt = conn.prepareStatement(sql);
    	//ִ��SQL������ResultSet�����
    	ResultSet rs = pstmt.executeQuery(sql);
    	//�������ת��ΪSysLateʵ��������List
		 //�������ָ���ƶ�����һ����¼
		 while(rs.next()){
		 //ͨ��ResutlSet �ӿڷ������λ�ȡÿһ�ж�Ӧ��ֵ			 
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
    
    //��ʾ�б�
    public List<SysLate> findAll()throws Exception{
    	
    	List<SysLate> late_list = new ArrayList<SysLate>();
    	Connection conn=dbUtil.getConn();
    	
			 //�����ѯSQL���
			 String sql="select * from sys_late where 1=1";			 
			 //����ִ�в�ѯSQL���Ķ���
			 Statement stmt=conn.createStatement();
			 //ִ�в�ѯ
			 ResultSet rs=stmt.executeQuery(sql);
			 //�������ת��ΪSysLateʵ��������List
			 //�������ָ���ƶ�����һ����¼
			 while(rs.next()){
			 //ͨ��ResutlSet �ӿڷ������λ�ȡÿһ�ж�Ӧ��ֵ			 
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
