<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>图书管理系统登入入口</title>
<link type="text/css"  href="css/login.css" rel="stylesheet"/>
<script type="text/javascript" src="jquery/jquery-1.8.3.js"></script>
<script src="/js/jquery/jquery-1.8.3.min.js" type="text/javascript"></script>
<script>
function userLogin(){
	
	myform.submit();
}
function check_user(field) {
	with (field) {

		if (value == "" || value.length<4||value.length>12) {
			alert("用户名不能为空且应为小于12且大于4的字符！");

			return false;
		} else
			return true;
	}
}
function check_password(field) {
	with (field) {

		if (value == "" || value.length<6||value.length>18) {
			alert("密码不能为空且应为小于18且大于6的字符！");

			return false;
		} else
			return true;
	}
}
function check_form(thisform) {
	with (thisform) {
		if (check_user(userName) == false) {
			userName.focus();
			return false;
		} else if (check_password(userPwd) == false) {
			userPwd.focus();
			return false;
		}
	}
}
</script>
</head>

<body class="r_bodybg" >

<div class="r_header"></div>
<div class="r_lcenter">
<%
		String str = (String) request.getSession().getAttribute("STR");
		if (str == "1") {
	%>
	<script>
		alert("对不起，您的账号或密码错误！");
	</script>
	<%
	  request.getSession().setAttribute("STR", "0");
		}
	%>
  <form action="LoginServlet" onsubmit="return check_form(this)"
					method="post" name="myform">
    
	
  <div class="r_lcenwid">
    <div class="r_ldzmydl">
      <a href="javascript:void(0);" onclick="calogin();">电子密钥登录</a>
    </div>
    <div class="r_lyhma">
      <label>用户名：</label>
      <input name="userName" id="userName" type="text"/>
      <div class="clear"></div>
    </div>
    <div class="r_lyhma">
      <label>密&nbsp;&nbsp;&nbsp;&nbsp;码：</label>
      <input type="password" id="userPwd" name="userPwd" />
      <div class="clear"></div>
    </div>
     <div class="r_ldl"><a href="#" onclick="userLogin();">登录</a></div>
    
  </div>
  </form>
</div>
<div class="r_footer">
 <p>技术支持：武汉科技大学 &nbsp;&nbsp;地址：洪山区白沙洲大道黄家湖西路</p>

 <p> 联系人：易向荣&nbsp;&nbsp; 电话：(027)68893313 </p>
<p>Copyright 2015 All Rights Ressrved.</p>
</div>
</body>
</html>

