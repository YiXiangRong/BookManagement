package com.library.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.library.dao.Userdao;

/**
 * Servlet implementation class UpdatePwd
 */
//�û��޸�����
public class UpdatePwd extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdatePwd() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String userName = (String) request.getSession().getAttribute("userName");//��ǰ�û���
		String pwd =  request.getParameter("pwd");
		Userdao userdao = new Userdao();
		try {
			if(userdao.updatepwd(userName,pwd)){
				request.getSession().setAttribute("STR_2", "�޸ĳɹ���");
                response.sendRedirect("");
			}
			else{
				request.getSession().setAttribute("STR_2", "�޸�ʧ�ܣ��Ժ����ԣ�");
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
