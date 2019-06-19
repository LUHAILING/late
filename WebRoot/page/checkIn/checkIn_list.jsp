<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/common/gloab.jsp" %>
<%@ page import="com.gxuwz.dorm.bean.entity.SysCheckIn"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
 <head>
    <base href="<%=basePath %>" >
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
    <meta name="renderer" content="webkit">
    <title>学生晚归记录系统</title>  
    <link rel="stylesheet" href="css/pintuer.css">
    <link rel="stylesheet" href="css/admin.css">
    <link rel="shortcut icon" href="images/logo.jpg">
    <script src="js/jquery.js"></script>
    <script src="js/pintuer.js"></script>   
</head>
  
  <body>
<form method="post" action="SysCheckInServlet?m=search" id="listform">
  <div class="panel admin-panel">
    <div class="panel-head"><strong class="icon-reorder"> 学生入住信息列表</strong> </div>
    <div class="padding border-bottom">
      <ul class="search" style="padding-left:10px;">
        <li> <a class="button border-main icon-plus-square-o" href="page/checkIn/checkIn_add.jsp"> 添加学生入住信息</a> </li>
        
        <li>搜索：</li>
        <li>
          <input type="text" placeholder="请输入搜索关键字" name="keywords" class="input" style="width:250px; line-height:17px;display:inline-block" />
          <input type="submit" class="button border-main icon-search" onclick="changesearch()" value="搜索"></li>
          
      </ul>
    </div>
    <table class="table table-hover text-center">
      <tr>
                <th width="100" style="text-align:left; padding-left:20px;">序号</th>
                <th>编号</th>
				<th>学号</th>
				<th>姓名</th>
				<th>班级</th>
				<th>入住宿舍</th>
				<th>入住时间</th>
				<th>操作</th>
      </tr>
    
				
				
			<%--读取所有学生入住信息记录 --%>
			<%
					request.setCharacterEncoding("utf-8");
					List<SysCheckIn> checkIn_list = new ArrayList<SysCheckIn>();
					checkIn_list = (List) request.getAttribute("checkIn_list");
					int index = 1;

					for (int i = 0; i < checkIn_list.size(); i++) {
						
						SysCheckIn sysCheckIn = checkIn_list.get(i);
				%>

			<tr>
				 <td style="text-align:left; padding-left:20px;"><input
						type="checkbox" name="id[]" value="" /><%=index++ %></td>
				<td><%=sysCheckIn.getCheckInId() %></td>
				<td><%=sysCheckIn.getStuId() %></td>
				<td><%=sysCheckIn.getStuName() %></td>
				<td><%=sysCheckIn.getClassName() %></td>
				<td><%=sysCheckIn.getDormId() %></td>
				<td><%=sysCheckIn.getTime() %></td>
				<td>
				<div class="button-group">
				 <a class="button border-main" href="<%=path%>/page/checkIn/checkIn_edit.jsp?checkInId=<%=sysCheckIn.getCheckInId() %>"><span class="icon-edit"></span> 修改</a> 
				 <a class="button border-red" href="<%=path%>/SysCheckInServlet?m=delete&checkInId=<%=sysCheckIn.getCheckInId() %>" ><span class="icon-trash-o"></span> 删除</a> 
				 </div>
				</td>
			</tr>
			<%
			}
			 %>
			
    </table>
  </div>
  <div class="pagelist"> <a href="">上一页</a> <span class="current">1</span><a href="">2</a><a href="">3</a><a href="">下一页</a><a href="">尾页</a> </div>
</form>
</body>
</html>
