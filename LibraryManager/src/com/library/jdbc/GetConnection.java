package com.library.jdbc;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class GetConnection {
	static Connection con = null;  
    static Statement stat = null;  
    static PreparedStatement pstat = null;  
    static ResultSet rs = null;  
      
  //无参数的构造函数  
    public GetConnection() {}  
      
      
    //取得数据库连接  
    public static Connection getCon(){  
        try{String url="jdbc:mysql://localhost/"+"library"+"?user="+"root"+"&password="+"root";  
    	Class.forName("com.mysql.jdbc.Driver").newInstance();  
    	con=DriverManager.getConnection(url);  
        }catch(Exception ex){  
            ex.printStackTrace();  
        }  
        return con;       
    }  
      
    //执行数据库查询并返回查询结果  
    public static ResultSet query(String sql){  
        try{  
            con = getCon();  
            stat = con.createStatement();  
            rs = stat.executeQuery(sql);  
        }catch(Exception ex){  
            ex.printStackTrace();  
        }  
        return rs;  
    }  
      
    //执行数据库更新  
    public void update(String sql){  
        try{  
            con = getCon();  
            stat = con.createStatement();  
            stat.executeUpdate(sql);  
        }catch(Exception ex){  
            ex.printStackTrace();  
        }  
    }  
  
    //执行数据库更新  
    public void update(String sql,String[] args){  
        try{  
            con = getCon();  
            pstat = con.prepareStatement(sql);  
            for (int i=0;i<args.length;i++){  
                pstat.setString(i+1,args[i]);  
            }  
            pstat.executeUpdate();  
        }catch(Exception ex){  
            ex.printStackTrace();  
        }  
    }  
      
    //关闭数据库连接  
    public void close(){  
        try{  
            if (rs != null)rs.close();  
            if (stat != null)stat.close();  
            if (pstat != null)pstat.close();  
            if (con != null)con.close();  
        }catch(Exception ex){  
            ex.printStackTrace();  
        }         
    }     
      

}
