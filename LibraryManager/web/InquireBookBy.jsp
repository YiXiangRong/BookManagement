<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@page import="com.library.bean.Book"%>
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
function searchbook(){
	searchForm.action="Books_Into_store?op=searchbook";
	searchForm.submit();
}
    function refresh(){
    	inqform.submit();
    }
    function increase(){
    	
    	window.open ('increasebook.jsp','newwindow1','height=700,width=1000,top=200,left=400,toolbar=no,menubar=no,scrollbars=yes, resizable=no,location=no, status=no') ;
    	   
    }
    function Update(){
    	
    	form1.target="_blank";
    	
    	form1.action="Books_Into_store?op=to_update";
		form1.submit();
 	   
    }
    
	function delcfm() {
		var r=confirm("确定要删除？");
		if(r==true)
		{
			form1.action="Books_Into_store?op=deletebook";
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
	<%
	ArrayList<Book> list = new ArrayList<Book>();
	list = (ArrayList<Book>) request.getAttribute("list_booksbytype");
	String str1 = (String) request.getSession().getAttribute("Bname");
	String str2 = (String) request.getSession().getAttribute("Bauthor");
	%>

	
	
	<div class="center_eva">
	    <form action="Books_Into_store?op=refresh" method="post" name="inqform"></form>
		<div class="position_bg" id="position_bg"
			style="padding-bottom: 0px; margin-top: 0px; padding-left: 0px; padding-right: 0px; padding-top: 0px;">
			<span class="pngFix"></span>首页> 图书馆信息管理> 图书管理
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
                                        <td colspan="3" align="center">
                                         <strong>书名：</strong>
										<input type="text" name="byname" value="<%=str1%>"/>&nbsp;&nbsp;&nbsp;                                                                                                        
                                        
                                        <strong>作者：</strong>
										<input type="text" name="byauthor" value="<%=str2%>"/>&nbsp;&nbsp;&nbsp;
										
										<strong>类型：</strong>
						                <select name="bytype" id="bytype" >
										  <option value=""></option>
										  <option value="文学" <%if("文学".equals(request.getAttribute("bytype") + ""))out.print(" selected ");%>>文学</option>
										  <option value="艺术" <%if("艺术".equals(request.getAttribute("bytype") + ""))out.print(" selected ");%>>艺术</option>
										  <option value="地理" <%if("地理".equals(request.getAttribute("bytype") + ""))out.print(" selected ");%>>地理</option>
										  <option value="小说" <%if("小说".equals(request.getAttribute("bytype") + ""))out.print(" selected ");%>>小说</option>
										  <option value="杂志" <%if("杂志".equals(request.getAttribute("bytype") + ""))out.print(" selected ");%>>杂志</option>
										  <option value="天文" <%if("天文".equals(request.getAttribute("bytype") + ""))out.print(" selected ");%>>天文</option>
										  <option value="数理" <%if("数理".equals(request.getAttribute("bytype") + ""))out.print(" selected ");%>>数理</option>
										  <option value="自然科学" <%if("自然科学".equals(request.getAttribute("bytype") + ""))out.print(" selected ");%>>自然科学</option>
										  <option value="医学" <%if("医学".equals(request.getAttribute("bytype") + ""))out.print(" selected ");%>>医学</option>
										  <option value="宗教" <%if("宗教".equals(request.getAttribute("bytype") + ""))out.print(" selected ");%>>宗教</option>
										  <option value="生命科学" <%if("生命科学".equals(request.getAttribute("bytype") + ""))out.print(" selected ");%>>生命科学</option>
										  <option value="诗歌" <%if("诗歌".equals(request.getAttribute("bytype") + ""))out.print(" selected ");%>>诗歌</option>
										  <option value="童话" <%if("童话".equals(request.getAttribute("bytype") + ""))out.print(" selected ");%>>童话</option>
										  <option value="名著" <%if("名著".equals(request.getAttribute("bytype") + ""))out.print(" selected ");%>>名著</option>
										</select>&nbsp;&nbsp;&nbsp;
										
										
										   <input
											type="button" name="button" id="button" value="搜索"
											class="gs_but3" onclick="searchbook()" />
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
												<th width="6%" style="text-align: center"><input
													type="checkbox" name="allcard" onclick="allCheck(this)" /></th>
												<th width="12%" style="text-align: center">图片</th>
												<th width="15%" style="text-align: center">编号</th>
												<th width="12%" style="text-align: center">书名</th>
												<th width="8%" style="text-align: center">作者</th>
												<th width="10%" style="text-align: center">类型</th>
												<th width="10%" style="text-align: center">出版</th>
												<th width="10%" style="text-align: center">价格</th>
												
												<th width="12%" style="text-align: center">出版时间</th>
												
												
                                                <th width="" style="text-align: center">库存</th>
											</tr>
											<%
											    
												for (int i = 0; i < list.size(); i++) {
													
													String url ="../LibraryManager/images/books"+"/"+list.get(i).getimgadd();
													
													String content_url=list.get(i).getcontent();
													
											%>
											<c:forEach items="${httpData_ServiceResult.data.page.items}"
												var="item">

												<tr datarow="datarow">
													<td><input type="checkbox" id="boxbook" name="boxbook"
														subcheckbox="subcheckbox"
														value="<%=list.get(i).getbookcode()%>" /></td>
													
													<td><img src="<%=url%>" width="85%" height="88%"/></td>
													<td><%=list.get(i).getbookcode()%></td>
													<td><%=list.get(i).getbookname()%></td>
										            <td><%=list.get(i).getauthor() %></td>
													<td><%=list.get(i).getbooktype()%></td>
													<td><%=list.get(i).getpress()%></td>
													<td><%=list.get(i).getprice()%></td>
													
													<td><%=list.get(i).getpressdate()%></td>
													
													
				                                    <td><%=list.get(i).getnumber()%></td>
												</tr>

												<%
												  }
												%>
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
		String str4 = (String) request.getSession().getAttribute("STR_6");
		if (str4 == "1") {
%>
	<script>
		alert("删除成功！");
		
	</script>
<%}
        else if(str4 == "2"){%>


	<script>
		alert("删除失败，稍后再试！");
	</script>
<%}
request.getSession().setAttribute("STR_6", "0");
%>


</body>
</html>