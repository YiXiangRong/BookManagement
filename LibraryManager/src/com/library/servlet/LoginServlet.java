package com.library.servlet;

import java.io.IOException;

import java.sql.SQLException;
import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.library.bean.User;
import com.library.dao.Userdao;

/**
 * Servlet implementation class LoginServlet
 */
//用户登录验证
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
        response.setContentType("text/html");
        
        String name = new String(request.getParameter("userName"));
                
        String pwd = new String(request.getParameter("userPwd"));
            
        User user = new User();
        user.setUserName(name);
        user.setUserPwd(pwd);
        Userdao userdao = new Userdao();
        boolean isLogin;
        try {
            isLogin = userdao.logoin(user);
 
            if (isLogin) {
            	request.getSession().setAttribute("userName_on", name);//当前用户名
            	request.getSession().setAttribute("STR", "true");
                response.sendRedirect("index.jsp");
            } else {
            	String i="1";
    			request.getSession().setAttribute("STR", i);
    			response.sendRedirect("login.jsp");
            }
 
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
 
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
 
    public void init() throws ServletException {
    }
 

}
