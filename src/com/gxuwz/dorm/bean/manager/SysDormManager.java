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
	
	//���
	@SuppressWarnings("null")
	public void addDorm(SysDorm sysDorm)throws Exception{
		Connection conn = dbUtil.getConn();
		PreparedStatement pstmt = null;
		try{
			//����SQL���
			String sql = "insert into sys_dorm(dormId,dormsID,checkIn,rest)";
			sql += "values(?,?,?,?)";
			//������̬SQL������
			pstmt = conn.prepareStatement(sql);
			//Ϊ��̬SQL�Ĳ�����ֵ
			int i = 1;
			pstmt.setString(i++, sysDorm.getDormId());
			pstmt.setString(i++, sysDorm.getDormsID());
			pstmt.setInt(i++, sysDorm.getCheckIn());
			pstmt.setInt(i++, sysDorm.getRest());
			
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
    public void delDorm(SysDorm sysDorm)throws Exception{
    	
    	Connection conn = dbUtil.getConn();
    	PreparedStatement pstmt = null;
		try{
			//����SQL���
			String sql = "delete from sys_dorm where dormId=?";
		    //������̬SQL���
			pstmt = conn.prepareStatement(sql);
			//Ϊ��̬SQL�Ĳ�����ֵ
			int i = 1;
			pstmt.setString(i, sysDorm.getDormId());
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
    public void editDorm(SysDorm sysDorm)throws Exception{
    	Connection conn = dbUtil.getConn();
    	PreparedStatement pstmt = null;
		try{
			//����SQL���
			String sql = "update sys_dorm set dormsID=?,checkIn=?,rest=? where dormId=?";
			StringBuffer sqlBuff=new StringBuffer();
			sqlBuff.append(sql);
			sql = sqlBuff.toString();
			//������̬SQL���
			pstmt = conn.prepareStatement(sql);
			//Ϊ��̬SQL�Ĳ�����ֵ
			int i = 1;
			pstmt.setString(i++, sysDorm.getDormsID());
			pstmt.setInt(i++, sysDorm.getCheckIn());
			pstmt.setInt(i++, sysDorm.getRest());
			pstmt.setString(i++, sysDorm.getDormId());
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
    public List<SysDorm> searchDorm(String keyword)throws Exception{
    	
    	List<SysDorm> dorm_list = new ArrayList<SysDorm>();
    	Connection conn=dbUtil.getConn();
    	//����SQL��䣬Ĭ��ģ����ѯ
    	String sql="select * from sys_dorm where 1=1";
    	//����ؼ��ֲ�Ϊ�գ����ض���SQL���
    	if(keyword != null){
    		sql = "select * from sys_dorm where "
    				+ "dormId like '%"+keyword+"%' "
    						+ "or dormsID like '%"+keyword+"%' "
    								+ "or checkIn like'%"+keyword+"%' "    										
    										+ "or rest like'%"+keyword+"%' ";
    	}
    	//����ִ�ж�̬SQL������
    	PreparedStatement pstmt = conn.prepareStatement(sql);
    	//ִ��SQL������ResultSet�����
    	ResultSet rs = pstmt.executeQuery(sql);
    	//�������ת��ΪSysDormʵ��������List
		 //�������ָ���ƶ�����һ����¼
		 while(rs.next()){
		 //ͨ��ResutlSet �ӿڷ������λ�ȡÿһ�ж�Ӧ��ֵ			 
		 SysDorm sysDorm = new SysDorm();
		 sysDorm.setDormId(rs.getString("dormId"));
		 sysDorm.setDormsID(rs.getString("dormsID"));
		 sysDorm.setCheckIn(rs.getInt("checkIn"));
		 sysDorm.setRest(rs.getInt("rest"));
		
		 dorm_list.add(sysDorm);
		 }
    	
		return dorm_list;
	}
    
    //��ʾ�б�
    public List<SysDorm> findAll()throws Exception{
    	
    	List<SysDorm> dorm_list = new ArrayList<SysDorm>();
    	Connection conn=dbUtil.getConn();
    	
			 //�����ѯSQL���
			 String sql="select * from sys_dorm where 1=1";			 
			 //����ִ�в�ѯSQL���Ķ���
			 Statement stmt=conn.createStatement();
			 //ִ�в�ѯ
			 ResultSet rs=stmt.executeQuery(sql);
			 //�������ת��ΪSysDormʵ��������List
			 //�������ָ���ƶ�����һ����¼
			 while(rs.next()){
			 //ͨ��ResutlSet �ӿڷ������λ�ȡÿһ�ж�Ӧ��ֵ			 
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
