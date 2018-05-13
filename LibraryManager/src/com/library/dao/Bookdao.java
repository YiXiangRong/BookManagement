package com.library.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import com.library.bean.Bookstatistics;
import com.library.bean.Book;
import com.library.bean.Libcard;
import com.library.jdbc.GetConnection;

//图书的增删改查
public class Bookdao {
	private Connection conn;
	private PreparedStatement pstmt = null;

	// 图书入库
	public boolean Book_into_store(String bookname, String bookcode,
			String author, String booktype, String press, double price,
			String printing,String pressdate, String content, String createuser, String createdate,
			String storetype, String donationuser,int number,int sum) throws SQLException {
		conn = GetConnection.getCon();
		String sql = "insert into tb_lib_books(BOOKNAME,BOOKCODE,AUTHOR,BOOKTYPE,PRESS,PRICE,PRINTING,PRESSDATE,CONTENT,CREATEUSER,CREATEDATE,STORAGETYPE,DONATIONUSER,NUMBER,SUM,STATE)"
				+ "VALUES('"                   
				+ bookname
				+ "','"
				+ bookcode
				+ "','"
				+ author
				+ "','"
				+ booktype
				+ "','"
				+ press
				+ "','"
				+ price
				+ "','"
				+ printing
				+ "','"
				+ pressdate
				+ "','"
				+ content
				+ "','"
				+ createuser
				+ "','"
				+ createdate
				+ "','"
				+ storetype + "','" + donationuser + "','" + number + "','" + sum + "','0')";
		pstmt = conn.prepareStatement(sql);
		int num = pstmt.executeUpdate();
		if (num > 0) {
			return true;
		} else {
			return false;
		}
	}
	
	//插入图片
	public boolean BookImage(String bookcode,String address)throws SQLException{
		conn = GetConnection.getCon();
		String sql = "update tb_lib_books set IMGADD='"+address+"' where BOOKCODE=?";
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, bookcode);
		
		int rows = pstmt.executeUpdate();
		if (rows > 0) {
			return true;
		} else {
			return false;
		}
	}
	//删除图书前判断是否借出
	public boolean Isborrow(String bookcode) throws SQLException {
		conn = GetConnection.getCon();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select BOOKCODE from tb_lib_borrowandretuenbooks";
		pstmt = conn.prepareStatement(sql);
		rs = pstmt.executeQuery();
		boolean i=false;//没借出
		if (rs != null) {
			while (rs.next()) {
				String str=rs.getString("BOOKCODE");
				if(str.equals(bookcode)){//借出过
					i=true;
					break;
				}
			}
		}
		
		return i;
		
	}

	// 图书出库
	public boolean delete(String bookcode) throws SQLException {

		conn = GetConnection.getCon();
		String sql = "delete from tb_lib_books where BOOKCODE=?";
		pstmt = conn.prepareStatement(sql);
		
		pstmt.setString(1, bookcode);
		int rows = pstmt.executeUpdate();
		if (rows > 0) {
			return true;
		} else {
			return false;
		}

	}

	// 图书借出
	public boolean Book_out(String bookcode)
			throws SQLException {
		conn = GetConnection.getCon();
		String sql = "update tb_lib_books set NUMBER=NUMBER-1,STATE='1' where BOOKCODE=?";
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, bookcode);
		
		int rows = pstmt.executeUpdate();
		if (rows > 0) {
			return true;
		} else {
			return false;
		}
	}

	// 图书还进
	public boolean Book_in(String bookcode)
			throws SQLException {
		conn = GetConnection.getCon();
		String sql = "update tb_lib_books set NUMBER=NUMBER+1,STATE='0' where BOOKCODE=?";
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, bookcode);
		
		int rows = pstmt.executeUpdate();
		if (rows > 0) {
			return true;
		} else {
			return false;
		}
	}

	
	// 查询一本图书
	public Book inquireOnebook(String bookcode) throws SQLException {
		conn = GetConnection.getCon();
		ResultSet rs = null;
		String sql = "select * from tb_lib_books where BOOKCODE=?";
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, bookcode);
		rs = pstmt.executeQuery();
		if(rs.next()) {
				Book book = new Book();

				book.setbookname(rs.getString("BOOKNAME"));
				book.setbookcode(rs.getString("BOOKCODE"));
				book.setauthor(rs.getString("AUTHOR"));
				book.setbooktype(rs.getString("BOOKTYPE"));
				book.setpress(rs.getString("PRESS"));
				book.setprice(rs.getDouble("PRICE"));
				
				book.setprinting(rs.getString("PRINTING"));
				book.setpressdate(rs.getString("PRESSDATE"));
				book.setcontent(rs.getString("CONTENT"));
				
				book.setimgadd(rs.getString("IMGADD"));
				book.setcreateuser(rs.getString("CREATEUSER"));
				book.setcreatedate(rs.getDate("CREATEDATE"));
				book.setstoretype(rs.getString("STORAGETYPE"));
				book.setdonationuser(rs.getString("DONATIONUSER"));
				book.setnumber(rs.getInt("NUMBER"));
				book.setsum(rs.getInt("SUM"));
				book.setSTATE(rs.getString("STATE"));
				return book;
		} else {
			return null;
		}

	}
	public Book inquireNameAndCode(String code)throws SQLException {
		conn = GetConnection.getCon();
		ResultSet rs = null;
		String sql="select * from tb_lib_books where BOOKCODE=?";
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, code);
		rs = pstmt.executeQuery();
		if(rs.next()){
			Book book=new Book();
			book.setbookname(rs.getString("BOOKNAME"));
			book.setbookcode(rs.getString("BOOKCODE"));
			book.setauthor(rs.getString("AUTHOR"));
			book.setbooktype(rs.getString("BOOKTYPE"));
			book.setpress(rs.getString("PRESS"));
			book.setprice(rs.getDouble("PRICE"));
			
			book.setprinting(rs.getString("PRINTING"));
			book.setpressdate(rs.getString("PRESSDATE"));
			book.setcontent(rs.getString("CONTENT"));
			
			book.setimgadd(rs.getString("IMGADD"));
			book.setcreateuser(rs.getString("CREATEUSER"));
			book.setcreatedate(rs.getDate("CREATEDATE"));
			book.setstoretype(rs.getString("STORAGETYPE"));
			book.setdonationuser(rs.getString("DONATIONUSER"));
			book.setnumber(rs.getInt("NUMBER"));
			book.setsum(rs.getInt("SUM"));
			book.setSTATE(rs.getString("STATE"));
			return book;
		}
		else return null;
	}
	//三个条件查询图书
	public ArrayList<Book> inquireBookBythree(String bookname ,String author,String type) throws SQLException {
		conn = GetConnection.getCon();
		ResultSet rs = null;
		String sql;
		if(bookname!=""&&type==""&&author=="")//只有书名
		{
			sql = "select * from tb_lib_books where BOOKNAME like'%" +bookname+ "%'";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
		}
		else if(bookname!=""&&type!=""&&author=="")//书名和类型
		{
			sql = "select * from tb_lib_books where BOOKTYPE=? and BOOKNAME like'%" +bookname+ "%'";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, type); 
			 rs = pstmt.executeQuery();
		}
		else if(bookname!=""&&type==""&&author!="")//书名和作者
		{
			sql = "select * from tb_lib_books where BOOKNAME like'%" +bookname+ "%' and AUTHOR=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, author);
			 rs = pstmt.executeQuery();
		}
		else if(bookname==""&&type==""&&author!="")//只有作者
		{
			sql = "select * from tb_lib_books where AUTHOR=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, author);
			 rs = pstmt.executeQuery();
		}
		else if(bookname==""&&type!=""&&author=="")//只有类型
		{
			sql = "select * from tb_lib_books where BOOKTYPE=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, type);
			 rs = pstmt.executeQuery();
		}
		else if(bookname==""&&type!=""&&author!="")//类型和作者
		{
			sql = "select * from tb_lib_books where BOOKTYPE=? and AUTHOR=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, type);
			pstmt.setString(2, author);
			 rs = pstmt.executeQuery();
		}
		else if(bookname!=""&&type!=""&&author!="")//类型,作者,书名
		{
			sql = "select * from tb_lib_books where BOOKTYPE=? and AUTHOR=? and BOOKNAME like'%" +bookname+ "%'";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, type);
			pstmt.setString(2, author);
			 rs = pstmt.executeQuery();
		}
		else{
			sql = "select * from tb_lib_books";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
		}
		
		if (rs != null) {
			ArrayList<Book> list = new ArrayList<Book>();
			while (rs.next()) {
				Book book = new Book();

				book.setbookname(rs.getString("BOOKNAME"));
				book.setbookcode(rs.getString("BOOKCODE"));
				book.setauthor(rs.getString("AUTHOR"));
				book.setbooktype(rs.getString("BOOKTYPE"));
				book.setpress(rs.getString("PRESS"));
				book.setprice(rs.getDouble("PRICE"));
				
				book.setprinting(rs.getString("PRINTING"));
				book.setpressdate(rs.getString("PRESSDATE"));
				book.setcontent(rs.getString("CONTENT"));
				
				book.setimgadd(rs.getString("IMGADD"));
				book.setcreateuser(rs.getString("CREATEUSER"));
				book.setcreatedate(rs.getDate("CREATEDATE"));
				book.setstoretype(rs.getString("STORAGETYPE"));
				book.setdonationuser(rs.getString("DONATIONUSER"));
				book.setnumber(rs.getInt("NUMBER"));
				book.setsum(rs.getInt("SUM"));
				book.setSTATE(rs.getString("STATE"));
				list.add(book);
			}
			return list;
		} else {
			return null;
		}
	}
	// 两个条件查询图书
	public ArrayList<Book> inquireBookBy(String bookname ,String type) throws SQLException {
			conn = GetConnection.getCon();
			ResultSet rs = null;
			String sql;
			if(bookname==""&&type!="")
			{
				sql = "select * from tb_lib_books where BOOKTYPE=?";
				pstmt = conn.prepareStatement(sql);
				 pstmt.setString(1, type);
				 rs = pstmt.executeQuery();
			}
			else if(bookname!=""&&type==""){
				sql = "select * from tb_lib_books where BOOKNAME like'%" +bookname+ "%'";
				pstmt = conn.prepareStatement(sql);
				 
				 rs = pstmt.executeQuery();
			}
			else if(bookname!=""&&type!=""){
				sql = "select * from tb_lib_books where BOOKNAME like'%" +bookname+ "%' and BOOKTYPE=?";
				pstmt = conn.prepareStatement(sql);
				
				 pstmt.setString(1, type);
				 rs = pstmt.executeQuery();
			}
			else{
				sql = "select * from tb_lib_books";
				pstmt = conn.prepareStatement(sql);
				rs = pstmt.executeQuery();
			}
			
			if (rs != null) {
				ArrayList<Book> list = new ArrayList<Book>();
				while (rs.next()) {
					Book book = new Book();

					book.setbookname(rs.getString("BOOKNAME"));
					book.setbookcode(rs.getString("BOOKCODE"));
					book.setauthor(rs.getString("AUTHOR"));
					book.setbooktype(rs.getString("BOOKTYPE"));
					book.setpress(rs.getString("PRESS"));
					book.setprice(rs.getDouble("PRICE"));
					
					book.setprinting(rs.getString("PRINTING"));
					book.setpressdate(rs.getString("PRESSDATE"));
					book.setcontent(rs.getString("CONTENT"));
					
					book.setimgadd(rs.getString("IMGADD"));
					book.setcreateuser(rs.getString("CREATEUSER"));
					book.setcreatedate(rs.getDate("CREATEDATE"));
					book.setstoretype(rs.getString("STORAGETYPE"));
					book.setdonationuser(rs.getString("DONATIONUSER"));
					book.setnumber(rs.getInt("NUMBER"));
					book.setsum(rs.getInt("SUM"));
					book.setSTATE(rs.getString("STATE"));
					list.add(book);
				}
				return list;
			} else {
				return null;
			}

		}
	//得到总页数，rowsPerPage是每页行数，SQL是查询语句
	 public int getMaxPage(int rowsPerPage)throws SQLException {
		 int maxPage;
		 FenYe fenye=new FenYe();
		 String sql="select count(*) from tb_lib_books";
		 maxPage=fenye.getMaxPage(rowsPerPage, sql);
        return maxPage;
	 }
	//查询所有图书信息
		public ArrayList<Book> inquireAllbooks(int curPage,int rowsPerPage) throws SQLException {
			
			int m=(curPage-1)*rowsPerPage;
			int n=rowsPerPage;
			String sql = "select * from tb_lib_books limit "+m+","+n+"";
			ArrayList<Book> list = new ArrayList<Book>();
			FenYe fenye=new FenYe();
			list=fenye.getBookData(sql);
			if(list!=null){
				return list;
			} else {
				return null;
			}
			

		}
		// 条件查询借出图书
				public ArrayList<Book> inquireBorrowedBookBy(String bookname ,String type) throws SQLException {
					conn = GetConnection.getCon();
					ResultSet rs = null;
					String sql;
					if(bookname==""&&type!="")
					{
						sql = "select * from tb_lib_books where BOOKTYPE=? and STATE='1'";
						pstmt = conn.prepareStatement(sql);
						 pstmt.setString(1, type);
						 rs = pstmt.executeQuery();
					}
					else if(bookname!=""&&type==""){
						sql = "select * from tb_lib_books where BOOKNAME like'%" +bookname+ "%' and STATE='1'";
						pstmt = conn.prepareStatement(sql);
						 
						 rs = pstmt.executeQuery();
					}
					else if(bookname!=""&&type!=""){
						sql = "select * from tb_lib_books where BOOKNAME like'%" +bookname+ "%' and BOOKTYPE=? and STATE='1'";
						pstmt = conn.prepareStatement(sql);
						
						 pstmt.setString(1, type);
						 rs = pstmt.executeQuery();
					}
					else{
						sql = "select * from tb_lib_books where STATE='1'";
						pstmt = conn.prepareStatement(sql);
						rs = pstmt.executeQuery();
					}
					
					if (rs != null) {
						ArrayList<Book> list = new ArrayList<Book>();
						while (rs.next()) {
							Book book = new Book();

							book.setbookname(rs.getString("BOOKNAME"));
							book.setbookcode(rs.getString("BOOKCODE"));
							book.setauthor(rs.getString("AUTHOR"));
							book.setbooktype(rs.getString("BOOKTYPE"));
							book.setpress(rs.getString("PRESS"));
							book.setprice(rs.getDouble("PRICE"));
							
							book.setprinting(rs.getString("PRINTING"));
							book.setpressdate(rs.getString("PRESSDATE"));
							book.setcontent(rs.getString("CONTENT"));
							
							book.setimgadd(rs.getString("IMGADD"));
							book.setcreateuser(rs.getString("CREATEUSER"));
							book.setcreatedate(rs.getDate("CREATEDATE"));
							book.setstoretype(rs.getString("STORAGETYPE"));
							book.setdonationuser(rs.getString("DONATIONUSER"));
							book.setnumber(rs.getInt("NUMBER"));
							book.setsum(rs.getInt("SUM"));
							book.setSTATE(rs.getString("STATE"));
							list.add(book);
						}
						return list;
					} else {
						return null;
					}

				}
			
		//查询所有借出图书信息
				public ArrayList<Book> inquireAllBorrowed() throws SQLException {
					conn = GetConnection.getCon();
					PreparedStatement pstmt = null;
					ResultSet rs = null;
					String sql = "select * from tb_lib_books where STATE='1'";
					pstmt = conn.prepareStatement(sql);
					
					rs = pstmt.executeQuery();
					if (rs != null) {
						ArrayList<Book> list = new ArrayList<Book>();
						while (rs.next()) {
							Book book = new Book();

							book.setbookname(rs.getString("BOOKNAME"));//书名
							book.setbookcode(rs.getString("BOOKCODE"));//图书编号
							book.setauthor(rs.getString("AUTHOR"));
							book.setbooktype(rs.getString("BOOKTYPE"));
							book.setpress(rs.getString("PRESS"));
							book.setprice(rs.getDouble("PRICE"));
							book.setprinting(rs.getString("PRINTING"));
							book.setpressdate(rs.getString("PRESSDATE"));
							book.setcontent(rs.getString("CONTENT"));
							book.setimgadd(rs.getString("IMGADD"));
							book.setcreateuser(rs.getString("CREATEUSER"));
							book.setcreatedate(rs.getDate("CREATEDATE"));
							book.setstoretype(rs.getString("STORAGETYPE"));
							book.setdonationuser(rs.getString("DONATIONUSER"));
							book.setnumber(rs.getInt("NUMBER"));
							book.setsum(rs.getInt("SUM"));
							book.setSTATE(rs.getString("STATE"));
							list.add(book);
						}
						return list;
					} else {
						return null;
					}

				}
		//验证修改后用户名是否重复
		public boolean Yanzheng2(String bookcode,String oldBook)throws SQLException{
			conn = GetConnection.getCon();
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String sql = "select BOOKCODE from tb_lib_books where BOOKCODE!='"+oldBook+"'";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			boolean i=false;
			if (rs != null) {
				while (rs.next()) {
					String str=rs.getString("BOOKCODE");
					if(str.equals(bookcode)){
						i=true;
						break;
					}
				}
			}
			return i;
		}
	
	//验证新增图书编号是否重复
	public boolean Yanzheng(String bookcode)throws SQLException{
		conn = GetConnection.getCon();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select BOOKCODE from tb_lib_books";
		pstmt = conn.prepareStatement(sql);
		rs = pstmt.executeQuery();
		boolean i=false;
		if (rs != null) {
			while (rs.next()) {
				String str=rs.getString("BOOKCODE");
				if(str.equals(bookcode)){
					i=true;
					break;
				}
			}
		}
		return i;
	}
//修改图书信息
	public boolean update_book(String bookname, String bookcode,
			String author, String booktype, String press, double price,
			String printing,String pressdate, String content, String createuser, String createdate,
			String storetype, String donationuser,int number,String oldcode)throws SQLException{ 
				conn = GetConnection.getCon();
				
				String sql = "update tb_lib_books set BOOKNAME='"+bookname+"',BOOKCODE='"+bookcode+"',AUTHOR='"+author+"',BOOKTYPE='"+booktype+"',PRESS='"+press+"',PRICE='"+price+"',PRINTING='"+printing+"',PRESSDATE='"+pressdate+"',CONTENT='"+content+"',CREATEUSER='"+createuser+"',CREATEDATE='"+createdate+"',STORAGETYPE='"+storetype+"',DONATIONUSER='"+donationuser+"',NUMBER='"+number+"' where BOOKCODE='"+oldcode+"'";
				pstmt = conn.prepareStatement(sql);
				int rows = pstmt.executeUpdate();
				if (rows > 0) {
					return true;
				} else {
					return false;
				}
			}
//统计查询
	public ArrayList<Bookstatistics> book_statistics(java.sql.Date date1,java.sql.Date date2)throws SQLException{
		conn = GetConnection.getCon();
		ResultSet rs = null;
		
		String _sql = "select BOOKTYPE,sum(SUM),sum(NUMBER) from tb_lib_books where CREATEDATE between '"+date1+"' and '"+date2+"' group by BOOKTYPE";
		pstmt = conn.prepareStatement(_sql);
		rs = pstmt.executeQuery();
		if (rs != null) {
			ArrayList<Bookstatistics> list=new ArrayList<Bookstatistics>();
			while(rs.next()){
				Bookstatistics bookstatistics=new Bookstatistics();
				bookstatistics.settype(rs.getString("BOOKTYPE"));//类型
				bookstatistics.setsum(rs.getInt("sum(SUM)"));//总数
				bookstatistics.setstoresum(rs.getInt("sum(NUMBER)"));//库存数
				bookstatistics.setborrowsum(rs.getInt("sum(SUM)")-rs.getInt("sum(NUMBER)"));
				list.add(bookstatistics);
			}
			return list;
		}
		else return null;
	}
	//求最早日期
		public java.sql.Date Mindate()throws SQLException {
			conn = GetConnection.getCon();
			ResultSet rs = null;
			
			String _sql = "select min(CREATEDATE) from tb_lib_books";
			pstmt = conn.prepareStatement(_sql);
			rs = pstmt.executeQuery();
			if(rs.next()){
				return rs.getDate("min(CREATEDATE)");
			}
			else return null;
		}
		//求最迟日期
			public java.sql.Date Maxdate()throws SQLException {
				conn = GetConnection.getCon();
				ResultSet rs = null;
				
				String _sql = "select max(CREATEDATE) from tb_lib_books";
				pstmt = conn.prepareStatement(_sql);
				rs = pstmt.executeQuery();
				if(rs.next()){
					return rs.getDate("max(CREATEDATE)");
				}
				else return null;
			}	

}
