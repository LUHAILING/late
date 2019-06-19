<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/common/gloab.jsp" %>
<%@ page import="com.gxuwz.dorm.bean.datebase.DbUtil" %>
<%@ page import="com.gxuwz.dorm.bean.entity.SysStudent" %>
<%@ page language="java" import="java.sql.*" %>
<%
    //接收参数
    String stuId = request.getParameter("stuId");
    //创建数据库连接对象
    DbUtil dbUtil = new DbUtil();
    Connection conn = dbUtil.getConn();
    //定义SQL语句
    String sql = "select * from sys_student where stuId=?";
    //创建执行动态SQL的对象
    PreparedStatement pstmt = conn.prepareStatement(sql);
    //为动态SQL的参数赋值
    pstmt.setString(1,stuId);
    //执行SQL并返回执行结果
    ResultSet rs = pstmt.executeQuery();
    //遍历结果集
    String rs_stuId ="";   
    String rs_stuName  ="";
    String rs_classId  ="";
    String rs_sex  ="";
    String rs_telephone ="";
   
    if(rs.next()){
    	   rs_stuId =rs.getString("stuId");	       
	       rs_stuName  =rs.getString("stuName");
	       rs_classId  =rs.getString("classId");
	       rs_sex    =rs.getString("sex");
	       rs_telephone =rs.getString("telephone");
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
<title>学生信息列表</title>
<link rel="stylesheet" href="css/pintuer.css">
<link rel="stylesheet" href="css/admin.css">
<link rel="shortcut icon" href="images/logo.jpg">
<script src="js/jquery.js"></script>
<script src="js/pintuer.js"></script>

</head>

<body>
<div class="panel admin-panel">
  <div class="panel-head" id="add"><strong><span class="icon-pencil-square-o"></span>编辑学生信息</strong></div>
  <div class="body-content">
    <form method="post" class="form-x" action="SysStudentServlet?m=edit">  
      <div class="form-group">
        <div class="label">
          <label>学号：</label>
        </div>
        <div class="field">
          <input type="text" class="input w50" readonly="readonly" value="<%=rs_stuId %>" name="stuId" data-validate="required:数据必填项" />
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
          <input type="text" class="input w50" value="<%=rs_classId %>" name="classId" data-validate="required:数据必填项" />
          <div class="tips"></div>
        </div>
      </div>
      <div class="form-group">
        <div class="label">
          <label>性别：</label>
        </div>
        <div class="field">
          <input type="text" class="input w50" value="<%=rs_sex %>" name="sex" data-validate="required:数据必填项" />
          <div class="tips"></div>
        </div>
      </div>
      <div class="form-group">
        <div class="label">
          <label>联系方式：</label>
        </div>
        <div class="field">
          <input type="text" class="input w50" value="<%=rs_telephone %>" name="telephone" data-validate="required:数据必填项" />
          <div class="tips"></div>
        </div>
      </div>
      
      <div class="form-group">
        <div class="label">
          <label></label>
        </div>
        <div class="field">
          <button class="button bg-main icon-check-square-o" type="submit"> 提交</button>
        </div>
      </div>
    </form>
  </div>
</div>

</body>
</html>
