<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@page import="com.library.bean.Libcard"%>
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
    function searchcard()
    {
    	searchForm.action="LibcardManage?op=searchcard";
    	searchForm.submit();
    }
    function refresh(){
    	inqform.action="LibcardManage?op=refresh";
    	inqform.submit();
    }
    function increase(){
    	
    	window.open ('increaselibcard.jsp','newwindow1','height=700,width=1000,top=200,left=400,toolbar=no,menubar=no,scrollbars=yes, resizable=no,location=no, status=no') ;
    	   
    }
    function Update(){
    	
    	
    	form1.target="_blank";
    	form1.action="LibcardManage?op=to_update";
		form1.submit();
 	   
    }
    
	function delcfm() {
		var r=confirm("确定要删除？");
		if(r==true)
		{
			form1.action="LibcardManage?op=delete";
			form1.submit();
	   }
	}
	
	function allCheck(check) {
		var checkbox = document.getElementsByName("boxcard");
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
    <%! int curPage,maxPage; %>
        <% curPage =Integer.parseInt(request.getAttribute("curPage").toString()); %> <!--取得当前页-->
        <% maxPage =Integer.parseInt((String)request.getAttribute("maxPage").toString()); %> <!--取得总页数-->
	<%
	ArrayList<Libcard> list = new ArrayList<Libcard>();
	list = (ArrayList<Libcard>) request.getAttribute("list_libcard");
		
	%>

	
	<%
		//判断查询所有用户是否成功
		String str = (String) request.getSession().getAttribute("str_Inqcard");
		if (str == "查询失败，稍后再试！") {
	%>
	<script>
		alert(str);
	</script>
	<%
		request.getSession().setAttribute("str_Inqcard", "0");
		}
	%>
	<div class="center_eva">
	    <form method="post" name="inqform"></form>
		<div class="position_bg" id="position_bg"
			style="padding-bottom: 0px; margin-top: 0px; padding-left: 0px; padding-right: 0px; padding-top: 0px;">
			<span class="pngFix"></span>首页> 图书馆信息管理> 借书证管理
		</div>
		<div class="center_mid">
			<div class="cen_right">
				<div class="tab_center" id="query" style="display: block">
					<div class="search">
						<form name="searchForm" id="searchForm"
							method="post" target="detail">

							<!-- 查询条件部分 -->
							<div class="query">
								<table width="100%" border="0" cellspacing="0" cellpadding="0"  align="center">

									<tr>
                                        <td colspan="2" align="center">
                                         <strong>借书证号：</strong>
										<input type="text" name="bylibcardno"/>&nbsp;&nbsp;&nbsp;                                                                                                        
                                        
                                        <strong>姓名：</strong>
										<input type="text" name="byname"/>&nbsp;&nbsp;&nbsp;	
									    
									    <input
											type="button" name="button" id="button" value="搜索"
											class="gs_but3" onclick="searchcard()" />
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
								    
									<input type="button" style="margin-top: 4px" name="button"
										id="button" value="修改" class="gs_but2g" onclick="Update();"/>								</c:if>
							</div>
							
					        
						</div>
						
						
						<div class="eva_tab">
							<div class="info_wid" style="background-color: white">
								<div class="info_table2" style="display: balck" id="jiben">
									<div class="info_mar">

										<table id="tab_cek" width="100%" border="0" cellspacing="0"
											cellpadding="1">

											<tr>
												<th width="4%" style="text-align: center"><input
													type="checkbox" name="allcard" onclick="allCheck(this)" /></th>
												<th width="8%" style="text-align: center">借书证号</th>
												<th width="7%" style="text-align: center">姓名</th>
												<th width="7%" style="text-align: center">证件类型</th>
												<th width="14%" style="text-align: center">证件号</th>
												<th width="8%" style="text-align: center">电话</th>
												<th width="14%" style="text-align: center">Email</th>
												<th width="14%" style="text-align: center">住所</th>
												<th width="7%" style="text-align: center">创建人</th>
												<th width="8%" style="text-align: center">创建日期</th>
                                                <th width="" style="text-align: center">有效日期</th>
											</tr>
											<%
											    String str3;
												for (int i = 0; i < list.size(); i++) {
													
													
											%>
											<c:forEach items="${httpData_ServiceResult.data.page.items}"
												var="item">

												<tr datarow="datarow">
													<td><input type="checkbox" id="boxcard" name="boxcard"
														subcheckbox="subcheckbox"
														value="<%=list.get(i).getlibcardno()%>" /></td>
													<td><%=list.get(i).getlibcardno()%></td>
													<td><%=list.get(i).getname()%></td>
													<td><%=list.get(i).getcardtype()%></td>
													<td><%=list.get(i).getcardno()%></td>
													<td><%=list.get(i).getphone()%></td>
													<td><%=list.get(i).getemall()%></td>
													<td><%=list.get(i).getaddress()%></td>
													<td><%=list.get(i).getcreateuser()%></td>
													<td><%=list.get(i).getcreatedate()%></td>
                                                    <td><%=list.get(i).geteffectivedate()%></td>
												</tr>

												<%
													}
												%>
											</c:forEach>

										</table>

									</div>

<div class="page">
    第<%= curPage %>页，共<%= maxPage %>页  
    <%if (curPage > 1){
    %>
    <a href="LibcardManage?page=1&op=refresh">首页</a>
    <a href="LibcardManage?page=<%=curPage - 1%>&op=refresh">上一页</a>
    <%
    }else {
    %>
    首页 上一页
    <%
        }%>
    <%if (curPage < maxPage){
    %>  
    <a href="LibcardManage?page=<%=curPage + 1%>&op=refresh">下一页</a>
    <a href="LibcardManage?page=<%=maxPage %>&op=refresh">尾页</a>
    <%
    }else {
    %>
    下一页 尾页
    <%
        }%>
       <form name="form1" action="LibcardManage?op=refresh" method="post">
        跳转至  <input type="text" size="5" name="page" value="<%=curPage%>"/>
        <a href="#" onclick="document.form1.submit();">跳转</a>
       
        
</form> 
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
<%//判断是否用户删除成功
		String str2 = (String) request.getSession().getAttribute("STR_10");
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
request.getSession().setAttribute("STR_10", "0");
%>


</body>
</html>