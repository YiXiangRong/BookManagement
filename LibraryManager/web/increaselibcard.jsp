<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>增加借书证</title>
<link type="text/css" href="css/style.css" rel="stylesheet" />
<script src="My97DatePicker/WdatePicker.js" type="text/javascript"></script>
</head>
<body class="badyauto4">
	


	<div class="ts_gdt" style="">
		<div class="wid_bai_tab">
			<form action="LibcardManage?op=increase" name="addUpdateForm" id="addUpdateForm"
				method="post">
				<div class="m_title">
					<strong>借书证信息（<font size="2" color="red">*</font><font
						size="2">为必填</font>）
					</strong>
				</div>
				<c:set value="${httpData_ServiceResult.data}" var="item" />
				<input type="hidden" name="id" value="${item.id}" /> <input
					type="hidden" name="splx" value="1" />
				<div class="info_table">
					<table class="table1" cellspacing="2" cellpadding="2" border="0">
						<tr>
							<td width="" align="right"><span><font color="red">*</font>借书证号：</span></td>
							<td width="" align="left" bgcolor="#FFFFFF">
								&nbsp;&nbsp;&nbsp;<input type="text" name="libcardno"
								style="float: left" />&nbsp;
							</td>
						</tr>

						<tr>
							<td width="" align="right"><span><font color="red">*</font>姓名：</span></td>
							<td width="" align="left" bgcolor="#FFFFFF">
								&nbsp;&nbsp;&nbsp;<input type="text" name="name"
								style="float: left" />
							</td>
						</tr>
						<tr>
							<td width="" align="right"><span><font color="red">*</font>证件类型：</span></td>
							<td width="" align="left" bgcolor="#FFFFFF"><input
								type="text" name="cardtype" style="float: left"/></td>
						</tr>
						<tr>
							<td width="" class="td1" align="right"><span><font
									color="red">*</font>证件号：</span></td>
							<td width="" align="left" bgcolor="#FFFFFF">&nbsp;&nbsp;&nbsp;<input
								type="text" name="cardno" style="float: left" /></td>
						</tr>
						<tr>
							<td width="11%" class="td1" align="right"><span><font
									color="red">*</font>电话：</span></td>
							<td align="left" bgcolor="#FFFFFF">&nbsp;&nbsp;&nbsp;<input
								type="text" name="phone" style="float: left" /></td>
						</tr>
						<tr>
							<td width="11%" class="td1" align="right"><span><font
									color="red">*</font>地址：</span></td>
							<td align="left" bgcolor="#FFFFFF"><input type="text"
								name="address" style="float: left" /></td>
						</tr>
						<tr>
							<td width="50%" class="td1" align="right"><span><font
									color="red">*</font>Emall：</span></td>
							<td align="left" bgcolor="#FFFFFF">&nbsp;&nbsp;&nbsp;<input
								type="text" name="emall" style="float: left" /></td>
						</tr>
						<tr>
							<td width="50%" class="td1" align="right"><span><font
									color="red">*</font>办理人：</span></td>
							<td width="50%" align="left" bgcolor="#FFFFFF">
								&nbsp;&nbsp;&nbsp;<input type="text" name="createuser"
								style="float: left" />
							</td>
						</tr>
						<tr>
							<td width="50%" class="td1" align="right"><span><font
									color="red">*</font>办理日期：</span></td>
							<td width="50%" align="left" bgcolor="#FFFFFF">
								&nbsp;&nbsp;&nbsp;<input type="text" name="createdate"
								style="float: left" class="Wdate" onclick="WdatePicker()"/>
							</td>
						</tr>
						
                        <tr>
							<td width="11%" class="td1" align="right"><span><font
									color="red">*</font>有效日期</span>：</td>
							<td width="" align="left" bgcolor="#FFFFFF"><input
								type="text" name="effectivedate" style="float: left" class="Wdate" onclick="WdatePicker()"/></td>
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
<%//判断借书证号重复
		String str = (String) request.getSession().getAttribute("STRcardF");
		if (str == "1") {
%>
	<script>
		alert("借书证已存在！");
	</script>
	<%
	request.getSession().setAttribute("STRcardF", "0");}
%>

<%//判断是否借书证添加成功
		String str2 = (String) request.getSession().getAttribute("STR_9");
		if (str2 == "1") {
%>
	<form id="myForm" method="post" target="detail"></form>
	<script>
		alert("添加成功！");
		var form = document.getElementById('myForm');//获取表单dom
	    form.action="LibcardManage?op=refresh";//重新设置提交url
	    form.submit();//提交表单
	    window.close();//关闭窗口
		
	</script>
<%}
        else if(str2 == "2"){%>


	<script>
		alert("添加失败，稍后再试！");
	</script>
<%}
request.getSession().setAttribute("STR_9", "0");
%>


</body>
</html>
