<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/common/gloab.jsp" %>
<%@ page import="com.gxuwz.dorm.bean.datebase.DbUtil" %>
<%@ page import="com.gxuwz.dorm.bean.entity.SysCheckIn" %>
<%@ page language="java" import="java.sql.*" %>
<%
    //接收参数
    String checkInId = request.getParameter("checkInId");
    //创建数据库连接对象
    DbUtil dbUtil = new DbUtil();
    Connection conn = dbUtil.getConn();
    //定义SQL语句
    String sql = "select * from sys_checkIn where checkInId=?";
    //创建执行动态SQL的对象
    PreparedStatement pstmt = conn.prepareStatement(sql);
    //为动态SQL的参数赋值
    pstmt.setString(1,checkInId);
    //执行SQL并返回执行结果
    ResultSet rs = pstmt.executeQuery();
    //遍历结果集
    String rs_checkInId ="";
    String rs_stuId ="";   
    String rs_stuName  ="";
    String rs_className  ="";
    String rs_dormId  ="";
    String rs_time ="";
   
    if(rs.next()){
           rs_checkInId =rs.getString("checkInId");
    	   rs_stuId =rs.getString("stuId");	       
	       rs_stuName  =rs.getString("stuName");
	       rs_className  =rs.getString("className");
	       rs_dormId    =rs.getString("dormId");
	       rs_time =rs.getString("time");
    }

%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, student-scalable=no" />
<meta name="renderer" content="webkit">
<title>学生入住信息列表</title>
<link rel="stylesheet" href="css/pintuer.css">
<link rel="stylesheet" href="css/admin.css">
<link rel="shortcut icon" href="images/logo.jpg">
<script src="js/jquery.js"></script>
<script src="js/pintuer.js"></script>

</head>
  
  <body>
<div class="panel admin-panel">
  <div class="panel-head" id="add"><strong><span class="icon-pencil-square-o"></span>编辑学生入住信息</strong></div>
  <div class="body-content">
    <form method="post" class="form-x" action="SysCheckInServlet?m=edit">  
      <div class="form-group">
        <div class="label">
          <label>编号：</label>
        </div>
        <div class="field">
          <input type="text" class="input w50" readonly="readonly" value="<%=rs_checkInId %>" name="checkInId" data-validate="required:数据必填项" />
          <div class="tips"></div>
        </div>
      </div>
      <div class="form-group">
        <div class="label">
          <label>学号：</label>
        </div>
        <div class="field">
          <input type="text" class="input w50" value="<%=rs_stuId %>" name="stuId" data-validate="required:数据必填项" />
          <div class="tips"></div>
        </div>
      </div>
       <div class="form-group">
        <div class="label">
          <label>姓名：</label>
        </div>
        <div class="field">
          <input type="text" class="input w50" value="<%=rs_stuName %>" name="stuName" data-validate="required:数据必填项" />
          <div class="tips"></div>
        </div>  
      </div>
        <div class="form-group">
        <div class="label">
          <label>班级：</label>
        </div>
        <div class="field">
          <input type="text" class="input w50" value="<%=rs_className %>" name="className" data-validate="required:数据必填项" />
          <div class="tips"></div>
        </div>
      </div>
      <div class="form-group">
        <div class="label">
          <label>宿舍：</label>
        </div>
        <div class="field">
          <input type="text" class="input w50" value="<%=rs_dormId %>" name="dormId" data-validate="required:数据必填项" />
          <div class="tips"></div>
        </div>
      </div>
      
      <div class="form-group">
        <div class="label">
          <label>入住时间：</label>
        </div>
        <div class="field">
          <input type="text" class="input w50" value="<%=rs_time %>" name="time" data-validate="required:数据必填项" />
          <div class="tips"></div>
        </div>
      </div>     
      
      <div class="form-group">
        <div class="label">
          <label></label>
        </div>
        <div class="field">
          <input class="button bg-main icon-check-square-o" type="submit"
									name="addcheckIn_btn" value="提交">
        </div>
      </div>
    </form>
  </div>
</div>

</body>
</html>
