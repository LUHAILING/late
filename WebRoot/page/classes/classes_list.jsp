<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/common/gloab.jsp"%>
<%@ page import="com.gxuwz.dorm.bean.entity.SysClasses"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, classes-scalable=no" />
<meta name="renderer" content="webkit">
<title>学生晚归记录系统</title>
<link rel="stylesheet" href="css/pintuer.css">
<link rel="stylesheet" href="css/admin.css">
<link rel="shortcut icon" href="images/logo.jpg">
<script src="js/jquery.js"></script>
<script src="js/pintuer.js"></script>

</head>

<body>
	<form method="post" action="<%=path %>/SysClassesServlet?m=search" id="listform">
		<div class="panel admin-panel">
			<div class="panel-head">
				<strong class="icon-reorder"> 班级信息列表</strong>
			</div>
			<div class="padding border-bottom">
				<ul class="search" style="padding-left:10px;">
					<li><a class="button border-main icon-plus-square-o"
						href="page/classes/classes_add.jsp"> 录入班级信息</a></li>
					<li>搜索：</li>
					<li><input type="text" placeholder="请输入搜索关键字" name="keywords"
							class="input"
							style="width:250px; line-height: 17px; display: inline-block;" /> <input
							type="submit" class="button border-main icon-search"
							onclick="changesearch()" value="搜索"></li>
				</ul>
			</div>
			<table class="table table-hover text-center">
				<tr>
					<th width="100" style="text-align:left; padding-left:20px;">序号</th>
					<th>班级编号</th>
					<th>班级名称</th>
					<th>所属二级学院</th>
					<th>专业</th>
					<th>年级</th>
					<th>操作</th>
				</tr>

                 <%--读取所有班级信息记录 --%>
				<%
					request.setCharacterEncoding("utf-8");
					List<SysClasses> class_list = new ArrayList<SysClasses>();
					class_list = (List) request.getAttribute("class_list");
					int index = 1;

					for (int i = 0; i < class_list.size(); i++) {
						SysClasses sysClasses = class_list.get(i);
				%>



				<tr>
					<td style="text-align:left; padding-left:20px;"><input
						type="checkbox" name="id[]" value="" /><%=index++%></td>

					<td><%=sysClasses.getClassId()%></td>
					<td><%=sysClasses.getClassName()%></td>
					<td><%=sysClasses.getDepId()%></td>
					<td><%=sysClasses.getMajor()%></td>
					<td><%=sysClasses.getGrade()%></td>
					<td>
						<div class="button-group">
							<a class="button border-main"
								href="<%=path%>/page/classes/classes_edit.jsp?classId=<%=sysClasses.getClassId()%>"><span
								class="icon-edit"></span> 修改</a> <a class="button border-red"
								href="<%=path%>/SysClassesServlet?m=delete&classId=<%=sysClasses.getClassId()%>"><span
								class="icon-trash-o"></span> 删除</a>
						</div>
					</td>
				</tr>
				
				<%
					}
				%>

			</table>
		</div>
		<div class="pagelist">
			<a href="">上一页</a> <span class="current">1</span><a href="">2</a><a
				href="">3</a><a href="">下一页</a><a href="">尾页</a>
		</div>
	</form>
</body>
</html>
