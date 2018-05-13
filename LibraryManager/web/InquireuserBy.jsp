<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@page import="com.library.bean.User"%>
<%@page import="java.util.ArrayList;"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>用户管理平台</title>
<link type="text/css" href="css/style.css" rel="stylesheet" />
<script type="text/javascript" src="jquery/jquery-1.8.3.js"></script>
<script src="/js/jquery/jquery-1.8.3.min.js" type="text/javascript"></script>

<script language="javascript">
function searchBy()
{
	searchForm.submit();
}
function refresh(){
	inqform.submit();
}
function increase(){
    	
    	window.open ('increaseUser.jsp','newwindow','height=700,width=1000,top=200,left=400,toolbar=no,menubar=no,scrollbars=yes, resizable=no,location=no, status=no') ;
    	   
    }
function Update(){
	
	form1.target="_blank";
	
	form1.action="UserManage?op=to_update";
	form1.submit();
	   
}
function delcfm() {
	var r=confirm("确定要删除？");
	if(r==true)
	{
		form1.action="UserManage?op=delete";
		form1.submit();
   }
}
	
	function allCheck(check) {
		var checkbox = document.getElementsByName("delete");
		if (check.checked) {
			for ( var i = 0; i < checkbox.length; i++) {
				checkbox[i].checked = "checked";
			}
		} else {
			for ( var i = 0; i < checkbox.length; i++) {
				checkbox[i].checked = "";
			}
		}
	}
</script>


</head>
<body class="eva_body">
	<%
		ArrayList<User> list = new ArrayList<User>();
		list = (ArrayList<User>) request.getAttribute("list_user2");
		String inquirename=(String) request.getSession().getAttribute("inquirename");
		String inquireusername=(String) request.getSession().getAttribute("inquireusername");
	%>

	<%//判断是否用户删除成功
		String str2 = (String) request.getSession().getAttribute("STR_1");
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
request.getSession().setAttribute("STR_1", "0");
%>

	<%
		//判断查询所有用户是否成功
		String str = (String) request.getSession().getAttribute("Str");
		if (str == "查询失败，稍后再试！") {
	%>
	<script>
		alert(str);
	</script>
	<%
		request.getSession().setAttribute("Str", "0");
		}
	%>
	<div class="center_eva">
	    <form action="UserManage?op=refresh" method="post" name="inqform"></form>
		<div class="position_bg" id="position_bg"
			style="padding-bottom: 0px; margin-top: 0px; padding-left: 0px; padding-right: 0px; padding-top: 0px;">
			<span class="pngFix"></span>首页> 图书馆信息管理> 用户管理> 查询用户
		</div>
		<div class="center_mid">
			<div class="cen_right">
				<div class="tab_center" id="query" style="display: block">
					<div class="search">
						<form name="searchForm" id="searchForm"
							action="UserManage?op=searchby" method="post" target="detail">

							<!-- 查询条件部分 -->
							<div class="query">
								<table width="100%" border="0" cellspacing="0" cellpadding="0"  align="center">

									<tr>
                                        <td colspan="2" align="center">
                                         <strong>帐号：</strong>
										<input type="text" name="byuserName" value="<%=inquireusername%>"/>&nbsp;&nbsp;&nbsp;                                                                                                        
                                        
                                        <strong>姓名：</strong>
										<input type="text" name="byname" value="<%=inquirename%>"/>&nbsp;&nbsp;&nbsp;	
									    
									    <input
											type="button" name="button" id="button" value="搜索"
											class="gs_but3" onclick="searchBy()" />
									    </td>
									 </tr>
									
								</table>
							</div>
						</form>
					</div>
					<form method="post" name="form1">
						<div class="info_menu">
							<div class="query_but">
								<c:if test="${sessionScope._session_userType!='2'}">
									<input type="button" style="margin-top: 4px" value="删除"
										class="gs_but2ss" onclick="delcfm();" />
									<input type="button" style="margin-top: 4px" name="button"
										id="button" value="增加" class="gs_but2sss" onclick="increase();"/>
								    <input type="button" style="margin-top: 4px" value="刷新"
										class="gs_but2ss" onclick="refresh();" />
									<input type="button" style="margin-top: 4px" name="button"
										id="button" value="修改" class="gs_but2g" onclick="Update();"/>
								</c:if>
							</div>
							
						</div>
						<div class="eva_tab">
							<div class="info_wid" style="background-color: white">
								<div class="info_table2" style="display: balck" id="jiben">
									<div class="info_mar">

										<table id="tab_cek" width="100%" border="0" cellspacing="0"
											cellpadding="1">

											<tr>
												<th width="5%" style="text-align: center"><input
													type="checkbox" name="all" onclick="allCheck(this)" /></th>
												<th width="7%" style="text-align: center">账号</th>
												<th width="7%" style="text-align: center">姓名</th>
												<th width="8%" style="text-align: center">证件类型</th>
												<th width="15%" style="text-align: center">证件号</th>
												<th width="11%" style="text-align: center">电话</th>
												<th width="15%" style="text-align: center">Emall</th>
												<th width="14%" style="text-align: center">住所</th>
												<th width="8%" style="text-align: center">类型</th>
												<th width="" style="text-align: center">创建日期</th>

											</tr>
											<%
											    String str3;
												for (int i = 0; i < list.size(); i++) {
													if(list.get(i).gettype()==1){
														str3="超级管理员";
													}
													else str3="管理员";
											%>
											<c:forEach items="${httpData_ServiceResult.data.page.items}"
												var="item">

												<tr datarow="datarow">
													<td><input type="checkbox" id="box" name="box"
														subcheckbox="subcheckbox"
														value="<%=list.get(i).getUserName()%>" /></td>
													<td><%=list.get(i).getUserName()%></td>
													<td><%=list.get(i).getname()%></td>
													<td><%=list.get(i).getcodetype()%></td>
													<td><%=list.get(i).getcodeno()%></td>
													<td><%=list.get(i).getphone()%></td>
													<td><%=list.get(i).getemall()%></td>
													<td><%=list.get(i).getaddress()%></td>
													<td><%=str3%></td>
													<td><%=list.get(i).getcreatedate()%></td>

												</tr>

										<%}%>
											</c:forEach>

										</table>

									</div>
									<div class="page">
										总记录数:12 &nbsp;总页数:2 &nbsp;<input type="text" size="5"
											value="2" />&nbsp;<a>跳转</a>&nbsp;<a href="#">首页</a><a
											href="${ctx}/pages/demo/xxgx/businessManagement/db_list.jsp">上一页</a><a
											href="${ctx}/pages/demo/xxgx/businessManagement/db_list.jsp">1</a><span
											class="current">2</span><span class="current prev">下一页</span><a
											href="#">尾页</a>
									</div>

								</div>

							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
	<div class="clear"></div>


</body>
</html>