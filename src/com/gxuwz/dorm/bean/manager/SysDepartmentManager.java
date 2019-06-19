package com.gxuwz.dorm.bean.manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.gxuwz.dorm.bean.datebase.DbUtil;
import com.gxuwz.dorm.bean.entity.SysDepartment;

public class SysDepartmentManager {

private DbUtil dbUtil =new DbUtil();
	
	//���
	@SuppressWarnings("null")
	public void addDepartment(SysDepartment sysDepartment)throws Exception{
		Connection conn = dbUtil.getConn();
		PreparedStatement pstmt = null;
		try{
			//����SQL���
			String sql = "insert into sys_department(depId,depName)";
			sql += "values(?,?)";
			//������̬SQL������
			pstmt = conn.prepareStatement(sql);
			//Ϊ��̬SQL�Ĳ�����ֵ
			int i = 1;
			
			pstmt.setString(i++, sysDepartment.getDepId());
			pstmt.setString(i++, sysDepartment.getDepName());
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
    public void delDepartment(SysDepartment sysDepartment)throws Exception{
    	
    	Connection conn = dbUtil.getConn();
    	PreparedStatement pstmt = null;
		try{
			//����SQL���
			String sql = "delete from sys_department where depId=?";
		    //������̬SQL���
			pstmt = conn.prepareStatement(sql);
			//Ϊ��̬SQL�Ĳ�����ֵ
			int i = 1;
			pstmt.setString(i, sysDepartment.getDepId());
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
    public void editDepartment(SysDepartment sysDepartment)throws Exception{
    	Connection conn = dbUtil.getConn();
    	PreparedStatement pstmt = null;
		try{
			//����SQL���
			String sql = "update sys_department set depName=? where depId=?";
			StringBuffer sqlBuff=new StringBuffer();
			sqlBuff.append(sql);
			sql = sqlBuff.toString();
			//������̬SQL���
			pstmt = conn.prepareStatement(sql);
			//Ϊ��̬SQL�Ĳ�����ֵ
			int i = 1;
			pstmt.setString(i++, sysDepartment.getDepName());
			pstmt.setString(i++, sysDepartment.getDepId());
			
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
    public List<SysDepartment> searchDepartment(String keyword)throws Exception{
    	
    	List<SysDepartment> dep_list = new ArrayList<SysDepartment>();
    	Connection conn=dbUtil.getConn();
    	//����SQL��䣬Ĭ��ģ����ѯ
    	String sql="select * from sys_department where 1=1";
    	//����ؼ��ֲ�Ϊ�գ����ض���SQL���
    	if(keyword != null){
    		sql = "select * from sys_department where "
    				+ "depId like '%"+keyword+"%' "
    						+ "or depName like '%"+keyword+"%'";
    	}
    	//����ִ�ж�̬SQL������
    	PreparedStatement pstmt = conn.prepareStatement(sql);
    	//ִ��SQL������ResultSet�����
    	ResultSet rs = pstmt.executeQuery(sql);
    	//�������ת��ΪSysDepartmentʵ��������List
		 //�������ָ���ƶ�����һ����¼
		 while(rs.next()){
		 //ͨ��ResutlSet �ӿڷ������λ�ȡÿһ�ж�Ӧ��ֵ			 
		 SysDepartment sysDepartment = new SysDepartment();
		 sysDepartment.setDepId(rs.getString("depId"));
		 sysDepartment.setDepName(rs.getString("depName"));
		 
		 dep_list.add(sysDepartment);
		 }
    	
		return dep_list;
	}
    
    //��ʾ�б�
    public List<SysDepartment> findAll()throws Exception{
    	
    	List<SysDepartment> dep_list = new ArrayList<SysDepartment>();
    	Connection conn=dbUtil.getConn();
    	
			 //�����ѯSQL���
			 String sql="select * from sys_department where 1=1";			 
			 //����ִ�в�ѯSQL���Ķ���
			 Statement stmt=conn.createStatement();
			 //ִ�в�ѯ
			 ResultSet rs=stmt.executeQuery(sql);
			 //�������ת��ΪSysDepartmentʵ��������List
			 //�������ָ���ƶ�����һ����¼
			 while(rs.next()){
			 //ͨ��ResutlSet �ӿڷ������λ�ȡÿһ�ж�Ӧ��ֵ			 
			 SysDepartment sysDepartment = new SysDepartment();
			 sysDepartment.setDepId(rs.getString("depId"));
			 sysDepartment.setDepName(rs.getString("depName"));
			 
			 dep_list.add(sysDepartment);
			 }
			 
			 return dep_list;
    	
    }

	
	
}
