<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>填写还书单</title>
<link type="text/css" href="css/style.css" rel="stylesheet" />
<script src="My97DatePicker/WdatePicker.js" type="text/javascript"></script>
function close(){
	window.close()
}
</head>
<body class="badyauto4">

<%
String bookcode=(String) request.getSession().getAttribute("book_code"); 
String bookname=(String) request.getSession().getAttribute("book_name"); 
%>	

	<div class="ts_gdt" style="">
		<div class="wid_bai_tab">
			<form action="Jie_Huan_Shu?op=huanshu" name="addUpdateForm" id="addUpdateForm"
				method="post">
				<div class="m_title">
					<strong>还书单信息（<font size="2" color="red">*</font><font
						size="2">为必填</font>）
					</strong>
				</div>
				<c:set value="${httpData_ServiceResult.data}" var="item" />
				<input type="hidden" name="id" value="${item.id}" /> <input
					type="hidden" name="splx" value="1" />
				<div class="info_table">
					<table class="table1" cellspacing="2" cellpadding="2" border="0">
						
						<tr>
							<td width="" align="right"><span><font color="red">*</font>图书编号：</span></td>
							<td width="" align="left" bgcolor="#FFFFFF">
								&nbsp;&nbsp;&nbsp;<input type="text" name="BOOKCODE" value="<%=bookcode%>" readonly="true" 
								style="float: left" />
							</td>
						</tr>
						<tr>
							<td width="" align="right"><span><font color="red">*</font>图书名：</span></td>
							<td width="" align="left" bgcolor="#FFFFFF">
								&nbsp;&nbsp;&nbsp;<input type="text" name="BOOKNAME" value="<%=bookname%>" readonly="true" 
								style="float: left" />
							</td>
						</tr>
						<tr>
							<td width="" align="right"><span><font color="red">*</font>借书证号：</span></td>
							<td width="" align="left" bgcolor="#FFFFFF">
								&nbsp;&nbsp;&nbsp;<input type="text" name="LIBCARDNO"
								style="float: left" />
							</td>
						</tr>
						
						<tr>
							<td width="" class="td1" align="right"><span><font
									color="red">*</font>还书日期：</span></td>
							<td width="" align="left" bgcolor="#FFFFFF">&nbsp;&nbsp;&nbsp;<input
								type="text" name="RETURNDATE" style="float: left" class="Wdate" onclick="WdatePicker()"/></td>
						</tr>
						<tr>
							<td width="11%" class="td1" align="right"><span><font
									color="red">*</font>还书受理人：</span></td>
							<td align="left" bgcolor="#FFFFFF">&nbsp;&nbsp;&nbsp;<input
								type="text" name="RETURNMANAGER" style="float: left" /></td>
						</tr>
						
						
					</table>
				</div>

				<div class="search_con">
					<center>
						<input type="submit" name="button" id="button" value="提交"
							class="gs_but2ss" />&nbsp;
                        <input type="button" name="button" id="button" onclick="close()" value="取消"
							class="gs_but2sss" />&nbsp;
					</center>
				</div>
			</form>
		</div>
	</div>


<%//判断是否还书成功
		String str2 = (String) request.getSession().getAttribute("STR_14");
		if(str2 == "3") {
%>
	

	<script>
		alert("还书失败，稍后再试！");
		window.close();
	</script>
<%}
request.getSession().setAttribute("STR_14", "4");
%>


</body>
</html>
