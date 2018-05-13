package com.library.bean;


public class User {
	int ID;
	String userName;//用户名  
    String userPwd; //密码
    String name;//真实姓名
    String codetype;//证件类型
    String codeno;//证件号
    String phone;//电话
    String emall;//邮箱
    String address;//住址
    int type;//用户类型，1为超级管理员，0为管理员
    String createdate;//创建日期
    public User(){}
    
    public int getID() {  
        return ID;  
    }  
    public void setID(int ID) {  
        this.ID = ID;  
    }
    public String getUserName() {  
        return userName;  
    }  
    public void setUserName(String userName) {  
        this.userName = userName;  
    }
    
    public String getUserPwd() {  
        return userPwd;  
    }  
    public void setUserPwd(String UserPwd) {  
        this.userPwd = UserPwd;  
    }
    
    public String getname() {  
        return name;  
    }  
    public void setname(String name) {  
        this.name = name;  
    }
    
    public String getcodetype() {  
        return codetype;  
    }  
    public void setcodetype(String codetype) {  
        this.codetype = codetype;  
    }
    
    public String getcodeno() {  
        return codeno;  
    }  
    public void setcodeno(String codeno) {  
        this.codeno = codeno;  
    }
    
    public String getphone() {  
        return phone;  
    }  
    public void setphone(String phone) {  
        this.phone = phone;  
    }
    
    public String getemall() {  
        return emall;  
    }  
    public void setemall(String emall) {  
        this.emall = emall;  
    }
    
    public String getaddress() {  
        return address;  
    }  
    public void setaddress(String address) {  
        this.address = address;  
    }
    
    public  int gettype() {  
        return type;  
    }  
    public void settype(int type) {  
        this.type = type;  
    }
    
    public String getcreatedate() {  
        return createdate;  
    }  
    public void setcreatedate(String createdate) {  
        this.createdate = createdate;  
    }
}