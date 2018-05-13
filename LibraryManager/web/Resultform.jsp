<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@page import="com.library.bean.BorrowAndRetuenbook"%>
<%@page import="java.util.ArrayList;"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>借书单</title>
<link type="text/css" href="css/style.css" rel="stylesheet" />
<script type="text/javascript" src="jquery/jquery-1.8.3.js"></script>
<script src="/js/jquery/jquery-1.8.3.min.js" type="text/javascript"></script>



</head>
<body class="eva_body">

	<%
	BorrowAndRetuenbook list = new BorrowAndRetuenbook();
	list = (BorrowAndRetuenbook)request.getAttribute("list_BorrowInfo");
		
	%>

	<div class="center_eva">
	   
		<div class="position_bg" id="position_bg"
			style="padding-bottom: 0px; margin-top: 0px; padding-left: 0px; padding-right: 0px; padding-top: 0px;">
			<span class="pngFix"></span>首页> 图书馆信息管理> 还书> 结果
		</div>
		<div class="center_mid">
			<div class="cen_right">
				<div class="tab_center" id="query" style="display: block">
					
					<form method="post" name="form1">
						
						
						
						<div class="eva_tab">
							<div class="info_wid" style="background-color: white">
								
									

										<table width="50%" border="0" cellspacing="0" align="center"
											cellpadding="1">

											<tr><td style="text-align: right">借书证号:</td><td style="text-align: left"><%=list.getLIBCARDNO() %></td></tr>
											<tr><td style="text-align: right">借书人:</td><td style="text-align: left"><%=list.getNAME() %></td></tr>	
											<tr><td style="text-align: right">书名:</td><td style="text-align: left"><%=list.getBOOKNAME() %></td></tr>
												
											<tr><td style="text-align: right">图书编号:</td><td style="text-align: left"><%=list.getBOOKCODE() %></td></tr>
											<tr><td style="text-align: right">借书日期:</td><td style="text-align: left"><%=list.getBORROWDATE() %></td></tr>
											<tr><td style="text-align: right">应还日期:</td><td style="text-align: left"><%=list.getSHOULDRETURNDATE() %></td></tr>
												
											<tr><td style="text-align: right">实际还书日期:</td><td style="text-align: left"><%=list.getRETURNDATE() %></td></tr>
											<tr><td style="text-align: right">是否超期:</td><td style="text-align: left"><%=list.getISOVERDUE() %></td></tr>
											<tr><td style="text-align: right">罚金:</td><td style="text-align: left"><%=list.getFINE() %>元</td></tr>
											<tr><td style="text-align: right">备注:</td><td style="text-align: left"><%=list.getNOTE() %></td></tr>
											<tr><td colspan="2" style="text-align: right"><%response.setIntHeader("Refresh", 60);%>
                                                   
                                                    <h4><%=(new java.util.Date()).toLocaleString() %></h4>
                                                 </td>
                                            </tr>
											
											
										</table>

									
									
										
									

								

							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
	<div class="clear"></div>
<%
String str3 = (String) request.getSession().getAttribute("DelbookF");
if (str3 == "1") {
%>
<script>
		alert("这本书已借出,无法删除！");
		
</script>
<%}
request.getSession().setAttribute("DelbookF", "0");
%>	
	
<%//判断是否图书删除成功
		String str2 = (String) request.getSession().getAttribute("STR_6");
		if (str2 == "1") {
%>
	<script>
		alert("删除成功！");
		
	</script>
<%}
        else if(str2 == "2"){%>


	<script>
		alert("删除失败，稍后再试！");
	</script>
<%}
request.getSession().setAttribute("STR_6", "0");
%>


</body>
</html>