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
	
	//���
	@SuppressWarnings("null")
	public void addManager(SysManager sysManager)throws Exception{
		Connection conn = dbUtil.getConn();
		PreparedStatement pstmt = null;
		try{
			//����SQL���
			String sql = "insert into sys_manager(managerId,managerName,sex,telephone,password)";
			sql += "values(?,?,?,?,?)";
			//������̬SQL������
			pstmt = conn.prepareStatement(sql);
			//Ϊ��̬SQL�Ĳ�����ֵ
			int i = 1;
			pstmt.setString(i++, sysManager.getManagerId());
			pstmt.setString(i++, sysManager.getManagerName());
			pstmt.setString(i++, sysManager.getSex());
			pstmt.setString(i++, sysManager.getTelephone());
			pstmt.setString(i++, sysManager.getPassword());
			
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
    public void delManager(SysManager sysManager)throws Exception{
    	
    	Connection conn = dbUtil.getConn();
    	PreparedStatement pstmt = null;
		try{
			//����SQL���
			String sql = "delete from sys_manager where managerId=?";
		    //������̬SQL���
			pstmt = conn.prepareStatement(sql);
			//Ϊ��̬SQL�Ĳ�����ֵ
			int i = 1;
			pstmt.setString(i, sysManager.getManagerId());
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
    public void editManager(SysManager sysManager)throws Exception{
    	Connection conn = dbUtil.getConn();
    	PreparedStatement pstmt = null;
		try{
			//����SQL���
			String sql = "update sys_manager set managerName=?,sex=?,telephone=?,password=? where managerId=?";
			StringBuffer sqlBuff=new StringBuffer();
			sqlBuff.append(sql);
			sql = sqlBuff.toString();
			//������̬SQL���
			pstmt = conn.prepareStatement(sql);
			//Ϊ��̬SQL�Ĳ�����ֵ
			int i = 1;
			pstmt.setString(i++, sysManager.getManagerName());
			pstmt.setString(i++, sysManager.getSex());
			pstmt.setString(i++, sysManager.getTelephone());
			pstmt.setString(i++, sysManager.getPassword());			
			pstmt.setString(i++, sysManager.getManagerId());
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
    public void infoManager(SysManager sysManager)throws Exception{
    	Connection conn = dbUtil.getConn();
    	PreparedStatement pstmt = null;
		try{
			//����SQL���
			String sql = "update sys_manager set managerName=?,sex=?,telephone=? where managerId=?";
			StringBuffer sqlBuff=new StringBuffer();
			sqlBuff.append(sql);
			sql = sqlBuff.toString();
			//������̬SQL���
			pstmt = conn.prepareStatement(sql);
			//Ϊ��̬SQL�Ĳ�����ֵ
			int i = 1;
			pstmt.setString(i++, sysManager.getManagerName());
			pstmt.setString(i++, sysManager.getSex());
			pstmt.setString(i++, sysManager.getTelephone());				
			pstmt.setString(i++, sysManager.getManagerId());
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
    public List<SysManager> searchManager(String keyword)throws Exception{
    	
    	List<SysManager> manager_list = new ArrayList<SysManager>();
    	Connection conn=dbUtil.getConn();
    	//����SQL��䣬Ĭ��ģ����ѯ
    	String sql="select * from sys_manager where 1=1";
    	//����ؼ��ֲ�Ϊ�գ����ض���SQL���
    	if(keyword != null){
    		sql = "select * from sys_manager where "
    				+ "managerId like '%"+keyword+"%' "
    						+ "or managerName like '%"+keyword+"%' "
    								+ "or sex like'%"+keyword+"%'";
    	}
    	//����ִ�ж�̬SQL������
    	PreparedStatement pstmt = conn.prepareStatement(sql);
    	//ִ��SQL������ResultSet�����
    	ResultSet rs = pstmt.executeQuery(sql);
    	//�������ת��ΪSysManagerʵ��������List
		 //�������ָ���ƶ�����һ����¼
		 while(rs.next()){
		 //ͨ��ResutlSet �ӿڷ������λ�ȡÿһ�ж�Ӧ��ֵ			 
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
    
    //��ʾ�б�
    public List<SysManager> findAll()throws Exception{
    	
    	List<SysManager> manager_list = new ArrayList<SysManager>();
    	Connection conn=dbUtil.getConn();
    	
			 //�����ѯSQL���
			 String sql="select * from sys_manager where 1=1";			 
			 //����ִ�в�ѯSQL���Ķ���
			 Statement stmt=conn.createStatement();
			 //ִ�в�ѯ
			 ResultSet rs=stmt.executeQuery(sql);
			 //�������ת��ΪSysManagerʵ��������List
			 //�������ָ���ƶ�����һ����¼
			 while(rs.next()){
			 //ͨ��ResutlSet �ӿڷ������λ�ȡÿһ�ж�Ӧ��ֵ			 
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
