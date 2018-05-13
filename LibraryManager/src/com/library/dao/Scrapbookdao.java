package com.library.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;


import com.library.bean.Scrapbook;
import com.library.jdbc.GetConnection;


public class Scrapbookdao {

	private Connection conn;
	private PreparedStatement pstmt = null;
	
	//新增报废图书
	public boolean Scrapbook(String BOOKID,String BOOKCODE,String SCRAPREASON,         
	  String DESCRIBES,Date SCRAPDATE,String CREATEUSER,Date CREATEDATE)throws SQLException{
				
				conn = GetConnection.getCon();
				String sql = "insert into tb_lib_scrapbook(BOOKID,BOOKCODE,SCRAPREASON,DESCRIBES,SCRAPDATE,CREATEUSER,CREATEDATE )"
				+ "VALUES('"
				+ BOOKID
				+ "','"
				+ BOOKCODE
				+ "','"
				+ SCRAPREASON
				+ "','"
				+ DESCRIBES
				+ "','"
				+ SCRAPDATE
				+ "','"
				+ CREATEUSER
				+ "','"
				+ CREATEDATE + "')";
				pstmt = conn.prepareStatement(sql);
				int num = pstmt.executeUpdate();
				if (num > 0) {
					return true;
				} else {
					return false;
				}
			
			}
	
	//删除报废图书
	public boolean delete_scrap(String BOOKID,String BOOKCODE) throws SQLException {

		conn = GetConnection.getCon();
		String sql = "delete from tb_lib_scrapbook where BOOKID=? and BOOKCODE=?";
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, BOOKID);
		pstmt.setString(2, BOOKCODE);
		int rows = pstmt.executeUpdate();
		if (rows > 0) {
			return true;
		} else {
			return false;
		}
     }
	
	//查询报废图书
	public Scrapbook inquire_card(String BOOKID,String BOOKCODE)throws SQLException{ 
		conn = GetConnection.getCon();
		ResultSet rs = null;
		String sql = "select * from tb_lib_scrapbook where BOOKID='"+BOOKID+"' and BOOKCODE='"+BOOKCODE+"'";
		pstmt = conn.prepareStatement(sql);
		
		rs = pstmt.executeQuery();
		if (rs != null && rs.next()) {
			Scrapbook scrapbook = new Scrapbook();
			scrapbook.setBOOKID(rs.getString("BOOKID"));
			scrapbook.setBOOKCODE(rs.getString("BORROWBOOKID"));
			scrapbook.setSCRAPREASON(rs.getString("SCRAPREASON"));
			scrapbook.setDESCRIBES(rs.getString("DESCRIBES"));
			scrapbook.setSCRAPDATE(rs.getDate("SCRAPDATE"));
			scrapbook.setCREATEUSER(rs.getString("CREATEUSER"));
			scrapbook.setCREATEDATE(rs.getDate("CREATEDATE"));
			
			return scrapbook;
		} else {
			return null;
		}
	}
	
	//修改报废图书信息
	public boolean update_scrapbook(String BOOKID,String BOOKCODE,String SCRAPREASON,         
			  String DESCRIBES,Date SCRAPDATE,String CREATEUSER,Date CREATEDATE)throws SQLException{ 
				conn = GetConnection.getCon();
				
				String sql = "update tb_lib_libcard set BOOKID='"+BOOKID+"',BORROWBOOKID='"+BOOKCODE+"',SCRAPREASON='"+SCRAPREASON+"',DESCRIBES='"+DESCRIBES+"',SCRAPDATE='"+SCRAPDATE+"',CREATEUSER='"+CREATEUSER+"',CREATEDATE='"+CREATEDATE+"'" +
						" where BOOKID='"+BOOKID+"' and BORROWBOOKID='"+BOOKCODE+"'";
				pstmt = conn.prepareStatement(sql);
				int rows = pstmt.executeUpdate();
				if (rows > 0) {
					return true;
				} else {
					return false;
				}
			}
}
