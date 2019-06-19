<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/common/gloab.jsp" %>
<%@ page import="com.gxuwz.dorm.bean.datebase.DbUtil" %>
<%@ page import="com.gxuwz.dorm.bean.entity.SysDorm" %>
<%@ page language="java" import="java.sql.*" %>
<%
    //接收参数
    String dormId = request.getParameter("dormId");
    //创建数据库连接对象
    DbUtil dbUtil = new DbUtil();
    Connection conn = dbUtil.getConn();
    //定义SQL语句
    String sql = "select * from sys_dorm where dormId=?";
    //创建执行动态SQL的对象
    PreparedStatement pstmt = conn.prepareStatement(sql);
    //为动态SQL的参数赋值
    pstmt.setString(1,dormId);
    //执行SQL并返回执行结果
    ResultSet rs = pstmt.executeQuery();
    //遍历结果集
    String rs_dormId ="";   
    String rs_dormsID ="";
    String rs_checkIn  ="";
    String rs_rest  ="";
   
   
    if(rs.next()){
    	   rs_dormId =rs.getString("dormId");	       
	       rs_dormsID =rs.getString("dormsID");
	       rs_checkIn  =rs.getString("checkIn");
	       rs_rest    =rs.getString("rest");
	      
    }

%> 

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, classes-scalable=no" />
<meta name="renderer" content="webkit">
<title>宿舍信息列表</title>
<link rel="stylesheet" href="css/pintuer.css">
<link rel="stylesheet" href="css/admin.css">
<link rel="shortcut icon" href="images/logo.jpg">
<script src="js/jquery.js"></script>
<script src="js/pintuer.js"></script>

</head>
  
  <body>
<div class="panel admin-panel">
  <div class="panel-head" id="add"><strong><span class="icon-pencil-square-o"></span>编辑宿舍信息</strong></div>
  <div class="body-content">
    <form method="post" class="form-x" action="SysDormServlet?m=edit">  
      <div class="form-group">
        <div class="label">
          <label>宿舍号：</label>
        </div>
        <div class="field">
          <input type="text" class="input w50" readonly="readonly" value="<%=rs_dormId%>" name="dormId" data-validate="required:数据必填项" />
          <div class="tips"></div>
        </div>
      </div>
       
        <div class="form-group">
        <div class="label">
          <label>宿舍楼号：</label>
        </div>
        <div class="field">
          <input type="text" class="input w50" value="<%=rs_dormsID %>" name="dormsID" data-validate="required:数据必填项" />
          <div class="tips"></div>
        </div>
      </div>
   
 	<div class="form-group">
        <div class="label">
          <label>已入住人数：</label>
        </div>
        <div class="field">
          <input type="text" class="input w50" value="<%=rs_checkIn %>" name="checkIn" data-validate="required:数据必填项" />
          <div class="tips"></div>
        </div>
      </div>
      <div class="form-group">
        <div class="label">
          <label>空床位数：</label>
        </div>
        <div class="field">
          <input type="text" class="input w50" value="<%=rs_rest %>" name="rest" data-validate="required:数据必填项" />
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
