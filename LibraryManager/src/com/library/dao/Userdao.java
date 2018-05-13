package com.library.dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.UUID;



import com.library.bean.User;
import com.library.jdbc.GetConnection;

public class Userdao {

	private Connection conn;

	/**
	 * 
	 * 用户登录
	 */
	public boolean logoin(User user) throws SQLException {
		conn = GetConnection.getCon();

		boolean i = false;
		String userName = user.getUserName();
		String userPwd = user.getUserPwd();
		String sql = "select * from tb_lib_managers where ACCOUNTS='" + userName
				+ "' and PWD='" + userPwd + "'";
		ResultSet rs = GetConnection.query(sql);
		if (rs.next()) {
			i = true;
			rs.close();

		} else {
			i = false;
			rs.close();

		}
		conn.close();
		return i;
	}

	// 对是否为超级用户进行判断
	public boolean issuper(String username)throws SQLException{
		conn = GetConnection.getCon();
		boolean i = false;
		String sql = "select type from tb_lib_managers where ACCOUNTS='"
				+ username + "'";
		ResultSet rs = GetConnection.query(sql);
		if (rs.next()) {
			if (rs.getInt("type") == 1) {
				i = true;
				rs.close();
			}

		}
		conn.close();
		return i;
	}

	//对用户修改
		public boolean Updateuser(User user,String username) throws SQLException {
			conn = GetConnection.getCon();
			PreparedStatement pstmt = null;
			String userName=user.getUserName();
			String userPwd = user.getUserPwd();
			String name = user.getname();
			String codetype = user.getcodetype();
			String codeno = user.getcodeno();
			String phone = user.getphone();
			String emall = user.getemall();
			String address = user.getaddress();
			int type = user.gettype();
			String createdate = user.getcreatedate();
			String sql = "Update tb_lib_managers set ACCOUNTS='"+userName+"',PWD='"+userPwd+"',NAME='"+name+"',CARDTYPE='"+codetype+"',CARDNO='"+codeno+"',PHONE='"+phone+"',EMAIL='"+emall+"',ADDRESS='"+address+"',TYPE='"+type+"',CREATEDATE='"+createdate+"' where ACCOUNTS='"+username+"' ";
					
			pstmt = conn.prepareStatement(sql);
			int num = pstmt.executeUpdate();
			if (num > 0) {
				return true;
			} else {
				return false;
			}
		}
	
	// 超级用户对普通用户新增
	public boolean insertuser(User user) throws SQLException {
		conn = GetConnection.getCon();
		PreparedStatement pstmt = null;
		String userName = user.getUserName();
		String userPwd = user.getUserPwd();
		String name = user.getname();
		String codetype = user.getcodetype();
		String codeno = user.getcodeno();
		String phone = user.getphone();
		String emall = user.getemall();
		String address = user.getaddress();
		int type = user.gettype();
		String createdate = user.getcreatedate();
		String sql = "INSERT INTO tb_lib_managers(ACCOUNTS,PWD,NAME,CARDTYPE,CARDNO,PHONE,EMAIL,ADDRESS,TYPE,CREATEDATE)"
				+ "VALUES('"
				+ userName
				+ "','"
				+ userPwd
				+ "','"
				+ name
				+ "','"
				+ codetype
				+ "','"
				+ codeno
				+ "','"
				+ phone
				+ "','"
				+ emall
				+ "','"
				+ address
				+ "','"
				+ type
				+ "','"
				+ createdate
				+ "')";
		pstmt = conn.prepareStatement(sql);
		int num = pstmt.executeUpdate();
		if (num > 0) {
			return true;
		} else {
			return false;
		}
	}

	// 超级用户对普通用户删减
	public boolean deleteuser(String userName) throws SQLException {
		conn = GetConnection.getCon();
		PreparedStatement pstmt = null;
		String sql = "delete from tb_lib_managers where ACCOUNTS=?";
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, userName);
		int rows = pstmt.executeUpdate();
		if (rows > 0) {
			return true;
		} else {
			return false;
		}
	}
    //比较两次输入密码是否一致
	public boolean TwoPwd(String pwd1, String pwd2) throws SQLException {
		if(pwd1.equals(pwd2)){
			return true;
		}
		else return false;
	}
	// 修改用户密码
	public boolean updatepwd(String userName, String pwd) throws SQLException {
		conn = GetConnection.getCon();
		PreparedStatement pstmt = null;
		String sql = "update tb_lib_managers set PWD=? where ACCOUNTS=?";
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, pwd);
		pstmt.setString(2, userName);
		int rows = pstmt.executeUpdate();
		if (rows > 0) {
			return true;
		} else {
			return false;
		}
	}
	//查询某一用户信息
	public User inquireOneuser(String uname)
			throws SQLException {
		conn = GetConnection.getCon();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from tb_lib_managers where ACCOUNTS=?";
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, uname);
		rs = pstmt.executeQuery();
		if (rs != null && rs.next()) {
			User user = new User();
			user.setID(rs.getInt("ID"));
			user.setUserName(rs.getString("ACCOUNTS"));
			user.setUserPwd(rs.getString("PWD"));
			user.setname(rs.getString("NAME"));
			user.setcodetype(rs.getString("CARDTYPE"));
			user.setcodeno(rs.getString("CARDNO"));
			user.setphone(rs.getString("PHONE"));
			user.setemall(rs.getString("EMAIL"));
			user.setaddress(rs.getString("ADDRESS"));
			user.settype(rs.getInt("TYPE"));
			user.setcreatedate(rs.getString("CREATEDATE"));
			return user;
		} else {
			return null;
		}
	}
	// 查询当前用户信息
	public User inquireMy(String uname)
			throws SQLException {
		conn = GetConnection.getCon();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from tb_lib_managers where ACCOUNTS=?";
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, uname);
		rs = pstmt.executeQuery();
		if (rs != null && rs.next()) {
			User user = new User();
			user.setID(rs.getInt("ID"));
			user.setUserName(rs.getString("ACCOUNTS"));
			user.setUserPwd(rs.getString("PWD"));
			user.setname(rs.getString("NAME"));
			user.setcodetype(rs.getString("CARDTYPE"));
			user.setcodeno(rs.getString("CARDNO"));
			user.setphone(rs.getString("PHONE"));
			user.setemall(rs.getString("EMAIL"));
			user.setaddress(rs.getString("ADDRESS"));
			user.settype(rs.getInt("TYPE"));
			user.setcreatedate(rs.getString("CREATEDATE"));
			return user;
		} else {
			return null;
		}
	}
	// 条件查询用户信息
	public ArrayList<User> inquireUserBy(String name,String username) throws SQLException {
		conn = GetConnection.getCon();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;
		if(name!=""&&username==""){
		sql = "select * from tb_lib_managers where NAME=?";
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, name);
		rs = pstmt.executeQuery();
		}
		else if(name==""&&username!=""){
			sql = "select * from tb_lib_managers where ACCOUNTS=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, username);
			rs = pstmt.executeQuery();
		}
		else if(name!=""&&username!=""){
			sql = "select * from tb_lib_managers where ACCOUNTS=? and NAME=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, username);
			pstmt.setString(2, name);
			rs = pstmt.executeQuery();
		}
		else{
			sql = "select * from tb_lib_managers";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
		}
		if (rs != null) {
			ArrayList<User> list = new ArrayList<User>();
			while (rs.next()) {
				User user = new User();
				user.setID(rs.getInt("ID"));
				user.setUserName(rs.getString("ACCOUNTS"));
				user.setUserPwd(rs.getString("PWD"));
				user.setname(rs.getString("NAME"));
				user.setcodetype(rs.getString("CARDTYPE"));
				user.setcodeno(rs.getString("CARDNO"));
				user.setphone(rs.getString("PHONE"));
				user.setemall(rs.getString("EMAIL"));
				user.setaddress(rs.getString("ADDRESS"));
				user.settype(rs.getInt("TYPE"));
				user.setcreatedate(rs.getString("CREATEDATE"));
				list.add(user);
			}
			return list;
		} else {
			return null;
		}

	}
	
	//验证修改后用户名是否重复
		public boolean Yanzheng2(String userName,String username)throws SQLException{
			conn = GetConnection.getCon();
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String sql = "select ACCOUNTS from tb_lib_managers where ACCOUNTS!='"+username+"'";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			boolean i=false;
			if (rs != null) {
				while (rs.next()) {
					String str=rs.getString("ACCOUNTS");
					if(str.equals(userName)){
						i=true;
						break;
					}
				}
			}
			return i;
		}
	
	//验证新增用户名是否重复
	public boolean Yanzheng(String username)throws SQLException{
		conn = GetConnection.getCon();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select ACCOUNTS from tb_lib_managers";
		pstmt = conn.prepareStatement(sql);
		rs = pstmt.executeQuery();
		boolean i=false;
		if (rs != null) {
			while (rs.next()) {
				String str=rs.getString("ACCOUNTS");
				if(str.equals(username)){
					i=true;
					break;
				}
			}
		}
		return i;
	}
	//超级用户查询所有用户信息
	public ArrayList<User> inquireAlluser() throws SQLException {
		conn = GetConnection.getCon();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from tb_lib_managers";
		pstmt = conn.prepareStatement(sql);
		
		rs = pstmt.executeQuery();
		if (rs != null) {
			ArrayList<User> list = new ArrayList<User>();
			while (rs.next()) {
				User user = new User();
				user.setUserName(rs.getString("ACCOUNTS"));
				user.setUserPwd(rs.getString("PWD"));
				user.setname(rs.getString("NAME"));
				user.setcodetype(rs.getString("CARDTYPE"));
				user.setcodeno(rs.getString("CARDNO"));
				user.setphone(rs.getString("PHONE"));
				user.setemall(rs.getString("EMAIL"));
				user.setaddress(rs.getString("ADDRESS"));
				user.settype(rs.getInt("TYPE"));
				user.setcreatedate(rs.getString("CREATEDATE"));
				list.add(user);
			}
			return list;
		} else {
			return null;
		}

	}
}
