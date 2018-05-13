package com.library.servlet;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.text.ParseException;




import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.output.*;

import com.library.bean.Book;
import com.library.dao.Bookdao;
import com.library.dao.Libcarddao;

/**
 * Servlet implementation class Books_Into_store
 */
//ͼ�����
public class Books_Into_store extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String bookcode;
	 public int rowsPerPage;  //ÿҳ��ʾ������
	    public int curPage;  //��ǰҳҳ��
	    public int maxPage;  //�ܹ�ҳ��
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Books_Into_store() {
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
        
		if(op.equals("book")){//����ͼ��
			this.BooksInfo(request,response);
		}
		else if(op.equals("image")){//�ϴ�ͼƬ
			this.BooksAddress(bookcode,request,response);
		}
		else if(op.equals("refresh")){//�鿴����ͼ��
			this.InquireAllbooks(request, response);
		}
		else if(op.equals("updatebook")){//����ͼ��
			this.UpdateBook(request, response);
		}
		else if(op.equals("deletebook")){//ɾ��ͼ��
			this.DeleteBook(request, response);
		}
		else if(op.equals("searchbook")){//��ѯͼ��
			this.InquireBookBy(request, response);
		}
		else if(op.equals("to_update")){//Ϊ�޸Ľ���֤��ѯ��ز���
			this.To_update(request, response);
		}
		
	}
	private void To_update(HttpServletRequest request,
			HttpServletResponse response)throws ServletException, IOException {
		// TODO Auto-generated method stub
		String bookcode=request.getParameter("boxbook");//ͼ����
		Bookdao bookdao = new Bookdao();
		Book book = new Book();
		try {
			book=bookdao.inquireOnebook(bookcode);
			if(book!=null){
				request.setAttribute("book",book);
				request.getRequestDispatcher("To_UpdateBook.jsp").forward(request, response);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	//��������ѯ
	private void InquireBookBy(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException{
		// TODO Auto-generated method stub
		String booktype=request.getParameter("bytype");
		String bookname=request.getParameter("byname");
		String bookauthor=request.getParameter("byauthor");
		request.getSession().setAttribute("Bname", bookname);//�����ѯ����
		request.getSession().setAttribute("Bauthor", bookauthor);//�����ѯ����
		request.setAttribute("bytype", booktype);//�����ѯ����
		Bookdao bookdao = new Bookdao();
		try {
			ArrayList<Book> list = new ArrayList<Book>();
			list=bookdao.inquireBookBythree(bookname,bookauthor,booktype);
			
				if(list!= null)
				{
					request.setAttribute("list_booksbytype",list);
					request.getRequestDispatcher("InquireBookBy.jsp").forward(request, response);
	            }
				else{
				request.getSession().setAttribute("Inqbookbytype", "��ѯʧ�ܣ��Ժ����ԣ�");
	            this.InquireAllbooks(request, response);}
	             
			}
			
		 catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	

	//ɾ��ͼ��
	private void DeleteBook(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException  {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");//��ֵ����
		response.setContentType("text/html;charset=UTF-8");//���ô������
		bookcode=request.getParameter("boxbook");
		Bookdao bookdao=new Bookdao();
	    try {
	    	
	    	if (bookdao.Isborrow(bookcode)==false)//û���ȥ
		{
			boolean i=bookdao.delete(bookcode);//ɾ�����Ƿ�ɹ�
			if(i)
			{
				request.getSession().setAttribute("STR_6", "1");
                
			}
			else
			{
				request.getSession().setAttribute("STR_6", "2");
               
			}
		}
		else{
			//���ѽ�����޷�ɾ��
    		request.getSession().setAttribute("DelbookF", "1");
           
		}
	    this.InquireAllbooks(request, response);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	//�洢ͼ����Ϣ
	protected void BooksInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");//��ֵ����
		response.setContentType("text/html;charset=UTF-8");//���ô������
		
		String bookname=request.getParameter("bookname");  
	    bookcode=request.getParameter("bookcode");
	    String author=request.getParameter("author");
	    String booktype=request.getParameter("booktype");
	    String press=request.getParameter("press");
	    double price=Double.parseDouble(request.getParameter("price"));
	    int number=Integer.parseInt(request.getParameter("number"));
	    int sum=Integer.parseInt(request.getParameter("sum"));
	    String printing=request.getParameter("printing");
	    String pressdate=request.getParameter("pressdate");
	    String content=request.getParameter("content");
	   
	    //String imgadd=request.getParameter("upload");//ͼƬ��ַ
	    String createuser=request.getParameter("createuser");
	    
	    String createdate=request.getParameter("createdate");
	    
	    String storetype=request.getParameter("storetype");
	    String donationuser=request.getParameter("donationuser");
	    
 
	    
	    Bookdao bookdao=new Bookdao();
	    try {
	    	
	    	if (bookdao.Yanzheng(bookcode)==false)
		{
			boolean i=bookdao.Book_into_store(bookname, bookcode, author, booktype, press, price, printing, pressdate, content, createuser, createdate, storetype, donationuser,number,sum);
			if(i)
			{
				request.getSession().setAttribute("STR_5", "1");
                response.sendRedirect("increaseImage.jsp");
			}
			else
			{
				request.getSession().setAttribute("STR_5", "2");
                response.sendRedirect("increasebook.jsp");
			}
		}
		else{
			//����֤�Ѵ���
    		request.getSession().setAttribute("STRbookF", "1");
            response.sendRedirect("increasebook.jsp");
		}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//�ϴ�ͼƬ
	protected void BooksAddress(String bookcode,HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");//��ֵ����
		response.setContentType("text/html;charset=UTF-8");//���ô������
		String piclist=null;//���ͼƬ��
		String path="D:\\workspace\\LibraryManager\\web\\images\\books";
		
		System.out.println(path);
		//String path=request.getSession().getServletContext().getRealPath("/images/books");
		//String path="C:/images";
		DiskFileItemFactory factory=new DiskFileItemFactory();
        ServletFileUpload sfu=new ServletFileUpload(factory);
        sfu.setHeaderEncoding("UTF-8");  //������������
        sfu.setSizeMax(1024*1024);   //�����ļ���С
        try {
        	
            List<FileItem> fileItems= sfu.parseRequest(request);  //�������� �õ����б�Ԫ��
            for (FileItem fi : fileItems) {
                //�п����� �ļ���Ҳ��������ͨ���� 
                if (!fi.isFormField()) { //���ѡ��� ���� 
                   
                    // ���ļ�
                	
                    String fn=fi.getName();
                    String f1=fn.substring(fn.lastIndexOf("\\")+1); 
                    
                    // fn �ǿ����������� c:\abc\de\tt\fish.jpg
                    File file = new File(path+"/"+f1); 
                    fi.write(file);
                    
                    if (fn.endsWith(".jpg")) {
                        piclist=f1;  //��ͼƬ���뼯��
                    }
                }                
            }  
            
            Bookdao bookdao=new Bookdao(); 
            boolean i=bookdao.BookImage(bookcode,piclist);
			if(i)
			{
				request.getSession().setAttribute("image", "1");
                response.sendRedirect("increaseImage.jsp");
			}
			else
			{
				request.getSession().setAttribute("image", "2");
                response.sendRedirect("increaseImage.jsp");
			}
        } catch (Exception e) {
            e.printStackTrace();
        }
        request.getSession().setAttribute("url1",piclist);
		
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
					request.setAttribute("list_books",list);
					request.getRequestDispatcher("bookmanage.jsp").forward(request, response);
	            }
				else{
				request.getSession().setAttribute("str_Inqbook", "��ѯʧ�ܣ��Ժ����ԣ�");
	            response.sendRedirect("bookmanage.jsp");}
	             
			}
			
		 catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	//�޸�ͼ����Ϣ
	protected void UpdateBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");//��ֵ����
		response.setContentType("text/html;charset=UTF-8");//���ô������
		String oldbook=request.getParameter("n");//�޸�ǰ�ı��
		String bookname=request.getParameter("bookname");  
	    bookcode=request.getParameter("bookcode");
	    String author=request.getParameter("author");
	    String booktype=request.getParameter("booktype");
	    String press=request.getParameter("press");
	    double price=Double.parseDouble(request.getParameter("price"));
	    int number=Integer.parseInt(request.getParameter("number"));
	    String printing=request.getParameter("printing");
	    String pressdate=request.getParameter("pressdate");
	    String content=request.getParameter("content");
	   
	    //String imgadd=request.getParameter("upload");//ͼƬ��ַ
	    String createuser=request.getParameter("createuser");
	    
	    String createdate=request.getParameter("createdate");
	    
	    String storetype=request.getParameter("storetype");
	    String donationuser=request.getParameter("donationuser");
	    
	    Bookdao bookdao=new Bookdao();
	   try {
		
		   if (bookdao.Yanzheng2(bookcode,oldbook)==false)
		{
		   boolean i= bookdao.update_book(bookname, bookcode, author, booktype, press, price, printing, pressdate, content, createuser, createdate, storetype, donationuser,number,oldbook);
	       if(i){
	    	request.getSession().setAttribute("STR_11", "1");
	    	request.getRequestDispatcher("updateBookInfo.jsp").forward(
					request, response);
	    	
	        }
	       else
		   {
			request.getSession().setAttribute("STR_11", "2");
			request.getRequestDispatcher("updateBookInfo.jsp").forward(
					request, response);
		    }
		}
		   else{
			   
			   request.getSession().setAttribute("book_upF", "1");
               request.getRequestDispatcher("updateBookInfo.jsp").forward(
						request, response);
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
		doPost(request, response);
	}

}
