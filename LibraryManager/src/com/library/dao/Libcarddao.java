package com.library.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import com.library.bean.Libcard;
import com.library.jdbc.GetConnection;

public class Libcarddao {
	private Connection conn;
	private PreparedStatement pstmt = null;
	
	//新增借书证
	public boolean create_card(String libcardno,String name,String cardtype,String cardno,
    String phone,String address,String emall,String createuser,String createdate,
    String effectivedate)throws SQLException{
		
		conn = GetConnection.getCon();
		String sql = "insert into tb_lib_libcard(LIBCARDNO,NAME,CARDTYPE,CARDNO,PHONE,ADDRESS,EMAIL,CREATEUSER,CREATEDATE,EFFECTIVEDATE)"
		+ "VALUES('"
		+ libcardno
		+ "','"
		+ name
		+ "','"
		+ cardtype
		+ "','"
		+ cardno
		+ "','"
		+ phone
		+ "','"
		+ address
		+ "','"
		+ emall
		+ "','"
		+ createuser
		+ "','"
		+ createdate
		+ "','"
		+ effectivedate + "')";
		pstmt = conn.prepareStatement(sql);
		int num = pstmt.executeUpdate();
		if (num > 0) {
			return true;
		} else {
			return false;
		}
	
	}
	
	//销毁借书证
	public boolean delete_card(String libcardno) throws SQLException {

		conn = GetConnection.getCon();
		String sql = "delete from tb_lib_libcard where LIBCARDNO=?";
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, libcardno);
		
		int rows = pstmt.executeUpdate();
		if (rows > 0) {
			return true;
		} else {
			return false;
		}

	}
	
	//修改借书证信息
	public boolean update_libcard(String libcardno,String name,String cardtype,String cardno,
    String phone,String address,String emall,String createuser,String createdate,
    String effectivedate,String oldlibcardno)throws SQLException{ 
		conn = GetConnection.getCon();
		
		String sql = "update tb_lib_libcard set NAME='"+name+"',CARDTYPE='"+cardtype+"',CARDNO='"+cardno+"',PHONE='"+phone+"',ADDRESS='"+address+"',EMAIL='"+emall+"',CREATEUSER='"+createuser+"',CREATEDATE='"+createdate+"',EFFECTIVEDATE='"+effectivedate+"' where LIBCARDNO='"+oldlibcardno+"'";
		pstmt = conn.prepareStatement(sql);
		int rows = pstmt.executeUpdate();
		if (rows > 0) {
			return true;
		} else {
			return false;
		}
	}
	
	//查询借书证信息
	public ArrayList<Libcard> inquire_cardBy(String name,String libcardno,int curPage,int rowsPerPage)throws SQLException{ 
		int m=(curPage-1)*rowsPerPage;
		int n=rowsPerPage;
		String sql;
		if(name!=""&&libcardno==""){//只有姓名
			sql = "select * from tb_lib_libcard where NAME='"+name+"' limit "+m+","+n+"";
			
		}
		else if(name==""&&libcardno!=""){//只有借书证号
			sql = "select * from tb_lib_libcard where LIBCARDNO='"+libcardno+"' limit "+m+","+n+"";
			
		}
		else if(name!=""&&libcardno!=""){//姓名和借书证号
			sql = "select * from tb_lib_libcard where NAME='"+name+"' and LIBCARDNO='"+libcardno+"' limit "+m+","+n+"";
			
		}
		else{
			sql = "select * from tb_lib_libcard limit "+m+","+n+"";
			
		}
		ArrayList<Libcard> list = new ArrayList<Libcard>();
		FenYe fenye=new FenYe();
		list=fenye.getLibcardData(sql);
		if(list!=null){
			return list;
		} else {
			return null;
		}
		
	}
	//为修改借书证查询借书证信息
	public Libcard inquireOnecard(String libcardno) throws SQLException {
		conn = GetConnection.getCon();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from tb_lib_libcard where LIBCARDNO=?";
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, libcardno); 
		rs = pstmt.executeQuery();
		if(rs.next()) {
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
			return libcard;
		}
		else return null;
	}
	//得到总页数，rowsPerPage是每页行数，SQL是查询语句
	 public int getMaxPage(int rowsPerPage,String name,String libcardno)throws SQLException {
		 int maxPage;
		 FenYe fenye=new FenYe();
		 String sql;
		 if(name!=""&&libcardno==""){//只有姓名
				sql = "select count(*) from tb_lib_libcard where NAME='"+name+"'";
				
			}
			else if(name==""&&libcardno!=""){//只有借书证号
				sql = "select count(*) from tb_lib_libcard where LIBCARDNO='"+libcardno+"'";
				
			}
			else if(name!=""&&libcardno!=""){//姓名和借书证号
				sql = "select count(*) from tb_lib_libcard where NAME='"+name+"' and LIBCARDNO='"+libcardno+"'";
				
			}
			else{
				sql = "select count(*) from tb_lib_libcard";
				
			}
		 maxPage=fenye.getMaxPage(rowsPerPage, sql);
        return maxPage;
	 }

	//得到总页数，rowsPerPage是每页行数，SQL是查询语句
		 public int getMaxPage(int rowsPerPage)throws SQLException {
			 int maxPage;
			 FenYe fenye=new FenYe();
			 String sql="select count(*) from tb_lib_libcard";
			 maxPage=fenye.getMaxPage(rowsPerPage, sql);
	         return maxPage;
		 }
	
	//查询所有借书证信息
	public ArrayList<Libcard> inquireAllcards(int curPage,int rowsPerPage) throws SQLException {
		
		
		int m=(curPage-1)*rowsPerPage;
		int n=rowsPerPage;
		String sql = "select * from tb_lib_libcard limit "+m+","+n+"";
		ArrayList<Libcard> list = new ArrayList<Libcard>();
		FenYe fenye=new FenYe();
		list=fenye.getLibcardData(sql);
		if(list!=null){
			return list;
		} else {
			return null;
		}

	}
	
	//验证修改后用户名是否重复
			public boolean Yanzheng2(String libcardno,String Libcardno)throws SQLException{
				conn = GetConnection.getCon();
				PreparedStatement pstmt = null;
				ResultSet rs = null;
				String sql = "select LIBCARDNO from tb_lib_libcard where LIBCARDNO!='"+Libcardno+"'";
				pstmt = conn.prepareStatement(sql);
				rs = pstmt.executeQuery();
				boolean i=false;
				if (rs != null) {
					while (rs.next()) {
						String str=rs.getString("LIBCARDNO");
						if(str.equals(libcardno)){
							i=true;
							break;
						}
					}
				}
				return i;
			}
		
		//验证新增借书证号是否重复
		public boolean Yanzheng(String libcardno)throws SQLException{
			conn = GetConnection.getCon();
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String sql = "select LIBCARDNO from tb_lib_libcard";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			boolean i=false;
			if (rs != null) {
				while (rs.next()) {
					String str=rs.getString("LIBCARDNO");
					if(str.equals(libcardno)){
						i=true;
						break;
					}
				}
			}
			return i;
		}
	
	

}
