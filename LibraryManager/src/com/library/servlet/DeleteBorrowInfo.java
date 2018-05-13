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
    //É¾³ý½èÊé¼ÇÂ¼
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String LIBCARDNO = request.getParameter("LIBCARDNO");
		String BOOKID = request.getParameter("BOOKID");
		BorrowAndReturnBookdao borrowAndReturnBookdao=new BorrowAndReturnBookdao();
	    try {
			boolean i=borrowAndReturnBookdao.delete_borrow(LIBCARDNO, BOOKID);
			if(i){
				//³É¹¦Ðø½è
		    	request.getSession().setAttribute("STR_16", "É¾³ý³É¹¦£¡");
                response.sendRedirect("");}
			else{
		    	//É¾³ýÊ§°Ü
		    	request.getSession().setAttribute("STR_16", "É¾³ýÊ§°Ü£¬ÇëÉÔºóÔÙÊÔ£¡");
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
