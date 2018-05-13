package com.library.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.library.bean.Libcard;
import com.library.dao.Libcarddao;
import com.library.dao.FenYe;


/**
 * Servlet implementation class LibcardManage
 */
public class LibcardManage extends HttpServlet {
	private static final long serialVersionUID = 1L;
	    public int rowsPerPage;  //每页显示的行数
	    public int curPage;  //当前页页码
	    public int maxPage;  //总共页数
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LibcardManage() {
    	 rowsPerPage = 5;
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("unused")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		request.setCharacterEncoding("UTF-8");//传值编码
		response.setContentType("text/html;charset=UTF-8");//设置传输编码
		String op=request.getParameter("op");
		
		String curPage1 = request.getParameter("page");  //获取当前页页码
        if (curPage1 == null){
            curPage = 1;
            request.setAttribute("curPage",curPage);  //设置curPage对象
        }else {
            curPage = Integer.parseInt(curPage1);
            if (curPage < 1){
                curPage = 1;
            }
            request.setAttribute("curPage",curPage);
        }
       
        
		
		if(op.equals("searchcard")){//条件查询借书证
			this.InquireLibcardBy(request,response);
		}
		else if(op.equals("refresh")){//查看所有借书证
			this.InquireAllcards(request,response);
		}
		else if(op.equals("delete")){//删除借书证
			this.Delete_libcard(request,response);
		}
		else if(op.equals("increase")){//增加借书证
			this.Increase_libcard(request,response);
		}
		else if(op.equals("to_update")){//为修改借书证查询相关参数
			this.To_Update(request,response);
		}
		else if(op.equals("update")){//修改借书证
			this.Updatelibcard(request,response);
		}
	}

	//修改借书证
	private void Updatelibcard(HttpServletRequest request,
			HttpServletResponse response)throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");//传值编码
		response.setContentType("text/html;charset=UTF-8");//设置传输编码
		String oldlibcardno=request.getParameter("n");//修改前的借书证号
		String libcardno=request.getParameter("libcardno");//现在借书证号  
	    String name=request.getParameter("name");//姓名
	    String cardtype=request.getParameter("cardtype");//证件类型
	    String cardno=request.getParameter("cardno");//证件号
	    String phone=request.getParameter("phone");
	    String address=request.getParameter("address");
	    String emall=request.getParameter("emall");
	    String createuser=request.getParameter("createuser");
	   
	    String createdate =request.getParameter("createdate");
	    String effectivedate=request.getParameter("effectivedate");
	    
	    Libcarddao libcarddao=new Libcarddao();
	   try {
		
		   if (libcarddao.Yanzheng2(libcardno,oldlibcardno)==false)
		{
		   boolean i= libcarddao.update_libcard(libcardno, name, cardtype, cardno, phone, address, emall, createuser, createdate, effectivedate,oldlibcardno);
	       if(i){
	    	request.getSession().setAttribute("STR_11", "1");
	    	request.getRequestDispatcher("updatelibcardInfo.jsp").forward(
					request, response);
	        }
	       else
		   {
			request.getSession().setAttribute("STR_11", "2");
			request.getRequestDispatcher("updatelibcardInfo.jsp").forward(
					request, response);
		    }
		}
		   else{
			   
			   request.getSession().setAttribute("card_upF", "1");
               request.getRequestDispatcher("updatelibcardInfo.jsp").forward(
						request, response);
		   }
	   } catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	    
	}

	private void To_Update(HttpServletRequest request,
			HttpServletResponse response)throws ServletException, IOException {
		// TODO Auto-generated method stub
		String libcardno=request.getParameter("boxcard");//借书证号
		Libcard libcard = new Libcard();
		Libcarddao libcarddao=new Libcarddao();
		try {
			libcard=libcarddao.inquireOnecard(libcardno);
			if(libcard!=null){
				request.setAttribute("libcard",libcard);
				request.getRequestDispatcher("To_UpdateCard.jsp").forward(request, response);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
//增加借书证
	private void Increase_libcard(HttpServletRequest request,
			HttpServletResponse response)throws ServletException, IOException {
		// TODO Auto-generated method stub
		//从页面获取借书证信息
				request.setCharacterEncoding("UTF-8");//传值编码
				response.setContentType("text/html;charset=UTF-8");//设置传输编码
				String libcardno=request.getParameter("libcardno");//借书证号  
			    String name=request.getParameter("name");//姓名
			    String cardtype=request.getParameter("cardtype");//证件类型
			    String cardno=request.getParameter("cardno");//证件号
			    String phone=request.getParameter("phone");
			    String address=request.getParameter("address");
			    String emall=request.getParameter("emall");
			    String createuser=request.getParameter("createuser");
			    
			    String createdate =request.getParameter("createdate");
			    String effectivedate=request.getParameter("effectivedate");
			   
			    Libcarddao libcarddao=new Libcarddao();
			    try {
			    	
			    	if (libcarddao.Yanzheng(libcardno)==false)
				{
			    	//借书证号不重复
			    	boolean i=libcarddao.create_card(libcardno, name, cardtype, cardno, phone, address, emall, createuser, createdate, effectivedate);
					if(i)
				   {
						request.getSession().setAttribute("STR_9", "1");
		                response.sendRedirect("increaselibcard.jsp");
					}
					else
					{
						request.getSession().setAttribute("STR_9", "2");
		                response.sendRedirect("increaselibcard.jsp");
					}
				}
			    else{//借书证已存在
			    		request.getSession().setAttribute("STRcardF", "1");
		                response.sendRedirect("increaselibcard.jsp");
			    	}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

	}
//删除借书证
	private void Delete_libcard(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
String[] libcardno=request.getParameterValues("boxcard");//要删除的用户名 
		
		Libcarddao libcarddao=new Libcarddao();
		for(int i=0;i<libcardno.length;i++)
		{
		try {
			if(libcarddao.delete_card(libcardno[i]))
			{
				request.getSession().setAttribute("STR_10", "1");
                
			}
			else
			{
				request.getSession().setAttribute("STR_10", "2");
                
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  }
		this.InquireAllcards(request,response);
	}
//查询所有借书证
	private void InquireAllcards(HttpServletRequest request,
			HttpServletResponse response)throws ServletException, IOException   {
		// TODO Auto-generated method stub
		Libcarddao libcarddao = new Libcarddao();
		try {
			ArrayList<Libcard> list = new ArrayList<Libcard>();
			list=libcarddao.inquireAllcards(curPage,rowsPerPage);
			maxPage=libcarddao.getMaxPage(rowsPerPage);
				if(list!= null)
				{
					request.setAttribute("maxPage", maxPage);
					request.setAttribute("list_libcard",list);
					request.getRequestDispatcher("libcardmanage.jsp").forward(request, response);
	            }
				else{
				request.getSession().setAttribute("str_Inqcard", "查询失败，稍后再试！");
	            response.sendRedirect("index.jsp");}
	             
			}
			
		 catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
//条件查询
	private void InquireLibcardBy(HttpServletRequest request,
			HttpServletResponse response)throws ServletException, IOException  {
		// TODO Auto-generated method stub
		String name=request.getParameter("byname");//姓名
		String libcardno=request.getParameter("bylibcardno");//借书证号码
		request.getSession().setAttribute("byname",name);//保留查询条件
		request.getSession().setAttribute("bylibcardno",libcardno);//保留查询条件
		Libcarddao libcarddao=new Libcarddao();
		try {
			
			ArrayList<Libcard> list = new ArrayList<Libcard>();
			list=libcarddao.inquire_cardBy(name,libcardno,curPage,rowsPerPage);
			maxPage=libcarddao.getMaxPage(rowsPerPage,name,libcardno);
			if(list!= null){
				request.setAttribute("maxPage", maxPage);
				request.setAttribute("libcard",list);
				request.getRequestDispatcher("inquireCardBy.jsp").forward(request, response);
			}
			else
			{
				request.getSession().setAttribute("STR_12", "查询失败，稍后再试！");
                response.sendRedirect("index.jsp");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request,response);
	}

}
