<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>图书入库</title>
<link type="text/css" href="css/style.css" rel="stylesheet" />

</head>
<body class="badyauto4">
	


	<div class="ts_gdt" style="">
		<div class="wid_bai_tab">
			<form action="Books_Into_store?op=image" name="addUpdateForm" id="addUpdateForm"
				method="post" enctype="multipart/form-data">
				<div class="m_title">
					<strong>上传图片（<font size="2" color="red">*</font><font
						size="2">为必填</font>）
					</strong>
				</div>
				<c:set value="${httpData_ServiceResult.data}" var="item" />
				<input type="hidden" name="id" value="${item.id}" /> <input
					type="hidden" name="splx" value="1" />
				<div class="info_table">
					<table class="table1" cellspacing="2" cellpadding="2" border="0">
						
						
						
						

						<tr>
							<td width="" align="right"><span><font color="red">*</font>上传文件：</span></td>
							<td width="" align="left" bgcolor="#FFFFFF">
								&nbsp;&nbsp;&nbsp;<input type="file" name="file"
								style="float: left" />
							</td>
						</tr>
						
					</table>
				</div>

				<div class="search_con">
					<center>
						<input type="submit" name="button" id="button" value="上传"
							class="gs_but2ss" />&nbsp;

					</center>
				</div>
			</form>
		</div>
	</div>
<%//判断是否图片上传成功
		String str2 = (String) request.getSession().getAttribute("image");
		if (str2 == "1") {
%>
	<form id="myForm" method="post" target="detail"></form>
	<script>
		alert("上传成功！");
		var form = document.getElementById('myForm');//获取表单dom
	    form.action="Books_Into_store?op=refresh";//重新设置提交url
	    form.submit();//提交表单
	    window.close();//关闭窗口
		
	</script>
<%}
        if(str2 == "2"){%>


	<script>
		alert("上传失败，稍后再试！");
	</script>
<%}
request.getSession().setAttribute("image", "0");
%>

</body>
</html>
