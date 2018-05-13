package com.library.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.library.dao.Scrapbookdao;

/**
 * Servlet implementation class Increase_Scrap
 */
public class Increase_Scrap extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Increase_Scrap() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    //新增报废图书
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		  String BOOKID  =  request.getParameter("BOOKID");              
		  String BOOKCODE =  request.getParameter("BOOKCODE");        
		  String SCRAPREASON =  request.getParameter("SCRAPREASON");         
		  String DESCRIBES =  request.getParameter("DESCRIBES"); 
		  String CREATEUSER =  request.getParameter("CREATEUSER");         
		  Date SCRAPDATE=null;            
		  Date CREATEDATE=null;
		  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd ");
		  try {
			  SCRAPDATE = sdf.parse(request.getParameter("SCRAPDATE"));
			  CREATEDATE = sdf.parse(request.getParameter("CREATEDATE"));
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		  Scrapbookdao scrapbookdao=new Scrapbookdao();
		  try {
			boolean i=scrapbookdao.Scrapbook(BOOKID, BOOKCODE, SCRAPREASON, DESCRIBES, SCRAPDATE, CREATEUSER, CREATEDATE);
		    if(i){
		    	request.getSession().setAttribute("STR_18", "增加成功！");
                response.sendRedirect("");
		    }
		    else
			{request.getSession().setAttribute("STR_18", "增加失败，稍后再试！");
            response.sendRedirect("");}
             
		  } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
