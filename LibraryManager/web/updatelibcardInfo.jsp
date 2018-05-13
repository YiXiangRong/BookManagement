<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>修改借书证</title>
<link type="text/css" href="css/style.css" rel="stylesheet" />
<script src="My97DatePicker/WdatePicker.js" type="text/javascript"></script>
</head>
<body class="badyauto4">
<%	
String to_libcardno= (String)request.getSession().getAttribute("to_libcardno");
String to_address= (String)request.getSession().getAttribute("to_address");
String to_cardno= (String)request.getSession().getAttribute("to_cardno");
String to_cardtype= (String)request.getSession().getAttribute("to_cardtype");
String to_createdate= (String)request.getSession().getAttribute("to_createdate");
String to_createuser= (String)request.getSession().getAttribute("to_createuser");
String to_effectivedate= (String)request.getSession().getAttribute("to_effectivedate");
String to_emall= (String)request.getSession().getAttribute("to_emall");
String to_name= (String)request.getSession().getAttribute("to_name");
String to_phone= (String)request.getSession().getAttribute("to_phone");
%>



	<div class="ts_gdt" style="">
		<div class="wid_bai_tab">
			<form action="LibcardManage?op=update" name="addUpdateForm" id="addUpdateForm"
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
								&nbsp;&nbsp;&nbsp;<input type="text" name="libcardno" value="<%=to_libcardno%>"
								style="float: left" />&nbsp;
							</td>
						</tr>

						<tr>
							<td width="" align="right"><span><font color="red">*</font>姓名：</span></td>
							<td width="" align="left" bgcolor="#FFFFFF">
								&nbsp;&nbsp;&nbsp;<input type="text" name="name" value="<%=to_name%>"
								style="float: left" />
							</td>
						</tr>
						<tr>
							<td width="" align="right"><span><font color="red">*</font>证件类型：</span></td>
							<td width="" align="left" bgcolor="#FFFFFF"><input
								type="text" name="cardtype" style="float: left" value="<%=to_cardtype%>"/></td>
						</tr>
						<tr>
							<td width="" class="td1" align="right"><span><font
									color="red">*</font>证件号：</span></td>
							<td width="" align="left" bgcolor="#FFFFFF">&nbsp;&nbsp;&nbsp;<input
								type="text" name="cardno" style="float: left" value="<%=to_cardno%>"/></td>
						</tr>
						<tr>
							<td width="11%" class="td1" align="right"><span><font
									color="red">*</font>电话：</span></td>
							<td align="left" bgcolor="#FFFFFF">&nbsp;&nbsp;&nbsp;<input
								type="text" name="phone" style="float: left" value="<%=to_phone%>"/></td>
						</tr>
						<tr>
							<td width="11%" class="td1" align="right"><span><font
									color="red">*</font>地址：</span></td>
							<td align="left" bgcolor="#FFFFFF"><input type="text"
								name="address" style="float: left" value="<%=to_address%>"/></td>
						</tr>
						<tr>
							<td width="50%" class="td1" align="right"><span><font
									color="red">*</font>Email：</span></td>
							<td align="left" bgcolor="#FFFFFF">&nbsp;&nbsp;&nbsp;<input
								type="text" name="emall" style="float: left" value="<%=to_emall%>"/></td>
						</tr>
						<tr>
							<td width="50%" class="td1" align="right"><span><font
									color="red">*</font>办理人：</span></td>
							<td width="50%" align="left" bgcolor="#FFFFFF">
								&nbsp;&nbsp;&nbsp;<input type="text" name="createuser" value="<%=to_createuser%>"
								style="float: left" />
							</td>
						</tr>
						<tr>
							<td width="50%" class="td1" align="right"><span><font
									color="red">*</font>办理日期：</span></td>
							<td width="50%" align="left" bgcolor="#FFFFFF">
								&nbsp;&nbsp;&nbsp;<input type="text" name="createdate" class="Wdate" onclick="WdatePicker()" value="<%=to_createdate%>"
								style="float: left" />
							</td>
						</tr>
						
                        <tr>
							<td width="11%" class="td1" align="right"><span><font
									color="red">*</font>有效日期</span>：</td>
							<td width="" align="left" bgcolor="#FFFFFF"><input
								type="text" name="effectivedate" style="float: left" value="<%=to_effectivedate%>" class="Wdate" onclick="WdatePicker()"/></td>
						</tr>
                        

					</table>
				</div>

				<div class="search_con">
					<center>
					    <input type="hidden" value="<%=to_libcardno%>" name="n"></input>
						<input type="submit" name="button" id="button" value="保存"
							class="gs_but2ss" />&nbsp;

					</center>
				</div>
			</form>
		</div>
	</div>
	<%//判断借书证号重复
		String str = (String) request.getSession().getAttribute("card_upF");
		if (str == "1") {
%>
	<script>
		alert("借书证已存在！");
	</script>
	<%
	request.getSession().setAttribute("card_upF", "0");}
%>
<%//判断是否借书证修改成功
		String str4 = (String) request.getSession().getAttribute("STR_11");
		if (str4 == "1") {
%>

	<form id="myForm" method="post" target="detail"></form>
	<script>
		alert("修改成功！");
		var form = document.getElementById('myForm');//获取表单dom
	    form.action="LibcardManage?op=refresh";//重新设置提交url
	    form.submit();//提交表单
	    window.close();//关闭窗口
		
	</script>
<%}
		else if(str4 == "2"){%>


	<script>
		alert("修改失败，稍后再试！");
	</script>
<%}
request.getSession().setAttribute("STR_11", "0");
%>

</body>
</html>
