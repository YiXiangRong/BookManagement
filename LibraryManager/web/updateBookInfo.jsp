<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>图书入库</title>
<link type="text/css" href="css/style.css" rel="stylesheet" />
<script src="My97DatePicker/WdatePicker.js" type="text/javascript"></script>
</head>
<body class="badyauto4">
<%
String bookname=(String)request.getSession().getAttribute("t_bookname");
String bookcode=(String)request.getSession().getAttribute("t_bookcode");

String author=(String)request.getSession().getAttribute("t_author");
String booktype=(String)request.getSession().getAttribute("t_booktype");
String press=(String)request.getSession().getAttribute("t_press");
String price=(String)request.getSession().getAttribute("t_price");
String printing=(String)request.getSession().getAttribute("t_printing");
String pressdate=(String)request.getSession().getAttribute("t_pressdate");
String content=(String)request.getSession().getAttribute("t_content");

String createuser=(String)request.getSession().getAttribute("t_createuser");
String createdate=(String)request.getSession().getAttribute("t_createdate");
String storetype=(String)request.getSession().getAttribute("t_storetype");
String donationuser=(String)request.getSession().getAttribute("t_donationuser");
String number=(String)request.getSession().getAttribute("t_number");
%>


	<div class="ts_gdt" style="">
		<div class="wid_bai_tab">
			<form action="Books_Into_store?op=updatebook" name="addUpdateForm" id="addUpdateForm"
				method="post">
				<div class="m_title">
					<strong>图书信息（<font size="2" color="red">*</font><font
						size="2">为必填</font>）
					</strong>
				</div>
				<c:set value="${httpData_ServiceResult.data}" var="item" />
				<input type="hidden" name="id" value="${item.id}" /> <input
					type="hidden" name="splx" value="1" />
				<div class="info_table">
					<table class="table1" cellspacing="2" cellpadding="2" border="0">
						
						
						
						

						<tr>
							<td width="" align="right"><span><font color="red">*</font>编号：</span></td>
							<td width="" align="left" bgcolor="#FFFFFF">
								&nbsp;&nbsp;&nbsp;<input type="text" name="bookcode" value="<%=bookcode%>"
								style="float: left" />
							</td>
						</tr>
						<tr>
							<td width="" align="right"><span><font color="red">*</font>书名：</span></td>
							<td width="" align="left" bgcolor="#FFFFFF"><input
								type="text" name="bookname" style="float: left" value="<%=bookname%>"/></td>
						</tr>
						<tr>
							<td width="" class="td1" align="right"><span><font
									color="red">*</font>作者：</span></td>
							<td width="" align="left" bgcolor="#FFFFFF">&nbsp;&nbsp;&nbsp;<input
								type="text" name="author" style="float: left" value="<%=author%>"/></td>
						</tr>
						<tr>
							<td width="11%" class="td1" align="right"><span><font
									color="red">*</font>图书类型：</span></td>
							<td align="left" bgcolor="#FFFFFF">&nbsp;&nbsp;&nbsp;<input
								type="text" name="booktype" style="float: left" value="<%=booktype%>"/></td>
						</tr>
						<tr>
							<td width="11%" class="td1" align="right"><span><font
									color="red">*</font>出版社：</span></td>
							<td align="left" bgcolor="#FFFFFF"><input type="text"
								name="press" style="float: left" value="<%=press%>"/></td>
						</tr>
						<tr>
							<td width="50%" class="td1" align="right"><span><font
									color="red">*</font>价格：</span></td>
							<td align="left" bgcolor="#FFFFFF">&nbsp;&nbsp;&nbsp;<input
								type="text" name="price" style="float: left" value="<%=price%>"/></td>
						</tr>
						
						<tr>
							<td width="50%" class="td1" align="right"><span><font
									color="red">*</font>印刷：</span></td>
							<td width="50%" align="left" bgcolor="#FFFFFF">
								&nbsp;&nbsp;&nbsp;<input type="text" name="printing"
								style="float: left" value="<%=printing%>"/>
							</td>
						</tr>
						
                        <tr>
							<td width="11%" class="td1" align="right"><span><font
									color="red">*</font>出版日期</span>：</td>
							<td width="" align="left" bgcolor="#FFFFFF"><input
								type="text" name="pressdate" style="float: left" value="<%=pressdate%>" class="Wdate" onclick="WdatePicker()"/></td>
						</tr>
                        <tr>
							<td width="11%" class="td1" align="right"><span><font
									color="red">*</font>内容详情</span>：</td>
							<td width="" align="left" bgcolor="#FFFFFF"><input
								type="text" name="content" style="float: left" value="<%=content%>"/></td>
						</tr>
						<tr>
							<td width="11%" class="td1" align="right"><span><font
									color="red">*</font>入库人</span>：</td>
							<td width="" align="left" bgcolor="#FFFFFF"><input
								type="text" name="createuser" style="float: left" value="<%=createuser%>"/></td>
						</tr>
						<tr>
							<td width="11%" class="td1" align="right"><span><font
									color="red">*</font>入库日期</span>：</td>
							<td width="" align="left" bgcolor="#FFFFFF"><input
								type="text" name="createdate" style="float: left" value="<%=createdate%>" class="Wdate" onclick="WdatePicker()"/></td>
						</tr>
						<tr>
							<td width="11%" class="td1" align="right"><span><font
									color="red">*</font>入库类型</span>：</td>
							<td width="" align="left" bgcolor="#FFFFFF"><input
								type="text" name="storetype" style="float: left" value="<%=storetype%>"/>(受赠或购买)</td>
						</tr>
						<tr>
							<td width="11%" class="td1" align="right"><span>捐赠人</span>：</td>
							<td width="" align="left" bgcolor="#FFFFFF"><input
								type="text" name="donationuser" style="float: left" value="<%=donationuser%>"/></td>
						</tr>
                        <tr>
							<td width="11%" class="td1" align="right"><span><font
									color="red">*</font>此书库存量</span>：</td>
							<td width="" align="left" bgcolor="#FFFFFF"><input
								type="text" name="number" style="float: left" value="<%=number%>"/></td>
						</tr>
					</table>
				</div>

				<div class="search_con">
					<center>
						<input type="submit" name="button" id="button" value="保存"
							class="gs_but2ss" />&nbsp;
                        <input type="hidden" value="<%=bookcode%>" name="n"></input>
					</center>
				</div>
			</form>
		</div>
	</div>
<%//判断图书编号重复
		String str = (String) request.getSession().getAttribute("book_upF");
		if (str == "1") {
%>
	<script>
		alert("图书编号已存在！");
	</script>
	<%
	request.getSession().setAttribute("book_upF", "0");}
%>

<%//判断是否图书修改成功
		String str2 = (String) request.getSession().getAttribute("STR_11");
		if (str2 == "1") {
%>
<form id="myForm" method="post" target="detail"></form>
	<script>
	    //关闭窗口后重新查询
		alert("修改成功！");
		var form = document.getElementById('myForm');//获取表单dom
	    form.action="Books_Into_store?op=refresh";//重新设置提交url
	    form.submit();//提交表单
	    window.close();//关闭窗口
		
	</script>
<%}else if(str2 == "2"){%>


	<script>
		alert("修改失败，稍后再试！");
	</script>
<% }
request.getSession().setAttribute("STR_11", "0");
%>


</body>
</html>
