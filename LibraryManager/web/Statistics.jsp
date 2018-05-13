<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@page import="com.library.bean.BorrowAndRetuenbook"%>
<%@page import="java.util.ArrayList" %>
<%@page import="java.text.SimpleDateFormat" %>

<%@page import="java.util.Date "%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>用户管理平台</title>
<link type="text/css" href="css/style.css" rel="stylesheet" />
<script type="text/javascript" src="jquery/jquery-1.8.3.js"></script>
<script src="/js/jquery/jquery-1.8.3.min.js" type="text/javascript"></script>
<script src="My97DatePicker/WdatePicker.js" type="text/javascript"></script>
<script language="javascript">
    function searchfine(){
    	searchForm.action="Statistics?op=fine";
    	searchForm.submit();
    }
    function bookstatistics(){
    	searchForm.action="Statistics?op=book";
    	searchForm.submit();
    }
   
	
	
</script>


</head>
<body class="eva_body">
	<%
	ArrayList<BorrowAndRetuenbook> list = new ArrayList<BorrowAndRetuenbook>();
	list = (ArrayList<BorrowAndRetuenbook>) request.getAttribute("list_fine");
	String date1= (String)request.getSession().getAttribute("date1");
	String date2= (String)request.getSession().getAttribute("date2");
	%>

	<div class="center_eva">
	    
		<div class="position_bg" id="position_bg"
			style="padding-bottom: 0px; margin-top: 0px; padding-left: 0px; padding-right: 0px; padding-top: 0px;">
			<span class="pngFix"></span>首页> 图书馆信息管理> 查询统计
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
                                    <tr >
                                        <td style="text-align:left;">
                                                                                                      类型：<input type="radio" name="radio" onclick="searchfine()"  checked="checked" />罚款统计
                                             <input type="radio" name="radio" onclick="bookstatistics()" /> 图书统计                                                    
                                        </td>
                                        
                                        <td colspan="2"><strong>日期：</strong>
										<input type="text" name="date1" class="Wdate" onclick="WdatePicker()" value="<%=date1%>"/>至<input type="text" name="date2" class="Wdate" onclick="WdatePicker()" value="<%=date2%>"/>&nbsp;
			                            <input
											type="button" name="button" id="button" value="搜索"
											class="gs_but3" onclick="searchfine()" />
										</td>
                                    
										
										
										
									</tr>
									
									
								</table>
							</div>
						</form>
					</div>
					<form method="post" name="form1">
						
						
						<div class="eva_tab">
							<div class="info_wid" style="background-color: white">
								<div class="info_table2" style="display: balck" id="jiben">
									<div class="info_mar">

										<table id="tab_cek" width="100%" border="0" cellspacing="0"
											cellpadding="1">
                                            <caption>
                                              <font size="5"><strong><%=date1%></strong></font><font size="6"> 至 </font><font size="5"><strong><%=date2%></strong></font><font size="6"> 罚金数据统计</font>
                                            </caption>
											<tr>
												
												<th width="20%" style="text-align: center">书名</th>
												<th width="15%" style="text-align: center">借书证号</th>
												<th width="15%" style="text-align: center">借书人</th>
												
												<th width="20%" style="text-align: center">借书日期</th>
												<th width="20%" style="text-align: center">还书日期</th>
												<th width="10%" style="text-align: center">罚金</th>
												
												
											</tr>
											<%
											    double fine=0;
												for (int i = 0; i < list.size(); i++) {
													
								             %>
											

												<tr datarow="datarow">
							                        <td><%=list.get(i).getBOOKNAME() %></td>
													<td><%=list.get(i).getLIBCARDNO() %></td>
										            <td><%=list.get(i).getNAME() %></td>
													<td><%=list.get(i).getBORROWDATE() %></td>
													<td><%=list.get(i).getRETURNDATE() %></td>
												    <td><%=list.get(i).getFINE() %>元</td>
													
													<%fine+=list.get(i).getFINE(); %>
				                                   
												</tr>
												

												<%
												  }
												%>
											    <tr>
											       <td colspan="6"><font size="5">罚金金额合计：<%=fine%>元</font></td>
											    
											    </tr>

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