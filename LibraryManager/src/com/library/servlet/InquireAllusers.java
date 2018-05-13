package com.library.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.library.bean.User;
import com.library.dao.Userdao;

/**
 * Servlet implementation class InquireAllusers
 */
public class InquireAllusers extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InquireAllusers() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//String userName = (String) request.getSession().getAttribute("userName");//当前用户名
		Userdao userdao = new Userdao();
		try {
			ArrayList<User> list = new ArrayList<User>();
			list=userdao.inquireAlluser();
			
				if(list!= null)
				{
					request.setAttribute("list_user",list);
					request.getRequestDispatcher("Usermanage.jsp").forward(request, response);
	            }
				else{
				request.getSession().setAttribute("STR_4", "查询失败，稍后再试！");
	            response.sendRedirect("index.jsp");}
	             
			}
			
		 catch (SQLException e) {
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
