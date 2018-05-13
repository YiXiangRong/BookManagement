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
//图书入库
public class Books_Into_store extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String bookcode;
	 public int rowsPerPage;  //每页显示的行数
	    public int curPage;  //当前页页码
	    public int maxPage;  //总共页数
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
		request.setCharacterEncoding("UTF-8");//传值编码
		response.setContentType("text/html;charset=UTF-8");//设置传输编码
		String op=request.getParameter("op");
		
		String curPage1 = request.getParameter("page");  //获取当前页页码
        if (curPage1 == null){
            curPage = 1;
            request.setAttribute("curPage",curPage);  //设置curPage对象
        }else {
            curPage = Integer.parseInt(curPage1);
            if (curPage < 1){
                curPage = 1;
            }
            request.setAttribute("curPage",curPage);
        }
        
		if(op.equals("book")){//增加图书
			this.BooksInfo(request,response);
		}
		else if(op.equals("image")){//上传图片
			this.BooksAddress(bookcode,request,response);
		}
		else if(op.equals("refresh")){//查看所有图书
			this.InquireAllbooks(request, response);
		}
		else if(op.equals("updatebook")){//更新图书
			this.UpdateBook(request, response);
		}
		else if(op.equals("deletebook")){//删除图书
			this.DeleteBook(request, response);
		}
		else if(op.equals("searchbook")){//查询图书
			this.InquireBookBy(request, response);
		}
		else if(op.equals("to_update")){//为修改借书证查询相关参数
			this.To_update(request, response);
		}
		
	}
	private void To_update(HttpServletRequest request,
			HttpServletResponse response)throws ServletException, IOException {
		// TODO Auto-generated method stub
		String bookcode=request.getParameter("boxbook");//图书编号
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

	//按条件查询
	private void InquireBookBy(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException{
		// TODO Auto-generated method stub
		String booktype=request.getParameter("bytype");
		String bookname=request.getParameter("byname");
		String bookauthor=request.getParameter("byauthor");
		request.getSession().setAttribute("Bname", bookname);//保存查询条件
		request.getSession().setAttribute("Bauthor", bookauthor);//保存查询条件
		request.setAttribute("bytype", booktype);//保存查询条件
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
				request.getSession().setAttribute("Inqbookbytype", "查询失败，稍后再试！");
	            this.InquireAllbooks(request, response);}
	             
			}
			
		 catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	

	//删除图书
	private void DeleteBook(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException  {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");//传值编码
		response.setContentType("text/html;charset=UTF-8");//设置传输编码
		bookcode=request.getParameter("boxbook");
		Bookdao bookdao=new Bookdao();
	    try {
	    	
	    	if (bookdao.Isborrow(bookcode)==false)//没借出去
		{
			boolean i=bookdao.delete(bookcode);//删除书是否成功
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
			//书已借出，无法删除
    		request.getSession().setAttribute("DelbookF", "1");
           
		}
	    this.InquireAllbooks(request, response);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	//存储图书信息
	protected void BooksInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");//传值编码
		response.setContentType("text/html;charset=UTF-8");//设置传输编码
		
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
	   
	    //String imgadd=request.getParameter("upload");//图片地址
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
			//借书证已存在
    		request.getSession().setAttribute("STRbookF", "1");
            response.sendRedirect("increasebook.jsp");
		}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//上传图片
	protected void BooksAddress(String bookcode,HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");//传值编码
		response.setContentType("text/html;charset=UTF-8");//设置传输编码
		String piclist=null;//存放图片名
		String path="D:\\workspace\\LibraryManager\\web\\images\\books";
		
		System.out.println(path);
		//String path=request.getSession().getServletContext().getRealPath("/images/books");
		//String path="C:/images";
		DiskFileItemFactory factory=new DiskFileItemFactory();
        ServletFileUpload sfu=new ServletFileUpload(factory);
        sfu.setHeaderEncoding("UTF-8");  //处理中文问题
        sfu.setSizeMax(1024*1024);   //限制文件大小
        try {
        	
            List<FileItem> fileItems= sfu.parseRequest(request);  //解码请求 得到所有表单元素
            for (FileItem fi : fileItems) {
                //有可能是 文件，也可能是普通文字 
                if (!fi.isFormField()) { //这个选项不是 文字 
                   
                    // 是文件
                	
                    String fn=fi.getName();
                    String f1=fn.substring(fn.lastIndexOf("\\")+1); 
                    
                    // fn 是可能是这样的 c:\abc\de\tt\fish.jpg
                    File file = new File(path+"/"+f1); 
                    fi.write(file);
                    
                    if (fn.endsWith(".jpg")) {
                        piclist=f1;  //把图片放入集合
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
	//查询所有图书
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
				request.getSession().setAttribute("str_Inqbook", "查询失败，稍后再试！");
	            response.sendRedirect("bookmanage.jsp");}
	             
			}
			
		 catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	//修改图书信息
	protected void UpdateBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");//传值编码
		response.setContentType("text/html;charset=UTF-8");//设置传输编码
		String oldbook=request.getParameter("n");//修改前的编号
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
	   
	    //String imgadd=request.getParameter("upload");//图片地址
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
