<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="com.library.bean.Book"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%

Book book = new Book();
book = (Book) request.getAttribute("to_book");
request.getSession().setAttribute("bookname_",book.getbookname());
request.getSession().setAttribute("bookcode_",book.getbookcode());
%>
<script type="text/javascript">
window.open ('write_borrowform.jsp','newwindow1','height=700,width=1000,top=200,left=400,toolbar=no,menubar=no,scrollbars=yes, resizable=no,location=no, status=no') ;
window.close();
</script>
</body>
</html>