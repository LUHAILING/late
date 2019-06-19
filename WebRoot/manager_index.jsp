<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/common/gloab.jsp" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<meta name="renderer" content="webkit">
<title>学生晚归记录系统</title>
<link rel="stylesheet" href="css/pintuer.css">
<link rel="stylesheet" href="css/admin.css">
<link rel="shortcut icon" href="images/logo.jpg">
<script src="js/jquery.js"></script>
<script src="js/pintuer.js"></script>

</head>

  
  <body style="background-image:url('images/img10.jpg');background-position: top;background-repeat: no-repeat;background-size: cover;">
   <%
    //获取session对象绑定的属性managerId的值
    String managerId=(String)session.getAttribute("userid");
  %>
    
    <div class="header bg-main">
  <div class="logo margin-big-left fadein-top">
    <h1><img src="images/logo.jpg" class="radius-circle rotate-hover" height="50" alt="" />学生晚归记录系统</h1>
  </div>
  <div class="head-l" ><a href="" target="_blank" style="color:#FFF" ><span class="icon-user"></span> 欢迎您！ admin</a>&nbsp;&nbsp;<a class="button button-little bg-green" href="" target="_blank" ><span class="icon-home"></span> 首页</a> &nbsp;&nbsp;<a class="button button-little bg-red" href="LoginServlet?m=out"><span class="icon-power-off"></span> 退出登录</a> </div>
</div>
<div class="leftnav">
  <div class="leftnav-title"><strong><span class="icon-list"></span>菜单列表</strong></div>
  
  
  <h2><span class="icon-user"></span>基本设置</h2>
  <ul style="display:block">
   <li><a href="page/manager/manager_info.jsp" target="right"><span class="icon-caret-right"></span>编辑个人信息</a></li>
    <li><a href="page/manager/manager_password.jsp" target="right"><span class="icon-caret-right"></span>修改密码</a></li> 
  </ul>   
  <h2><span class="icon-pencil-square-o"></span>宿舍管理</h2>
  <ul>
    <li><a href="<%=path%>/SysDormServlet?m=list" target="right"><span class="icon-caret-right"></span>宿舍信息管理</a></li>
    <li><a href="page/dorm/dorm_add.jsp" target="right"><span class="icon-caret-right"></span>录入宿舍信息</a></li>
    <li><a href="page/dorm/dorm_list.jsp" target="right"><span class="icon-caret-right"></span>查找宿舍</a></li>        
  </ul>
  <h2><span class="icon-pencil-square-o"></span>学生入住管理</h2>
  <ul>
    <li><a href=" <%=path %>/SysCheckInServlet?m=list" target="right"><span class="icon-caret-right"></span>入住信息管理</a></li>
    <li><a href="page/checkIn/checkIn_add.jsp" target="right"><span class="icon-caret-right"></span>录入学生的入住信息</a></li>
    <li><a href="page/checkIn/checkIn_list.jsp" target="right"><span class="icon-caret-right"></span>查看学生入住情况</a></li>     
  </ul>
  <h2><span class="icon-pencil-square-o"></span>学生晚归管理</h2>
  <ul>
    <li><a href=" <%=path %>/SysLateServlet?m=list" target="right"><span class="icon-caret-right"></span>晚归信息管理</a></li>
    <li><a href="page/late/late_add.jsp" target="right"><span class="icon-caret-right"></span>登记学生晚归信息</a></li>
    <li><a href="page/late/late_list.jsp" target="right"><span class="icon-caret-right"></span>查询学生晚归记录</a></li>        
  </ul>  
 
</div>
<script type="text/javascript">
$(function(){
  $(".leftnav h2").click(function(){
	  $(this).next().slideToggle(200);	
	  $(this).toggleClass("on"); 
  })
  $(".leftnav ul li a").click(function(){
	    $("#a_leader_txt").text($(this).text());
  		$(".leftnav ul li a").removeClass("on");
		$(this).addClass("on");
  })
});
</script>


<ul class="bread">
  <li><a href="{:U('Index/info')}" target="right" class="icon-home"> 首页</a></li>
  <li><a href="##" id="a_leader_txt">欢迎界面</a></li>
  <li><b>当前语言：</b><span style="color:red;">中文</php></span>
  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;切换语言：<a href="##">中文</a></li>
</ul>
<div class="admin">
  <iframe scrolling="auto" rameborder="0" src="welcome.html" name="right" width="100%" height="100%"></iframe>
</div>

  </body>
</html>
