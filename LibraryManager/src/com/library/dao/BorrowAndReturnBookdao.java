package com.library.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;
import java.text.SimpleDateFormat;

import com.library.bean.Book;
import com.library.bean.BorrowAndRetuenbook;
import com.library.bean.User;
import com.library.jdbc.GetConnection;

public class BorrowAndReturnBookdao {
	private Connection conn;
	private PreparedStatement pstmt = null;
	
	// 借书
	public boolean borrow_book(String LIBCARDNO,String NAME,String BOOKNAME,String BOOKCODE,Date BORROWDATE,Date SHOULDRETURNDATE,String BORROWMANAGER) throws SQLException {

		conn = GetConnection.getCon();
		String sql = "insert into tb_lib_borrowandretuenbooks(LIBCARDNO,NAME,BOOKNAME,BOOKCODE,BORROWDATE,SHOULDRETURNDATE,BORROWMANAGER,RETURNMANAGER,ISOVERDUE,FINE,NOTE)"
				+ "VALUES('"
				+ LIBCARDNO
				+ "','"
				+ NAME
				+ "','"
				+ BOOKNAME
				+ "','"
				+ BOOKCODE
				+ "','"
				+ BORROWDATE
				+ "','"
				+ SHOULDRETURNDATE
				+ "','"
				+ BORROWMANAGER
				+ "','unknow','unknow','0','业务进行中')";
		pstmt = conn.prepareStatement(sql);
		int num = pstmt.executeUpdate();
		if (num > 0) {
			Bookdao bookdao = new Bookdao();
			bookdao.Book_out(BOOKCODE);// 被借图书数量少一,状态改变
			return true;
		} else {
			return false;
		}
	}

	// 还书
	public boolean return_book(String LIBCARDNO, String BOOKCODE,
			Date RETURNDATE, String RETURNMANAGER) throws SQLException {

		conn = GetConnection.getCon();
		ResultSet rs = null;
		String ISOVERDUE = null;
		double FINE = 0;
		String NOTE = null;
		int day;// 超期天数
		// 判断是否超期
		SimpleDateFormat f = new SimpleDateFormat("hhmmss");
		String _sql = "select * from tb_lib_borrowandretuenbooks where LIBCARDNO=? and BOOKCODE=?";
		pstmt = conn.prepareStatement(_sql);
		pstmt.setString(1, LIBCARDNO);
		pstmt.setString(2, BOOKCODE);
		rs = pstmt.executeQuery();
		
		Bookdao bookdao = new Bookdao();
		if (rs != null && rs.next()) {
			day = RETURNDATE.compareTo(rs.getDate("SHOULDRETURNDATE"));
					
			
			if (day<0||day==0) {
				
				bookdao.Book_in(BOOKCODE);// 被还图书数量增一，状态改变
				ISOVERDUE = "未超期";// 即及时还书
				FINE = 0;// 罚金为0
				NOTE = "已及时还书，业务结束！";
			} 
			else {
				bookdao.Book_in(BOOKCODE);// 被还图书数量增一，状态改变
				ISOVERDUE = "超期";// 即未及时还书
				long days=Math.abs(RETURNDATE.getTime()-rs.getDate("SHOULDRETURNDATE").getTime())/(24 * 60 * 60 * 1000);
				FINE = (double)days * 1;// 超期一天罚1元
				NOTE = "超期罚款，业务结束！";
			}
			String sql = "update tb_lib_borrowandretuenbooks set RETURNDATE='"+RETURNDATE+"',RETURNMANAGER='"+RETURNMANAGER+"',ISOVERDUE='"+ISOVERDUE+"',FINE='"+FINE+"',NOTE='"+NOTE+"' where LIBCARDNO=? and BOOKCODE=?";
					
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, LIBCARDNO);
			pstmt.setString(2, BOOKCODE);
			int num = pstmt.executeUpdate();
			if (num > 0) {
				return true;
			} else {
				return false;
			}
		}
		return false;

	}
	//查看是否超期
	public int look_isoverdue(String LIBCARDNO, String BOOKCODE)throws SQLException {
		conn = GetConnection.getCon();
		ResultSet rs = null;
		int i=-1;
		String _sql = "select ISOVERDUE from tb_lib_borrowandretuenbooks where LIBCARDNO='"
				+ LIBCARDNO + "' and BOOKCODE='" + BOOKCODE + "'";
		pstmt = conn.prepareStatement(_sql);
		rs = pstmt.executeQuery();
		if(rs.next())
		{
			
			if(rs.getString("ISOVERDUE")=="0")
				i=0;//不超期
			else
				 i=1;//超期
		}
		return i;
}
	//查看罚金
	public double look_fine(String LIBCARDNO, String BOOKCODE)throws SQLException {
		conn = GetConnection.getCon();
		ResultSet rs = null;
		String _sql = "select FINE from tb_lib_borrowandretuenbooks where LIBCARDNO='"
				+ LIBCARDNO + "' and BOOKCODE='" + BOOKCODE + "'";
		pstmt = conn.prepareStatement(_sql);
		rs = pstmt.executeQuery();
		if(rs.next())
		{return rs.getDouble("FINE");}
		else
			return -1;
}

	// 续借
	public int borrowagain_book(String LIBCARDNO, String BOOKID,
			Date newRETURNDATE, Date nowdate) throws SQLException {

		conn = GetConnection.getCon();
		ResultSet rs = null;
		double FINE = 0;
		String NOTE = null;
		int day;// 超期天数
		// 判断是否超期
		SimpleDateFormat f = new SimpleDateFormat("hhmmss");
		String _sql = "select SHOULDRETURNDATE from tb_lib_borrowandretuenbooks where LIBCARDNO='"
				+ LIBCARDNO + "' and BOOKID='" + BOOKID + "'";
		pstmt = conn.prepareStatement(_sql);
		rs = pstmt.executeQuery();
		if (rs.next()) {
			int d1 = Integer.parseInt(f.format(rs.getDate("SHOULDRETURNDATE"))
					.toString()); // 应还时间
			int d2 = Integer.parseInt(f.format(nowdate).toString()); // 实际还书时间
			if (d1 >= d2) {
				
				NOTE = "已续借至'" + newRETURNDATE + "日！";
				pstmt = conn
						.prepareStatement("update tb_lib_borrowandretuenbooks set RETURNDATE='"
								+ newRETURNDATE
								+ "' NOTE='"
								+ NOTE
								+ "' where LIBCARDNO='"
								+ LIBCARDNO + "' and BOOKID='" + BOOKID + "'");
				
				return 1;//成功续借
			} else {
				return 0;//超期无法续借
			}

		}
		return -1;//续借不成功
	}

	// 删除借书记录
	public boolean delete_borrow(String LIBCARDNO, String BOOKID)
			throws SQLException {

		conn = GetConnection.getCon();
		String sql = "delete from tb_lib_borrowandretuenbooks where LIBCARDNO='"
				+ LIBCARDNO + "' and BOOKID='" + BOOKID + "'";
		pstmt = conn.prepareStatement(sql);
		int rows = pstmt.executeUpdate();
		if (rows > 0) {
			return true;
		} else {
			return false;
		}
	}

	// 查询某人借书信息
	public BorrowAndRetuenbook inquireoneborrow(String LIBCARDNO,String BOOKCODE)
			throws SQLException {
		conn = GetConnection.getCon();
		ResultSet rs = null;
		String _sql = "select * from tb_lib_borrowandretuenbooks where LIBCARDNO=? and BOOKCODE=?";
		pstmt = conn.prepareStatement(_sql);
		pstmt.setString(1, LIBCARDNO);
		pstmt.setString(2, BOOKCODE);
		rs = pstmt.executeQuery();
		
			
			if(rs.next()) {

				BorrowAndRetuenbook borrowAndRetuenbook = new BorrowAndRetuenbook();
				borrowAndRetuenbook.setLIBCARDNO(rs.getString("LIBCARDNO"));
				borrowAndRetuenbook.setNAME(rs.getString("NAME"));
				borrowAndRetuenbook.setBOOKNAME(rs.getString("BOOKNAME"));
				borrowAndRetuenbook.setBOOKCODE(rs.getString("BOOKCODE"));
				borrowAndRetuenbook.setBORROWDATE(rs.getDate("BORROWDATE"));
				borrowAndRetuenbook.setSHOULDRETURNDATE(rs.getDate("SHOULDRETURNDATE"));
				borrowAndRetuenbook.setBORROWMANAGER(rs.getString("BORROWMANAGER"));
				
				borrowAndRetuenbook.setRETURNDATE(rs.getDate("RETURNDATE"));
				borrowAndRetuenbook.setRETURNMANAGER(rs.getString("RETURNMANAGER"));
				borrowAndRetuenbook.setISOVERDUE(rs.getString("ISOVERDUE"));
				borrowAndRetuenbook.setFINE(rs.getDouble("FINE"));
				borrowAndRetuenbook.setNOTE(rs.getString("NOTE"));
				return borrowAndRetuenbook;
			}
			
		else {
			return null;
		}

	}
	//求最早日期
	public java.sql.Date Mindate()throws SQLException {
		conn = GetConnection.getCon();
		ResultSet rs = null;
		
		String _sql = "select min(RETURNDATE) from tb_lib_borrowandretuenbooks";
		pstmt = conn.prepareStatement(_sql);
		rs = pstmt.executeQuery();
		if(rs.next()){
			return rs.getDate("min(RETURNDATE)");
		}
		else return null;
	}
	//求最迟日期
		public java.sql.Date Maxdate()throws SQLException {
			conn = GetConnection.getCon();
			ResultSet rs = null;
			
			String _sql = "select max(RETURNDATE) from tb_lib_borrowandretuenbooks";
			pstmt = conn.prepareStatement(_sql);
			rs = pstmt.executeQuery();
			if(rs.next()){
				return rs.getDate("max(RETURNDATE)");
			}
			else return null;
		}
	public ArrayList<BorrowAndRetuenbook> FineStatistics(java.sql.Date date1,java.sql.Date date2)
			throws SQLException {
		conn = GetConnection.getCon();
		ResultSet rs = null;
		
		String _sql = "select * from tb_lib_borrowandretuenbooks where RETURNDATE between '"+date1+"' and '"+date2+"'";
		pstmt = conn.prepareStatement(_sql);
		rs = pstmt.executeQuery();
		if (rs != null) {
			ArrayList<BorrowAndRetuenbook> list = new ArrayList<BorrowAndRetuenbook>();
			while (rs.next()) {
				BorrowAndRetuenbook borrowAndRetuenbook = new BorrowAndRetuenbook();
				borrowAndRetuenbook.setLIBCARDNO(rs.getString("LIBCARDNO"));
				borrowAndRetuenbook.setNAME(rs.getString("NAME"));
				borrowAndRetuenbook.setBOOKNAME(rs.getString("BOOKNAME"));
				borrowAndRetuenbook.setBOOKCODE(rs.getString("BOOKCODE"));
				borrowAndRetuenbook.setBORROWDATE(rs.getDate("BORROWDATE"));
				borrowAndRetuenbook.setSHOULDRETURNDATE(rs.getDate("SHOULDRETURNDATE"));
				borrowAndRetuenbook.setBORROWMANAGER(rs.getString("BORROWMANAGER"));
				
				borrowAndRetuenbook.setRETURNDATE(rs.getDate("RETURNDATE"));
				borrowAndRetuenbook.setRETURNMANAGER(rs.getString("RETURNMANAGER"));
				borrowAndRetuenbook.setISOVERDUE(rs.getString("ISOVERDUE"));
				borrowAndRetuenbook.setFINE(rs.getDouble("FINE"));
				borrowAndRetuenbook.setNOTE(rs.getString("NOTE"));
				list.add(borrowAndRetuenbook);
			}
			return list;
		}
		else return null;
	}
}
