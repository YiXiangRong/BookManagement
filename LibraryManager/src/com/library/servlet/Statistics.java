package com.library.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.library.bean.Book;
import com.library.bean.Bookstatistics;
import com.library.bean.BorrowAndRetuenbook;
import com.library.dao.Bookdao;
import com.library.dao.BorrowAndReturnBookdao;

/**
 * Servlet implementation class Statistics
 */
public class Statistics extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Statistics() {
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
		if(op.equals("fine")){//罚金统计
			this.FineStatistics(request, response);
		}
		else if(op.equals("book")){//图书统计
			this.BookStatistics(request, response);
		}
	}

	private void BookStatistics(HttpServletRequest request,
			HttpServletResponse response)throws ServletException, IOException {
		// TODO Auto-generated method stub
		Bookdao bookdao = new Bookdao();
		String time1=request.getParameter("_date1");
		String time2=request.getParameter("_date2");
		java.sql.Date Date1=null;
		java.sql.Date Date2=null;
		if(time1==null||time2==null||time1==""||time2==""){//默认日期
			try {
				Date1=bookdao.Mindate();
				Date2=bookdao.Maxdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		else{//用户输入日期
		Date1=java.sql.Date.valueOf(time1);
		Date2=java.sql.Date.valueOf(time2);
		}
		request.getSession().setAttribute("C_date1", ""+Date1);
		request.getSession().setAttribute("C_date2", ""+Date2);
		ArrayList<Bookstatistics> list=new ArrayList<Bookstatistics>();
		try {
			list=bookdao.book_statistics(Date1, Date2);
			if(list!=null){
				request.setAttribute("book_statistics",list);
				request.getRequestDispatcher("BookStatistics.jsp").forward(request, response);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	

	private void FineStatistics(HttpServletRequest request,
			HttpServletResponse response)throws ServletException, IOException  {
		// TODO Auto-generated method stub
		BorrowAndReturnBookdao borrowAndReturnBookdao=new BorrowAndReturnBookdao();
		String time1=request.getParameter("date1");
		String time2=request.getParameter("date2");
		java.sql.Date Date1=null;
		java.sql.Date Date2=null;
		if(time1==null||time2==null||time1==""||time2==""){//默认日期
			try {
				Date1=borrowAndReturnBookdao.Mindate();
				Date2=borrowAndReturnBookdao.Maxdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		else{//用户输入日期
		Date1=java.sql.Date.valueOf(time1);
		Date2=java.sql.Date.valueOf(time2);
		}
		request.getSession().setAttribute("date1", ""+Date1);
		request.getSession().setAttribute("date2", ""+Date2);
		
		ArrayList<BorrowAndRetuenbook> list = new ArrayList<BorrowAndRetuenbook>();
		try {
			list=borrowAndReturnBookdao.FineStatistics(Date1,Date2);
			if(list!=null){
				request.setAttribute("list_fine",list);
				request.getRequestDispatcher("Statistics.jsp").forward(request, response);
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
	}	

}
