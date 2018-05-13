package com.library.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import com.library.bean.Libcard;
import com.library.bean.Book;
import com.library.bean.Bookstatistics;
import com.library.bean.BorrowAndRetuenbook;
import com.library.bean.User;
import com.library.jdbc.GetConnection;

public class FenYe {
	ResultSet rs = null;
	Connection conn;
	PreparedStatement pstmt = null;
	
	//得到总页数，rowsPerPage是每页行数，SQL是查询语句
	 public int getMaxPage(int rowsPerPage,String SQL)throws SQLException {
		 int maxPage;
	     int maxRowCount = 0;
	     String sql = SQL;
	     conn = GetConnection.getCon();
	     pstmt = conn.prepareStatement(sql);
		 rs = pstmt.executeQuery();
         if (rs.next()){
             maxRowCount = rs.getInt(1);  //总行数
         }
         maxPage = (maxRowCount + rowsPerPage - 1) / rowsPerPage;  //总页数
         return maxPage;
	 }
	 //得到Libcard每页数据;curPage是当前行,rowsPerPage是每页行数,SQL是查询语句
	 public ArrayList<Libcard> getLibcardData(String SQL)throws SQLException{
		 String sql = SQL;
		 conn = GetConnection.getCon();
	     pstmt = conn.prepareStatement(sql);
		 rs = pstmt.executeQuery();
		 if (rs != null) {
				ArrayList<Libcard> list = new ArrayList<Libcard>();
				while (rs.next()) {
					Libcard libcard = new Libcard();
					libcard.setlibcardno(rs.getString("LIBCARDNO"));
					libcard.setcardno(rs.getString("CARDNO"));
					libcard.setname(rs.getString("NAME"));
					libcard.setcardtype(rs.getString("CARDTYPE"));
					libcard.setcreatedate(rs.getString("CREATEDATE"));
					libcard.setphone(rs.getString("PHONE"));
					libcard.setemall(rs.getString("EMAIL"));
					libcard.setaddress(rs.getString("ADDRESS"));
					libcard.setcreateuser(rs.getString("CREATEUSER"));
					libcard.seteffectivedate(rs.getString("EFFECTIVEDATE"));
					list.add(libcard);
				}
				return list;
			} else {
				return null;
			}
      }
	//得到Book每页数据;curPage是当前行,rowsPerPage是每页行数,SQL是查询语句
		 public ArrayList<Book> getBookData(String SQL)throws SQLException{
			 String sql = SQL;
			 conn = GetConnection.getCon();
		     pstmt = conn.prepareStatement(sql);
			 rs = pstmt.executeQuery();
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
	
}
