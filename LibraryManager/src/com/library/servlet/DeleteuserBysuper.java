package com.library.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.library.dao.Userdao;

/**
 * Servlet implementation class DeleteuserBysuper
 */
//超级用户对普通用户删减
public class DeleteuserBysuper extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteuserBysuper() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String[] userName=request.getParameterValues("box");//要删除的用户名
		Userdao userdao = new Userdao();
		for(int i=0;i<userName.length;i++)
		{
			try {
			
				if(userdao.deleteuser(userName[i]))
				{
					request.getSession().setAttribute("STR_1", "1");
					
	            }
				else{
				request.getSession().setAttribute("STR_1", "2");}
				
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		request.getRequestDispatcher("/InquireAllusers").forward(
				request, response);	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
 
    public void init() throws ServletException {
    }

}
