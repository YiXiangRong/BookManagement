package com.library.servlet;

import java.awt.Window;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.library.bean.Book;
import com.library.bean.BorrowAndRetuenbook;
import com.library.bean.Libcard;
import com.library.dao.Bookdao;
import com.library.dao.BorrowAndReturnBookdao;
import com.library.dao.Libcarddao;

/**
 * Servlet implementation class Jie_Huan_Shu
 */
public class Jie_Huan_Shu extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public int rowsPerPage;  //ÿҳ��ʾ������
    public int curPage;  //��ǰҳҳ��
    public int maxPage;  //�ܹ�ҳ��
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Jie_Huan_Shu() {
    	rowsPerPage = 5;
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    @SuppressWarnings("deprecation")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");//��ֵ����
		response.setContentType("text/html;charset=UTF-8");//���ô������
		String op=request.getParameter("op");
		String code=request.getParameter("str1");//Ҫ���ͼ����
		
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
		
		if(op.equals("refresh")){//��ʾ����
			this.InquireAllbooks(request, response);
		}
		else if(op.equals("index")){//��ҳ
			this.InquireAllbooks(request, response);
		}
		else if(op.equals("searchbook")){//����ͼ��
			this.InquireBookBy(request, response);
		}
		else if(op.equals("searchBorrowedbookBy")){//����ͼ��
			this.InquireBorrowedBookBy(request, response);
		}
		
		else if(op.equals("Borrow")){//Ϊ���鴫����Ϣ
			this.To_Borrowbook(request, response,code);
		}
		else if(op.equals("to_return")){//��ת�����鵥
			this.To_Return(request, response,code);
		}
		else if(op.equals("look_borrow")){//��ѯ����鼮
			this.Look_Borrow(request, response);
		}
		else if(op.equals("jieshu")){//����
			this.Borrokbooks(request, response);
		}
		else if(op.equals("huanshu")){//����
			this.Returnbooks(request, response);
		}
	}
    //������ѯ���ͼ��
    private void InquireBorrowedBookBy(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException{
		// TODO Auto-generated method stub
    	String booktype=request.getParameter("bytype");
        String bookname=request.getParameter("byname");
        request.getSession().setAttribute("Bname2", bookname);
        request.setAttribute("bytype",booktype);
		Bookdao bookdao = new Bookdao();
		try {
			ArrayList<Book> list = new ArrayList<Book>();
			list=bookdao.inquireBorrowedBookBy(bookname ,booktype);
			
				if(list!= null)
				{
					request.setAttribute("list_by",list);
					request.getRequestDispatcher("InquireBorrowedBookBy.jsp").forward(request, response);
	            }
				else{
				request.getSession().setAttribute("Inqborrowedby", "1");
	            this.InquireAllbooks(request, response);}
	             
			}
			
		 catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
//��ת�����鵥
	private void To_Return(HttpServletRequest request,
			HttpServletResponse response, String code) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Book book = new Book();
		Bookdao Bookdao=new Bookdao();
		try {
			book=Bookdao.inquireNameAndCode(code);
			if(book!=null){
				request.setAttribute("to_book",book);
				request.getRequestDispatcher("To_return.jsp").forward(request, response);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	//�鿴���н��ȥ����
	private void Look_Borrow(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Bookdao bookdao = new Bookdao();
		try {
			ArrayList<Book> list = new ArrayList<Book>();
			list=bookdao.inquireAllBorrowed();
			
				if(list!= null)
				{
					request.setAttribute("borrowed_books",list);
					request.getRequestDispatcher("AllBorrowed_books.jsp").forward(request, response);
	            }
				else{
				request.getSession().setAttribute("jiehuan_book", "��ѯʧ�ܣ��Ժ����ԣ�");
	            response.sendRedirect("jie_huan_shu_manager.jsp");}
	             
			}
			
		 catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//��ת�����鵥
	private void To_Borrowbook(HttpServletRequest request,
			HttpServletResponse response,String code)throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		Book book = new Book();
		Bookdao Bookdao=new Bookdao();
		try {
			book=Bookdao.inquireNameAndCode(code);
			if(book!=null){
				request.setAttribute("to_book",book);
				request.getRequestDispatcher("To_borrow.jsp").forward(request, response);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	//��ѯ�軹���¼
	protected void InquireOneBorrow(HttpServletRequest request,
			HttpServletResponse response,String LIBCARDNO,String BOOKCODE) throws ServletException, IOException{
		
		BorrowAndReturnBookdao borrowAndReturnBookdao=new BorrowAndReturnBookdao();
		try {
			BorrowAndRetuenbook list = new BorrowAndRetuenbook();
			list=borrowAndReturnBookdao.inquireoneborrow(LIBCARDNO,BOOKCODE);
				if(list!= null)
				{
					request.setAttribute("list_BorrowInfo",list);
				
					request.getRequestDispatcher("Resultform.jsp").forward(request, response);
	            }
				
				
	            
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//����
	protected void Returnbooks(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException{
		// TODO Auto-generated method stub
		String LIBCARDNO = request.getParameter("LIBCARDNO");
		String BOOKCODE = request.getParameter("BOOKCODE");
		String RETURNMANAGER= request.getParameter("RETURNMANAGER");
		String str1=request.getParameter("RETURNDATE");
		java.sql.Date RETURNDATE=java.sql.Date.valueOf(str1);
		BorrowAndReturnBookdao borrowAndReturnBookdao=new BorrowAndReturnBookdao();
		try {
			boolean i=borrowAndReturnBookdao.return_book(LIBCARDNO, BOOKCODE, RETURNDATE, RETURNMANAGER);
		    //����ɹ�
			if(i){
		    	this.InquireOneBorrow(request,response,LIBCARDNO,BOOKCODE);
		    }
			//����ʧ��
		    else
			{request.getSession().setAttribute("STR_14", "3");
             response.sendRedirect("write_returnform.jsp");}
	    } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
//����
	protected void Borrokbooks(HttpServletRequest request,
			HttpServletResponse response)throws ServletException, IOException {
		// TODO Auto-generated method stub
		String LIBCARDNO = request.getParameter("LIBCARDNO");
		String BOOKNAME = request.getParameter("BOOKNAME");
		String BOOKCODE = request.getParameter("BOOKCODE");
		String BORROWMANAGER = request.getParameter("BORROWMANAGER");
		String NAME = request.getParameter("NAME");
		
		String str1=request.getParameter("BORROWDATE");
		java.sql.Date BORROWDATE=java.sql.Date.valueOf(str1);
		String str2=request.getParameter("SHOULDRETURNDATE");
		java.sql.Date SHOULDRETURNDATE=java.sql.Date.valueOf(str2);
		
		
		BorrowAndReturnBookdao borrowAndReturnBookdao=new BorrowAndReturnBookdao();
	    try {
			boolean i=borrowAndReturnBookdao.borrow_book(LIBCARDNO,NAME,BOOKNAME,BOOKCODE,BORROWDATE,SHOULDRETURNDATE,BORROWMANAGER);
		    if(i){
		    	request.getSession().setAttribute("STR_13", "1");
                response.sendRedirect("write_borrowform.jsp");
		    }
		    else
			{request.getSession().setAttribute("STR_13", "2");
            response.sendRedirect("write_borrowform.jsp");}
	    } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	//��������ѯͼ��
protected void InquireBookBy(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException{
         String booktype=request.getParameter("bytype");
         String bookname=request.getParameter("byname");
         request.getSession().setAttribute("Bname2", bookname);
         request.setAttribute("bytype",booktype);
		Bookdao bookdao = new Bookdao();
		try {
			ArrayList<Book> list = new ArrayList<Book>();
			list=bookdao.inquireBookBy(bookname ,booktype);
			
				if(list!= null)
				{
					request.setAttribute("list_by",list);
					request.getRequestDispatcher("BorrowInquireBy.jsp").forward(request, response);
	            }
				else{
				request.getSession().setAttribute("Inqbookby", "1");
	            this.InquireAllbooks(request, response);}
	             
			}
			
		 catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	//��ѯ����ͼ��
		protected void InquireAllbooks(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			Bookdao bookdao = new Bookdao();
			try {
				ArrayList<Book> list = new ArrayList<Book>();
				list=bookdao.inquireAllbooks(curPage,rowsPerPage);
				maxPage=bookdao.getMaxPage(rowsPerPage);
					if(list!= null)
					{
						request.setAttribute("maxPage", maxPage);
						request.setAttribute("jie_huan_books",list);
						request.getRequestDispatcher("jie_huan_shu_manager.jsp").forward(request, response);
		            }
					else{
					request.getSession().setAttribute("jiehuan_book", "��ѯʧ�ܣ��Ժ����ԣ�");
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
		doPost(request, response);
	}

}
