<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/common/gloab.jsp" %>

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
  <div class="panel-head" id="add"><strong><span class="icon-pencil-square-o"></span>录入学生入住信息</strong></div>
  <div class="body-content">
    <form method="post" class="form-x" action="SysCheckInServlet?m=add">  
      <div class="form-group">
        <div class="label">
          <label>编号：</label>
        </div>
        <div class="field">
          <input type="text" class="input w50" value="" name="checkInId" id="checkInId" data-validate="required:数据必填项" />
          <div class="tips"></div>
        </div>
      </div>
      <div class="form-group">
        <div class="label">
          <label>学号：</label>
        </div>
        <div class="field">
          <input type="text" class="input w50" value="" name="stuId" id="stuId" data-validate="required:数据必填项" />
          <div class="tips"></div>
        </div>
      </div>
       <div class="form-group">
        <div class="label">
          <label>姓名：</label>
        </div>
        <div class="field">
          <input type="text" class="input w50" value="" name="stuName" id="stuName" data-validate="required:数据必填项" />
          <div class="tips"></div>
        </div>  
      </div>
        <div class="form-group">
        <div class="label">
          <label>班级：</label>
        </div>
        <div class="field">
          <input type="text" class="input w50" value="" name="className" id="className" data-validate="required:数据必填项" />
          <div class="tips"></div>
        </div>
      </div>
      <div class="form-group">
        <div class="label">
          <label>宿舍：</label>
        </div>
        <div class="field">
          <input type="text" class="input w50" value="" name="dormId" id="dormId" data-validate="required:数据必填项" />
          <div class="tips"></div>
        </div>
      </div>
      
      <div class="form-group">
        <div class="label">
          <label>入住时间：</label>
        </div>
        <div class="field">
          <input type="text" class="input w50" value="" name="time" id="time" data-validate="required:数据必填项" />
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
