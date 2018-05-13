package com.library.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.library.bean.Scrapbook;
import com.library.dao.Scrapbookdao;

/**
 * Servlet implementation class InquireScrap
 */
public class InquireScrap extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InquireScrap() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    //查询报废图书
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String BOOKID  =  request.getParameter("BOOKID");              
		String BOOKCODE =  request.getParameter("BOOKID");
		Scrapbookdao scrapbookdao=new Scrapbookdao();
		Scrapbook scrapbook=new Scrapbook();
		try {
			scrapbook=scrapbookdao.inquire_card(BOOKID, BOOKCODE);
			if(scrapbook != null){
				request.setAttribute("scrapbookInfo",scrapbook);
				response.sendRedirect("");
			}
			else
			{
				request.getSession().setAttribute("STR_20", "查询失败，稍后再试！");
                response.sendRedirect("");
			}
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
