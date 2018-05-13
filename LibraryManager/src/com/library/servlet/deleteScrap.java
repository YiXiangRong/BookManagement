package com.library.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.library.dao.Libcarddao;
import com.library.dao.Scrapbookdao;

/**
 * Servlet implementation class deleteScrap
 */
public class deleteScrap extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public deleteScrap() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    //删除报废图书
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String BOOKID  =  request.getParameter("BOOKID");              
		String BOOKCODE =  request.getParameter("BOOKID");
		Scrapbookdao scrapbookdao=new Scrapbookdao();
		try {
			if(scrapbookdao.delete_scrap(BOOKID, BOOKCODE))
			{
				request.getSession().setAttribute("STR_19", "销毁成功！");
                response.sendRedirect("");
			}
			else
			{
				request.getSession().setAttribute("STR_10", "销毁失败，稍后再试！");
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
