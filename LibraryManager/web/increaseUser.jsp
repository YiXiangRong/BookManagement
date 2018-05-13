<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>增加管理员</title>
<link type="text/css" href="css/style.css" rel="stylesheet" />
<script src="My97DatePicker/WdatePicker.js" type="text/javascript"></script>
</head>
<body class="badyauto4">
	



	<div class="ts_gdt" style="">
		<div class="wid_bai_tab">
			<form action="UserManage?op=increase" name="addUpdateForm" id="addUpdateForm"
				method="post">
				<div class="m_title">
					<strong>管理员信息（<font size="2" color="red">*</font><font
						size="2">为必填</font>）
					</strong>
				</div>
				<c:set value="${httpData_ServiceResult.data}" var="item" />
				<input type="hidden" name="id" value="${item.id}" /> <input
					type="hidden" name="splx" value="1" />
				<div class="info_table">
					<table class="table1" cellspacing="2" cellpadding="2" border="0">
						<tr>
							<td width="" align="right"><span><font color="red">*</font>账号：</span></td>
							<td width="" align="left" bgcolor="#FFFFFF">
								&nbsp;&nbsp;&nbsp;<input type="text" name="userName"
								style="float: left" />&nbsp;
							</td>
						</tr>

						<tr>
							<td width="" align="right"><span><font color="red">*</font>初始密码：</span></td>
							<td width="" align="left" bgcolor="#FFFFFF">
								&nbsp;&nbsp;&nbsp;<input type="text" name="userPwd"
								style="float: left" />
							</td>
						</tr>
						<tr>
							<td width="" align="right"><span><font color="red">*</font>姓名：</span></td>
							<td width="" align="left" bgcolor="#FFFFFF"><input
								type="text" name="name" style="float: left"/></td>
						</tr>
						<tr>
							<td width="" class="td1" align="right"><span><font
									color="red">*</font>证件类型：</span></td>
							<td width="" align="left" bgcolor="#FFFFFF">&nbsp;&nbsp;&nbsp;<input
								type="text" name="codetype" style="float: left" /></td>
						</tr>
						<tr>
							<td width="11%" class="td1" align="right"><span><font
									color="red">*</font>证件号：</span></td>
							<td align="left" bgcolor="#FFFFFF">&nbsp;&nbsp;&nbsp;<input
								type="text" name="codeno" style="float: left" /></td>
						</tr>
						<tr>
							<td width="11%" class="td1" align="right"><span><font
									color="red">*</font>电话：</span></td>
							<td align="left" bgcolor="#FFFFFF"><input type="text"
								name="phone" style="float: left" /></td>
						</tr>
						<tr>
							<td width="50%" class="td1" align="right"><span><font
									color="red">*</font>Emall：</span></td>
							<td align="left" bgcolor="#FFFFFF">&nbsp;&nbsp;&nbsp;<input
								type="text" name="emall" style="float: left" /></td>
						</tr>
						<tr>
							<td width="50%" class="td1" align="right"><span><font
									color="red">*</font>住址：</span></td>
							<td width="50%" align="left" bgcolor="#FFFFFF">
								&nbsp;&nbsp;&nbsp;<input type="text" name="address"
								style="float: left" />
							</td>
						</tr>
						<tr>
							<td width="50%" class="td1" align="right"><span><font
									color="red">*</font>类型：</span></td>
							<td width="50%" align="left" bgcolor="#FFFFFF">
								&nbsp;&nbsp;&nbsp;<input type="text" name="type"
								style="float: left" />(1为超级管理员，0为管理员)
							</td>
						</tr>
						<tr>
							<td width="11%" class="td1" align="right"><span><font
									color="red">*</font>创建日期</span>：</td>
							<td width="" align="left" bgcolor="#FFFFFF"><input
								type="text" name="createdate" style="float: left" class="Wdate" onclick="WdatePicker()"/></td>
						</tr>


					</table>
				</div>

				<div class="search_con">
					<center>
						<input type="submit" name="button" id="button" value="保存"
							class="gs_but2ss" />&nbsp;

					</center>
				</div>
			</form>
		</div>
	</div>

<%//判断用户名重复
		String str = (String) request.getSession().getAttribute("STR02");
		if (str == "1") {
%>
	<script>
		alert("用户名已存在！");
	</script>
	<%
	request.getSession().setAttribute("STR02", "0");}
%>

<%//判断是否用户添加成功
		String str2 = (String) request.getSession().getAttribute("STR_0");
		if (str2 == "1") {
%>
	<form id="myForm" method="post" target="detail"></form>
	<script>
		alert("添加成功！");
		var form = document.getElementById('myForm');//获取表单dom
	    form.action="UserManage?op=refresh";//重新设置提交url
	    form.submit();//提交表单
	    window.close();//关闭窗口
		
	</script>
<%}
        else if(str2 == "2"){%>


	<script>
		alert("添加失败，稍后再试！");
	</script>
<%}
request.getSession().setAttribute("STR_0", "0");
%>
</body>
</html>
