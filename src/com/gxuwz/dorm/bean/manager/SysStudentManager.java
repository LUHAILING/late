package com.gxuwz.dorm.bean.manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.gxuwz.dorm.bean.datebase.*;
import com.gxuwz.dorm.bean.entity.SysStudent;

public class SysStudentManager {

	private DbUtil dbUtil =new DbUtil();
	
	//���
	@SuppressWarnings("null")
	public void addStudent(SysStudent sysStudent)throws Exception{
		Connection conn = dbUtil.getConn();
		PreparedStatement pstmt = null;
		try{
			//����SQL���
			String sql = "insert into sys_student(stuId,stuName,classId,sex,telephone)";
			sql += "values(?,?,?,?,?)";
			//������̬SQL������
			pstmt = conn.prepareStatement(sql);
			//Ϊ��̬SQL�Ĳ�����ֵ
			int i = 1;
			pstmt.setString(i++, sysStudent.getStuId());
			pstmt.setString(i++, sysStudent.getStuName());
			pstmt.setString(i++, sysStudent.getClassId());
			pstmt.setString(i++, sysStudent.getSex());
			pstmt.setString(i++, sysStudent.getTelephone());
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
    public void delStudent(SysStudent sysStudent)throws Exception{
    	
    	Connection conn = dbUtil.getConn();
    	PreparedStatement pstmt = null;
		try{
			//����SQL���
			String sql = "delete from sys_student where stuId=?";
		    //������̬SQL���
			pstmt = conn.prepareStatement(sql);
			//Ϊ��̬SQL�Ĳ�����ֵ
			int i = 1;
			pstmt.setString(i, sysStudent.getStuId());
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
    public void editStudent(SysStudent sysStudent)throws Exception{
    	Connection conn = dbUtil.getConn();
    	PreparedStatement pstmt = null;
		try{
			//����SQL���
			String sql = "update sys_student set stuName=?,classId=?,sex=?,telephone=? where stuId=?";
			StringBuffer sqlBuff=new StringBuffer();
			sqlBuff.append(sql);
			sql = sqlBuff.toString();
			//������̬SQL���
			pstmt = conn.prepareStatement(sql);
			//Ϊ��̬SQL�Ĳ�����ֵ
			int i = 1;
			pstmt.setString(i++, sysStudent.getStuName());
			pstmt.setString(i++, sysStudent.getClassId());
			pstmt.setString(i++, sysStudent.getSex());
			pstmt.setString(i++, sysStudent.getTelephone());
			pstmt.setString(i++, sysStudent.getStuId());
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
    public List<SysStudent> searchStudent(String keyword)throws Exception{
    	
    	List<SysStudent> student_list = new ArrayList<SysStudent>();
    	Connection conn=dbUtil.getConn();
    	//����SQL��䣬Ĭ��ģ����ѯ
    	String sql="select * from sys_student where 1=1";
    	//����ؼ��ֲ�Ϊ�գ����ض���SQL���
    	if(keyword != null){
    		sql = "select * from sys_student where stuId like '%"+keyword+"%' or stuName like '%"+keyword+"%' or classId like'%"+keyword+"%'";
    	}
    	//����ִ�ж�̬SQL������
    	PreparedStatement pstmt = conn.prepareStatement(sql);
    	//ִ��SQL������ResultSet�����
    	ResultSet rs = pstmt.executeQuery(sql);
    	//�������ת��ΪSysStudentʵ��������List
		 //�������ָ���ƶ�����һ����¼
		 while(rs.next()){
		 //ͨ��ResutlSet �ӿڷ������λ�ȡÿһ�ж�Ӧ��ֵ			 
		 SysStudent sysStudent = new SysStudent();
		 sysStudent.setStuId(rs.getString("stuId"));
		 sysStudent.setStuName(rs.getString("stuName"));
		 sysStudent.setClassId(rs.getString("classId"));
		 sysStudent.setSex(rs.getString("sex"));
		 sysStudent.setTelephone(rs.getString("telephone"));
		 
		 student_list.add(sysStudent);
		 }
    	
		return student_list;
	}
    
    //��ʾ�б�
    public List<SysStudent> findAll()throws Exception{
    	
    	List<SysStudent> student_list = new ArrayList<SysStudent>();
    	Connection conn=dbUtil.getConn();
    	
			 //�����ѯSQL���
			 String sql="select * from sys_student where 1=1";			 
			 //����ִ�в�ѯSQL���Ķ���
			 Statement stmt=conn.createStatement();
			 //ִ�в�ѯ
			 ResultSet rs=stmt.executeQuery(sql);
			 //�������ת��ΪSysStudentʵ��������List
			 //�������ָ���ƶ�����һ����¼
			 while(rs.next()){
			 //ͨ��ResutlSet �ӿڷ������λ�ȡÿһ�ж�Ӧ��ֵ			 
			 SysStudent sysStudent = new SysStudent();
			 sysStudent.setStuId(rs.getString("stuId"));
			 sysStudent.setStuName(rs.getString("stuName"));
			 sysStudent.setClassId(rs.getString("classId"));
			 sysStudent.setSex(rs.getString("sex"));
			 sysStudent.setTelephone(rs.getString("telephone"));
			 
			 student_list.add(sysStudent);
			 }
			 
			 return student_list;
    	
    }

	
	
}
