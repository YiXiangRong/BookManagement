<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>修改密码</title>
<link type="text/css" href="css/style.css" rel="stylesheet" />
<script src="My97DatePicker/WdatePicker.js" type="text/javascript"></script>
<script>
function close(){
	window.close()
}

</script>
</head>
<body class="badyauto4">
	


	


	<div class="ts_gdt" style="">
		<div class="wid_bai_tab">
			<form action="UserManage?op=updatePwd" name="addUpdateForm2" id="addUpdateForm2"
				method="post">
				<div class="m_title">
					<strong>修改密码<font size="2" color="red">*</font><font
						size="2">为必填</font>）
					</strong>
				</div>
				
				<input type="hidden" name="id" value="${item.id}" /> <input
					type="hidden" name="splx" value="1" />
				<div class="info_table">
					<table class="table1" cellspacing="2" cellpadding="2" border="0">
						<tr>
							<td width="" align="right"><span><font color="red">*</font>旧密码：</span></td>
							<td width="" align="left" bgcolor="#FFFFFF">
								&nbsp;&nbsp;&nbsp;<input type="text" name="oldPwd" 
								style="float: left"/>&nbsp;
							</td>
						</tr>

						<tr>
							<td width="" align="right"><span><font color="red">*</font>新密码：</span></td>
							<td width="" align="left" bgcolor="#FFFFFF">
								&nbsp;&nbsp;&nbsp;<input type="text" name="newPwd" 
								style="float: left"/>
							</td>
						</tr>
						<tr>
							<td width="" align="right"><span><font color="red">*</font>重复新密码：</span></td>
							<td width="" align="left" bgcolor="#FFFFFF"><input
								type="text" name="newPwd2" style="float: left" /></td>
						</tr>
						
					</table>
				</div>

				<div class="search_con">
					<center>
					
						<input type="submit" name="button" id="button" value="保存"
							class="gs_but2ss" />&nbsp;
						<input type="button" name="button" id="button" onclick="close()" value="取消"
							class="gs_but2sss" />&nbsp;

					</center>
				</div>
			</form>
		</div>
	</div>
<%
String str1=(String) request.getSession().getAttribute("STR_2");
if(str1=="1"){
%>
<script>
		alert("修改成功！");
        window.close()
</script>
<%}else if(str1=="0"){ %>
<script>
		alert("两次密码输入不一致！");
		
</script>
<%}request.getSession().setAttribute("STR_2", "3");%>
</body>
</html>
