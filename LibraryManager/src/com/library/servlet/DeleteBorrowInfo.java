package com.library.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.library.dao.BorrowAndReturnBookdao;

/**
 * Servlet implementation class DeleteBorrowInfo
 */
public class DeleteBorrowInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteBorrowInfo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    //ɾ�������¼
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String LIBCARDNO = request.getParameter("LIBCARDNO");
		String BOOKID = request.getParameter("BOOKID");
		BorrowAndReturnBookdao borrowAndReturnBookdao=new BorrowAndReturnBookdao();
	    try {
			boolean i=borrowAndReturnBookdao.delete_borrow(LIBCARDNO, BOOKID);
			if(i){
				//�ɹ�����
		    	request.getSession().setAttribute("STR_16", "ɾ���ɹ���");
                response.sendRedirect("");}
			else{
		    	//ɾ��ʧ��
		    	request.getSession().setAttribute("STR_16", "ɾ��ʧ�ܣ����Ժ����ԣ�");
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
