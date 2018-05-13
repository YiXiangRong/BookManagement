<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@page import="com.library.bean.User"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%

User user=new User();
user=(User)request.getAttribute("user");
request.getSession().setAttribute("is_userName",user.getUserName());
request.getSession().setAttribute("is_userPwd",user.getUserPwd());
request.getSession().setAttribute("is_name",user.getname());
request.getSession().setAttribute("is_codetype",user.getcodetype());
request.getSession().setAttribute("is_codeno",user.getcodeno());
request.getSession().setAttribute("is_emall",user.getemall());
request.getSession().setAttribute("is_address",user.getaddress());
request.getSession().setAttribute("is_type",""+user.gettype());
request.getSession().setAttribute("is_createdate",user.getcreatedate());
request.getSession().setAttribute("is_phone",user.getphone());

%>
<script type="text/javascript">
window.open ('updateUserInfo.jsp','newwindow1','height=700,width=1000,top=200,left=400,toolbar=no,menubar=no,scrollbars=yes, resizable=no,location=no, status=no') ;
window.close();
</script>
</body>
</html>