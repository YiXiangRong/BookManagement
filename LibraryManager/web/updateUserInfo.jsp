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
	

<%
String userName = (String)request.getSession().getAttribute("is_userName");
String userPwd = (String)request.getSession().getAttribute("is_userPwd");
String name = (String)request.getSession().getAttribute("is_name");
String codetype = (String)request.getSession().getAttribute("is_codetype");
String codeno = (String)request.getSession().getAttribute("is_codeno");
String emall = (String)request.getSession().getAttribute("is_emall");
String address = (String)request.getSession().getAttribute("is_address");
String type = (String)request.getSession().getAttribute("is_type");
String createdate = (String)request.getSession().getAttribute("is_createdate");
String phone = (String)request.getSession().getAttribute("is_phone");

  %>
	
	


	<div class="ts_gdt" style="">
		<div class="wid_bai_tab">
			<form action="UserManage?op=update" name="addUpdateForm2" id="addUpdateForm2"
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
								&nbsp;&nbsp;&nbsp;<input type="text" name="userName_up" value="<%=userName%>"
								style="float: left"/>&nbsp;
							</td>
						</tr>

						<tr>
							<td width="" align="right"><span><font color="red">*</font>初始密码：</span></td>
							<td width="" align="left" bgcolor="#FFFFFF">
								&nbsp;&nbsp;&nbsp;<input type="text" name="userPwd_up" value="<%=userPwd%>"
								style="float: left"/>
							</td>
						</tr>
						<tr>
							<td width="" align="right"><span><font color="red">*</font>姓名：</span></td>
							<td width="" align="left" bgcolor="#FFFFFF"><input
								type="text" name="name_up" style="float: left" value="<%=name%>"/></td>
						</tr>
						<tr>
							<td width="" class="td1" align="right"><span><font
									color="red">*</font>证件类型：</span></td>
							<td width="" align="left" bgcolor="#FFFFFF">&nbsp;&nbsp;&nbsp;<input
								type="text" name="codetype_up" style="float: left" value="<%=codetype%>" /></td>
						</tr>
						<tr>
							<td width="11%" class="td1" align="right"><span><font
									color="red">*</font>证件号：</span></td>
							<td align="left" bgcolor="#FFFFFF">&nbsp;&nbsp;&nbsp;<input
								type="text" name="codeno_up" style="float: left" value="<%=codeno%>"/></td>
						</tr>
						<tr>
							<td width="11%" class="td1" align="right"><span><font
									color="red">*</font>电话：</span></td>
							<td align="left" bgcolor="#FFFFFF"><input type="text"
								name="phone_up" style="float: left" value="<%=phone%>"/></td>
						</tr>
						<tr>
							<td width="50%" class="td1" align="right"><span><font
									color="red">*</font>Email：</span></td>
							<td align="left" bgcolor="#FFFFFF">&nbsp;&nbsp;&nbsp;<input
								type="text" name="emall_up" style="float: left" value="<%=emall%>"/></td>
						</tr>
						<tr>
							<td width="50%" class="td1" align="right"><span><font
									color="red">*</font>住址：</span></td>
							<td width="50%" align="left" bgcolor="#FFFFFF">
								&nbsp;&nbsp;&nbsp;<input type="text" name="address_up" value="<%=address%>"
								style="float: left"/>
							</td>
						</tr>
						<tr>
							<td width="50%" class="td1" align="right"><span><font
									color="red">*</font>类型：</span></td>
							<td width="50%" align="left" bgcolor="#FFFFFF">
								&nbsp;&nbsp;&nbsp;<input type="text" name="type_up"
								style="float: left" value="<%=type%>"/>(1为超级管理员，0为管理员)
							</td>
						</tr>
						<tr>
							<td width="11%" class="td1" align="right"><span><font
									color="red">*</font>创建日期</span>：</td>
							<td width="" align="left" bgcolor="#FFFFFF"><input
								type="text" name="createdate_up" style="float: left" value="<%=createdate%>" class="Wdate" onclick="WdatePicker()"/></td>
						</tr>


					</table>
				</div>

				<div class="search_con">
					<center>
					<input type="hidden" value="<%=userName%>" name="n"></input>
						<input type="submit" name="button" id="button" value="保存"
							class="gs_but2ss" />&nbsp;

					</center>
				</div>
			</form>
		</div>
	</div>
<%//判断用户名重复
		String str = (String) request.getSession().getAttribute("STR_upF");
		if (str == "1") {
%>
	<script>
		alert("用户名已存在！");
	</script>
	<%
	request.getSession().setAttribute("STR_upF", "0");}
%>

<%//判断是否用户修改成功
		String str2 = (String) request.getSession().getAttribute("STR_up");
		if (str2 == "1") {
%>
	<form id="myForm" method="post" target="detail"></form>
	<script>
		alert("修改成功！");
		var form = document.getElementById('myForm');//获取表单dom
	    form.action="UserManage?op=refresh";//重新设置提交url
	    form.submit();//提交表单
	    window.close();//关闭窗口
		
	</script>
<%}
        else if(str2 == "2"){%>


	<script>
		alert("修改失败，稍后再试！");
	</script>
<%}
request.getSession().setAttribute("STR_up", "0");
%>

</body>
</html>
