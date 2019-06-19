package com.gxuwz.dorm.bean.manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.gxuwz.dorm.bean.datebase.DbUtil;
import com.gxuwz.dorm.bean.entity.SysAdmin;

public class SysAdminManager {
	private DbUtil dbUtil =new DbUtil();
	
	//�༭������Ϣ
    public void infoAdmin(SysAdmin sysAdmin)throws Exception{
    	Connection conn = dbUtil.getConn();
    	PreparedStatement pstmt = null;
		try{
			//����SQL���
			String sql = "update sys_admin set fullname=?,telephone=? where userid=?";
			StringBuffer sqlBuff=new StringBuffer();
			sqlBuff.append(sql);
			sql = sqlBuff.toString();
			//������̬SQL���
			pstmt = conn.prepareStatement(sql);
			//Ϊ��̬SQL�Ĳ�����ֵ
			int i = 1;
			pstmt.setString(i++, sysAdmin.getFullname());
			pstmt.setString(i++, sysAdmin.getTelephone());				
			pstmt.setString(i++, sysAdmin.getUserid());
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
    

}
