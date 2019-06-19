package com.gxuwz.dorm.bean.manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.gxuwz.dorm.bean.datebase.DbUtil;
import com.gxuwz.dorm.bean.entity.SysClasses;

public class SysClassesManager {

private DbUtil dbUtil =new DbUtil();
	
	//���
	@SuppressWarnings("null")
	public void addClasses(SysClasses sysClasses)throws Exception{
		Connection conn = dbUtil.getConn();
		PreparedStatement pstmt = null;
		try{
			//����SQL���
			String sql = "insert into sys_classes(classId,className,depId,major,grade)";
			sql += "values(?,?,?,?,?)";
			//������̬SQL������
			pstmt = conn.prepareStatement(sql);
			//Ϊ��̬SQL�Ĳ�����ֵ
			int i = 1;
			pstmt.setString(i++, sysClasses.getClassId());
			pstmt.setString(i++, sysClasses.getClassName());
			pstmt.setString(i++, sysClasses.getDepId());
			pstmt.setString(i++, sysClasses.getMajor());
			pstmt.setString(i++, sysClasses.getGrade());
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
    public void delClasses(SysClasses sysClasses)throws Exception{
    	
    	Connection conn = dbUtil.getConn();
    	PreparedStatement pstmt = null;
		try{
			//����SQL���
			String sql = "delete from sys_classes where classId=?";
		    //������̬SQL���
			pstmt = conn.prepareStatement(sql);
			//Ϊ��̬SQL�Ĳ�����ֵ
			int i = 1;
			pstmt.setString(i, sysClasses.getClassId());
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
    public void editClasses(SysClasses sysClasses)throws Exception{
    	Connection conn = dbUtil.getConn();
    	PreparedStatement pstmt = null;
		try{
			//����SQL���
			String sql = "update sys_classes set className=?,depId=?,major=?,grade=? where classId=?";
			StringBuffer sqlBuff=new StringBuffer();
			sqlBuff.append(sql);
			sql = sqlBuff.toString();
			//������̬SQL���
			pstmt = conn.prepareStatement(sql);
			//Ϊ��̬SQL�Ĳ�����ֵ
			int i = 1;
			pstmt.setString(i++, sysClasses.getClassName());
			pstmt.setString(i++, sysClasses.getDepId());
			pstmt.setString(i++, sysClasses.getMajor());
			pstmt.setString(i++, sysClasses.getGrade());
			pstmt.setString(i++, sysClasses.getClassId());
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
    public List<SysClasses> searchClasses(String keyword)throws Exception{
    	
    	List<SysClasses> class_list = new ArrayList<SysClasses>();
    	Connection conn=dbUtil.getConn();
    	//����SQL��䣬Ĭ��ģ����ѯ
    	String sql="select * from sys_classes where 1=1";
    	//����ؼ��ֲ�Ϊ�գ����ض���SQL���
    	if(keyword != null){
    		sql = "select * from sys_classes where "
    				+ "classId like '%"+keyword+"%' "
    						+ "or className like '%"+keyword+"%' "
    								+ "or depId like'%"+keyword+"%' "
    										+ "or major like'%"+keyword+"%' "
    												+ "or grade like'%"+keyword+"%' ";
    	}
    	//����ִ�ж�̬SQL������
    	PreparedStatement pstmt = conn.prepareStatement(sql);
    	//ִ��SQL������ResultSet�����
    	ResultSet rs = pstmt.executeQuery(sql);
    	//�������ת��ΪSysClassesʵ��������List
		 //�������ָ���ƶ�����һ����¼
		 while(rs.next()){
		 //ͨ��ResutlSet �ӿڷ������λ�ȡÿһ�ж�Ӧ��ֵ			 
		 SysClasses sysClasses = new SysClasses();
		 sysClasses.setClassId(rs.getString("classId"));
		 sysClasses.setClassName(rs.getString("className"));
		 sysClasses.setDepId(rs.getString("depId"));
		 sysClasses.setMajor(rs.getString("major"));
		 sysClasses.setGrade(rs.getString("grade"));
		 
		 class_list.add(sysClasses);
		 }
    	
		return class_list;
	}
    
    //��ʾ�б�
    public List<SysClasses> findAll()throws Exception{
    	
    	List<SysClasses> class_list = new ArrayList<SysClasses>();
    	Connection conn=dbUtil.getConn();
    	
			 //�����ѯSQL���
			 String sql="select * from sys_classes where 1=1";			 
			 //����ִ�в�ѯSQL���Ķ���
			 Statement stmt=conn.createStatement();
			 //ִ�в�ѯ
			 ResultSet rs=stmt.executeQuery(sql);
			 //�������ת��ΪSysClassesʵ��������List
			 //�������ָ���ƶ�����һ����¼
			 while(rs.next()){
			 //ͨ��ResutlSet �ӿڷ������λ�ȡÿһ�ж�Ӧ��ֵ			 
			 SysClasses sysClasses = new SysClasses();
			 sysClasses.setClassId(rs.getString("classId"));
			 sysClasses.setClassName(rs.getString("className"));
			 sysClasses.setDepId(rs.getString("depId"));
			 sysClasses.setMajor(rs.getString("major"));
			 sysClasses.setGrade(rs.getString("grade"));
			 
			 class_list.add(sysClasses);
			 }
			 
			 return class_list;
    	
    }

	
	
}
