package com.library.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.library.bean.Libcard;
import com.library.bean.User;
import com.library.dao.Libcarddao;
import com.library.dao.Userdao;

/**
 * Servlet implementation class UserManage
 */
public class UserManage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserManage() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");//传值编码
		response.setContentType("text/html;charset=UTF-8");//设置传输编码
		String op=request.getParameter("op");
		if(op.equals("refresh")){//查询所有用户
			this.InquireAllusers(request,response);
		}
		else if(op.equals("searchby")){//条件查询用户
			this.Inquireusersby(request,response);
		}
		else if(op.equals("delete")){//条件查询用户
			this.DeleteUsers(request,response);
		}
		else if(op.equals("increase")){//增加借书证
			this.Increase_User(request,response);
		}
		else if(op.equals("to_update")){//为修改用户名获取信息
			this.To_Update(request,response);
		}
		else if(op.equals("update")){//为修改用户名获取信息
			this.UpdateUser(request,response);
		}
		else if(op.equals("updatePwd")){//修改密码
			this.UpdatePwd(request,response);
		}
	}
	private void UpdatePwd(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String userName = (String) request.getSession().getAttribute("userName_on");//当前用户名
		String newPwd =  request.getParameter("newPwd");
		String newPwd2 =  request.getParameter("newPwd2");
		Userdao userdao = new Userdao();
		try {
			if(userdao.TwoPwd(newPwd, newPwd2))
			{
				if(userdao.updatepwd(userName,newPwd)){
			
				request.getSession().setAttribute("STR_2", "1");
                response.sendRedirect("Update_pwd.jsp");
			    }
			    
			}
			else{
				request.getSession().setAttribute("STR_2", "0");
	            response.sendRedirect("Update_pwd.jsp");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	private void UpdateUser(HttpServletRequest request,
			HttpServletResponse response)throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");//传值编码
		response.setContentType("text/html;charset=UTF-8");//设置传输编码
		Userdao userdao = new Userdao();
		String username = request.getParameter("n");//原来用户名
		String userName=request.getParameter("userName_up");//新修改的用户名
		  String userPwd = request.getParameter("userPwd_up");
				String name = request.getParameter("name_up");
				String codetype = request.getParameter("codetype_up");
				String codeno = request.getParameter("codeno_up");
				String phone = request.getParameter("phone_up");
				String emall = request.getParameter("emall_up");
				String address = request.getParameter("address_up");
				String createdate = request.getParameter("createdate_up");
				int type = Integer.parseInt(request.getParameter("type_up"));
				
				User user = new User();
				user.setUserName(userName);
				user.setUserPwd(userPwd);
				user.setname(name);
				user.setcodetype(codetype);
				user.setcodeno(codeno);
				user.setphone(phone);
				user.setemall(emall);
				user.setaddress(address);
				user.settype(type);
				user.setcreatedate(createdate);
				try {
					if (userdao.Yanzheng2(userName,username)==false)
					{
						if (userdao.Updateuser(user,username))
						{
					       request.getSession().setAttribute("STR_up", "1");
					       response.sendRedirect("updateUserInfo.jsp");} 
						else {
					      request.getSession().setAttribute("STR_up", "2");
					      response.sendRedirect("updateUserInfo.jsp");}
				    }
					else{
						request.getSession().setAttribute("STR_upF", "1");

						request.getRequestDispatcher("updateUserInfo.jsp").forward(
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
		String UserName=request.getParameter("box");//借书证号
		User user = new User();
		Userdao userdao=new Userdao();
		try {
			user=userdao.inquireOneuser(UserName);
			if(user!=null){
				request.setAttribute("user",user);
				request.getRequestDispatcher("To_UpdateUser.jsp").forward(request, response);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void Increase_User(HttpServletRequest request,
			HttpServletResponse response)throws ServletException, IOException  {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");//传值编码
		response.setContentType("text/html;charset=UTF-8");//设置传输编码
		Userdao userdao = new Userdao();
		String userName = request.getParameter("userName");
		  String userPwd = request.getParameter("userPwd");
				String name = request.getParameter("name");
				String codetype = request.getParameter("codetype");
				String codeno = request.getParameter("codeno");
				String phone = request.getParameter("phone");
				String emall = request.getParameter("emall");
				String address = request.getParameter("address");
				String createdate = request.getParameter("createdate");
				int type = Integer.parseInt(request.getParameter("type"));
				
				User user = new User();
				user.setUserName(userName);
				user.setUserPwd(userPwd);
				user.setname(name);
				user.setcodetype(codetype);
				user.setcodeno(codeno);
				user.setphone(phone);
				user.setemall(emall);
				user.setaddress(address);
				user.settype(type);
				user.setcreatedate(createdate);
				try {
					if (userdao.Yanzheng(userName)==false)
					{
						if (userdao.insertuser(user))
						{
					       request.getSession().setAttribute("STR_0", "1");
					       response.sendRedirect("increaseUser.jsp");} 
						else {
					      request.getSession().setAttribute("STR_0", "2");
					      response.sendRedirect("increaseUser.jsp");}
				    }
					else{
						request.getSession().setAttribute("STR02", "1");

						request.getRequestDispatcher("increaseUser.jsp").forward(
								request, response);
					}

			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	//删除用户
	private void DeleteUsers(HttpServletRequest request,
			HttpServletResponse response)throws ServletException, IOException {
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

	//条件查询用户
private void Inquireusersby(HttpServletRequest request,
			HttpServletResponse response)throws ServletException, IOException  {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");//传值编码
		response.setContentType("text/html;charset=UTF-8");//设置传输编码
		String userName = request.getParameter("byuserName");//查询条件
		String name = request.getParameter("byname");//查询条件
		request.getSession().setAttribute("inquirename",name);
		request.getSession().setAttribute("inquireusername",userName);
		Userdao userdao = new Userdao();
		try {
			ArrayList<User> list = new ArrayList<User>();
			list=userdao.inquireUserBy(name,userName);
			
				if(list!= null)
				{
					request.setAttribute("list_user2",list);
					request.getRequestDispatcher("InquireuserBy.jsp").forward(request, response);
	            }
				else{
				request.getSession().setAttribute("Str", "查询失败，稍后再试！");
	            response.sendRedirect("InquireuserBy.jsp");}
	             
			}
			
		 catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
//查询所有用户
private void InquireAllusers(HttpServletRequest request,
			HttpServletResponse response)throws ServletException, IOException  {
		// TODO Auto-generated method stub
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
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
