<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.library.bean.Libcard"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%
Libcard libcard = new Libcard();
libcard = (Libcard) request.getAttribute("libcard");
request.getSession().setAttribute("to_libcardno",libcard.getlibcardno());
request.getSession().setAttribute("to_address",libcard.getaddress());
request.getSession().setAttribute("to_cardno",libcard.getcardno());
request.getSession().setAttribute("to_cardtype",libcard.getcardtype());
request.getSession().setAttribute("to_createdate",libcard.getcreatedate());
request.getSession().setAttribute("to_createuser",libcard.getcreateuser());
request.getSession().setAttribute("to_effectivedate",libcard.geteffectivedate());
request.getSession().setAttribute("to_emall",libcard.getemall());
request.getSession().setAttribute("to_name",libcard.getname());
request.getSession().setAttribute("to_phone",libcard.getphone());
%>
<script type="text/javascript">
window.open ('updatelibcardInfo.jsp','newwindow1','height=700,width=1000,top=200,left=400,toolbar=no,menubar=no,scrollbars=yes, resizable=no,location=no, status=no') ;
window.close();
</script>
</body>
</html>