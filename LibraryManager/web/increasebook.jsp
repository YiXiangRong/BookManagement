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
	


	<div class="ts_gdt" style="">
		<div class="wid_bai_tab">
			<form action="Books_Into_store?op=book" name="addUpdateForm" id="addUpdateForm"
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
								&nbsp;&nbsp;&nbsp;<input type="text" name="bookcode"
								style="float: left" />
							</td>
						</tr>
						<tr>
							<td width="" align="right"><span><font color="red">*</font>书名：</span></td>
							<td width="" align="left" bgcolor="#FFFFFF"><input
								type="text" name="bookname" style="float: left"/></td>
						</tr>
						<tr>
							<td width="" class="td1" align="right"><span><font
									color="red">*</font>作者：</span></td>
							<td width="" align="left" bgcolor="#FFFFFF">&nbsp;&nbsp;&nbsp;<input
								type="text" name="author" style="float: left" /></td>
						</tr>
						<tr>
							<td width="11%" class="td1" align="right"><span><font
									color="red">*</font>图书类型：</span></td>
							<td align="left" bgcolor="#FFFFFF">&nbsp;&nbsp;&nbsp;<input
								type="text" name="booktype" style="float: left" /></td>
						</tr>
						<tr>
							<td width="11%" class="td1" align="right"><span><font
									color="red">*</font>出版社：</span></td>
							<td align="left" bgcolor="#FFFFFF"><input type="text"
								name="press" style="float: left" /></td>
						</tr>
						<tr>
							<td width="50%" class="td1" align="right"><span><font
									color="red">*</font>价格：</span></td>
							<td align="left" bgcolor="#FFFFFF">&nbsp;&nbsp;&nbsp;<input
								type="text" name="price" style="float: left" /></td>
						</tr>
						
						<tr>
							<td width="50%" class="td1" align="right"><span><font
									color="red">*</font>印刷：</span></td>
							<td width="50%" align="left" bgcolor="#FFFFFF">
								&nbsp;&nbsp;&nbsp;<input type="text" name="printing"
								style="float: left" />
							</td>
						</tr>
						
                        <tr>
							<td width="11%" class="td1" align="right"><span><font
									color="red">*</font>出版日期</span>：</td>
							<td width="" align="left" bgcolor="#FFFFFF"><input
								type="text" name="pressdate" style="float: left" class="Wdate" onclick="WdatePicker()"/></td>
						</tr>
                        
						<tr>
							<td width="11%" class="td1" align="right"><span><font
									color="red">*</font>入库人</span>：</td>
							<td width="" align="left" bgcolor="#FFFFFF"><input
								type="text" name="createuser" style="float: left" /></td>
						</tr>
						<tr>
							<td width="11%" class="td1" align="right"><span><font
									color="red">*</font>入库日期</span>：</td>
							<td width="" align="left" bgcolor="#FFFFFF"><input
								type="text" name="createdate" style="float: left" class="Wdate" onclick="WdatePicker()"/></td>
						</tr>
						<tr>
							<td width="11%" class="td1" align="right"><span><font
									color="red">*</font>入库类型</span>：</td>
							<td width="" align="left" bgcolor="#FFFFFF"><input
								type="text" name="storetype" style="float: left" />(受赠或购买)</td>
						</tr>
						<tr>
							<td width="11%" class="td1" align="right"><span>捐赠人</span>：</td>
							<td width="" align="left" bgcolor="#FFFFFF"><input
								type="text" name="donationuser" style="float: left" /></td>
						</tr>
                        <tr>
							<td width="11%" class="td1" align="right"><span><font
									color="red">*</font>此书库存量</span>：</td>
							<td width="" align="left" bgcolor="#FFFFFF"><input
								type="text" name="number" style="float: left" /></td>
						</tr>
						<tr>
							<td width="11%" class="td1" align="right"><span><font
									color="red">*</font>总数</span>：</td>
							<td width="" align="left" bgcolor="#FFFFFF"><input
								type="text" name="sum" style="float: left" /></td>
						</tr>
					</table>
				</div>

				<div class="search_con">
					<center>
						<input type="submit" name="button" id="button" value="下一步"
							class="gs_but2ss" />&nbsp;
                        <input type="reset" name="button" id="button" value="取消"
							class="gs_but2ss" />&nbsp;
					</center>
				</div>
			</form>
		</div>
	</div>
<%//判断图书编号重复
		String str = (String) request.getSession().getAttribute("STRbookF");
		if (str == "1") {
%>
	<script>
		alert("图书编号已存在！");
	</script>
	<%
	request.getSession().setAttribute("STRbookF", "0");}
%>

<%//判断是否图书添加成功
		String str2 = (String) request.getSession().getAttribute("STR_5");
		//if (str2 == "1") {
%>
	
<%
        if(str2 == "2"){%>


	<script>
		alert("添加失败，稍后再试！");
	</script>
<%}
request.getSession().setAttribute("STR_5", "0");
%>


</body>
</html>
