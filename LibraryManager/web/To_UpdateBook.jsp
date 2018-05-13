<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
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
book = (Book) request.getAttribute("book");
request.getSession().setAttribute("t_bookname",book.getbookname());
request.getSession().setAttribute("t_bookcode",book.getbookcode());
request.getSession().setAttribute("t_author",book.getauthor());
request.getSession().setAttribute("t_booktype",book.getbooktype());
request.getSession().setAttribute("t_press",book.getpress());
request.getSession().setAttribute("t_price",""+book.getprice());
request.getSession().setAttribute("t_printing",book.getprinting());
request.getSession().setAttribute("t_pressdate",""+book.getpressdate());
request.getSession().setAttribute("t_content",book.getcontent());
request.getSession().setAttribute("t_createuser",book.getcreateuser());
request.getSession().setAttribute("t_createdate",""+book.getcreatedate());
request.getSession().setAttribute("t_storetype",book.getstoretype());
request.getSession().setAttribute("t_donationuser",book.getdonationuser());
request.getSession().setAttribute("t_number",""+book.getnumber());
%>
<script type="text/javascript">
window.open ('updateBookInfo.jsp','newwindow1','height=700,width=1000,top=200,left=400,toolbar=no,menubar=no,scrollbars=yes, resizable=no,location=no, status=no') ;
window.close();
</script>
</body>
</html>