 <%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/common/gloab.jsp" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<meta name="renderer" content="webkit">
<title>登录</title>
<link rel="stylesheet" href="css/pintuer.css">
<link rel="stylesheet" href="css/admin.css">
<link rel="shortcut icon" href="images/logo.jpg">
<script src="js/jquery.js"></script>
<script src="js/pintuer.js"></script>

</head>
<body style="background: url(images/bgreg.jpg);background-size:100% 100%;background-attachment: fixed">
 
<div class="container">
    <div class="line bouncein">
        <div class="xs6 xm4 xs3-move xm4-move">
            <div style="height:100px;">
            </div>
            <div class="media media-y margin-big-bottom">           
            </div>         
            <form id="loginForm" action="LoginServlet?m=do" method="post">
            <div class="panel loginbox">
                <div class="text-center margin-big padding-big-top"><h1><img src="images/logo.jpg" class="radius-circle rotate-hover" height="50" alt="" /> 学 生 晚 归 记 录 系 统</h1>
                </div>
                <div class="panel-body" style="padding:30px; padding-bottom:10px; padding-top:10px;">
                    <div class="form-group">
                        <div class="field field-icon-right">
                            <input type="text" class="input input-big" name="userid" placeholder="登录账号" data-validate="required:请填写账号" />
                            <span class="icon icon-user margin-small"></span>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="field field-icon-right">
                            <input type="password" class="input input-big" name="password" value="666666" placeholder="登录密码" data-validate="required:请填写密码" />
                            <span class="icon icon-key margin-small"></span>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="field">
                            <input type="text" class="input input-big" name="code" placeholder="填写右侧的验证码" value="6982" data-validate="required:请填写右侧的验证码" />
                            <img src="images/passcode.jpg" alt="" width="100" height="32" class="passcode" style="height:43px;cursor:pointer;" onclick="this.src=this.src+'?'">                                 
                        </div>
                    </div>
                    <div style="color:#333333;font-size:16px;font-weight:bold">
                    <label>宿舍管员</label><input type="radio" checked="checked" name="identype" value="0" />
                    <label>辅导员</label><input type="radio" name="identype" value="1" />
                    <label>系统管理员</label><input type="radio" name="identype" value="2" />
                    </div>
                </div>
                <div style="padding:30px;"><input type="submit" class="button button-block bg-main text-big input-big" value="登录"></div>
            </div>
            </form>          
        </div>
    </div>
</div>

</body>
</html>
