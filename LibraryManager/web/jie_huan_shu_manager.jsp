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
    	searchForm.action="Jie_Huan_Shu?op=searchbook";
    	searchForm.submit();
    }
    function look_return(){
    	searchForm.action="Jie_Huan_Shu?op=look_borrow";
    	searchForm.submit();
    }
    function refresh(){
    	inqform.submit();
    }
    function borrowBook(code){
    	
    	form1.target="_blank";
    	
        form1.action="Jie_Huan_Shu?op=Borrow&str1="+code+"";
    	form1.submit();
    	   
    }
    
    
	function borrowagain(){
		form1.action="Jie_Huan_Shu?op=xujie";
    	form1.submit();
	}
	function jieshu_dan(){
		form1.action="Jie_Huan_Shu?op=jieshudan";
    	form1.submit();
	}
	
	
</script>


</head>
<body class="eva_body">
	<%! int curPage,maxPage; %>
        <% curPage =Integer.parseInt(request.getAttribute("curPage").toString()); %> <!--取得当前页-->
        <% maxPage =Integer.parseInt((String)request.getAttribute("maxPage").toString()); %> <!--取得总页数-->
	<%
	ArrayList<Book> list = new ArrayList<Book>();
	list = (ArrayList<Book>) request.getAttribute("jie_huan_books");
		
	%>
<%
	//判断通过姓名查询图书是否成功
		String str1 = (String) request.getSession().getAttribute("jiehuan_book");
		if (str1 == "查询失败，稍后再试！") {
	%>
	<script>
		alert(str1);
	</script>
	<%
		request.getSession().setAttribute("str_Inqbook", "0");
		}
	%>
	
	<%
		//判断查询所有用户是否成功
		String str = (String) request.getSession().getAttribute("str_Inqbook");
		if (str == "查询失败，稍后再试！") {
	%>
	<script>
		alert(str);
	</script>
	<%
		request.getSession().setAttribute("str_Inqbook", "0");
		}
	%>
	<div class="center_eva">
	    <form action="Jie_Huan_Shu?op=refresh" method="post" name="inqform"></form>
		<div class="position_bg" id="position_bg"
			style="padding-bottom: 0px; margin-top: 0px; padding-left: 0px; padding-right: 0px; padding-top: 0px;">
			<span class="pngFix"></span>首页> 图书馆信息管理> 借还书
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
                                        <td style="text-align:left;">
                                                                                                      类型：<input type="radio" name="radio" onclick="refresh()"  checked="checked" />借书
                                             <input type="radio" name="radio" onclick="look_return()" /> 还书                                                    
                                        </td>
                                    
                                    
										<td colspan="2"><strong>书名：</strong>
										<input type="text" name="byname"/>&nbsp;
										
										<strong>类型：</strong>
										
										<select name="bytype" id="bytype" >
										  <option value=""></option>
										  <option value="文学">文学</option>
										  <option value="艺术">艺术</option>
										  <option value="地理">地理</option>
										  <option value="小说">小说</option>
										  <option value="杂志">杂志</option>
										  <option value="天文">天文</option>
										  <option value="数理">数理</option>
										  <option value="自然科学">自然科学</option>
										  <option value="医学">医学</option>
										  <option value="宗教">宗教</option>
										  <option value="生命科学">生命科学</option>
										  <option value="诗歌">诗歌</option>
										  <option value="童话">童话</option>
										  <option value="名著">名著</option>
										</select>&nbsp;
										
										
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
									
									
								    <input type="button" style="margin-top: 4px" value="刷新"
										class="gs_but2sss" onclick="refresh();" />
									
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
												<th width="15%" style="text-align: center">书名</th>
												
												<th width="10%" style="text-align: center">类型</th>
												<th width="10%" style="text-align: center">出版</th>
												<th width="10%" style="text-align: center">价格</th>
												
												<th width="12%" style="text-align: center">出版时间</th>
												<th width="" style="text-align: center">库存</th>
												<th width="5%" style="text-align: center">操作</th>
											</tr>
											<%
											    
												for (int i = 0; i < list.size(); i++) {
													
													String url ="../LibraryManager/images/books"+"/"+list.get(i).getimgadd();
													
													String content_url=list.get(i).getcontent();
													
											%>
											<c:forEach items="${httpData_ServiceResult.data.page.items}"
												var="item">

												<tr datarow="datarow">
													<td><input type="checkbox" id="boxborrow" name="boxborrow"
														subcheckbox="subcheckbox"
														value="<%=list.get(i).getbookcode()%>" /></td>
													
													<td><img src="<%=url%>" width="85%" height="85%"/></td>
													<td><%=list.get(i).getbookcode()%></td>
													<td><%=list.get(i).getbookname()%></td>
										            
													<td><%=list.get(i).getbooktype()%></td>
													<td><%=list.get(i).getpress()%></td>
													<td><%=list.get(i).getprice()%></td>
													
													<td><%=list.get(i).getpressdate()%></td>
													
													
				                                    <td><%=list.get(i).getnumber()%></td>
				                                    <td><input type="button" style="margin-top: 4px" value="借书"
										class="gs_but2ss" onclick="borrowBook(<%=list.get(i).getbookcode()%>)" /></td>
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
    <a href="Jie_Huan_Shu?page=1&op=refresh">首页</a>
    <a href="Jie_Huan_Shu?page=<%=curPage - 1%>&op=refresh">上一页</a>
    <%
    }else {
    %>
    首页 上一页
    <%
        }%>
    <%if (curPage < maxPage){
    %>  
    <a href="Jie_Huan_Shu?page=<%=curPage + 1%>&op=refresh">下一页</a>
    <a href="Jie_Huan_Shu?page=<%=maxPage %>&op=refresh">尾页</a>
    <%
    }else {
    %>
    下一页 尾页
    <%
        }%>
       <form name="form1" action="Jie_Huan_Shu?op=refresh" method="post">
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