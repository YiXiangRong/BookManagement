<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@page import="com.library.dao.Userdao"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>图书馆管理平台</title>
<link type="text/css"  href="css/style.css" rel="stylesheet"/>
<script type="text/javascript" src="jquery/jquery-1.8.3.js"></script>

<script>
function exist() {
	var r=confirm("确定要离开？");
	if(r==true)
	{
		var a=document.getElementById("a");
		a.setAttribute("href","login.jsp");
   }
}
function jiehuanshumanage(){
	jiehuanshuform.submit();
}
function bookmanage(){
	bookmanageform.submit();
}
function libcardmanage(){
	libcardform.submit();
}
function usermanage(){
	
	usermanageform.submit();
}
function statistics(){
	statisticsform.submit();
}
function toClass(){
	
	$("#ywjs").attr("class","")
	$("#xksc").attr("class","nav_hover");
}
function toUpdatePwd(){
	window.open ('Update_pwd.jsp','newwindow1','height=300,width=400,top=300,left=400,toolbar=no,menubar=no,scrollbars=yes, resizable=no,location=no, status=no') ;
}
</script>

 
</head>
<body >

<form action="LibcardManage?op=refresh" method="post" name="libcardform" target="detail"></form>
<form action="UserManage?op=refresh" method="post" name="usermanageform" target="detail"></form>
<form action="Books_Into_store?op=refresh" method="post" name="bookmanageform" target="detail"></form>
<form action="Jie_Huan_Shu?op=refresh" method="post" name="jiehuanshuform" target="detail"></form>
<form action="Statistics?op=fine" method="post" name="statisticsform" target="detail"></form>
<div class="r_headbg">
<div class="r_header_bg">
<div class="r_headicon">
<div class="header_right">
        	
               
  				<div id="Updatepwd" class="header_lock"><a style="color:#793300;" class="pngFix" href="#" onclick="toUpdatePwd()">修改密码 </a></div>	
          </div>
          <div class="header_info">
				
				<div class="info_more" id="divid1" style="display: none" onmouseover="mouseover1()" onmouseout="mouseout1()">
				</div>
			</div>
        </div>
</div>

<div id="mainMenu" class="r_menu">

</div>

</div>
</div>

<div class="mid_jian jian_right" style="display: none;" onclick="openOrClose()" id="close"></div>
<form name="searchForm" id="searchForm" action="${ctx }/servlet/com.chinauip.common.servlet.UIUrlServlet" style="width:100%;height:100%;">
<div style="width:200px;height:100%;overflow:auto;position:absolute;left:0px;border-right:solid 1px #666">
<div class="mid_left" id="contentMiddleLeft" >   
    <div class="admin_bg">
    	<div class="admin_left">
    		<img src="images/group/tongyong.png" width="55px"/>
    	</div>
        <div class="admin_right">
        	<div class="admin_hi">Hi,<strong>系统管理员</strong></div>
            <div class="admin_a"><a href="#" id="a" onclick="exist()">退出</a></div>
        </div>
        <div class="clear"></div>
    </div>
	<div class="nav_list" id="leftMenu" ><h2>图书馆信息管理</h2>
		<ul>
			<li id = "xksc"><a onclick = "jiehuanshumanage()">
				<span><img width="48" src="images/ywjs.png" complete="complete"/>借还书</img></span></a>
			</li>
			<li id = "xksc"><a onclick = "bookmanage()">
				<span><img width="48" src="images/xksc.png" complete="complete"/>图书管理</img></span></a>
			</li>
			<li><a onclick="libcardmanage();">
				<span><img width="48" src="images/nrjg.png" complete="complete"/>借书证管理</img></span></a>
			</li>
			<%//判断是否是超级用户
			  Userdao userdao=new Userdao();
			  String userName=(String) request.getSession().getAttribute("userName_on");//当前用户名
			  if(userdao.issuper(userName)){
			%>
			<li><a onclick="usermanage();">
				<span><img width="48" src="images/xksq.png" complete="complete"/>用户管理</img></span></a>
			</li>
			<%} %>
			<li><a onclick="statistics();">
				<span><img width="48" src="images/xxck.png" complete="complete"/>查询统计</img></span></a>
			</li>
		</ul>
		<br/><br/><br/><br/><br/><br/><br/><br/>
		
     <center><h3><%=(new java.util.Date()).toLocaleString() %></h3></center>
	</div>
    <div class="clear"></div>
  </div>
</div>
<div style="height:100%;margin:auto auto auto 200px;"
><iframe id="detail" name="detail" style="margin:0;padding:0;
     height:100%;width:100%;right:0px;border:solid 0px red;"
     src="Jie_Huan_Shu?op=index">"
></iframe></div>

</form>


<div class="mid_jian jian_left" onclick="openOrClose()" id="open"></div>
</body>
</html>


