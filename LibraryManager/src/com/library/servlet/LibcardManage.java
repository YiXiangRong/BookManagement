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
	    public int rowsPerPage;  //ÿҳ��ʾ������
	    public int curPage;  //��ǰҳҳ��
	    public int maxPage;  //�ܹ�ҳ��
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
		
		request.setCharacterEncoding("UTF-8");//��ֵ����
		response.setContentType("text/html;charset=UTF-8");//���ô������
		String op=request.getParameter("op");
		
		String curPage1 = request.getParameter("page");  //��ȡ��ǰҳҳ��
        if (curPage1 == null){
            curPage = 1;
            request.setAttribute("curPage",curPage);  //����curPage����
        }else {
            curPage = Integer.parseInt(curPage1);
            if (curPage < 1){
                curPage = 1;
            }
            request.setAttribute("curPage",curPage);
        }
       
        
		
		if(op.equals("searchcard")){//������ѯ����֤
			this.InquireLibcardBy(request,response);
		}
		else if(op.equals("refresh")){//�鿴���н���֤
			this.InquireAllcards(request,response);
		}
		else if(op.equals("delete")){//ɾ������֤
			this.Delete_libcard(request,response);
		}
		else if(op.equals("increase")){//���ӽ���֤
			this.Increase_libcard(request,response);
		}
		else if(op.equals("to_update")){//Ϊ�޸Ľ���֤��ѯ��ز���
			this.To_Update(request,response);
		}
		else if(op.equals("update")){//�޸Ľ���֤
			this.Updatelibcard(request,response);
		}
	}

	//�޸Ľ���֤
	private void Updatelibcard(HttpServletRequest request,
			HttpServletResponse response)throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");//��ֵ����
		response.setContentType("text/html;charset=UTF-8");//���ô������
		String oldlibcardno=request.getParameter("n");//�޸�ǰ�Ľ���֤��
		String libcardno=request.getParameter("libcardno");//���ڽ���֤��  
	    String name=request.getParameter("name");//����
	    String cardtype=request.getParameter("cardtype");//֤������
	    String cardno=request.getParameter("cardno");//֤����
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
		String libcardno=request.getParameter("boxcard");//����֤��
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
//���ӽ���֤
	private void Increase_libcard(HttpServletRequest request,
			HttpServletResponse response)throws ServletException, IOException {
		// TODO Auto-generated method stub
		//��ҳ���ȡ����֤��Ϣ
				request.setCharacterEncoding("UTF-8");//��ֵ����
				response.setContentType("text/html;charset=UTF-8");//���ô������
				String libcardno=request.getParameter("libcardno");//����֤��  
			    String name=request.getParameter("name");//����
			    String cardtype=request.getParameter("cardtype");//֤������
			    String cardno=request.getParameter("cardno");//֤����
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
			    	//����֤�Ų��ظ�
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
			    else{//����֤�Ѵ���
			    		request.getSession().setAttribute("STRcardF", "1");
		                response.sendRedirect("increaselibcard.jsp");
			    	}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

	}
//ɾ������֤
	private void Delete_libcard(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
String[] libcardno=request.getParameterValues("boxcard");//Ҫɾ�����û��� 
		
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
//��ѯ���н���֤
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
				request.getSession().setAttribute("str_Inqcard", "��ѯʧ�ܣ��Ժ����ԣ�");
	            response.sendRedirect("index.jsp");}
	             
			}
			
		 catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
//������ѯ
	private void InquireLibcardBy(HttpServletRequest request,
			HttpServletResponse response)throws ServletException, IOException  {
		// TODO Auto-generated method stub
		String name=request.getParameter("byname");//����
		String libcardno=request.getParameter("bylibcardno");//����֤����
		request.getSession().setAttribute("byname",name);//������ѯ����
		request.getSession().setAttribute("bylibcardno",libcardno);//������ѯ����
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
				request.getSession().setAttribute("STR_12", "��ѯʧ�ܣ��Ժ����ԣ�");
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
